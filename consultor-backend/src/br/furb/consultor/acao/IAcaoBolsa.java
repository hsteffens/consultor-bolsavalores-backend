package br.furb.consultor.acao;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.furb.consultor.entities.Usuario;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IAcaoBolsa {

	@WebMethod
	Usuario getUsuario(long codigoUsuario);
	
}