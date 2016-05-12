package br.furb.usuario;

import java.util.ArrayList;
import java.util.List;

import entities.Usuario;

public class BOUsuario {
	
	private static List<Usuario> usuarios;
	
	static{
		usuarios = new ArrayList<Usuario>();
		
		Usuario usuario01 = new Usuario();
		usuario01.setCodigo(1l);
		usuario01.setCpf(12345678910l);
		usuario01.setNome("Batatinha");
		usuario01.setProfissao("Pedreiro");
		usuario01.setTipoInvestidor(EnTipoInvestidor.AGRESSIVO.getCodigo());
		usuario01.setTipoTransacao(EnFormaOperacao.BUY_HOLD.getCodigo());
		
		Usuario usuario02 = new Usuario();
		usuario02.setCodigo(2l);
		usuario02.setCpf(11108545655l);
		usuario02.setNome("Joãozinho");
		usuario02.setProfissao("Estudante");
		usuario02.setTipoInvestidor(EnTipoInvestidor.MODERADO.getCodigo());
		usuario02.setTipoTransacao(EnFormaOperacao.POSITION_TRADE.getCodigo());
		
		usuarios.add(usuario01);
		usuarios.add(usuario02);
	}

	public static Usuario getUsuario(long codigoUsuario){
		for (Usuario usuario : usuarios) {
			if (usuario.getCodigo() == codigoUsuario) {
				return usuario;
			}
		}
		
		return new Usuario();
	}
}
