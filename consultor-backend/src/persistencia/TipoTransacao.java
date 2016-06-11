package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Diogo Lehner
 */
@Entity
@Table(name = "tipo_transacao")
@NamedQueries({
    @NamedQuery(name = "TipoTransacao.findAll", query = "SELECT t FROM TipoTransacao t"),
    @NamedQuery(name = "TipoTransacao.findByCdTransacao", query = "SELECT t FROM TipoTransacao t WHERE t.cdTransacao = :cdTransacao"),
    @NamedQuery(name = "TipoTransacao.findByDsTransacao", query = "SELECT t FROM TipoTransacao t WHERE t.dsTransacao = :dsTransacao")})
public class TipoTransacao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "cd_transacao")
    private Integer cdTransacao;
    
    @Column(name = "ds_transacao")
    private String dsTransacao;
    
    @OneToMany(mappedBy = "cdTransacao")
    private Collection<Usuario> usuarioCollection;

    public TipoTransacao() {
    }

    public TipoTransacao(Integer cdTransacao) {
        this.cdTransacao = cdTransacao;
    }

    public Integer getCdTransacao() {
        return cdTransacao;
    }

    public void setCdTransacao(Integer cdTransacao) {
        this.cdTransacao = cdTransacao;
    }

    public String getDsTransacao() {
        return dsTransacao;
    }

    public void setDsTransacao(String dsTransacao) {
        this.dsTransacao = dsTransacao;
    }

    public Collection<Usuario> getUsuarioCollection() {
        return usuarioCollection;
    }

    public void setUsuarioCollection(Collection<Usuario> usuarioCollection) {
        this.usuarioCollection = usuarioCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdTransacao != null ? cdTransacao.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoTransacao)) {
            return false;
        }
        TipoTransacao other = (TipoTransacao) object;
        if ((this.cdTransacao == null && other.cdTransacao != null) || (this.cdTransacao != null && !this.cdTransacao.equals(other.cdTransacao))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TipoTransacao[ cdTransacao=" + cdTransacao + " ]";
    }
    
}
