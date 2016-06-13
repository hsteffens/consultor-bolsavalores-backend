package br.furb.consultor.resources;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;

import br.furb.consultor.autenticacao.BOAutenticacao;

import com.sun.jersey.api.client.ClientResponse.Status;
import com.sun.jersey.api.container.ContainerException;
import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;

@Provider
public class CheckRequestFilter implements ContainerRequestFilter {

	public static final String AUTHENTICATION_HEADER = "Authorization";
	
	@Context
	private HttpHeaders httRequest;
	
	@Override
	public ContainerRequest filter(ContainerRequest request) {
		if (!request.getRequestUri().getPath().contains("/auth/") &&
				!request.getRequestUri().getPath().contains("/user/create")) {
			String authCredentials = request.getHeaderValue(AUTHENTICATION_HEADER);
	
			Response response = BOAutenticacao.parseJWT(authCredentials);
	
			if (Status.OK.getStatusCode()  !=  response.getStatus()) {
				throw new ContainerException((Exception) response.getEntity());
			}
			httRequest.getRequestHeaders().add("userName", (String)response.getEntity());
			
		}
		return request;
	}

}
