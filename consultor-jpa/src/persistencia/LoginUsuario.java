package persistencia;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "login_usuario")
@NamedQueries({
    @NamedQuery(name = "LoginUsuario.findAll", query = "SELECT l FROM LoginUsuario l"),
    @NamedQuery(name = "LoginUsuario.findByDsUserNameAndDsPassword", query = "SELECT l FROM LoginUsuario l WHERE l.dsUserName = :dsUserName and l.dsPassword = :dsPassword"),
    @NamedQuery(name = "LoginUsuario.findByCdUsuario", query = "SELECT l FROM LoginUsuario l WHERE l.cdUsuario = :cdUsuario")})
public class LoginUsuario implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @JoinColumn(name = "cd_usuario", referencedColumnName = "cd_usuario")
    @OneToOne
    private Usuario cdUsuario;
    
    @Column(name = "ds_username")
    private String dsUserName;
    
    @Column(name = "ds_password")
    private String dsPassword;
    
    public LoginUsuario() {
	}

	public Usuario getCdUsuario() {
		return cdUsuario;
	}

	public void setCdUsuario(Usuario cdUsuario) {
		this.cdUsuario = cdUsuario;
	}

	public String getDsUserName() {
		return dsUserName;
	}

	public void setDsUserName(String dsUserName) {
		this.dsUserName = dsUserName;
	}

	public String getDsPassword() {
		return dsPassword;
	}

	public void setDsPassword(String dsPassword) {
		this.dsPassword = dsPassword;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((cdUsuario == null) ? 0 : cdUsuario.hashCode());
		result = prime * result
				+ ((dsPassword == null) ? 0 : dsPassword.hashCode());
		result = prime * result
				+ ((dsUserName == null) ? 0 : dsUserName.hashCode());
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
		LoginUsuario other = (LoginUsuario) obj;
		if (cdUsuario == null) {
			if (other.cdUsuario != null)
				return false;
		} else if (!cdUsuario.equals(other.cdUsuario))
			return false;
		if (dsPassword == null) {
			if (other.dsPassword != null)
				return false;
		} else if (!dsPassword.equals(other.dsPassword))
			return false;
		if (dsUserName == null) {
			if (other.dsUserName != null)
				return false;
		} else if (!dsUserName.equals(other.dsUserName))
			return false;
		return true;
	}
    
    

}
