package curso.java.administracionTienda;



import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioRestIT {

	@LocalServerPort
    private int port;
	
	TestRestTemplate restTemplate = new TestRestTemplate();
	
	HttpHeaders headers = new HttpHeaders();
	
	
	@Test
	public void testBuscarUsuario() throws Exception {
		 HttpEntity<String> entity = new HttpEntity<String>(null, headers);
		 
		 ResponseEntity<String> response = restTemplate.exchange(
				 createURLWithPort("/api/usuarios?nombre=Admin"), HttpMethod.GET, entity, String.class);
		 
		 String actual = response.getHeaders().get(HttpHeaders.LOCATION).get(0);
		 
		 assertTrue(actual.contains("/api/usuarios?nombre=Admin"));
		 
	}
	
	private String createURLWithPort(String uri) {
		return "http://localhost:" + port + uri;
	}
	
}
