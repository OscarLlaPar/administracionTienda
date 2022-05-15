package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.DetallePedido;
import curso.java.administracionTienda.entidades.Pedido;
import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.modelos.DetallePedidoRepositorio;
import curso.java.administracionTienda.modelos.PedidoRepositorio;
import curso.java.administracionTienda.servicios.ConfiguracionServicio;
import curso.java.administracionTienda.servicios.DetallePedidoServicio;
import curso.java.administracionTienda.servicios.PedidoServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class DetallePedidoServicioTest {

	@Autowired
	private DetallePedidoServicio dps;
	
	@MockBean
	private DetallePedidoRepositorio dpr;
	
	@MockBean
	private ConfiguracionServicio cs;
	
	private static ArrayList<DetallePedido> detalles = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		detalles.add(new DetallePedido(1,new Pedido(),new Producto(),12,12,12,144,"PE"));
		detalles.add(new DetallePedido(2,new Pedido(),new Producto(),12,12,12,144,"PE"));
		detalles.add(new DetallePedido(3,new Pedido(),new Producto(),12,12,12,144,"E"));
	}
	
	@Test
	public void sumUnidadesTest() {
		when(dpr.countEnviados()).thenReturn(1);
		when(dpr.sumUnidades()).thenReturn(detalles.get(2).getUnidades());
		
		int unidades=dps.sumUnidades();
		
		assertTrue(unidades==12);
		
	}
	
	@Test
	public void sumUnidadesTest2() {
		when(dpr.countEnviados()).thenReturn(0);
		
		int unidades=dps.sumUnidades();
		
		assertTrue(unidades==0);
		
	}
	
	@Test
	public void guardarDetallePedidoTest() {
		DetallePedido dp =new DetallePedido(4,new Pedido(),new Producto(),12,12,12,144,"E");
		when(dpr.save(dp)).thenReturn(dp);
		
		DetallePedido dp2=dps.guardarDetallePedido(dp);
		assertNotNull(dp2);
		assertTrue(dp.getId()==dp2.getId());
		
	}
	
	
}
