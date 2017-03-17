package test;

import static org.junit.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import javax.ejb.embeddable.EJBContainer;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import mx.com.gm.sga.domain.Persona;

public class TestEntidadPersona {
	static EntityManager em = null;
	static EntityTransaction tx = null;
	static EntityManagerFactory emf = null;
	static Logger log = Logger.getLogger("TestEntidadPersona");
	static EJBContainer ejbContainer;

	@BeforeClass
	public static void init() throws Exception {
		//dejo un ratito para que de tiempo a  q en el anterior test se cierre la conexion
		TimeUnit.SECONDS.sleep(10);
		ejbContainer = EJBContainer.createEJBContainer();
		emf = Persistence.createEntityManagerFactory("PersonaPU");
	}

	@Before
	public void setup() {
		try {
			em = emf.createEntityManager();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	//@Test
	public void testPersonaEntity() {
		System.out.println("Iniciando test Persona Entity con JPA");
		assertTrue(em != null);
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		Persona persona1 = new Persona("Angelica", "Lara", "Gomez", "alara@mail.com3", "1314145519");
		log.debug("Objeto a persistir:" + persona1);
		em.persist(persona1);
		// assertTrue(persona1.getIdPersona() == null);

		tx.commit();
		log.debug("Objeto persistido:" + persona1);
		System.out.println("Fin test Persona Entity con JPA");
	}

	@After
	public void tearDown() throws Exception {
		if (em != null) {
			em.close();
		}
	}

	@AfterClass
	public static void end() throws Exception {
		ejbContainer.close();
		log.debug("ejbContainer closed");
	}
}
