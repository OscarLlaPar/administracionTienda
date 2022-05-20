package curso.java.administracionTienda.testModelos;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.modelos.OpcionMenuRepositorio;
import curso.java.administracionTienda.modelos.ProveedorRepositorio;

@DataJpaTest
public class ProveedorRepositorioTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ProveedorRepositorio pr;
	
	@Test
	public void findAllSinBajaTest() {
		Proveedor p1=new Proveedor(0, "Proveedor 1", "a","a","a","a","a","a",null);
		entityManager.persistAndFlush(p1);
		Proveedor p2=new Proveedor(0, "Proveedor 2", "a","a","a","a","a","a",new Timestamp(System.currentTimeMillis()));
		entityManager.persistAndFlush(p2);
		Proveedor p3=new Proveedor(0, "Proveedor 3", "a","a","a","a","a","a",null);
		entityManager.persistAndFlush(p3);
		
		List<Proveedor> lista=pr.findAllSinBaja();
		assertThat(lista).isNotNull();
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombre()).isEqualTo("Proveedor 1");
		assertThat(lista.get(1).getNombre()).isEqualTo("Proveedor 3");
	}
	
	@Test
	public void buscarProveedoresPorNombreTest() {
		Proveedor p1=new Proveedor(0, "asdasdfasdf", "a","a","a","a","a","a",null);
		entityManager.persistAndFlush(p1);
		Proveedor p2=new Proveedor(0, "Proveedor 2", "a","a","a","a","a","a",new Timestamp(System.currentTimeMillis()));
		entityManager.persistAndFlush(p2);
		Proveedor p3=new Proveedor(0, "Proveedor 3", "a","a","a","a","a","a",null);
		entityManager.persistAndFlush(p3);
		
		List<Proveedor> lista=pr.buscarProveedoresPorNombre("Proveedor");
		assertThat(lista).isNotNull();
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombre()).isEqualTo("Proveedor 2");
		assertThat(lista.get(1).getNombre()).isEqualTo("Proveedor 3");
	}
	
}
