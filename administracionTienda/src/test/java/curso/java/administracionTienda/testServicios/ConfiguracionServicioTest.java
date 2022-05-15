package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.modelos.CategoriaRepositorio;
import curso.java.administracionTienda.modelos.ConfiguracionRepositorio;
import curso.java.administracionTienda.servicios.CategoriaServicio;
import curso.java.administracionTienda.servicios.ConfiguracionServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ConfiguracionServicioTest {
 
	@Autowired
	private ConfiguracionServicio cs;
	
	@MockBean
	private ConfiguracionRepositorio cr;
	
	private static ArrayList<Configuracion> config = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		config.add(new Configuracion(1, "Config1", "Valor1", "Texto"));
		config.add(new Configuracion(2, "Config2", "Valor2", "Texto"));
		config.add(new Configuracion(3, "Config3", "Valor3", "Texto"));
		config.add(new Configuracion(4, "numFacturas", "0", "Numero"));
	}
	
	@Test
	public void obtenerConfiguracionTest() {
		Configuracion c=config.get(0);
		when(cr.findByClave("Config1")).thenReturn(c);
		
		Configuracion c2=cs.obtenerConfiguracion("Config1");
		assertNotNull(c2);
		assertEquals(c2.getClave(), "Config1");
	}
	
	@Test
	public void obetenerTodaLaConfiguracionTest() {
		when(cr.findAll()).thenReturn(config);
		assertNotNull(cs.obtenerTodaLaConfiguracion());
		assertTrue(cs.obtenerTodaLaConfiguracion().size()>0);
	}
	
	@Test
	public void guardarConfiguracionTest() {
		Configuracion nueva=new Configuracion(5, "Config5", "Valor5", "Texto");
		when(cr.save(nueva)).thenReturn(nueva);
		
		when(cr.findAll()).thenReturn(config);
		Configuracion insertada = cs.guardarConfiguracion(nueva);
		assertNotNull(insertada);
		assertEquals(insertada.getClave(),"Config5");
	}
	
	@Test
	public void actualizarNumFacturasTest() {
		when(cr.findByClave("numFacturas")).thenReturn(config.get(3));
		assertEquals(cs.actualizarNumFacturas().getValor(), "1");
	}
	
	
}
