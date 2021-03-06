package persistencia;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "carteira_cliente")
@NamedQueries({
    @NamedQuery(name = "CarteiraCliente.findAll", query = "SELECT c FROM CarteiraCliente c"),
    @NamedQuery(name = "CarteiraCliente.findByCdCarteira", query = "SELECT c FROM CarteiraCliente c WHERE c.cdCarteira = :cdCarteira"),
    @NamedQuery(name = "CarteiraCliente.findByUsuario", query = "SELECT c FROM CarteiraCliente c WHERE c.cdUsuario.cdUsuario = :cdUsuario"),
    @NamedQuery(name = "CarteiraCliente.findByAcaoAndUsuario", query = "SELECT c FROM CarteiraCliente c WHERE c.cdUsuario.cdUsuario = :cdUsuario and c.dsCodigo.dsCodigo = :dsCodigo"),
    @NamedQuery(name = "CarteiraCliente.findByVlQuantidade", query = "SELECT c FROM CarteiraCliente c WHERE c.vlQuantidade = :vlQuantidade")})
public class CarteiraCliente implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @GenericGenerator(name = "generator", strategy = "sequence-identity", parameters = @Parameter(name = "sequence", value = "cd_carteira_seq"))
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "cd_carteira")
    private Integer cdCarteira;
    
    @Column(name = "vl_preco")
    private BigDecimal vlPreco;

    @Column(name = "vl_quantidade")
    private Integer vlQuantidade;
    
    @JoinColumn(name = "ds_codigo", referencedColumnName = "ds_codigo")
    @ManyToOne
    private Acao dsCodigo;
    
    @JoinColumn(name = "cd_usuario", referencedColumnName = "cd_usuario")
    @ManyToOne
    private Usuario cdUsuario;

    public CarteiraCliente() {
    }

    public CarteiraCliente(Integer cdCarteira) {
        this.cdCarteira = cdCarteira;
    }

    public Integer getCdCarteira() {
        return cdCarteira;
    }

    public void setCdCarteira(Integer cdCarteira) {
        this.cdCarteira = cdCarteira;
    }

    public Integer getVlQuantidade() {
        return vlQuantidade;
    }

    public void setVlQuantidade(Integer vlQuantidade) {
        this.vlQuantidade = vlQuantidade;
    }

    public Acao getDsCodigo() {
        return dsCodigo;
    }

    public void setDsCodigo(Acao dsCodigo) {
        this.dsCodigo = dsCodigo;
    }

    public Usuario getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Usuario cdUsuario) {
        this.cdUsuario = cdUsuario;
    }
    
    public BigDecimal getVlPreco() {
		return vlPreco;
	}

	public void setVlPreco(BigDecimal vlPreco) {
		this.vlPreco = vlPreco;
	}

	@Override
    public int hashCode() {
        int hash = 0;
        hash += (cdCarteira != null ? cdCarteira.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof CarteiraCliente)) {
            return false;
        }
        CarteiraCliente other = (CarteiraCliente) object;
        if ((this.cdCarteira == null && other.cdCarteira != null) || (this.cdCarteira != null && !this.cdCarteira.equals(other.cdCarteira))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.CarteiraCliente[ cdCarteira=" + cdCarteira + " ]";
    }
    
}
