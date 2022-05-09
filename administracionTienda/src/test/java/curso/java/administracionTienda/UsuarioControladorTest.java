package curso.java.administracionTienda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import curso.java.administracionTienda.controladores.UsuarioControlador;
import curso.java.administracionTienda.servicios.ConfiguracionServicio;
import curso.java.administracionTienda.servicios.DetallePedidoServicio;
import curso.java.administracionTienda.servicios.OpcionMenuServicio;
import curso.java.administracionTienda.servicios.PedidoServicio;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.servicios.RolServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;


@WebMvcTest(UsuarioControlador.class)
public class UsuarioControladorTest {
	
	@Autowired
    private MockMvc mvc;
	
	@MockBean
	private UsuarioServicio us;
	
	@MockBean
	private RolServicio rs;
	
	@MockBean
	private ProductoServicio ps;
	
	@MockBean
	private DetallePedidoServicio dps;
	
	@MockBean
	private PedidoServicio pds;
	
	@MockBean
	private ConfiguracionServicio cs;
	
	@MockBean
	private OpcionMenuServicio oms;
	
	
	@Test
	public void probarMostrarPaginaLogin() throws Exception {
		mvc.perform(
				get("/login?email=prueba@mail.com&password=123")
				.contentType(MediaType.TEXT_HTML)
				).andExpect(status().isOk());
	}
	
	
}
