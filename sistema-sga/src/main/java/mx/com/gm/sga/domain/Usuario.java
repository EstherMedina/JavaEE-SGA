package mx.com.gm.sga.domain;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the usuario database table.
 * 
 */
@Entity
@NamedQueries( { @NamedQuery(name = "Usuario.findAll", query = "SELECT u FROM Usuario u ORDER BY u.idUsuario") })
public class Usuario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_usuario")
	private int idUsuario;

	private String username;
	
	private String password;

	//bi-directional many-to-one association to Persona
	@ManyToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="id_persona")
	private Persona persona;

    public Usuario() {
    }
    
    public Usuario(int idUsuario) {
    	this.idUsuario = idUsuario;
    }

	public Usuario(int idUsuario, String password, String username,
			Persona persona) {
		this.idUsuario = idUsuario;
		this.username = username;
		this.password = password;
		this.persona = persona;
	}
	
	public Usuario(String username, String password,
			Persona persona) {
		this.username = username;
		this.password = password;
		this.persona = persona;
	}
	
	public Usuario(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public int getIdUsuario() {
		return this.idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Persona getPersona() {
		return this.persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + idUsuario;
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((persona == null) ? 0 : persona.hashCode());
		result = prime * result
				+ ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public String toString() {
		return "Usuario [idUsuario=" + idUsuario 
				+ ", username=" + username
				+ ", password=" + password
				+ ", persona=" + persona + "]";
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		if (idUsuario != other.idUsuario)
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (persona == null) {
			if (other.persona != null)
				return false;
		} else if (!persona.equals(other.persona))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}
	
	
}