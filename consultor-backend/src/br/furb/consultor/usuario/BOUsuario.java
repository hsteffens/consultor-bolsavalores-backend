package br.furb.consultor.usuario;

import java.math.BigInteger;
import java.net.MalformedURLException;
import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import br.furb.consultor.entities.StatusRespostaDTO;
import br.furb.consultor.entities.UsuarioDTO;
import controle.exceptions.UsuarioException;
import entities.IAcaoBolsa;
import entities.Usuario;

public final class BOUsuario {

	private BOUsuario(){
		
	}
	
	public static UsuarioDTO getUsuarioLogado(Integer id) {
		Object[] fields = FacadeUsuario.getUsuario(id);
		if (fields == null || fields.length < 1) {
			return null;
		}
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId((Integer)fields[1]);
		usuarioDTO.setCpf(((Number)fields[2]).longValue());
		usuarioDTO.setNome((String)fields[3]);
		usuarioDTO.setProfissao((String)fields[4]);
		usuarioDTO.setEmail((String)fields[5]);
		usuarioDTO.setPerfilInvestidor((Integer)fields[9]);
		usuarioDTO.setFormaOperacao((Integer)fields[10]);
		
		return usuarioDTO;
	}
	
	public static StatusRespostaDTO inserirUsuario(UsuarioDTO usuario){
		StatusRespostaDTO resposta = new StatusRespostaDTO();
		try {
			FacadeUsuario.inserirUsuario(usuario.getNome(), new BigInteger(usuario.getCpf().toString()), usuario.getEmail(), usuario.getUserName(), usuario.getPassword(), usuario.getPerfilInvestidor(), usuario.getFormaOperacao(),usuario.getProfissao());
			resposta.setStatus("OK");
			resposta.setMensagem("Usuário inserido com sucesso!");
			
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
	
	public static StatusRespostaDTO alterarUsuario(Integer idUsuario, UsuarioDTO usuario) {
		StatusRespostaDTO resposta = new StatusRespostaDTO();
		try {
			FacadeUsuario.alterarUsuario(idUsuario, usuario.getNome(), new BigInteger(usuario.getCpf().toString()), usuario.getEmail(), usuario.getUserName(), usuario.getPassword(), usuario.getPerfilInvestidor(), usuario.getFormaOperacao(),usuario.getProfissao());
			resposta.setStatus("OK");
			resposta.setMensagem("Usuário alterado com sucesso!");
			
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
	
	public static UsuarioDTO getUsuario(String username, String password){
		Object[] fields = FacadeUsuario.getUsuario(username, password);
		if (fields == null || fields.length < 1) {
			return null;
		}
		UsuarioDTO usuarioDTO = new UsuarioDTO();
		usuarioDTO.setId((Integer)fields[1]);
		usuarioDTO.setCpf(((Number)fields[2]).longValue());
		usuarioDTO.setNome((String)fields[3]);
		usuarioDTO.setProfissao((String)fields[4]);
		usuarioDTO.setEmail((String)fields[5]);
		usuarioDTO.setPerfilInvestidor((Integer)fields[9]);
		usuarioDTO.setFormaOperacao((Integer)fields[10]);
		usuarioDTO.setUserName(username);
		usuarioDTO.setPassword(password);
		
		return usuarioDTO;
	}
	
	public static Usuario getUsuario(long codigoUsuario) throws Exception {
		IAcaoBolsa acao = connect();
		return acao.getUsuario(codigoUsuario);
	}
	
	public static Long getTimeExpiracao(Long codigoUsuario){
		Long expira = 0l;
		try {
			UsuarioDTO usuario = getUsuarioLogado(codigoUsuario.intValue());
			if (usuario != null) {
				if (usuario.getPerfilInvestidor()== EnTipoInvestidor.CONSERVADOR.getCodigo()) {
					expira = 1800000l;//Cada 30 Minutos
				}else if (usuario.getPerfilInvestidor() == EnTipoInvestidor.MODERADO.getCodigo()) {
					expira = 600000l;//Cada 10 Minutos
				} else if (usuario.getPerfilInvestidor() == EnTipoInvestidor.AGRESSIVO.getCodigo()){
					expira = 60000l;//Cada Minuto
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return expira;
	}

	private static IAcaoBolsa connect() throws MalformedURLException {
		URL url = new URL("http://127.0.0.1:9876/entities?wsdl");
		QName qname = new QName("http://entities/", "AcaoBolsaService");
		Service ws = Service.create(url, qname);
		IAcaoBolsa acao = ws.getPort(IAcaoBolsa.class);
		return acao;
	}

}
