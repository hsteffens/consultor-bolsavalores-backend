package persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Diogo Lehner
 */
@Entity
@Table(name = "historico_cotacao")
@NamedQueries({
    @NamedQuery(name = "HistoricoCotacao.findAll", query = "SELECT h FROM HistoricoCotacao h"),
    @NamedQuery(name = "HistoricoCotacao.findByCdHistorico", query = "SELECT h FROM HistoricoCotacao h WHERE h.cdHistorico = :cdHistorico"),
    @NamedQuery(name = "HistoricoCotacao.findByVlPreco", query = "SELECT h FROM HistoricoCotacao h WHERE h.vlPreco = :vlPreco"),
    @NamedQuery(name = "HistoricoCotacao.findByDsCodigo", query = "SELECT h FROM HistoricoCotacao h WHERE h.dsCodigo = :dsCodigo")})
public class HistoricoCotacao implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "cd_historico")
    private Integer cdHistorico;
    
    @Column(name = "vl_preco")
    private BigDecimal vlPreco;
    
    @Column(name = "ds_codigo")
    private String dsCodigo;

    public HistoricoCotacao() {
    }

    public HistoricoCotacao(Integer cdHistorico) {
        this.cdHistorico = cdHistorico;
    }

    public Integer getCdHistorico() {
        return cdHistorico;
    }

    public void setCdHistorico(Integer cdHistorico) {
        this.cdHistorico = cdHistorico;
    }

    public BigDecimal getVlPreco() {
        return vlPreco;
    }

    public void setVlPreco(BigDecimal vlPreco) {
        this.vlPreco = vlPreco;
    }

    public String getDsCodigo() {
        return dsCodigo;
    }

    public void setDsCodigo(String dsCodigo) {
        this.dsCodigo = dsCodigo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdHistorico != null ? cdHistorico.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof HistoricoCotacao)) {
            return false;
        }
        HistoricoCotacao other = (HistoricoCotacao) object;
        if ((this.cdHistorico == null && other.cdHistorico != null) || (this.cdHistorico != null && !this.cdHistorico.equals(other.cdHistorico))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.HistoricoCotacao[ cdHistorico=" + cdHistorico + " ]";
    }
    
}
