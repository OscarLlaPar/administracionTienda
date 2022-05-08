package curso.java.administracionTienda;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import curso.java.administracionTienda.controladores.UsuarioControlador;


/*
 * Clase por defecto del paquete de testing. Se le puede añadir un controlador
 * con la anotación @Autowired y una aserción para comprobar que el controlador se crea
 * al iniciar la aplicación.
 * 
 * La anotación @SpringBootTest permite que Spring Boot busque la clase main con la
 * anotación @SprinBootApplication, creada por defecto en STS. La utiliza para crear un
 * contexto de aplicación al ejecutarse.
 * 
 */

@SpringBootTest
class AdministracionTiendaApplicationTests {

	@Autowired
	private UsuarioControlador uc;
	
	@Test
	void contextLoads() {
		assertThat(uc).isNotNull();
	}

}
