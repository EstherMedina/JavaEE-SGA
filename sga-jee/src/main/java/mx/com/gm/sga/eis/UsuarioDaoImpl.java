package mx.com.gm.sga.eis;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import mx.com.gm.sga.domain.Usuario;

@Stateless(name = "usuarioBean")
public class UsuarioDaoImpl implements UsuarioDao {

	@PersistenceContext(unitName = "PersonaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios() {
		return em.createNamedQuery("Usuario.findAll").getResultList();
	}

	public Usuario findUsuarioById(Usuario usuario) {
		return em.find(Usuario.class, usuario.getIdUsuario());
	}

	public void insertUsuario(Usuario usuario) {
		em.persist(usuario);
	}

	public void updateUsuario(Usuario usuario) {
		em.merge(usuario);
	}

	public void deleteUsuario(Usuario usuario) {
		em.merge(usuario);
		em.remove(usuario);
	}

}
