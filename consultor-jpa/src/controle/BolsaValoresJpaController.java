package controle;

import controle.exceptions.NonexistentEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.Acao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.BolsaValores;

/**
 *
 * @author Diogo Lehner
 */
public class BolsaValoresJpaController implements Serializable {

    public BolsaValoresJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(BolsaValores bolsaValores) {
        if (bolsaValores.getAcaoCollection() == null) {
            bolsaValores.setAcaoCollection(new ArrayList<Acao>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Acao> attachedAcaoCollection = new ArrayList<Acao>();
            for (Acao acaoCollectionAcaoToAttach : bolsaValores.getAcaoCollection()) {
                acaoCollectionAcaoToAttach = em.getReference(acaoCollectionAcaoToAttach.getClass(), acaoCollectionAcaoToAttach.getDsCodigo());
                attachedAcaoCollection.add(acaoCollectionAcaoToAttach);
            }
            bolsaValores.setAcaoCollection(attachedAcaoCollection);
            em.persist(bolsaValores);
            for (Acao acaoCollectionAcao : bolsaValores.getAcaoCollection()) {
                BolsaValores oldCdBolsavaloresOfAcaoCollectionAcao = acaoCollectionAcao.getCdBolsavalores();
                acaoCollectionAcao.setCdBolsavalores(bolsaValores);
                acaoCollectionAcao = em.merge(acaoCollectionAcao);
                if (oldCdBolsavaloresOfAcaoCollectionAcao != null) {
                    oldCdBolsavaloresOfAcaoCollectionAcao.getAcaoCollection().remove(acaoCollectionAcao);
                    oldCdBolsavaloresOfAcaoCollectionAcao = em.merge(oldCdBolsavaloresOfAcaoCollectionAcao);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(BolsaValores bolsaValores) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BolsaValores persistentBolsaValores = em.find(BolsaValores.class, bolsaValores.getCdBolsavalores());
            Collection<Acao> acaoCollectionOld = persistentBolsaValores.getAcaoCollection();
            Collection<Acao> acaoCollectionNew = bolsaValores.getAcaoCollection();
            Collection<Acao> attachedAcaoCollectionNew = new ArrayList<Acao>();
            for (Acao acaoCollectionNewAcaoToAttach : acaoCollectionNew) {
                acaoCollectionNewAcaoToAttach = em.getReference(acaoCollectionNewAcaoToAttach.getClass(), acaoCollectionNewAcaoToAttach.getDsCodigo());
                attachedAcaoCollectionNew.add(acaoCollectionNewAcaoToAttach);
            }
            acaoCollectionNew = attachedAcaoCollectionNew;
            bolsaValores.setAcaoCollection(acaoCollectionNew);
            bolsaValores = em.merge(bolsaValores);
            for (Acao acaoCollectionOldAcao : acaoCollectionOld) {
                if (!acaoCollectionNew.contains(acaoCollectionOldAcao)) {
                    acaoCollectionOldAcao.setCdBolsavalores(null);
                    acaoCollectionOldAcao = em.merge(acaoCollectionOldAcao);
                }
            }
            for (Acao acaoCollectionNewAcao : acaoCollectionNew) {
                if (!acaoCollectionOld.contains(acaoCollectionNewAcao)) {
                    BolsaValores oldCdBolsavaloresOfAcaoCollectionNewAcao = acaoCollectionNewAcao.getCdBolsavalores();
                    acaoCollectionNewAcao.setCdBolsavalores(bolsaValores);
                    acaoCollectionNewAcao = em.merge(acaoCollectionNewAcao);
                    if (oldCdBolsavaloresOfAcaoCollectionNewAcao != null && !oldCdBolsavaloresOfAcaoCollectionNewAcao.equals(bolsaValores)) {
                        oldCdBolsavaloresOfAcaoCollectionNewAcao.getAcaoCollection().remove(acaoCollectionNewAcao);
                        oldCdBolsavaloresOfAcaoCollectionNewAcao = em.merge(oldCdBolsavaloresOfAcaoCollectionNewAcao);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = bolsaValores.getCdBolsavalores();
                if (findBolsaValores(id) == null) {
                    throw new NonexistentEntityException("The bolsaValores with id " + id + " no longer exists.");
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
            BolsaValores bolsaValores;
            try {
                bolsaValores = em.getReference(BolsaValores.class, id);
                bolsaValores.getCdBolsavalores();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The bolsaValores with id " + id + " no longer exists.", enfe);
            }
            Collection<Acao> acaoCollection = bolsaValores.getAcaoCollection();
            for (Acao acaoCollectionAcao : acaoCollection) {
                acaoCollectionAcao.setCdBolsavalores(null);
                acaoCollectionAcao = em.merge(acaoCollectionAcao);
            }
            em.remove(bolsaValores);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<BolsaValores> findBolsaValoresEntities() {
        return findBolsaValoresEntities(true, -1, -1);
    }

    public List<BolsaValores> findBolsaValoresEntities(int maxResults, int firstResult) {
        return findBolsaValoresEntities(false, maxResults, firstResult);
    }

    private List<BolsaValores> findBolsaValoresEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(BolsaValores.class));
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

    public BolsaValores findBolsaValores(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(BolsaValores.class, id);
        } finally {
            em.close();
        }
    }

    public int getBolsaValoresCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<BolsaValores> rt = cq.from(BolsaValores.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
