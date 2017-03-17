package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;

public class PersonaServiceTest {

	private static EJBContainer contenedor;
	private static Context contexto;
	private PersonaService personaService;

	@BeforeClass
	public static void init() throws Exception {

		TimeUnit.SECONDS.sleep(10);
		System.out.println("iniciando el contenedor");
		contenedor = EJBContainer.createEJBContainer();
	}

	@Before
	public void setUp() throws Exception {

		contexto = contenedor.getContext();
		personaService = (PersonaService) contexto
				.lookup("java:global/classes/PersonaServiceImpl!mx.com.gm.sga.servicio.PersonaService");
	}

	//@Test
	public void testEJBPersonaService() throws Exception {
		System.out.println("Iniciando test EJB PersonaService ");
		assertTrue(personaService != null);

		assertEquals(3, personaService.listarPersonas().size());
		System.out.println("El n�mero de personas es igual a: " + personaService.listarPersonas().size());

		this.desplegarPersonas(personaService.listarPersonas());
	}

	private void desplegarPersonas(List<Persona> listarPersonas) {
		for (Persona persona : listarPersonas) {
			System.out.println(persona);
		}
	}

	@AfterClass
	public static void tearDown() throws Exception {
		System.out.println("Finalizando el contenedor");

		contenedor.close();

	}

}
