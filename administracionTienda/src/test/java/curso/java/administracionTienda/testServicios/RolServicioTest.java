package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.OpcionMenu;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.modelos.OpcionMenuRepositorio;
import curso.java.administracionTienda.modelos.RolRepositorio;
import curso.java.administracionTienda.servicios.OpcionMenuServicio;
import curso.java.administracionTienda.servicios.RolServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RolServicioTest {

	@Autowired
	private RolServicio rs;
	
	@MockBean
	private RolRepositorio rr;
	
	private static ArrayList<Rol> roles = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		roles.add(new Rol(1, "Rol 1"));
		roles.add(new Rol(2, "Rol 2"));
		roles.add(new Rol(3, "Rol 3"));
	}
	
	@Test
	public void obtenerRol1Test() {
		Rol rol = roles.get(0);
		when(rr.findById(1)).thenReturn(Optional.of(rol));
		Rol rol2= rs.obtenerRol(1);
		assertNotNull(rol2);
		assertEquals(rol, rol2);
	}
	
	@Test
	public void obtenerRol2Test() {
		Rol rol = roles.get(0);
		when(rr.findByRol("Rol 1")).thenReturn(rol);
		Rol rol2= rs.obtenerRol("Rol 1");
		assertNotNull(rol2);
		assertEquals(rol, rol2);
	}
	
}
