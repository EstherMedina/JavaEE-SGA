package testclientews;

import java.util.List;
import clientesga.ws.Persona;
import clientesga.ws.PersonaServiceImplService;
import clientesga.ws.PersonaServiceWS;

public class TestPersonaServiceWS {
	public static void main(String[] args) {
		PersonaServiceWS personaService = new PersonaServiceImplService().getPersonaServiceImplPort();
		System.out.println("Ejecutando Servicio Listar Personas WS");
		List<Persona> personas = personaService.listarPersonas();
		for (Persona persona : personas) {
			System.out.println("Persona: " + persona.getNombre() + " " + persona.getApePaterno());
		}
		System.out.println("Fin Servicio Listar Personas WS");
	}
}