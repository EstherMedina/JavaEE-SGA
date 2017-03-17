package mx.com.gm.sga.servicio;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.jws.WebService;

import mx.com.gm.sga.domain.Persona;
import mx.com.gm.sga.eis.PersonaDao;

@Stateless
@WebService(endpointInterface = "mx.com.gm.sga.servicio.PersonaServiceWS")
public class PersonaServiceImpl implements PersonaServiceRemote, PersonaService, PersonaServiceWS {

	@Resource
	private SessionContext contexto;

	@EJB
	private PersonaDao personaDao;

	@Override
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

}
