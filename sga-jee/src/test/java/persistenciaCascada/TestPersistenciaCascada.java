package persistenciaCascada;

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
import mx.com.gm.sga.domain.Usuario;

public class TestPersistenciaCascada {

	static EntityManager em = null;
	static EntityTransaction tx = null;
	static EntityManagerFactory emf = null;
	Logger log = Logger.getLogger("TestEncontarObjeto");
	static EJBContainer contenedor;

	
	@BeforeClass
	public static void init() throws Exception {
		TimeUnit.SECONDS.sleep(10);
		contenedor = EJBContainer.createEJBContainer();
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
	public void testPersistenciaCascada() {

		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();

		//Creamos el objeto persona (es el menos dependiente)
		Persona persona1 = new Persona("Hugo","Sanchez","Pinto","hsanchez@mail.com","09093090");
		
		//Creamos el objeto usuario (tiene dependencia con un objeto persona)
		Usuario usuario1 = new Usuario("hsanchez","123",persona1);
		
		//Solo Persistimos el objeto Usuario 
		//No hay necesidad de persistir el objeto Persona
		em.persist(usuario1);
		
		tx1.commit();
		
		//Ya debe tener el objeto persona y su PK asociada
		log.debug("Objeto recuperado:" + usuario1);
	}
	
	@After
    public void tearDown() throws Exception {
        if (em != null) {
            em.close();
        }
    }
	
	@AfterClass
	public static void end() throws Exception {
		contenedor.close();
	}

}
