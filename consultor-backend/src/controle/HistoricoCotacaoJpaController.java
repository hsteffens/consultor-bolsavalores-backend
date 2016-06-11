package controle;

import controle.exceptions.NonexistentEntityException;
import controle.exceptions.PreexistingEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.HistoricoCotacao;

/**
 *
 * @author Diogo Lehner
 */
public class HistoricoCotacaoJpaController implements Serializable {

    public HistoricoCotacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(HistoricoCotacao historicoCotacao) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(historicoCotacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findHistoricoCotacao(historicoCotacao.getCdHistorico()) != null) {
                throw new PreexistingEntityException("HistoricoCotacao " + historicoCotacao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(HistoricoCotacao historicoCotacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            historicoCotacao = em.merge(historicoCotacao);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = historicoCotacao.getCdHistorico();
                if (findHistoricoCotacao(id) == null) {
                    throw new NonexistentEntityException("The historicoCotacao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            HistoricoCotacao historicoCotacao;
            try {
                historicoCotacao = em.getReference(HistoricoCotacao.class, id);
                historicoCotacao.getCdHistorico();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The historicoCotacao with id " + id + " no longer exists.", enfe);
            }
            em.remove(historicoCotacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<HistoricoCotacao> findHistoricoCotacaoEntities() {
        return findHistoricoCotacaoEntities(true, -1, -1);
    }

    public List<HistoricoCotacao> findHistoricoCotacaoEntities(int maxResults, int firstResult) {
        return findHistoricoCotacaoEntities(false, maxResults, firstResult);
    }

    private List<HistoricoCotacao> findHistoricoCotacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(HistoricoCotacao.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public HistoricoCotacao findHistoricoCotacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(HistoricoCotacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getHistoricoCotacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<HistoricoCotacao> rt = cq.from(HistoricoCotacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
