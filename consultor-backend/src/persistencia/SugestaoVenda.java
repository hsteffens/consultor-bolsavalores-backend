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
@Table(name = "sugestao_venda")
@NamedQueries({
    @NamedQuery(name = "SugestaoVenda.findAll", query = "SELECT s FROM SugestaoVenda s"),
    @NamedQuery(name = "SugestaoVenda.findByCdVenda", query = "SELECT s FROM SugestaoVenda s WHERE s.cdVenda = :cdVenda"),
    @NamedQuery(name = "SugestaoVenda.findByDsCodigo", query = "SELECT s FROM SugestaoVenda s WHERE s.dsCodigo = :dsCodigo")})
public class SugestaoVenda implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "cd_venda")
    private Integer cdVenda;
    
    @Column(name = "ds_codigo")
    private String dsCodigo;
    
    @JoinColumn(name = "cd_usuario", referencedColumnName = "cd_usuario")
    @ManyToOne
    private Usuario cdUsuario;

    public SugestaoVenda() {
    }

    public SugestaoVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
    }

    public Integer getCdVenda() {
        return cdVenda;
    }

    public void setCdVenda(Integer cdVenda) {
        this.cdVenda = cdVenda;
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
        hash += (cdVenda != null ? cdVenda.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof SugestaoVenda)) {
            return false;
        }
        SugestaoVenda other = (SugestaoVenda) object;
        if ((this.cdVenda == null && other.cdVenda != null) || (this.cdVenda != null && !this.cdVenda.equals(other.cdVenda))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.SugestaoVenda[ cdVenda=" + cdVenda + " ]";
    }
    
}
