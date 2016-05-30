package br.furb.consultor.venda;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import br.furb.consultor.acao.BOAcaoBolsaValores;
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

	public static AcoesDTO getMelhoresOpcoesVendasUsuario(Long codigoUsuario){
		IConsultorVendas venda = BOVendaAcao.getVenda();

		try {
			List<String> opcoes = venda.getMelhoresOpcoesVendasPorCliente(codigoUsuario);
			return getAcoesDTO(opcoes, codigoUsuario);
		} catch (RemoteException e) {
			e.printStackTrace();
		}
		
		return new AcoesDTO();
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
