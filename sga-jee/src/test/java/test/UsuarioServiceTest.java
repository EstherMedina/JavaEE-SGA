package test;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.embeddable.EJBContainer;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mx.com.gm.sga.domain.Usuario;
import mx.com.gm.sga.servicio.UsuarioService;

public class UsuarioServiceTest {

	private UsuarioService usuarioService;
	private static EJBContainer contenedor;

	@BeforeClass
	public static void init() throws Exception {
		TimeUnit.SECONDS.sleep(10);
		contenedor = EJBContainer.createEJBContainer();
	}

	@Before
	public void setUp() throws Exception {
		usuarioService = (UsuarioService) contenedor.getContext()
				.lookup("java:global/classes/UsuarioServiceImpl!mx.com.gm.sga.servicio.UsuarioService");
	}

	//@Test
	public void testEJBUsuarioService() {
		System.out.println("Iniciando test EJB UsuarioService");

		assertTrue(usuarioService != null);

		// assertEquals(3, usuarioService.listarUsuarios().size());
		System.out.println("El no. de usuarios es igual a:" + usuarioService.listarUsuarios().size());

		this.desplegarUsuarios(usuarioService.listarUsuarios());

		System.out.println("Fin test EJB UsuarioService");
	}

	private void desplegarUsuarios(List<Usuario> usuarios) {
		for (Usuario usuario : usuarios) {
			System.out.println(usuario);
		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("Finalizando el contenedor");

		contenedor.close();

	}
}
