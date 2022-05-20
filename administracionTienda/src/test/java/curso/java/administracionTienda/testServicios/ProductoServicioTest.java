package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.modelos.ProductoRepositorio;
import curso.java.administracionTienda.modelos.ProveedorRepositorio;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.servicios.ProveedorServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProductoServicioTest {
	
	@Autowired
	private ProductoServicio ps;
	
	@MockBean
	private ProductoRepositorio pr;
	
	private static ArrayList<Producto> productos = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		productos.add(new Producto(1,new Categoria(),"Producto 1", "Descripcion 1",12,12,new Timestamp(System.currentTimeMillis()),null,12,"/imagen1","/audio1",new Proveedor()));
		productos.add(new Producto(2,new Categoria(),"Producto 2", "Descripcion 2",12,12,new Timestamp(System.currentTimeMillis()),null,12,"/imagen2","/audio2",new Proveedor()));
		productos.add(new Producto(3,new Categoria(),"Producto 3", "Descripcion 3",12,12,new Timestamp(System.currentTimeMillis()),new Timestamp(System.currentTimeMillis()),12,"/imagen2","/audio2",new Proveedor()));
	}
	
	@Test
	public void obtenerProductosTest() {
		when(pr.findAll()).thenReturn(productos);
		List<Producto> lista=ps.obtenerProductos();
		
		assertNotNull(lista);
		assertTrue(lista.size()>0);
		assertTrue(lista.size()==3);
		assertEquals(lista.get(0),productos.get(0));
	}
	
	@Test
	public void obtenerProductoTest() {
		Producto p=productos.get(0);
		when(pr.findById(1)).thenReturn(Optional.of(p));
		Producto p2=ps.obtenerProducto(1);
		
		assertNotNull(p2);
		assertEquals(p, p2);
		assertTrue(p2.getId()==1);
		
	}
	
	@Test
	public void darDeBajaTest() {
		Producto p=productos.get(0);
		when(pr.findById(1)).thenReturn(Optional.of(p));
		when(pr.save(p)).thenReturn(p);
		
		Producto p2=ps.darDeBaja(p.getId());
		
		assertNotNull(p2);
		assertNotNull(p2.getFechaBaja());
		
	}
	
	@Test
	public void quitarBajaTest() {
		Producto p=productos.get(2);
		when(pr.findById(3)).thenReturn(Optional.of(p));
		when(pr.save(p)).thenReturn(p);
		
		Producto p2=ps.quitarBaja(p.getId());
		assertNotNull(p2);
		assertNull(p2.getFechaBaja());
	}
	
	@Test
	public void editarProductoTest() {
		Producto p=productos.get(0);
		p.setNombre("Producto editado");
		when(pr.save(p)).thenReturn(p);
		
		Producto p2=ps.editarProducto(p);
		
		assertNotNull(p2);
		assertEquals(p2.getNombre(),"Producto editado");
		
	}
	
	@Test
	public void guardarProductosTest() {
		ArrayList<Producto> lista=new ArrayList<>();
		lista.add(new Producto(4,new Categoria(),"Producto 4", "Descripcion 4",12,12,new Timestamp(System.currentTimeMillis()),null,12,"/imagen4","/audio4",new Proveedor()));
		lista.add(new Producto(5,new Categoria(),"Producto 5", "Descripcion 5",12,12,new Timestamp(System.currentTimeMillis()),null,12,"/imagen5","/audio5",new Proveedor()));
		lista.add(new Producto(6,new Categoria(),"Producto 6", "Descripcion 6",12,12,new Timestamp(System.currentTimeMillis()),null,12,"/imagen6","/audio6",new Proveedor()));
		
		when(pr.saveAll(lista)).thenReturn(lista);
		
		List<Producto> lista2=ps.guardarProductos(lista);
		
		assertNotNull(lista2);
		assertTrue(lista2.size()==3);
		assertEquals(lista.get(0),lista2.get(0));
		
	}
	
	@Test
	public void findByNombre() {
		ArrayList<Producto> lista=new ArrayList<>();
		lista.add(productos.get(0));
		when(pr.findByNombre("Producto 1")).thenReturn(lista);
		
		List<Producto> lista2=ps.findByNombre("Producto 1");
		
		assertNotNull(lista2);
		assertTrue(lista2.size()==1);
		assertEquals(lista.get(0),lista2.get(0));
		
	}
	
	
}
