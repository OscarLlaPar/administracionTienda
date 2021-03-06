package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.entidades.OpcionMenu;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.modelos.OpcionMenuRepositorio;
import curso.java.administracionTienda.servicios.OpcionMenuServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OpcionMenuServicioTest {
	
	@Autowired
	private OpcionMenuServicio oms;
	
	@MockBean
	private OpcionMenuRepositorio omr;
	
	private static ArrayList<OpcionMenu> opciones = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		opciones.add(new OpcionMenu(1, new Rol(2,"Empleado"), "Opcion 1", "/opcion1"));
		opciones.add(new OpcionMenu(2, new Rol(3,"Administrador"), "Opcion 2", "/opcion2"));
		opciones.add(new OpcionMenu(3, new Rol(3,"Administrador"), "Opcion 3", "/opcion3"));
	}
	
	@Test
	public void findAllTest() {
		opciones.remove(0);
		when(omr.findAllByRol("Administrador")).thenReturn(opciones);
		List<OpcionMenu> menu = oms.findAll("Administrador");
		assertNotNull(menu);
		assertTrue(menu.size()==2);
		assertEquals(menu.get(0).getRol().getRol(),"Administrador");
		assertEquals(menu.get(1).getRol().getRol(),"Administrador");
	}
	
	
}
