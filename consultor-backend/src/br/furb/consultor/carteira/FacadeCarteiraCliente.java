package br.furb.consultor.carteira;

import java.util.ArrayList;
import java.util.List;

import persistencia.CarteiraCliente;
import br.furb.consultor.carteiracliente.BOCarteiraCliente;
import br.furb.consultor.entities.AcoesUsuarioDTO;
import br.furb.consultor.entities.CarteiraClienteDTO;
import br.furb.consultor.entities.StatusRespostaDTO;
import controle.exceptions.UsuarioException;

public final class FacadeCarteiraCliente {

	private FacadeCarteiraCliente(){
		
	}
	
	public static StatusRespostaDTO inserirAcaoCarteiraCliente(Integer idUsuario, String acao, Integer quantidade, Float preco){
		StatusRespostaDTO resposta = new StatusRespostaDTO();
		try {
			BOCarteiraCliente.inserirAcaoCarteiraCliente(idUsuario, acao, quantidade, preco);
			resposta.setStatus("OK");
			resposta.setMensagem("Ação inserida na carteira do usuário com sucesso!");
			
			return resposta;
		}catch (UsuarioException e) {
			resposta.setStatus("Erro");
			resposta.setMensagem(e.getError());
		} catch (Exception e) {
			resposta.setStatus("Erro");
			resposta.setMensagem(e.getMessage());
		}
		
		return resposta;
	}

	public static CarteiraClienteDTO getCarteiraCliente(int id) {
		List<CarteiraCliente> carteirasCliente = BOCarteiraCliente.getCarteiraCliente(id);
		
		CarteiraClienteDTO carteiraClienteDTO = new CarteiraClienteDTO();
		
		ArrayList<AcoesUsuarioDTO> result = new ArrayList<AcoesUsuarioDTO>();
		
		for (CarteiraCliente carteiraCliente : carteirasCliente) {
			AcoesUsuarioDTO acoesUsuarioDTO = new AcoesUsuarioDTO();

			acoesUsuarioDTO.setAcao(carteiraCliente.getDsCodigo().getDsCodigo());
			acoesUsuarioDTO.setPreco(carteiraCliente.getVlPreco().floatValue());
			acoesUsuarioDTO.setQuant(carteiraCliente.getVlQuantidade());
			
			result.add(acoesUsuarioDTO);
		}
		carteiraClienteDTO.setResult(result);
		
		return carteiraClienteDTO;
	}
}
