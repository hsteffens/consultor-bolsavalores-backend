package controle;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import persistencia.CarteiraCliente;
import persistencia.SugestaoCompra;
import persistencia.SugestaoVenda;
import persistencia.TipoInvestidor;
import persistencia.TipoTransacao;
import persistencia.Usuario;
import controle.exceptions.NonexistentEntityException;
import controle.exceptions.UsuarioException;

/**
 *
 * @author Diogo Lehner
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) {
        if (usuario.getCarteiraClienteCollection() == null) {
            usuario.setCarteiraClienteCollection(new ArrayList<CarteiraCliente>());
        }
        if (usuario.getSugestaoCompraCollection() == null) {
            usuario.setSugestaoCompraCollection(new ArrayList<SugestaoCompra>());
        }
        if (usuario.getSugestaoVendaCollection() == null) {
            usuario.setSugestaoVendaCollection(new ArrayList<SugestaoVenda>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            TipoInvestidor cdInvestidor = usuario.getCdInvestidor();
            if (cdInvestidor != null) {
                cdInvestidor = em.getReference(cdInvestidor.getClass(), cdInvestidor.getCdInvestidor());
                usuario.setCdInvestidor(cdInvestidor);
            }
            TipoTransacao cdTransacao = usuario.getCdTransacao();
            if (cdTransacao != null) {
                cdTransacao = em.getReference(cdTransacao.getClass(), cdTransacao.getCdTransacao());
                usuario.setCdTransacao(cdTransacao);
            }
            Collection<CarteiraCliente> attachedCarteiraClienteCollection = new ArrayList<CarteiraCliente>();
            for (CarteiraCliente carteiraClienteCollectionCarteiraClienteToAttach : usuario.getCarteiraClienteCollection()) {
                carteiraClienteCollectionCarteiraClienteToAttach = em.getReference(carteiraClienteCollectionCarteiraClienteToAttach.getClass(), carteiraClienteCollectionCarteiraClienteToAttach.getCdCarteira());
                attachedCarteiraClienteCollection.add(carteiraClienteCollectionCarteiraClienteToAttach);
            }
            usuario.setCarteiraClienteCollection(attachedCarteiraClienteCollection);
            Collection<SugestaoCompra> attachedSugestaoCompraCollection = new ArrayList<SugestaoCompra>();
            for (SugestaoCompra sugestaoCompraCollectionSugestaoCompraToAttach : usuario.getSugestaoCompraCollection()) {
                sugestaoCompraCollectionSugestaoCompraToAttach = em.getReference(sugestaoCompraCollectionSugestaoCompraToAttach.getClass(), sugestaoCompraCollectionSugestaoCompraToAttach.getCdCompra());
                attachedSugestaoCompraCollection.add(sugestaoCompraCollectionSugestaoCompraToAttach);
            }
            usuario.setSugestaoCompraCollection(attachedSugestaoCompraCollection);
            Collection<SugestaoVenda> attachedSugestaoVendaCollection = new ArrayList<SugestaoVenda>();
            for (SugestaoVenda sugestaoVendaCollectionSugestaoVendaToAttach : usuario.getSugestaoVendaCollection()) {
                sugestaoVendaCollectionSugestaoVendaToAttach = em.getReference(sugestaoVendaCollectionSugestaoVendaToAttach.getClass(), sugestaoVendaCollectionSugestaoVendaToAttach.getCdVenda());
                attachedSugestaoVendaCollection.add(sugestaoVendaCollectionSugestaoVendaToAttach);
            }
            usuario.setSugestaoVendaCollection(attachedSugestaoVendaCollection);
            em.persist(usuario);
            if (cdInvestidor != null) {
                cdInvestidor.getUsuarioCollection().add(usuario);
                cdInvestidor = em.merge(cdInvestidor);
            }
            if (cdTransacao != null) {
                cdTransacao.getUsuarioCollection().add(usuario);
                cdTransacao = em.merge(cdTransacao);
            }
            for (CarteiraCliente carteiraClienteCollectionCarteiraCliente : usuario.getCarteiraClienteCollection()) {
                Usuario oldCdUsuarioOfCarteiraClienteCollectionCarteiraCliente = carteiraClienteCollectionCarteiraCliente.getCdUsuario();
                carteiraClienteCollectionCarteiraCliente.setCdUsuario(usuario);
                carteiraClienteCollectionCarteiraCliente = em.merge(carteiraClienteCollectionCarteiraCliente);
                if (oldCdUsuarioOfCarteiraClienteCollectionCarteiraCliente != null) {
                    oldCdUsuarioOfCarteiraClienteCollectionCarteiraCliente.getCarteiraClienteCollection().remove(carteiraClienteCollectionCarteiraCliente);
                    oldCdUsuarioOfCarteiraClienteCollectionCarteiraCliente = em.merge(oldCdUsuarioOfCarteiraClienteCollectionCarteiraCliente);
                }
            }
            for (SugestaoCompra sugestaoCompraCollectionSugestaoCompra : usuario.getSugestaoCompraCollection()) {
                Usuario oldCdUsuarioOfSugestaoCompraCollectionSugestaoCompra = sugestaoCompraCollectionSugestaoCompra.getCdUsuario();
                sugestaoCompraCollectionSugestaoCompra.setCdUsuario(usuario);
                sugestaoCompraCollectionSugestaoCompra = em.merge(sugestaoCompraCollectionSugestaoCompra);
                if (oldCdUsuarioOfSugestaoCompraCollectionSugestaoCompra != null) {
                    oldCdUsuarioOfSugestaoCompraCollectionSugestaoCompra.getSugestaoCompraCollection().remove(sugestaoCompraCollectionSugestaoCompra);
                    oldCdUsuarioOfSugestaoCompraCollectionSugestaoCompra = em.merge(oldCdUsuarioOfSugestaoCompraCollectionSugestaoCompra);
                }
            }
            for (SugestaoVenda sugestaoVendaCollectionSugestaoVenda : usuario.getSugestaoVendaCollection()) {
                Usuario oldCdUsuarioOfSugestaoVendaCollectionSugestaoVenda = sugestaoVendaCollectionSugestaoVenda.getCdUsuario();
                sugestaoVendaCollectionSugestaoVenda.setCdUsuario(usuario);
                sugestaoVendaCollectionSugestaoVenda = em.merge(sugestaoVendaCollectionSugestaoVenda);
                if (oldCdUsuarioOfSugestaoVendaCollectionSugestaoVenda != null) {
                    oldCdUsuarioOfSugestaoVendaCollectionSugestaoVenda.getSugestaoVendaCollection().remove(sugestaoVendaCollectionSugestaoVenda);
                    oldCdUsuarioOfSugestaoVendaCollectionSugestaoVenda = em.merge(oldCdUsuarioOfSugestaoVendaCollectionSugestaoVenda);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCdUsuario());
            TipoInvestidor cdInvestidorOld = persistentUsuario.getCdInvestidor();
            TipoInvestidor cdInvestidorNew = usuario.getCdInvestidor();
            TipoTransacao cdTransacaoOld = persistentUsuario.getCdTransacao();
            TipoTransacao cdTransacaoNew = usuario.getCdTransacao();
            if (cdInvestidorNew != null) {
                cdInvestidorNew = em.getReference(cdInvestidorNew.getClass(), cdInvestidorNew.getCdInvestidor());
                usuario.setCdInvestidor(cdInvestidorNew);
            }
            if (cdTransacaoNew != null) {
                cdTransacaoNew = em.getReference(cdTransacaoNew.getClass(), cdTransacaoNew.getCdTransacao());
                usuario.setCdTransacao(cdTransacaoNew);
            }
            
            usuario = em.merge(usuario);
            if (cdInvestidorOld != null && !cdInvestidorOld.equals(cdInvestidorNew)) {
                cdInvestidorOld.getUsuarioCollection().remove(usuario);
                cdInvestidorOld = em.merge(cdInvestidorOld);
            }
            if (cdInvestidorNew != null && !cdInvestidorNew.equals(cdInvestidorOld)) {
                cdInvestidorNew.getUsuarioCollection().add(usuario);
                cdInvestidorNew = em.merge(cdInvestidorNew);
            }
            if (cdTransacaoOld != null && !cdTransacaoOld.equals(cdTransacaoNew)) {
                cdTransacaoOld.getUsuarioCollection().remove(usuario);
                cdTransacaoOld = em.merge(cdTransacaoOld);
            }
            if (cdTransacaoNew != null && !cdTransacaoNew.equals(cdTransacaoOld)) {
                cdTransacaoNew.getUsuarioCollection().add(usuario);
                cdTransacaoNew = em.merge(cdTransacaoNew);
            }
           
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getCdUsuario();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCdUsuario();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            TipoInvestidor cdInvestidor = usuario.getCdInvestidor();
            if (cdInvestidor != null) {
                cdInvestidor.getUsuarioCollection().remove(usuario);
                cdInvestidor = em.merge(cdInvestidor);
            }
            TipoTransacao cdTransacao = usuario.getCdTransacao();
            if (cdTransacao != null) {
                cdTransacao.getUsuarioCollection().remove(usuario);
                cdTransacao = em.merge(cdTransacao);
            }
            Collection<CarteiraCliente> carteiraClienteCollection = usuario.getCarteiraClienteCollection();
            for (CarteiraCliente carteiraClienteCollectionCarteiraCliente : carteiraClienteCollection) {
                carteiraClienteCollectionCarteiraCliente.setCdUsuario(null);
                carteiraClienteCollectionCarteiraCliente = em.merge(carteiraClienteCollectionCarteiraCliente);
            }
            Collection<SugestaoCompra> sugestaoCompraCollection = usuario.getSugestaoCompraCollection();
            for (SugestaoCompra sugestaoCompraCollectionSugestaoCompra : sugestaoCompraCollection) {
                sugestaoCompraCollectionSugestaoCompra.setCdUsuario(null);
                sugestaoCompraCollectionSugestaoCompra = em.merge(sugestaoCompraCollectionSugestaoCompra);
            }
            Collection<SugestaoVenda> sugestaoVendaCollection = usuario.getSugestaoVendaCollection();
            for (SugestaoVenda sugestaoVendaCollectionSugestaoVenda : sugestaoVendaCollection) {
                sugestaoVendaCollectionSugestaoVenda.setCdUsuario(null);
                sugestaoVendaCollectionSugestaoVenda = em.merge(sugestaoVendaCollectionSugestaoVenda);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroyByCpf(BigInteger cpf) throws NonexistentEntityException {
    	EntityManager em = null;
    	try {
    		em = getEntityManager();
    		em.getTransaction().begin();
    		Usuario usuario;
    		try {
    			usuario = findUsuarioPorCpf(cpf);
    		} catch (EntityNotFoundException enfe) {
    			throw new NonexistentEntityException("The usuario with cpf " + cpf + " no longer exists.", enfe);
    		}
//    		Collection<CarteiraCliente> carteiraClienteCollection = usuario.getCarteiraClienteCollection();
//    		for (CarteiraCliente carteiraClienteCollectionCarteiraCliente : carteiraClienteCollection) {
//    			carteiraClienteCollectionCarteiraCliente.setCdUsuario(null);
//    			carteiraClienteCollectionCarteiraCliente = em.merge(carteiraClienteCollectionCarteiraCliente);
//    		}
//    		Collection<SugestaoCompra> sugestaoCompraCollection = usuario.getSugestaoCompraCollection();
//    		for (SugestaoCompra sugestaoCompraCollectionSugestaoCompra : sugestaoCompraCollection) {
//    			sugestaoCompraCollectionSugestaoCompra.setCdUsuario(null);
//    			sugestaoCompraCollectionSugestaoCompra = em.merge(sugestaoCompraCollectionSugestaoCompra);
//    		}
//    		Collection<SugestaoVenda> sugestaoVendaCollection = usuario.getSugestaoVendaCollection();
//    		for (SugestaoVenda sugestaoVendaCollectionSugestaoVenda : sugestaoVendaCollection) {
//    			sugestaoVendaCollectionSugestaoVenda.setCdUsuario(null);
//    			sugestaoVendaCollectionSugestaoVenda = em.merge(sugestaoVendaCollectionSugestaoVenda);
//    		}
    		em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
    		em.getTransaction().commit();
    	} catch(Exception e){
    		throw new UsuarioException(e.getMessage());
    	}
    	finally {
    		if (em != null) {
    			em.close();
    		}
    	}
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public Usuario findUsuarioPorCpf(BigInteger cpf) {
    	EntityManager em = getEntityManager();
    	try {
    		Query query = em.createNamedQuery("Usuario.findByCdCpf").setParameter("cdCpf", cpf);
    		return (Usuario) query.getSingleResult();
    	}catch(NoResultException e){
    		throw new UsuarioException(e.getMessage());
    	} finally {
    		em.close();
    	}
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
