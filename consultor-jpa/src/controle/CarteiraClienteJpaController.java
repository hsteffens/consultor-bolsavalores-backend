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
import persistencia.Acao;
import persistencia.CarteiraCliente;
import persistencia.Usuario;

/**
 *
 * @author Diogo Lehner
 */
public class CarteiraClienteJpaController implements Serializable {

    public CarteiraClienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(CarteiraCliente carteiraCliente) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Acao dsCodigo = carteiraCliente.getDsCodigo();
            if (dsCodigo != null) {
                dsCodigo = em.getReference(dsCodigo.getClass(), dsCodigo.getDsCodigo());
                carteiraCliente.setDsCodigo(dsCodigo);
            }
            Usuario cdUsuario = carteiraCliente.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario = em.getReference(cdUsuario.getClass(), cdUsuario.getCdUsuario());
                carteiraCliente.setCdUsuario(cdUsuario);
            }
            em.persist(carteiraCliente);
            if (dsCodigo != null) {
                dsCodigo.getCarteiraClienteCollection().add(carteiraCliente);
                dsCodigo = em.merge(dsCodigo);
            }
            if (cdUsuario != null) {
                cdUsuario.getCarteiraClienteCollection().add(carteiraCliente);
                cdUsuario = em.merge(cdUsuario);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(CarteiraCliente carteiraCliente) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            CarteiraCliente persistentCarteiraCliente = em.find(CarteiraCliente.class, carteiraCliente.getCdCarteira());
            Acao dsCodigoOld = persistentCarteiraCliente.getDsCodigo();
            Acao dsCodigoNew = carteiraCliente.getDsCodigo();
            Usuario cdUsuarioOld = persistentCarteiraCliente.getCdUsuario();
            Usuario cdUsuarioNew = carteiraCliente.getCdUsuario();
            if (dsCodigoNew != null) {
                dsCodigoNew = em.getReference(dsCodigoNew.getClass(), dsCodigoNew.getDsCodigo());
                carteiraCliente.setDsCodigo(dsCodigoNew);
            }
            if (cdUsuarioNew != null) {
                cdUsuarioNew = em.getReference(cdUsuarioNew.getClass(), cdUsuarioNew.getCdUsuario());
                carteiraCliente.setCdUsuario(cdUsuarioNew);
            }
            carteiraCliente = em.merge(carteiraCliente);
            if (dsCodigoOld != null && !dsCodigoOld.equals(dsCodigoNew)) {
                dsCodigoOld.getCarteiraClienteCollection().remove(carteiraCliente);
                dsCodigoOld = em.merge(dsCodigoOld);
            }
            if (dsCodigoNew != null && !dsCodigoNew.equals(dsCodigoOld)) {
                dsCodigoNew.getCarteiraClienteCollection().add(carteiraCliente);
                dsCodigoNew = em.merge(dsCodigoNew);
            }
            if (cdUsuarioOld != null && !cdUsuarioOld.equals(cdUsuarioNew)) {
                cdUsuarioOld.getCarteiraClienteCollection().remove(carteiraCliente);
                cdUsuarioOld = em.merge(cdUsuarioOld);
            }
            if (cdUsuarioNew != null && !cdUsuarioNew.equals(cdUsuarioOld)) {
                cdUsuarioNew.getCarteiraClienteCollection().add(carteiraCliente);
                cdUsuarioNew = em.merge(cdUsuarioNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = carteiraCliente.getCdCarteira();
                if (findCarteiraCliente(id) == null) {
                    throw new NonexistentEntityException("The carteiraCliente with id " + id + " no longer exists.");
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
            CarteiraCliente carteiraCliente;
            try {
                carteiraCliente = em.getReference(CarteiraCliente.class, id);
                carteiraCliente.getCdCarteira();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The carteiraCliente with id " + id + " no longer exists.", enfe);
            }
            Acao dsCodigo = carteiraCliente.getDsCodigo();
            if (dsCodigo != null) {
                dsCodigo.getCarteiraClienteCollection().remove(carteiraCliente);
                dsCodigo = em.merge(dsCodigo);
            }
            Usuario cdUsuario = carteiraCliente.getCdUsuario();
            if (cdUsuario != null) {
                cdUsuario.getCarteiraClienteCollection().remove(carteiraCliente);
                cdUsuario = em.merge(cdUsuario);
            }
            em.remove(carteiraCliente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<CarteiraCliente> findCarteiraClienteEntities() {
        return findCarteiraClienteEntities(true, -1, -1);
    }

    public List<CarteiraCliente> findCarteiraClienteEntities(int maxResults, int firstResult) {
        return findCarteiraClienteEntities(false, maxResults, firstResult);
    }

    private List<CarteiraCliente> findCarteiraClienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(CarteiraCliente.class));
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

    public CarteiraCliente findCarteiraCliente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CarteiraCliente.class, id);
        } finally {
            em.close();
        }
    }

    public int getCarteiraClienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<CarteiraCliente> rt = cq.from(CarteiraCliente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
