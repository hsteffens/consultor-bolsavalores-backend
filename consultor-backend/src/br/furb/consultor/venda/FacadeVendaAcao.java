package br.furb.consultor.venda;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import persistencia.CarteiraCliente;
import br.furb.consultor.acao.BOAcaoBolsaValores;
import br.furb.consultor.carteiracliente.BOCarteiraCliente;
import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.entities.AcoesDTO;
import br.furb.rmi.IConsultorVendas;

public final class FacadeVendaAcao {

	private FacadeVendaAcao(){
		
	}
	
	public static AcoesDTO getMelhoresOpcoesVendas(){
		IConsultorVendas venda = BOVendaAcao.getVenda();
		
		try {
			List<String> opcoes = venda.getMelhoresOpcoesVendas();
			return getAcoesDTO(opcoes);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return new AcoesDTO();
		
	}

	public static AcoesDTO getMelhoresOpcoesVendasBolsa(Long codigoBolsa){
		IConsultorVendas venda = BOVendaAcao.getVenda();

		try {
			List<String> opcoes = venda.getMelhoresOpcoesVendasPorBolsa(codigoBolsa);
			return getAcoesDTO(opcoes);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return new AcoesDTO();
	}

	public static AcoesDTO getMelhoresOpcoesVendasUsuario(Integer codigoUsuario){
		List<CarteiraCliente> carteiraCliente = BOCarteiraCliente.getCarteiraCliente(codigoUsuario);
		List<String> acoes = new ArrayList<String>();
		
		for (CarteiraCliente carteira : carteiraCliente) {
			acoes.add(carteira.getDsCodigo().getDsCodigo());
		}
		return getAcoesDTO(acoes.subList(0, acoes.size() > 2 ? 2 : acoes.size()), codigoUsuario.longValue());
	}
	
	private static AcoesDTO getAcoesDTO(List<String> opcoes) {
		AcoesDTO acoesDTO = new AcoesDTO();
		List<AcaoBolsaDTO> acoes = new ArrayList<AcaoBolsaDTO>();
		for (String codigo : opcoes) {
			acoes.add(BOAcaoBolsaValores.getAcaoBolsa(codigo));
		}
		
		acoesDTO.setResult(acoes);
		return acoesDTO;
	}

	private static AcoesDTO getAcoesDTO(List<String> opcoes, Long usuario) {
		AcoesDTO acoesDTO = new AcoesDTO();
		List<AcaoBolsaDTO> acoes = new ArrayList<AcaoBolsaDTO>();
		for (String codigo : opcoes) {
			acoes.add(BOAcaoBolsaValores.getAcaoBolsa(codigo, usuario));
		}
		
		acoesDTO.setResult(acoes);
		return acoesDTO;
	}

}
