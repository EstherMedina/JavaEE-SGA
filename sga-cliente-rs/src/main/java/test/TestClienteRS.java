package test;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import domain.Persona;

public class TestClienteRS {
	public static void main(String[] args) {
		Client client = Client.create();

		// Recuperar una persona
		WebResource web = client.resource("http://localhost:8080/sistema-sga/webservice/personas/1");
		Persona persona = web.get(Persona.class);
		System.out.println("La persona es: " + persona.getNombre() + " " + persona.getApePaterno());
		System.out.println();

		// Agregar una persona
		web = client.resource("http://localhost:8080/sistema-sga/webservice/personas");

		Persona nuevaPersona = new Persona();
		nuevaPersona.setNombre("Ricardo");
		nuevaPersona.setApePaterno("Gonzalez");
		nuevaPersona.setEmail("rgonzalezxxx@mail.com");
		ClientResponse response = web.post(ClientResponse.class, nuevaPersona);
		System.out.println("El código de respuesta en la inserción fue: " + response.getStatus());
		if (response.getStatus() == 200) {

			Persona per = response.getEntity(Persona.class);
			System.out.println("Nueva persona: " + per.getIdPersona() + " " + per.getNombre());
		}
		System.out.println();
	}
}