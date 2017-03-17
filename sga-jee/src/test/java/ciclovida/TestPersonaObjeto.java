package ciclovida;

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

public class TestPersonaObjeto {

	static EntityManager em = null;
	static EntityTransaction tx = null;
	static EntityManagerFactory emf = null;
	static Logger log = Logger.getLogger("TestEncontarObjeto");
	static EJBContainer ejbContainer;

	@BeforeClass
	public static void init() throws Exception {
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
	public void testActualizarObjeto() {

		// Paso 1. Inicia transaccion 1
		EntityTransaction tx1 = em.getTransaction();
		tx1.begin();

		// Paso 2. Ejecuta SQL de tipo select
		Persona persona1 = em.find(Persona.class, 23);

		// Paso 3. Termina transaccion 1
		tx1.commit();

		// Objeto en estado detached
		log.debug("testActualizarObjeto Objeto recuperado:" + persona1);

		// Paso 4. setValue (nuevoValor)
		persona1.setApeMaterno("Nava");

		// Paso 5. Incia transaccion 2
		EntityTransaction tx2 = em.getTransaction();
		tx2.begin();

		// Paso 6. Ejecuta SQL (es un select, pero al estar modificado, al
		// terminar la transaccion hara un update)
		// Como ya tenemos el objeto hacemos solo un merge para resincronizar
		em.merge(persona1);

		// Paso 7. Termina transaccion 2
		// Al momento de hacer commit, se revisan las diferencias entre el
		// objeto de la base de datos
		// y el objeto en memoria, y se aplican los cambios si los hubiese
		tx2.commit();

		// Objeto en estado detached ya modificado
		log.debug("testActualizarObjeto Objeto modificado:" + persona1);
	}

	//@Test
	public void testActualizarObjetoSesionLarga() {

		// Paso 1. Inicia transaccion 1
		EntityTransaction tx3 = em.getTransaction();
		tx3.begin();

		// Paso 2. Ejecuta SQL de tipo select
		// Puede ser un find o un merge si ya tenemos el objeto
		Persona persona1 = em.find(Persona.class, 23);

		// Paso 3. setValue (nuevoValor)
		persona1.setApeMaterno("Aguirre");

		persona1.setApeMaterno("Torres");

		// Paso 4. Termina transaccion 1
		// Ejecuta el update, aunque hayamos hecho 2 cambios sobre el objeto
		// unicamente persiste el ultimo cambio del objeto
		// es decir, el valor de apeMaterno=Torres
		tx3.commit();

		// Objeto en estado detached
		log.debug("testActualizarObjetoSesionLarga Objeto recuperado:" + persona1);

	}

	//@Test
	public void testEncontrarObjeto() {

		// Paso 1. Inicia transaccion
		EntityTransaction tx4 = em.getTransaction();
		tx4.begin();

		// Paso 2. Ejecuta SQL de tipo select
		Persona persona1 = em.find(Persona.class, 23);

		// Paso 3. Termina transaccion
		tx4.commit();

		// Objeto en estado detached
		log.debug("testEncontrarObjeto Objeto recuperado:" + persona1);
	}

	//@Test
	public void eliminarObjetoSesionLarga() {

		// Paso 1. Inicia transaccion 1
		EntityTransaction tx5 = em.getTransaction();
		tx5.begin();

		// Paso 2. Ejecuta SQL de tipo select
		Persona persona1 = em.find(Persona.class, 23);

		// Paso 3. Termina transaccion 1
		tx5.commit();

		// Objeto en estado detached
		log.debug("eliminarObjetoSesionLarga Objeto recuperado:" + persona1);

		// Paso 4. Incia transaccion 2
		EntityTransaction tx2 = em.getTransaction();
		tx2.begin();

		// Paso 5. Ejecuta SQL (es un delete)
		em.remove(persona1);

		// Paso 6. Termina transaccion 2
		// Al momento de hacer commit,
		// se realiza el delete
		tx5.commit();

		// Objeto en estado detached ya modificado
		// Ya no es posible resincronizarlo en otra transaccion
		// Solo esta en memoria, pero al terminar el metodo se eliminara
		log.debug("eliminarObjetoSesionLarga Objeto eliminado:" + persona1);
	}

	//@Test
	public void testPersistirObjeto() {

		// Paso 1. Crea nuevo objeto
		// Objeto en estado transitivo
		Persona persona1 = new Persona(23, "Pedro", "Luna", null, "pluna@mail.com", "19292943");

		// Paso 2. Inicia transaccion
		EntityTransaction tx6 = em.getTransaction();
		tx6.begin();

		// Paso 3. Ejecuta SQL de tipo insert
		em.persist(persona1);

		// Paso 4. Commit/rollback
		tx6.commit();

		// Objeto en estado detached
		log.debug("testPersistirObjeto Objeto persistido:" + persona1);
	}

	@After
	public void tearDown() throws Exception {
		if (em != null) {
			em.close();
			log.debug("Entity manager closed");
			;
		}
	}

	@AfterClass
	public static void end() throws Exception {
		ejbContainer.close();
		log.debug("ejbContainer closed");
	}

}
