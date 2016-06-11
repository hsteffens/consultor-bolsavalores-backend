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
@Table(name = "bolsa_valores")
@NamedQueries({
    @NamedQuery(name = "BolsaValores.findAll", query = "SELECT b FROM BolsaValores b"),
    @NamedQuery(name = "BolsaValores.findByCdBolsavalores", query = "SELECT b FROM BolsaValores b WHERE b.cdBolsavalores = :cdBolsavalores"),
    @NamedQuery(name = "BolsaValores.findByDsNome", query = "SELECT b FROM BolsaValores b WHERE b.dsNome = :dsNome"),
    @NamedQuery(name = "BolsaValores.findByDsPais", query = "SELECT b FROM BolsaValores b WHERE b.dsPais = :dsPais")})
public class BolsaValores implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_bolsavalores")
    private Integer cdBolsavalores;
    
    @Column(name = "ds_nome")
    private String dsNome;
    
    @Column(name = "ds_pais")
    private String dsPais;
    
    @OneToMany(mappedBy = "cdBolsavalores")
    private Collection<Acao> acaoCollection;

    public BolsaValores() {
    }

    public BolsaValores(Integer cdBolsavalores) {
        this.cdBolsavalores = cdBolsavalores;
    }

    public Integer getCdBolsavalores() {
        return cdBolsavalores;
    }

    public void setCdBolsavalores(Integer cdBolsavalores) {
        this.cdBolsavalores = cdBolsavalores;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsPais() {
        return dsPais;
    }

    public void setDsPais(String dsPais) {
        this.dsPais = dsPais;
    }

    public Collection<Acao> getAcaoCollection() {
        return acaoCollection;
    }

    public void setAcaoCollection(Collection<Acao> acaoCollection) {
        this.acaoCollection = acaoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdBolsavalores != null ? cdBolsavalores.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof BolsaValores)) {
            return false;
        }
        BolsaValores other = (BolsaValores) object;
        if ((this.cdBolsavalores == null && other.cdBolsavalores != null) || (this.cdBolsavalores != null && !this.cdBolsavalores.equals(other.cdBolsavalores))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.BolsaValores[ cdBolsavalores=" + cdBolsavalores + " ]";
    }
    
}
