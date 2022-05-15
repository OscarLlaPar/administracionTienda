package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.entidades.Descuento;
import curso.java.administracionTienda.modelos.CategoriaRepositorio;
import curso.java.administracionTienda.modelos.DescuentoRepositorio;
import curso.java.administracionTienda.servicios.CategoriaServicio;
import curso.java.administracionTienda.servicios.DescuentoServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DescuentoServicioTest {
	@Autowired
	private DescuentoServicio ds;
	
	@MockBean
	private DescuentoRepositorio dr;
	
	private static ArrayList<Descuento> descuentos = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		descuentos.add(new Descuento(1, "codigo1",10,null,null));
		descuentos.add(new Descuento(2, "codigo2",10,null,null));
		descuentos.add(new Descuento(3, "codigo3",10,null,null));
	}
	
	@Test
	public void findAllTest() {
		when(dr.findAll()).thenReturn(descuentos);
		assertNotNull(ds.findAll());
		assertTrue(ds.findAll().size()>0);
	}
	
	@Test
	public void guardarescuentoTest() {
		Descuento d=new Descuento(4, "codigo4",10,null,null);
		when(dr.save(d)).thenReturn(d);
		Descuento insertado=ds.guardarDescuento(d);
		assertNotNull(insertado);
		assertEquals(insertado.getCodigo(),"codigo4");
		
	}
}
