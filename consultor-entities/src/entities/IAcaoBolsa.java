package entities;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService
@SOAPBinding(style = Style.RPC)
public interface IAcaoBolsa {

	@WebMethod
	AcaoBolsaValores getAcaoBolsa(String codigoAcao);
	
	@WebMethod
	AcaoBolsaValores getAcaoBolsaDentroValidade(String codigoAcao,Long inicio, Long expira);

	@WebMethod
	Usuario getUsuario(long codigoUsuario);
	
}