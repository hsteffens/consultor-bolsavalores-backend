package persistencia;

import java.io.Serializable;
import java.math.BigInteger;
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
@Table(name = "usuario")
@NamedQueries({
    @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u"),
    @NamedQuery(name = "Usuario.findByCdUsuario", query = "SELECT u FROM Usuario u WHERE u.cdUsuario = :cdUsuario"),
    @NamedQuery(name = "Usuario.findByCdCpf", query = "SELECT u FROM Usuario u WHERE u.cdCpf = :cdCpf"),
    @NamedQuery(name = "Usuario.findByDsNome", query = "SELECT u FROM Usuario u WHERE u.dsNome = :dsNome"),
    @NamedQuery(name = "Usuario.findByDsProfissao", query = "SELECT u FROM Usuario u WHERE u.dsProfissao = :dsProfissao"),
    @NamedQuery(name = "Usuario.findByDsEmail", query = "SELECT u FROM Usuario u WHERE u.dsEmail = :dsEmail")})
public class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "cd_usuario")
    private Integer cdUsuario;
    
    @Column(name = "cd_cpf")
    private BigInteger cdCpf;
    
    @Column(name = "ds_nome")
    private String dsNome;
    
    @Column(name = "ds_profissao")
    private String dsProfissao;
    
    @Column(name = "ds_email")
    private String dsEmail;
    
    @OneToMany(mappedBy = "cdUsuario")
    private Collection<CarteiraCliente> carteiraClienteCollection;
    
    @OneToMany(mappedBy = "cdUsuario")
    private Collection<SugestaoCompra> sugestaoCompraCollection;
    
    @OneToMany(mappedBy = "cdUsuario")
    private Collection<SugestaoVenda> sugestaoVendaCollection;
    
    @JoinColumn(name = "cd_investidor", referencedColumnName = "cd_investidor")
    @ManyToOne
    private TipoInvestidor cdInvestidor;
    
    @JoinColumn(name = "cd_transacao", referencedColumnName = "cd_transacao")
    @ManyToOne
    private TipoTransacao cdTransacao;

    public Usuario() {
    }

    public Usuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public Integer getCdUsuario() {
        return cdUsuario;
    }

    public void setCdUsuario(Integer cdUsuario) {
        this.cdUsuario = cdUsuario;
    }

    public BigInteger getCdCpf() {
        return cdCpf;
    }

    public void setCdCpf(BigInteger cdCpf) {
        this.cdCpf = cdCpf;
    }

    public String getDsNome() {
        return dsNome;
    }

    public void setDsNome(String dsNome) {
        this.dsNome = dsNome;
    }

    public String getDsProfissao() {
        return dsProfissao;
    }

    public void setDsProfissao(String dsProfissao) {
        this.dsProfissao = dsProfissao;
    }

    public String getDsEmail() {
        return dsEmail;
    }

    public void setDsEmail(String dsEmail) {
        this.dsEmail = dsEmail;
    }

    public Collection<CarteiraCliente> getCarteiraClienteCollection() {
        return carteiraClienteCollection;
    }

    public void setCarteiraClienteCollection(Collection<CarteiraCliente> carteiraClienteCollection) {
        this.carteiraClienteCollection = carteiraClienteCollection;
    }

    public Collection<SugestaoCompra> getSugestaoCompraCollection() {
        return sugestaoCompraCollection;
    }

    public void setSugestaoCompraCollection(Collection<SugestaoCompra> sugestaoCompraCollection) {
        this.sugestaoCompraCollection = sugestaoCompraCollection;
    }

    public Collection<SugestaoVenda> getSugestaoVendaCollection() {
        return sugestaoVendaCollection;
    }

    public void setSugestaoVendaCollection(Collection<SugestaoVenda> sugestaoVendaCollection) {
        this.sugestaoVendaCollection = sugestaoVendaCollection;
    }

    public TipoInvestidor getCdInvestidor() {
        return cdInvestidor;
    }

    public void setCdInvestidor(TipoInvestidor cdInvestidor) {
        this.cdInvestidor = cdInvestidor;
    }

    public TipoTransacao getCdTransacao() {
        return cdTransacao;
    }

    public void setCdTransacao(TipoTransacao cdTransacao) {
        this.cdTransacao = cdTransacao;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cdUsuario != null ? cdUsuario.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Usuario)) {
            return false;
        }
        Usuario other = (Usuario) object;
        if ((this.cdUsuario == null && other.cdUsuario != null) || (this.cdUsuario != null && !this.cdUsuario.equals(other.cdUsuario))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "persistencia.Usuario[ cdUsuario=" + cdUsuario + " ]";
    }
    
}
