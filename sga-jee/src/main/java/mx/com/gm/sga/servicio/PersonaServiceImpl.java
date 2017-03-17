package mx.com.gm.sga.servicio;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.eis.PersonaDao;

@Stateless
public class PersonaServiceImpl implements PersonaServiceRemote, PersonaService {
	// Llamamos al contexto si necesitamos hacer operaciones del tipo rollback
	// con @Resource ya se le indica que recupere el contexto comun a toda la
	// aplicacion. Busca el sessionContext y nos inyecta una referencia de ese tipo
	@Resource
	private SessionContext contexto;

	// esto es para inyeccion de dependencia. Con EJB no se debe de hacer new
	// Persona()
	@EJB(beanName = "personaBean")
	private PersonaDao personaDao;

	public List<Persona> listarPersonas() {
		return personaDao.findAllPersonas();
	}

	public Persona encontrarPersonaPorId(Persona persona) {
		return personaDao.findPersonaById(persona);
	}

	public Persona econtrarPersonaPorEmail(Persona persona) {
		return personaDao.findPersonaByEmail(persona);
	}

	public void registrarPersona(Persona persona) {
		personaDao.insertPersona(persona);
	}

	public void modificarPersona(Persona persona) {
		try {
			personaDao.updatePersona(persona);
		} catch (Throwable t) {
			contexto.setRollbackOnly();
		}
	}

	public void eliminarPersona(Persona persona) {
		personaDao.deletePersona(persona);
	}

	public Persona encontrarPersonaPorEmail(Persona persona) {
		// TODO Auto-generated method stub
		return null;
	}

	public void modificarPernona(Persona persona) {
		// TODO Auto-generated method stub

	}
}
