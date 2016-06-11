package persistencia;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Diogo Lehner
 */
@Entity
@Table(name = "sugestao_compra")
@NamedQueries({
    @NamedQuery(name = "SugestaoCompra.findAll", query = "SELECT s FROM SugestaoCompra s"),
    @NamedQuery(name = "SugestaoCompra.findByCdCompra", query = "SELECT s FROM SugestaoCompra s WHERE s.cdCompra = :cdCompra"),
    @NamedQuery(name = "SugestaoCompra.findByDsCodigo", query = "SELECT s FROM SugestaoCompra s WHERE s.dsCodigo = :dsCodigo")})
public class SugestaoCompra implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "cd_compra")
    private Integer cdCompra;
    
    @Column(name = "ds_codigo")
    private String dsCodigo;
    
    @JoinColumn(name = "cd_usuario", referencedColumnName = "cd_usuario")
    @ManyToOne
    private Usuario cdUsuario;

    public SugestaoCompra() {
    }

    public SugestaoCompra(Integer cdCompra) {
        this.cdCompra = cdCompra;
    }

    public Integer getCdCompra() {
        return cdCompra;
    }

    public void setCdCompra(Integer cdCompra) {
        this.cdCompra = cdCompra;
    }

    public String getDsCodigo() {
        return dsCodigo;
    }

    public void setDsCodigo(String dsCodigo) {
        this.dsCodigo = dsCodigo;
    }

    public Usuario getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Usuario cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdCompra != null ? cdCompra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SugestaoCompra)) {
            return false;
        }
        SugestaoCompra other = (SugestaoCompra) object;
        if ((this.cdCompra == null && other.cdCompra != null) || (this.cdCompra != null && !this.cdCompra.equals(other.cdCompra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.SugestaoCompra[ cdCompra=" + cdCompra + " ]";
    }
    
}
