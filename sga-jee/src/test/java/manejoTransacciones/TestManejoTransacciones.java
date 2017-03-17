package manejoTransacciones;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import javax.ejb.embeddable.EJBContainer;

import org.apache.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;

public class TestManejoTransacciones {

	private PersonaService personaService;
	Logger log = Logger.getLogger("TestManejoTransacciones");
	static EJBContainer contenedor;

	
	@BeforeClass
	public static void init() throws Exception {
		TimeUnit.SECONDS.sleep(10);
		contenedor = EJBContainer.createEJBContainer();
	}
	
	
	@Before
	public void setUp() throws Exception {

		personaService = (PersonaService) contenedor.getContext()
				.lookup("java:global/classes/PersonaServiceImpl!mx.com.gm.sga.servicio.PersonaService");
	}

	//@Test
	public void testEJBPersonaService() {
		log.debug("Iniciando test Manejo Transaccional PersonaService");

		assertTrue(personaService != null);

		// Buscamos un objeto persona
		Persona persona1 = personaService.encontrarPersonaPorId(new Persona(1));

		// Cambiamos la persona
		persona1.setApeMaterno("Cambio con error......................................................");
		// persona1.setApeMaterno("Cambio sin errror");

		this.personaService.modificarPernona(persona1);

		log.debug("Objeto modificado:" + persona1);

		log.debug("Fin test EJB PersonaService");
	}

	@AfterClass
	public static void end() throws Exception {
		contenedor.close();
	}

}
