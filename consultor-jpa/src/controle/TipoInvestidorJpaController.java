package controle;

import controle.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.Usuario;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.TipoInvestidor;

/**
 *
 * @author Diogo Lehner
 */
public class TipoInvestidorJpaController implements Serializable {

    public TipoInvestidorJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoInvestidor tipoInvestidor) {
        if (tipoInvestidor.getUsuarioCollection() == null) {
            tipoInvestidor.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : tipoInvestidor.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getCdUsuario());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            tipoInvestidor.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(tipoInvestidor);
            for (Usuario usuarioCollectionUsuario : tipoInvestidor.getUsuarioCollection()) {
                TipoInvestidor oldCdInvestidorOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getCdInvestidor();
                usuarioCollectionUsuario.setCdInvestidor(tipoInvestidor);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldCdInvestidorOfUsuarioCollectionUsuario != null) {
                    oldCdInvestidorOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldCdInvestidorOfUsuarioCollectionUsuario = em.merge(oldCdInvestidorOfUsuarioCollectionUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoInvestidor tipoInvestidor) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoInvestidor persistentTipoInvestidor = em.find(TipoInvestidor.class, tipoInvestidor.getCdInvestidor());
            Collection<Usuario> usuarioCollectionOld = persistentTipoInvestidor.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = tipoInvestidor.getUsuarioCollection();
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getCdUsuario());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            tipoInvestidor.setUsuarioCollection(usuarioCollectionNew);
            tipoInvestidor = em.merge(tipoInvestidor);
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setCdInvestidor(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    TipoInvestidor oldCdInvestidorOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getCdInvestidor();
                    usuarioCollectionNewUsuario.setCdInvestidor(tipoInvestidor);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldCdInvestidorOfUsuarioCollectionNewUsuario != null && !oldCdInvestidorOfUsuarioCollectionNewUsuario.equals(tipoInvestidor)) {
                        oldCdInvestidorOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldCdInvestidorOfUsuarioCollectionNewUsuario = em.merge(oldCdInvestidorOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoInvestidor.getCdInvestidor();
                if (findTipoInvestidor(id) == null) {
                    throw new NonexistentEntityException("The tipoInvestidor with id " + id + " no longer exists.");
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
            TipoInvestidor tipoInvestidor;
            try {
                tipoInvestidor = em.getReference(TipoInvestidor.class, id);
                tipoInvestidor.getCdInvestidor();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoInvestidor with id " + id + " no longer exists.", enfe);
            }
            Collection<Usuario> usuarioCollection = tipoInvestidor.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.setCdInvestidor(null);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(tipoInvestidor);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoInvestidor> findTipoInvestidorEntities() {
        return findTipoInvestidorEntities(true, -1, -1);
    }

    public List<TipoInvestidor> findTipoInvestidorEntities(int maxResults, int firstResult) {
        return findTipoInvestidorEntities(false, maxResults, firstResult);
    }

    private List<TipoInvestidor> findTipoInvestidorEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoInvestidor.class));
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

    public TipoInvestidor findTipoInvestidor(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoInvestidor.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoInvestidorCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoInvestidor> rt = cq.from(TipoInvestidor.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
