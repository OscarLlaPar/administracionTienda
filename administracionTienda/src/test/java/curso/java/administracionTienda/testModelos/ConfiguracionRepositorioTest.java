package curso.java.administracionTienda.testModelos;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.modelos.ConfiguracionRepositorio;

@DataJpaTest
public class ConfiguracionRepositorioTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ConfiguracionRepositorio cr;
	
	
	@Test
	public void findByClaveTest() {
		Configuracion config=new Configuracion(0,"numFacturas","1","Numero");
		
		entityManager.persist(config);
		entityManager.flush();
		
		Configuracion c=cr.findByClave("numFacturas");
		assertThat(c.getId()).isEqualTo(1);
		assertThat(c.getClave()).isEqualTo("numFacturas");
		assertThat(c.getValor()).isEqualTo("1");
		assertThat(c.getTipo()).isEqualTo("Numero");
	}
}
