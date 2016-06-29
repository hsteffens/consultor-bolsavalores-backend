package persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

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
    @NamedQuery(name = "HistoricoCotacao.findByKeys", query = "SELECT h FROM HistoricoCotacao h WHERE h.dsCodigo = :dsCodigo and h.dtDia = :dtDia and h.cdHora = :cdHora"),
    @NamedQuery(name = "HistoricoCotacao.findByDsCodigo", query = "SELECT h FROM HistoricoCotacao h WHERE h.dsCodigo = :dsCodigo")})
public class HistoricoCotacao implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "cd_historico_seq"))
    @Id
    @GeneratedValue(generator = "generator")
    @Basic(optional = false)
    @Column(name = "cd_historico")
    private Integer cdHistorico;
    
    @Column(name = "vl_preco")
    private BigDecimal vlPreco;
    
    @Column(name = "ds_codigo")
    private String dsCodigo;
    
    @Column(name = "dt_dia")
    private Date dtDia;

    @Column(name = "cd_hora")
    private Integer cdHora;

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

    public Date getDtDia() {
		return dtDia;
	}

	public void setDtDia(Date dtDia) {
		this.dtDia = dtDia;
	}

	public Integer getCdHora() {
		return cdHora;
	}

	public void setCdHora(Integer cdHora) {
		this.cdHora = cdHora;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdHistorico == null) ? 0 : cdHistorico.hashCode());
		result = prime * result + ((cdHora == null) ? 0 : cdHora.hashCode());
		result = prime * result
				+ ((dsCodigo == null) ? 0 : dsCodigo.hashCode());
		result = prime * result + ((dtDia == null) ? 0 : dtDia.hashCode());
		result = prime * result + ((vlPreco == null) ? 0 : vlPreco.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HistoricoCotacao other = (HistoricoCotacao) obj;
		if (cdHistorico == null) {
			if (other.cdHistorico != null)
				return false;
		} else if (!cdHistorico.equals(other.cdHistorico))
			return false;
		if (cdHora == null) {
			if (other.cdHora != null)
				return false;
		} else if (!cdHora.equals(other.cdHora))
			return false;
		if (dsCodigo == null) {
			if (other.dsCodigo != null)
				return false;
		} else if (!dsCodigo.equals(other.dsCodigo))
			return false;
		if (dtDia == null) {
			if (other.dtDia != null)
				return false;
		} else if (!dtDia.equals(other.dtDia))
			return false;
		if (vlPreco == null) {
			if (other.vlPreco != null)
				return false;
		} else if (!vlPreco.equals(other.vlPreco))
			return false;
		return true;
	}

	@Override
    public String toString() {
        return "persistencia.HistoricoCotacao[ cdHistorico=" + cdHistorico + " ]";
    }
    
}
