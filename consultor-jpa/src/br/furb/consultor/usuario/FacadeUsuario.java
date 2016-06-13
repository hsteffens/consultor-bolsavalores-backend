package br.furb.consultor.usuario;

import java.lang.reflect.Field;
import java.math.BigInteger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;

import org.hibernate.exception.ConstraintViolationException;

import persistencia.LoginUsuario;
import persistencia.TipoInvestidor;
import persistencia.TipoTransacao;
import persistencia.Usuario;
import provider.EntityManager;
import controle.LoginUsuarioJpaController;
import controle.TipoInvestidorJpaController;
import controle.TipoTransacaoJpaController;
import controle.UsuarioJpaController;
import controle.exceptions.NonexistentEntityException;
import controle.exceptions.UsuarioException;


public final class FacadeUsuario {

	private FacadeUsuario(){
		
	}
	
	public static void inserirUsuario(String nome, BigInteger cpf, String email, String userName, String password, Integer perfilInvestidor,Integer formaInvestimento, String profissao){
		EntityManagerFactory factory = EntityManager.getFactory();
        Usuario usuario = new Usuario();
        usuario.setCdCpf(cpf);
        
        if (perfilInvestidor != null) {
        	TipoInvestidorJpaController tipoInvestidorJpaController = new TipoInvestidorJpaController(factory);
        	usuario.setCdInvestidor(tipoInvestidorJpaController.findTipoInvestidor(perfilInvestidor));
        }
        
        if (formaInvestimento != null) {
        	TipoTransacaoJpaController tipoTransacaoJpaController = new TipoTransacaoJpaController(factory);
        	usuario.setCdTransacao(tipoTransacaoJpaController.findTipoTransacao(formaInvestimento));
		}
        
        usuario.setDsEmail(email);
        usuario.setDsNome(nome);
        usuario.setDsProfissao(profissao);
                
        UsuarioJpaController usuarioJpaController = new UsuarioJpaController(factory);
        
        try {
            usuarioJpaController.create(usuario);
        } catch (PersistenceException ex) {
        	if (ex.getCause() instanceof ConstraintViolationException) {
        		throw new UsuarioException("Já existe um usuário cadastrado com este email/cpf!");
			}
        	throw new UsuarioException(ex.getMessage());
        }  catch (Exception ex) {
			throw new UsuarioException(ex.getMessage());
		}
        
        Usuario Usuario = usuarioJpaController.findUsuarioPorCpf(cpf);
        
        try{
	        LoginUsuario loginUsuario = new LoginUsuario();
	        loginUsuario.setCdUsuario(Usuario);
	        loginUsuario.setDsUserName(userName);
	        loginUsuario.setDsPassword(password);
	        
	        LoginUsuarioJpaController loginUsuarioJpaController = new LoginUsuarioJpaController(factory);
	        loginUsuarioJpaController.create(loginUsuario);
        }catch (PersistenceException ex) {
        	try {
				usuarioJpaController.destroyByCpf(cpf);
			} catch (NonexistentEntityException e) {
				throw new UsuarioException(ex.getMessage());
			}
        	if (ex.getCause().getCause() instanceof ConstraintViolationException) {
        		throw new UsuarioException("Já existe um usuário cadastrado com este username!");
			}
        	throw new UsuarioException(ex.getMessage());
        }  catch (Exception ex) {
        	try {
				usuarioJpaController.destroyByCpf(cpf);
			} catch (NonexistentEntityException e) {
				throw new UsuarioException(ex.getMessage());
			}
			throw new UsuarioException(ex.getMessage());
		}
        
        factory.close();
		
	}
	
	public static Object[] getUsuario(String userName, String password){
		EntityManagerFactory factory = EntityManager.getFactory();
		LoginUsuarioJpaController loginUsuarioJpaController = new LoginUsuarioJpaController(factory);
		LoginUsuario loginUsuario = loginUsuarioJpaController.findUsuario(userName, password);
		Object[] fields = new Object[loginUsuario.getCdUsuario().getClass().getDeclaredFields().length];
		
		int count = 0;
		for (Field field : loginUsuario.getCdUsuario().getClass().getDeclaredFields()) {
			if (field.getName().contains("serialVersionUID") || field.getName().contains("carteiraClienteCollection") || 
				field.getName().contains("sugestaoCompraCollection")  || field.getName().contains("carteiraClienteCollection") || 
				field.getName().contains("sugestaoVendaCollection")){
				count ++;
				continue;
			}
			
		    field.setAccessible(true); // You might want to set modifier to public first.
		    Object value;
			try {
				value = field.get(loginUsuario.getCdUsuario());
				if (value instanceof TipoInvestidor) {
					value = ((TipoInvestidor) value).getCdInvestidor();
				}else if (value instanceof TipoTransacao) {
					value = ((TipoTransacao) value).getCdTransacao();
				}
				
			} catch (Exception e) {
				throw new IllegalArgumentException(e.getMessage());
			} 
		    if (value != null) {
		    	fields[count] = value;
		    }
		    
		    count++;
		}
		
		return fields;
	}
}
