package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.entidades.Valoracion;
import curso.java.administracionTienda.modelos.RolRepositorio;
import curso.java.administracionTienda.modelos.ValoracionRepositorio;
import curso.java.administracionTienda.servicios.RolServicio;
import curso.java.administracionTienda.servicios.ValoracionServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ValoracionServicioTest {
	@Autowired
	private ValoracionServicio vs;
	
	@MockBean
	private ValoracionRepositorio vr;
	
	private static ArrayList<Valoracion> valoraciones = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		valoraciones.add(new Valoracion(1, new Producto(), new Usuario(), 10, "Valoracion 1"));
		valoraciones.add(new Valoracion(2, new Producto(), new Usuario(), 10, "Valoracion 2"));
		valoraciones.add(new Valoracion(3, new Producto(), new Usuario(), 10, "Valoracion 3"));
	}
	
	@Test
	public void obtenerValoracionesTest() {
		when(vr.findAll()).thenReturn(valoraciones);
		List<Valoracion> lista=vs.obtenerValoraciones();
		assertNotNull(lista);
		assertTrue(lista.size()>0);
		assertEquals(lista.size(),3);
		assertEquals(valoraciones.get(0),lista.get(0));
	}
	
	@Test
	public void eliminarValoracionTest() {
		Valoracion v=valoraciones.get(0);
		
		vs.eliminarValoracion(v.getId());
		
		verify(vr,times(1)).deleteById(v.getId());
		
	}
	
	
}
