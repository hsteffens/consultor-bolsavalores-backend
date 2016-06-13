package controle;

import controle.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.SugestaoCompra;
import persistencia.Usuario;

/**
 *
 * @author Diogo Lehner
 */
public class SugestaoCompraJpaController implements Serializable {

    public SugestaoCompraJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(SugestaoCompra sugestaoCompra) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario cdUsuario = sugestaoCompra.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario = em.getReference(cdUsuario.getClass(), cdUsuario.getCdUsuario());
                sugestaoCompra.setCdUsuario(cdUsuario);
            }
            em.persist(sugestaoCompra);
            if (cdUsuario != null) {
                cdUsuario.getSugestaoCompraCollection().add(sugestaoCompra);
                cdUsuario = em.merge(cdUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(SugestaoCompra sugestaoCompra) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            SugestaoCompra persistentSugestaoCompra = em.find(SugestaoCompra.class, sugestaoCompra.getCdCompra());
            Usuario cdUsuarioOld = persistentSugestaoCompra.getCdUsuario();
            Usuario cdUsuarioNew = sugestaoCompra.getCdUsuario();
            if (cdUsuarioNew != null) {
                cdUsuarioNew = em.getReference(cdUsuarioNew.getClass(), cdUsuarioNew.getCdUsuario());
                sugestaoCompra.setCdUsuario(cdUsuarioNew);
            }
            sugestaoCompra = em.merge(sugestaoCompra);
            if (cdUsuarioOld != null && !cdUsuarioOld.equals(cdUsuarioNew)) {
                cdUsuarioOld.getSugestaoCompraCollection().remove(sugestaoCompra);
                cdUsuarioOld = em.merge(cdUsuarioOld);
            }
            if (cdUsuarioNew != null && !cdUsuarioNew.equals(cdUsuarioOld)) {
                cdUsuarioNew.getSugestaoCompraCollection().add(sugestaoCompra);
                cdUsuarioNew = em.merge(cdUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sugestaoCompra.getCdCompra();
                if (findSugestaoCompra(id) == null) {
                    throw new NonexistentEntityException("The sugestaoCompra with id " + id + " no longer exists.");
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
            SugestaoCompra sugestaoCompra;
            try {
                sugestaoCompra = em.getReference(SugestaoCompra.class, id);
                sugestaoCompra.getCdCompra();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sugestaoCompra with id " + id + " no longer exists.", enfe);
            }
            Usuario cdUsuario = sugestaoCompra.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario.getSugestaoCompraCollection().remove(sugestaoCompra);
                cdUsuario = em.merge(cdUsuario);
            }
            em.remove(sugestaoCompra);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<SugestaoCompra> findSugestaoCompraEntities() {
        return findSugestaoCompraEntities(true, -1, -1);
    }

    public List<SugestaoCompra> findSugestaoCompraEntities(int maxResults, int firstResult) {
        return findSugestaoCompraEntities(false, maxResults, firstResult);
    }

    private List<SugestaoCompra> findSugestaoCompraEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(SugestaoCompra.class));
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

    public SugestaoCompra findSugestaoCompra(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(SugestaoCompra.class, id);
        } finally {
            em.close();
        }
    }

    public int getSugestaoCompraCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<SugestaoCompra> rt = cq.from(SugestaoCompra.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
