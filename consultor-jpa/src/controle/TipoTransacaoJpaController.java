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
import persistencia.TipoTransacao;

/**
 *
 * @author Diogo Lehner
 */
public class TipoTransacaoJpaController implements Serializable {

    public TipoTransacaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(TipoTransacao tipoTransacao) {
        if (tipoTransacao.getUsuarioCollection() == null) {
            tipoTransacao.setUsuarioCollection(new ArrayList<Usuario>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Usuario> attachedUsuarioCollection = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionUsuarioToAttach : tipoTransacao.getUsuarioCollection()) {
                usuarioCollectionUsuarioToAttach = em.getReference(usuarioCollectionUsuarioToAttach.getClass(), usuarioCollectionUsuarioToAttach.getCdUsuario());
                attachedUsuarioCollection.add(usuarioCollectionUsuarioToAttach);
            }
            tipoTransacao.setUsuarioCollection(attachedUsuarioCollection);
            em.persist(tipoTransacao);
            for (Usuario usuarioCollectionUsuario : tipoTransacao.getUsuarioCollection()) {
                TipoTransacao oldCdTransacaoOfUsuarioCollectionUsuario = usuarioCollectionUsuario.getCdTransacao();
                usuarioCollectionUsuario.setCdTransacao(tipoTransacao);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
                if (oldCdTransacaoOfUsuarioCollectionUsuario != null) {
                    oldCdTransacaoOfUsuarioCollectionUsuario.getUsuarioCollection().remove(usuarioCollectionUsuario);
                    oldCdTransacaoOfUsuarioCollectionUsuario = em.merge(oldCdTransacaoOfUsuarioCollectionUsuario);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(TipoTransacao tipoTransacao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoTransacao persistentTipoTransacao = em.find(TipoTransacao.class, tipoTransacao.getCdTransacao());
            Collection<Usuario> usuarioCollectionOld = persistentTipoTransacao.getUsuarioCollection();
            Collection<Usuario> usuarioCollectionNew = tipoTransacao.getUsuarioCollection();
            Collection<Usuario> attachedUsuarioCollectionNew = new ArrayList<Usuario>();
            for (Usuario usuarioCollectionNewUsuarioToAttach : usuarioCollectionNew) {
                usuarioCollectionNewUsuarioToAttach = em.getReference(usuarioCollectionNewUsuarioToAttach.getClass(), usuarioCollectionNewUsuarioToAttach.getCdUsuario());
                attachedUsuarioCollectionNew.add(usuarioCollectionNewUsuarioToAttach);
            }
            usuarioCollectionNew = attachedUsuarioCollectionNew;
            tipoTransacao.setUsuarioCollection(usuarioCollectionNew);
            tipoTransacao = em.merge(tipoTransacao);
            for (Usuario usuarioCollectionOldUsuario : usuarioCollectionOld) {
                if (!usuarioCollectionNew.contains(usuarioCollectionOldUsuario)) {
                    usuarioCollectionOldUsuario.setCdTransacao(null);
                    usuarioCollectionOldUsuario = em.merge(usuarioCollectionOldUsuario);
                }
            }
            for (Usuario usuarioCollectionNewUsuario : usuarioCollectionNew) {
                if (!usuarioCollectionOld.contains(usuarioCollectionNewUsuario)) {
                    TipoTransacao oldCdTransacaoOfUsuarioCollectionNewUsuario = usuarioCollectionNewUsuario.getCdTransacao();
                    usuarioCollectionNewUsuario.setCdTransacao(tipoTransacao);
                    usuarioCollectionNewUsuario = em.merge(usuarioCollectionNewUsuario);
                    if (oldCdTransacaoOfUsuarioCollectionNewUsuario != null && !oldCdTransacaoOfUsuarioCollectionNewUsuario.equals(tipoTransacao)) {
                        oldCdTransacaoOfUsuarioCollectionNewUsuario.getUsuarioCollection().remove(usuarioCollectionNewUsuario);
                        oldCdTransacaoOfUsuarioCollectionNewUsuario = em.merge(oldCdTransacaoOfUsuarioCollectionNewUsuario);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = tipoTransacao.getCdTransacao();
                if (findTipoTransacao(id) == null) {
                    throw new NonexistentEntityException("The tipoTransacao with id " + id + " no longer exists.");
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
            TipoTransacao tipoTransacao;
            try {
                tipoTransacao = em.getReference(TipoTransacao.class, id);
                tipoTransacao.getCdTransacao();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The tipoTransacao with id " + id + " no longer exists.", enfe);
            }
            Collection<Usuario> usuarioCollection = tipoTransacao.getUsuarioCollection();
            for (Usuario usuarioCollectionUsuario : usuarioCollection) {
                usuarioCollectionUsuario.setCdTransacao(null);
                usuarioCollectionUsuario = em.merge(usuarioCollectionUsuario);
            }
            em.remove(tipoTransacao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<TipoTransacao> findTipoTransacaoEntities() {
        return findTipoTransacaoEntities(true, -1, -1);
    }

    public List<TipoTransacao> findTipoTransacaoEntities(int maxResults, int firstResult) {
        return findTipoTransacaoEntities(false, maxResults, firstResult);
    }

    private List<TipoTransacao> findTipoTransacaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(TipoTransacao.class));
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

    public TipoTransacao findTipoTransacao(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(TipoTransacao.class, id);
        } finally {
            em.close();
        }
    }

    public int getTipoTransacaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<TipoTransacao> rt = cq.from(TipoTransacao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
