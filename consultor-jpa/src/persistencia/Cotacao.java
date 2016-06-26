package persistencia;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "acao_cotacao")
@NamedQueries({
    @NamedQuery(name = "Cotacao.findAll", query = "SELECT a FROM Cotacao a"),
    @NamedQuery(name = "Cotacao.findByDsCodigo", query = "SELECT a FROM Cotacao a WHERE a.dsCodigo = :dsCodigo")})
public class Cotacao implements Serializable{

	private static final long serialVersionUID = 1L;
	
	 @Id
	 @Basic(optional = false)
	 @Column(name = "ds_codigo")
	 private String dsCodigo;
	 
	 @Column(name = "dt_dia")
	 private Date dtDia;

	 @Column(name = "cd_hora")
	 private Integer cdHora;
	 
	 @Column(name = "vl_abertura")
	 private BigDecimal vlAbertura;

	 @Column(name = "vl_preco")
	 private BigDecimal vlPreco;
	 
	 @Column(name = "vl_percentual")
	 private BigDecimal vlPercentual;

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

	public BigDecimal getVlAbertura() {
		return vlAbertura;
	}

	public void setVlAbertura(BigDecimal vlAbertura) {
		this.vlAbertura = vlAbertura;
	}

	public BigDecimal getVlPreco() {
		return vlPreco;
	}

	public void setVlPreco(BigDecimal vlPreco) {
		this.vlPreco = vlPreco;
	}

	public BigDecimal getVlPercentual() {
		return vlPercentual;
	}

	public void setVlPercentual(BigDecimal vlPercentual) {
		this.vlPercentual = vlPercentual;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cdHora == null) ? 0 : cdHora.hashCode());
		result = prime * result
				+ ((dsCodigo == null) ? 0 : dsCodigo.hashCode());
		result = prime * result + ((dtDia == null) ? 0 : dtDia.hashCode());
		result = prime * result
				+ ((vlAbertura == null) ? 0 : vlAbertura.hashCode());
		result = prime * result
				+ ((vlPercentual == null) ? 0 : vlPercentual.hashCode());
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
		Cotacao other = (Cotacao) obj;
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
		if (vlAbertura == null) {
			if (other.vlAbertura != null)
				return false;
		} else if (!vlAbertura.equals(other.vlAbertura))
			return false;
		if (vlPercentual == null) {
			if (other.vlPercentual != null)
				return false;
		} else if (!vlPercentual.equals(other.vlPercentual))
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
		return "Cotacao [dsCodigo=" + dsCodigo + ", dtDia=" + dtDia
				+ ", cdHora=" + cdHora + ", vlPreco=" + vlPreco + "]";
	}
	
}
