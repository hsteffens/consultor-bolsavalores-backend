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
import persistencia.SugestaoVenda;
import persistencia.Usuario;

/**
 *
 * @author Diogo Lehner
 */
public class SugestaoVendaJpaController implements Serializable {

    public SugestaoVendaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SugestaoVenda sugestaoVenda) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario cdUsuario = sugestaoVenda.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario = em.getReference(cdUsuario.getClass(), cdUsuario.getCdUsuario());
                sugestaoVenda.setCdUsuario(cdUsuario);
            }
            em.persist(sugestaoVenda);
            if (cdUsuario != null) {
                cdUsuario.getSugestaoVendaCollection().add(sugestaoVenda);
                cdUsuario = em.merge(cdUsuario);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSugestaoVenda(sugestaoVenda.getCdVenda()) != null) {
                throw new PreexistingEntityException("SugestaoVenda " + sugestaoVenda + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SugestaoVenda sugestaoVenda) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SugestaoVenda persistentSugestaoVenda = em.find(SugestaoVenda.class, sugestaoVenda.getCdVenda());
            Usuario cdUsuarioOld = persistentSugestaoVenda.getCdUsuario();
            Usuario cdUsuarioNew = sugestaoVenda.getCdUsuario();
            if (cdUsuarioNew != null) {
                cdUsuarioNew = em.getReference(cdUsuarioNew.getClass(), cdUsuarioNew.getCdUsuario());
                sugestaoVenda.setCdUsuario(cdUsuarioNew);
            }
            sugestaoVenda = em.merge(sugestaoVenda);
            if (cdUsuarioOld != null && !cdUsuarioOld.equals(cdUsuarioNew)) {
                cdUsuarioOld.getSugestaoVendaCollection().remove(sugestaoVenda);
                cdUsuarioOld = em.merge(cdUsuarioOld);
            }
            if (cdUsuarioNew != null && !cdUsuarioNew.equals(cdUsuarioOld)) {
                cdUsuarioNew.getSugestaoVendaCollection().add(sugestaoVenda);
                cdUsuarioNew = em.merge(cdUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sugestaoVenda.getCdVenda();
                if (findSugestaoVenda(id) == null) {
                    throw new NonexistentEntityException("The sugestaoVenda with id " + id + " no longer exists.");
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
            SugestaoVenda sugestaoVenda;
            try {
                sugestaoVenda = em.getReference(SugestaoVenda.class, id);
                sugestaoVenda.getCdVenda();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sugestaoVenda with id " + id + " no longer exists.", enfe);
            }
            Usuario cdUsuario = sugestaoVenda.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario.getSugestaoVendaCollection().remove(sugestaoVenda);
                cdUsuario = em.merge(cdUsuario);
            }
            em.remove(sugestaoVenda);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SugestaoVenda> findSugestaoVendaEntities() {
        return findSugestaoVendaEntities(true, -1, -1);
    }

    public List<SugestaoVenda> findSugestaoVendaEntities(int maxResults, int firstResult) {
        return findSugestaoVendaEntities(false, maxResults, firstResult);
    }

    private List<SugestaoVenda> findSugestaoVendaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SugestaoVenda.class));
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

    public SugestaoVenda findSugestaoVenda(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SugestaoVenda.class, id);
        } finally {
            em.close();
        }
    }

    public int getSugestaoVendaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SugestaoVenda> rt = cq.from(SugestaoVenda.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
