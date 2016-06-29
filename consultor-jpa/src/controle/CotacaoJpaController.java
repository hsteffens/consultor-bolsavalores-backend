package controle;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import persistencia.Cotacao;
import controle.exceptions.NonexistentEntityException;

public class CotacaoJpaController implements Serializable {

		private EntityManagerFactory emf = null;

		public CotacaoJpaController(EntityManagerFactory emf) {
	        this.emf = emf;
	    }

	    public EntityManager getEntityManager() {
	        return emf.createEntityManager();
	    }

	    public void create(Cotacao cotacao) {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            
	            em.persist(cotacao);
	            em.getTransaction().commit();
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }
	    
	    public Cotacao findCotacao(String acao) {
	    	EntityManager em = getEntityManager();
	    	try {
	    		Query query = em.createNamedQuery("Cotacao.findByDsCodigo")
	    				.setParameter("dsCodigo", acao);
	    		return (Cotacao) query.getSingleResult();
	    	}catch(NoResultException e){
	    		return null;
	    	} finally {
	    		em.close();
	    	}
	    }
	    
	    public List<Cotacao> findAllCotacao(){
	    	EntityManager em = getEntityManager();
	    	try {
	    		Query query = em.createNamedQuery("Cotacao.findAll");
	    		return query.getResultList();
	    	}catch(NoResultException e){
	    		return null;
	    	} finally {
	    		em.close();
	    	}
	    }
	    
	    public void destroy(String id) throws NonexistentEntityException {
	        EntityManager em = null;
	        try {
	            em = getEntityManager();
	            em.getTransaction().begin();
	            Cotacao cotacao;
	            try {
	            	cotacao = findCotacao(id);
	            	if (cotacao == null) {
						return;
					}
	            } catch (EntityNotFoundException enfe) {
	                return;
	            }
	            Cotacao cotacaoMerge = em.merge(cotacao);
	            em.remove(cotacaoMerge);
	            em.getTransaction().commit();
	        } finally {
	            if (em != null) {
	                em.close();
	            }
	        }
	    }
}
