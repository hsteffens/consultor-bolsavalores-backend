package br.furb.consultor.compra;

import java.util.ArrayList;
import java.util.List;

import br.furb.consultor.acao.BOAcaoBolsaValores;
import br.furb.consultor.entities.AcaoBolsaDTO;
import br.furb.consultor.entities.AcoesDTO;
import Compras.AcoesCodHolder;
import Compras.Compra;

public final class FacadeCompraAcao {

	private FacadeCompraAcao(){
		
	}
	
	public static AcoesDTO getMelhoresOpcoesCompras(){
		Compra compra = BOCompraAcao.getCompra();
		AcoesCodHolder acoesHolder = new AcoesCodHolder();
		compra.getMelhoresOpcoesCompras(acoesHolder);
		
		return getAcoesDTO(acoesHolder);
	}

	public static AcoesDTO getMelhoresOpcoesComprasPorBolsa(Integer bolsa){
		Compra compra = BOCompraAcao.getCompra();
		AcoesCodHolder acoesHolder = new AcoesCodHolder();
		compra.getMelhoresOpcoesComprasPorBolsa(bolsa, acoesHolder);
		
		return getAcoesDTO(acoesHolder);
	}
	
	public static AcoesDTO getMelhoresOpcoesUsuario(Integer codigoCliente){
		Compra compra = BOCompraAcao.getCompra();
		AcoesCodHolder acoesHolder = new AcoesCodHolder();
		compra.getMelhoresOpcoesComprasPorCliente(codigoCliente, acoesHolder);
		
		return getAcoesDTO(acoesHolder);
	}
	
	private static AcoesDTO getAcoesDTO(AcoesCodHolder acoesHolder) {
		List<AcaoBolsaDTO> acoesCompra = new ArrayList<AcaoBolsaDTO>();
		for (String codigo : acoesHolder.value) {
			acoesCompra.add(BOAcaoBolsaValores.getAcaoBolsa(codigo));
		}
		
		AcoesDTO acoesDTO = new AcoesDTO();
		acoesDTO.setResult(acoesCompra);
		
		return acoesDTO;
	}
}
