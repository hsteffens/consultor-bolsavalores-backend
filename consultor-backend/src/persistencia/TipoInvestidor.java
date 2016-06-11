package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "tipo_investidor")
@NamedQueries({
    @NamedQuery(name = "TipoInvestidor.findAll", query = "SELECT t FROM TipoInvestidor t"),
    @NamedQuery(name = "TipoInvestidor.findByCdInvestidor", query = "SELECT t FROM TipoInvestidor t WHERE t.cdInvestidor = :cdInvestidor"),
    @NamedQuery(name = "TipoInvestidor.findByDsInvestidor", query = "SELECT t FROM TipoInvestidor t WHERE t.dsInvestidor = :dsInvestidor")})
public class TipoInvestidor implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_investidor")
    private Integer cdInvestidor;
    
    @Column(name = "ds_investidor")
    private String dsInvestidor;
    
    @OneToMany(mappedBy = "cdInvestidor")
    private Collection<Usuario> usuarioCollection;

    public TipoInvestidor() {
    }

    public TipoInvestidor(Integer cdInvestidor) {
        this.cdInvestidor = cdInvestidor;
    }

    public Integer getCdInvestidor() {
        return cdInvestidor;
    }

    public void setCdInvestidor(Integer cdInvestidor) {
        this.cdInvestidor = cdInvestidor;
    }

    public String getDsInvestidor() {
        return dsInvestidor;
    }

    public void setDsInvestidor(String dsInvestidor) {
        this.dsInvestidor = dsInvestidor;
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
        hash += (cdInvestidor != null ? cdInvestidor.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TipoInvestidor)) {
            return false;
        }
        TipoInvestidor other = (TipoInvestidor) object;
        if ((this.cdInvestidor == null && other.cdInvestidor != null) || (this.cdInvestidor != null && !this.cdInvestidor.equals(other.cdInvestidor))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.TipoInvestidor[ cdInvestidor=" + cdInvestidor + " ]";
    }
    
}
