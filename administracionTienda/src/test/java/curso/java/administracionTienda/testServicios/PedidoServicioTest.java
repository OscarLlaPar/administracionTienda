package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.entidades.Pedido;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.modelos.PedidoRepositorio;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;
import curso.java.administracionTienda.servicios.ConfiguracionServicio;
import curso.java.administracionTienda.servicios.PedidoServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PedidoServicioTest {

	@Autowired
	private PedidoServicio ps;
	
	@MockBean
	private PedidoRepositorio pr;
	
	@MockBean
	private ConfiguracionServicio cs;
	
	private static ArrayList<Pedido> pedidos = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		pedidos.add(new Pedido(1, new Usuario(), new Timestamp(System.currentTimeMillis()), "Tarjeta","PE", null, 100, null));
		pedidos.add(new Pedido(2, new Usuario(), new Timestamp(System.currentTimeMillis()), "Tarjeta","PE", null, 100, null)); 
		pedidos.add(new Pedido(3, new Usuario(), new Timestamp(System.currentTimeMillis()), "Tarjeta","E", null, 100, null)); 
	}
	
	
	@Test
	public void obtenerPedidosTest() {
		when(pr.findAll()).thenReturn(pedidos);
		
		List<Pedido> lista=ps.obtenerPedidos();
		assertNotNull(lista);
		assertTrue(lista.size()>0);
		assertEquals(pedidos.get(0),lista.get(0));
	}
	
	@Test
	public void obtenerPedidoTest() {
		Pedido p=pedidos.get(0);
		when(pr.findById(1)).thenReturn(Optional.of(p));
		
		Pedido p2=ps.obtenerPedido(1);
		
		assertNotNull(p2);
		assertTrue(p2.getId()==1);
	}
	
	@Test
	public void guardarPedidoTest() {
		Pedido p=new Pedido(4, new Usuario(), new Timestamp(System.currentTimeMillis()), "Tarjeta","PE", null, 100, null);
		when(pr.save(p)).thenReturn(p);
		
		Pedido p2=ps.guardarPedido(p);
		assertNotNull(p2);
		assertEquals(p.getId(),p2.getId());
	}
	
	@Test
	public void asignarNumeroFacturaTest() {
		Pedido p=pedidos.get(2);
		when(cs.obtenerConfiguracion("numFacturas")).thenReturn(new Configuracion(1,"numFacturas","1","Numero"));
		when(pr.save(p)).thenReturn(p);
		
		ps.asignarNumeroFactura(p);
		
		assertNotNull(p.getNumFactura());
		assertEquals(p.getNumFactura(),"20221");
		
	}
	
	@Test
	public void sumTotalTest() {
		when(pr.countEnviados()).thenReturn(1);
		when(pr.sumTotal()).thenReturn(pedidos.get(2).getTotal());
		
		double suma=ps.sumTotal();
		
		assertTrue(suma!=0.0);
		assertTrue(suma==100);
	}
	
	@Test
	public void sumTotalTest2() {
		when(pr.countEnviados()).thenReturn(0);
		
		double suma=ps.sumTotal();
		
		assertTrue(suma==0.0);
	}
}
