package persistencia;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Diogo Lehner
 */
@Entity
@Table(name = "acao")
@NamedQueries({
    @NamedQuery(name = "Acao.findAll", query = "SELECT a FROM Acao a"),
    @NamedQuery(name = "Acao.findByDsCodigo", query = "SELECT a FROM Acao a WHERE a.dsCodigo = :dsCodigo"),
    @NamedQuery(name = "Acao.findByDsNome", query = "SELECT a FROM Acao a WHERE a.dsNome = :dsNome")})
public class Acao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "ds_codigo")
    private String dsCodigo;
    
    @Column(name = "ds_nome")
    private String dsNome;
    
    @OneToMany(mappedBy = "dsCodigo")
    private Collection<CarteiraCliente> carteiraClienteCollection;
    
    @JoinColumn(name = "cd_bolsavalores", referencedColumnName = "cd_bolsavalores")
    @ManyToOne
    private BolsaValores cdBolsavalores;

    public Acao() {
    }

    public Acao(String dsCodigo) {
        this.dsCodigo = dsCodigo;
    }

    public String getDsCodigo() {
        return dsCodigo;
    }

    public void setDsCodigo(String dsCodigo) {
        this.dsCodigo = dsCodigo;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public Collection<CarteiraCliente> getCarteiraClienteCollection() {
        return carteiraClienteCollection;
    }

    public void setCarteiraClienteCollection(Collection<CarteiraCliente> carteiraClienteCollection) {
        this.carteiraClienteCollection = carteiraClienteCollection;
    }

    public BolsaValores getCdBolsavalores() {
        return cdBolsavalores;
    }

    public void setCdBolsavalores(BolsaValores cdBolsavalores) {
        this.cdBolsavalores = cdBolsavalores;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (dsCodigo != null ? dsCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Acao)) {
            return false;
        }
        Acao other = (Acao) object;
        if ((this.dsCodigo == null && other.dsCodigo != null) || (this.dsCodigo != null && !this.dsCodigo.equals(other.dsCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Acao[ dsCodigo=" + dsCodigo + " ]";
    }
    
}
