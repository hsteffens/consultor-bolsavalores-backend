package controle;

import controle.exceptions.NonexistentEntityException;
import controle.exceptions.PreexistingEntityException;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import persistencia.BolsaValores;
import persistencia.CarteiraCliente;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import persistencia.Acao;

/**
 *
 * @author Diogo Lehner
 */
public class AcaoJpaController implements Serializable {

    public AcaoJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Acao acao) throws PreexistingEntityException, Exception {
        if (acao.getCarteiraClienteCollection() == null) {
            acao.setCarteiraClienteCollection(new ArrayList<CarteiraCliente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            BolsaValores cdBolsavalores = acao.getCdBolsavalores();
            if (cdBolsavalores != null) {
                cdBolsavalores = em.getReference(cdBolsavalores.getClass(), cdBolsavalores.getCdBolsavalores());
                acao.setCdBolsavalores(cdBolsavalores);
            }
            Collection<CarteiraCliente> attachedCarteiraClienteCollection = new ArrayList<CarteiraCliente>();
            for (CarteiraCliente carteiraClienteCollectionCarteiraClienteToAttach : acao.getCarteiraClienteCollection()) {
                carteiraClienteCollectionCarteiraClienteToAttach = em.getReference(carteiraClienteCollectionCarteiraClienteToAttach.getClass(), carteiraClienteCollectionCarteiraClienteToAttach.getCdCarteira());
                attachedCarteiraClienteCollection.add(carteiraClienteCollectionCarteiraClienteToAttach);
            }
            acao.setCarteiraClienteCollection(attachedCarteiraClienteCollection);
            em.persist(acao);
            if (cdBolsavalores != null) {
                cdBolsavalores.getAcaoCollection().add(acao);
                cdBolsavalores = em.merge(cdBolsavalores);
            }
            for (CarteiraCliente carteiraClienteCollectionCarteiraCliente : acao.getCarteiraClienteCollection()) {
                Acao oldDsCodigoOfCarteiraClienteCollectionCarteiraCliente = carteiraClienteCollectionCarteiraCliente.getDsCodigo();
                carteiraClienteCollectionCarteiraCliente.setDsCodigo(acao);
                carteiraClienteCollectionCarteiraCliente = em.merge(carteiraClienteCollectionCarteiraCliente);
                if (oldDsCodigoOfCarteiraClienteCollectionCarteiraCliente != null) {
                    oldDsCodigoOfCarteiraClienteCollectionCarteiraCliente.getCarteiraClienteCollection().remove(carteiraClienteCollectionCarteiraCliente);
                    oldDsCodigoOfCarteiraClienteCollectionCarteiraCliente = em.merge(oldDsCodigoOfCarteiraClienteCollectionCarteiraCliente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAcao(acao.getDsCodigo()) != null) {
                throw new PreexistingEntityException("Acao " + acao + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Acao acao) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acao persistentAcao = em.find(Acao.class, acao.getDsCodigo());
            BolsaValores cdBolsavaloresOld = persistentAcao.getCdBolsavalores();
            BolsaValores cdBolsavaloresNew = acao.getCdBolsavalores();
            Collection<CarteiraCliente> carteiraClienteCollectionOld = persistentAcao.getCarteiraClienteCollection();
            Collection<CarteiraCliente> carteiraClienteCollectionNew = acao.getCarteiraClienteCollection();
            if (cdBolsavaloresNew != null) {
                cdBolsavaloresNew = em.getReference(cdBolsavaloresNew.getClass(), cdBolsavaloresNew.getCdBolsavalores());
                acao.setCdBolsavalores(cdBolsavaloresNew);
            }
            Collection<CarteiraCliente> attachedCarteiraClienteCollectionNew = new ArrayList<CarteiraCliente>();
            for (CarteiraCliente carteiraClienteCollectionNewCarteiraClienteToAttach : carteiraClienteCollectionNew) {
                carteiraClienteCollectionNewCarteiraClienteToAttach = em.getReference(carteiraClienteCollectionNewCarteiraClienteToAttach.getClass(), carteiraClienteCollectionNewCarteiraClienteToAttach.getCdCarteira());
                attachedCarteiraClienteCollectionNew.add(carteiraClienteCollectionNewCarteiraClienteToAttach);
            }
            carteiraClienteCollectionNew = attachedCarteiraClienteCollectionNew;
            acao.setCarteiraClienteCollection(carteiraClienteCollectionNew);
            acao = em.merge(acao);
            if (cdBolsavaloresOld != null && !cdBolsavaloresOld.equals(cdBolsavaloresNew)) {
                cdBolsavaloresOld.getAcaoCollection().remove(acao);
                cdBolsavaloresOld = em.merge(cdBolsavaloresOld);
            }
            if (cdBolsavaloresNew != null && !cdBolsavaloresNew.equals(cdBolsavaloresOld)) {
                cdBolsavaloresNew.getAcaoCollection().add(acao);
                cdBolsavaloresNew = em.merge(cdBolsavaloresNew);
            }
            for (CarteiraCliente carteiraClienteCollectionOldCarteiraCliente : carteiraClienteCollectionOld) {
                if (!carteiraClienteCollectionNew.contains(carteiraClienteCollectionOldCarteiraCliente)) {
                    carteiraClienteCollectionOldCarteiraCliente.setDsCodigo(null);
                    carteiraClienteCollectionOldCarteiraCliente = em.merge(carteiraClienteCollectionOldCarteiraCliente);
                }
            }
            for (CarteiraCliente carteiraClienteCollectionNewCarteiraCliente : carteiraClienteCollectionNew) {
                if (!carteiraClienteCollectionOld.contains(carteiraClienteCollectionNewCarteiraCliente)) {
                    Acao oldDsCodigoOfCarteiraClienteCollectionNewCarteiraCliente = carteiraClienteCollectionNewCarteiraCliente.getDsCodigo();
                    carteiraClienteCollectionNewCarteiraCliente.setDsCodigo(acao);
                    carteiraClienteCollectionNewCarteiraCliente = em.merge(carteiraClienteCollectionNewCarteiraCliente);
                    if (oldDsCodigoOfCarteiraClienteCollectionNewCarteiraCliente != null && !oldDsCodigoOfCarteiraClienteCollectionNewCarteiraCliente.equals(acao)) {
                        oldDsCodigoOfCarteiraClienteCollectionNewCarteiraCliente.getCarteiraClienteCollection().remove(carteiraClienteCollectionNewCarteiraCliente);
                        oldDsCodigoOfCarteiraClienteCollectionNewCarteiraCliente = em.merge(oldDsCodigoOfCarteiraClienteCollectionNewCarteiraCliente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                String id = acao.getDsCodigo();
                if (findAcao(id) == null) {
                    throw new NonexistentEntityException("The acao with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(String id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acao acao;
            try {
                acao = em.getReference(Acao.class, id);
                acao.getDsCodigo();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The acao with id " + id + " no longer exists.", enfe);
            }
            BolsaValores cdBolsavalores = acao.getCdBolsavalores();
            if (cdBolsavalores != null) {
                cdBolsavalores.getAcaoCollection().remove(acao);
                cdBolsavalores = em.merge(cdBolsavalores);
            }
            Collection<CarteiraCliente> carteiraClienteCollection = acao.getCarteiraClienteCollection();
            for (CarteiraCliente carteiraClienteCollectionCarteiraCliente : carteiraClienteCollection) {
                carteiraClienteCollectionCarteiraCliente.setDsCodigo(null);
                carteiraClienteCollectionCarteiraCliente = em.merge(carteiraClienteCollectionCarteiraCliente);
            }
            em.remove(acao);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Acao> findAcaoEntities() {
        return findAcaoEntities(true, -1, -1);
    }

    public List<Acao> findAcaoEntities(int maxResults, int firstResult) {
        return findAcaoEntities(false, maxResults, firstResult);
    }

    private List<Acao> findAcaoEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Acao.class));
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

    public Acao findAcao(String id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Acao.class, id);
        } finally {
            em.close();
        }
    }

    public int getAcaoCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Acao> rt = cq.from(Acao.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
