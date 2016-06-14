package controle;

import java.io.Serializable;
import java.math.BigInteger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import controle.exceptions.UsuarioException;
import persistencia.LoginUsuario;
import persistencia.Usuario;

public class LoginUsuarioJpaController implements Serializable {

	private EntityManagerFactory emf = null;

	public LoginUsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(LoginUsuario loginUsuario) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            
            Usuario cdUsuario = loginUsuario.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario = em.getReference(cdUsuario.getClass(), cdUsuario.getCdUsuario());
                loginUsuario.setCdUsuario(cdUsuario);
            }
            
            em.persist(loginUsuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(LoginUsuario loginUsuario) {
    	EntityManager em = null;
    	try {
    		em = getEntityManager();
    		em.getTransaction().begin();
    		
    		Usuario cdUsuario = loginUsuario.getCdUsuario();
    		if (cdUsuario != null) {
    			cdUsuario = em.getReference(cdUsuario.getClass(), cdUsuario.getCdUsuario());
    			loginUsuario.setCdUsuario(cdUsuario);
    		}
    		
    		em.merge(loginUsuario);
    		em.getTransaction().commit();
    	} finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    }


    public LoginUsuario findUsuario(String username, String password) {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("LoginUsuario.findByDsUserNameAndDsPassword")
    				.setParameter("dsUserName", username)
    				.setParameter("dsPassword", password);
    		return (LoginUsuario) query.getSingleResult();
    	}catch(NoResultException e){
    		throw new UsuarioException("UserName ou senha inválida!");
    	} finally {
    		em.close();
    	}
    }

}