package mx.com.gm.sga.servicio;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import mx.com.gm.sga.domain.Usuario;
import mx.com.gm.sga.eis.UsuarioDao;

@Stateless
public class UsuarioServiceImpl implements UsuarioService {
	
	@EJB
	private UsuarioDao usuarioDao;

	public List<Usuario> listarUsuarios() {
		return usuarioDao.findAllUsuarios();
	}

	public Usuario encontrarUsuarioPorId(Usuario usuario) {
		return usuarioDao.findUsuarioById(usuario);
	}

	public void registrarUsuario(Usuario usuario) {
		usuarioDao.insertUsuario(usuario);
	}

	public void modificarUsuario(Usuario usuario) {
		usuarioDao.updateUsuario(usuario);
	}

	public void eliminarUsuario(Usuario usuario) {
		usuarioDao.deleteUsuario(usuario);
	}

}
