package br.furb.consultor.converter;

import java.util.ArrayList;
import java.util.List;

import br.furb.consultor.bovespa.destaque.Papel;
import br.furb.consultor.entities.PapelDTO;

public final class ConverterPapel {
	
	private ConverterPapel(){
		
	}
	
	public static List<PapelDTO> converterPapel(List<Papel> lPapel){
		List<PapelDTO> lPapelDTO = new ArrayList<PapelDTO>();
		if (lPapel != null) {
			for (Papel papel : lPapel) {
				lPapelDTO.add(converterPaper(papel));
			}
		}	
		
		return lPapelDTO;
	}
	
	public static PapelDTO converterPaper(Papel papel){
		PapelDTO papelDTO = new PapelDTO();
		papelDTO.setCodigo(papel.getCodigo());
		papelDTO.setNome(papel.getNome());
		papelDTO.setEspecificacao(papel.getEspecificacao());
		papelDTO.setData(papel.getData());
		papelDTO.setPreco(papel.getPreco());
		papelDTO.setOscilacao(papel.getOscilacao());
		
		return papelDTO;
	}
}
