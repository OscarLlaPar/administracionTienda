package curso.java.administracionTienda;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

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

//@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class AdministracionTiendaApplicationTests {

	@Autowired
	private UsuarioControlador uc;
	
	/*@Test
	void contextLoads() {
		assertThat(uc).isNotNull();
		
	}*/

	
	
	/*@BeforeAll
	public static void setUp() {
		System.out.println("Antes de todos");
	}
	
	
	
	@AfterAll
	public static void tearDown() {
		System.out.println("Después de todos");
	}
	
	@RepeatedTest(value=3, name="Prueba {currentRepetition}/{totalRepetitions}")
	public void saludo() {
		System.out.println("Hola");
		assertNotNull("Hola");
	}*/
	
	@Test
	@DisplayName(value="Prueba Windows")
	@EnabledOnOs(value=OS.WINDOWS)
	public void saludo() {
		System.out.println("Hola");
		assertNotNull("Hola");
	}
	
	@Test
	@DisplayName(value="Prueba Mac")
	@EnabledOnOs(value=OS.MAC)
	public void saludo2() {
		System.out.println("Hola");
		assertNotNull("Hola");
	}
	
	
	/*@ParameterizedTest
	@ValueSource(ints= {2,4,6,8})
	public void numeros(int numero) {
		assertEquals(numero%2,0);
	}*/
	
	
	
}
