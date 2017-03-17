package mx.com.gm.sga.web;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.event.RowEditEvent;
import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.servicio.PersonaService;

@ManagedBean
@RequestScoped
public class PersonaBean {
	@EJB
	private PersonaService personaService;
	List<Persona> personas;

	public PersonaBean() {
	}

	@PostConstruct
	public void inicializar() {
		personas = personaService.listarPersonas();
	}

	public void editListener(RowEditEvent event) {
		Persona persona = (Persona) event.getObject();
		personaService.modificarPersona(persona);
	}

	public List<Persona> getPersonas() {
		return personas;
	}

	public void setPersonas(List<Persona> personas) {
		this.personas = personas;
	}
}