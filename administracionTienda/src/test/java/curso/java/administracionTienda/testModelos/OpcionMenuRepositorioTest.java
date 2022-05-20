package curso.java.administracionTienda.testModelos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import curso.java.administracionTienda.entidades.OpcionMenu;
import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.modelos.OpcionMenuRepositorio;
import curso.java.administracionTienda.modelos.ProductoRepositorio;

@DataJpaTest
public class OpcionMenuRepositorioTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private OpcionMenuRepositorio omr;
	
	@Test
	public void findAllByRolTest() {
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		Rol empleado= new Rol(0,"Empleado");
		entityManager.persistAndFlush(empleado);
		Rol administrador=new Rol(0,"Administrador");
		entityManager.persistAndFlush(administrador);
		
		OpcionMenu om1=new OpcionMenu(0,cliente,"Opcion 1", "/opcion1");
		entityManager.persistAndFlush(om1);
		OpcionMenu om2=new OpcionMenu(0,empleado,"Opcion 2", "/opcion2");
		entityManager.persistAndFlush(om2);
		OpcionMenu om3=new OpcionMenu(0,empleado,"Opcion 3", "/opcion3");
		entityManager.persistAndFlush(om3);
		
		List<OpcionMenu> lista=omr.findAllByRol("Empleado");
		assertThat(lista).isNotNull();
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombreOpcion()).isEqualTo("Opcion 2");
		assertThat(lista.get(1).getNombreOpcion()).isEqualTo("Opcion 3");
	}
}
