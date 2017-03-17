package mx.com.gm.sga.eis;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import mx.com.gm.sga.domain.Persona;

//NOTA: aqu� no haremos tratamiento de excepciones, lo dejamos para capas superiores

@Stateless(name = "personaBean")
public class PersonaDaoImpl implements PersonaDao {
	@PersistenceContext(unitName = "PersonaPU")
	EntityManager em;

	@SuppressWarnings("unchecked")
	public List<Persona> findAllPersonas() {
		return em.createNamedQuery("Persona.findAll").getResultList();

	}

	public Persona findPersonaById(Persona persona) {
		return em.find(Persona.class, persona.getIdPersona());
	}

	public Persona findPersonaByEmail(Persona persona) {
		Query query = em.createQuery("from Persona p where p.email = :email");
		query.setParameter("email", persona.getEmail());
		return (Persona) query.getSingleResult();
	}

	public void insertPersona(Persona persona) {
		em.persist(persona);
	}

	public void updatePersona(Persona persona) {
		em.merge(persona);
	}

	public void deletePersona(Persona persona) {
		persona = em.find(Persona.class, persona.getIdPersona());
		em.remove(persona);
	}
}