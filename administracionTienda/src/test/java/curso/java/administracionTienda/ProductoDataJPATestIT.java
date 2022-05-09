package curso.java.administracionTienda;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.modelos.ProductoRepositorio;

@DataJpaTest
public class ProductoDataJPATestIT {
	
	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private ProductoRepositorio pr;
	
	@Test
	public void buscarProductoPorIdDevolverLibro() {
		
		Producto p=pr.findById(1).orElseThrow();
		
		assertThat(p.getNombre()).isEqualTo("Guitarra");
		
	}
	
	
}
