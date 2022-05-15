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

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.entidades.Valoracion;
import curso.java.administracionTienda.modelos.ProveedorRepositorio;
import curso.java.administracionTienda.modelos.ValoracionRepositorio;
import curso.java.administracionTienda.servicios.ProveedorServicio;
import curso.java.administracionTienda.servicios.ValoracionServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProveedorServicioTest {
	@Autowired
	private ProveedorServicio ps;
	
	@MockBean
	private ProveedorRepositorio pr;
	
	private static ArrayList<Proveedor> proveedores = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		proveedores.add(new Proveedor(1, "Proveedor 1","C/ Proveedor nº 1","Zamora","Zamora","611111111","A123456","proveedor1@mail.com",null));
		proveedores.add(new Proveedor(2, "Proveedor 2","C/ Proveedor nº 2","Zamora","Zamora","622222222","B123456","proveedor2@mail.com",null));
		proveedores.add(new Proveedor(3, "Proveedor 3","C/ Proveedor nº 3","Zamora","Zamora","633333333","C123456","proveedor3@mail.com",new Timestamp(System.currentTimeMillis())));
	}
	
	@Test
	public void findAllTest() {
		when(pr.findAll()).thenReturn(proveedores);
		List<Proveedor> lista=ps.findAll();
		assertNotNull(lista);
		assertTrue(lista.size()>0);
		assertTrue(lista.size()==3);
		assertEquals(lista.get(0), proveedores.get(0));
	}
	
	@Test
	public void guardarProveedorTest() {
		Proveedor p=new Proveedor(4, "Proveedor 4","C/ Proveedor nº 4","Zamora","Zamora","644444444","D123456","proveedor4@mail.com",null);
		when(pr.save(p)).thenReturn(p);
		Proveedor insertado = ps.guardarProveedor(p);
		assertNotNull(insertado);
		assertEquals(p.getNombre(),insertado.getNombre());
	}
	
	@Test
	public void bajaProveedorTest() {
		Proveedor p = proveedores.get(0);
		when(pr.findById(1)).thenReturn(Optional.of(p));
		when(pr.save(p)).thenReturn(p);
		
		Proveedor pBaja=ps.bajaProveedor(p.getId());
		assertNotNull(pBaja.getFechaBaja());
		
	}
	
	@Test
	public void findByIdTest() {
		Proveedor p = proveedores.get(0);
		when(pr.findById(1)).thenReturn(Optional.of(p));
		
		Proveedor p2=ps.findById(1);
		assertNotNull(p2);
		assertEquals(p, p2);
		
	}
	
	@Test
	public void quitarBajaProveedorTest() {
		Proveedor pBaja=proveedores.get(2);
		when(pr.findById(3)).thenReturn(Optional.of(pBaja));
		when(pr.save(pBaja)).thenReturn(pBaja);
		
		Proveedor p=ps.quitarBajaProveedor(pBaja.getId());
		assertNull(p.getFechaBaja());
	}
	
	@Test
	public void findAllSinBajaTest() {
		List<Proveedor> lista=proveedores;
		lista.remove(2);
		when(pr.findAllSinBaja()).thenReturn(lista);
		List<Proveedor> listaDevuelta=ps.findAllSinBaja();
		
		assertNotNull(listaDevuelta);
		assertTrue(listaDevuelta.size()>0);
		assertTrue(listaDevuelta.size()==2);
		
	}
	
	@Test
	public void buscarProveedoresPorNombreTest() {
		ArrayList<Proveedor> lista=new ArrayList<>();
		lista.add(proveedores.get(0));
		when(pr.buscarProveedoresPorNombre("Proveedor 1")).thenReturn(lista);
		
		List<Proveedor> listaDevuelta=ps.buscarProveedoresPorNombre("Proveedor 1");
		assertNotNull(listaDevuelta);
		assertTrue(listaDevuelta.size()>0);
		assertTrue(listaDevuelta.size()==1);
		assertEquals(listaDevuelta.get(0).getNombre(), "Proveedor 1");
		
		
	}
}
