package curso.java.administracionTienda.testControladores;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.servicios.ConfiguracionServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@SpringBootTest
@AutoConfigureMockMvc
public class UsuarioControladorTest {
	@Autowired
    private MockMvc mockMvc;
	
	@MockBean
	private UsuarioServicio us;
	
	@MockBean
	private ConfiguracionServicio cs;
	
	@Test
	public void loginTest() throws Exception {
        mockMvc.perform(get("/login?email=&password=")).andExpect(status().isOk())
        .andExpect(view().name("index"));
	}
	
	@Test
	public void loginTest2() throws Exception {
        Usuario u=new Usuario(1, "empleado1@mail.com",new Rol(2, "Empleado"), "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Empleado 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null);
		when(us.verificarUsuario("empleado1@mail.com", "paso")).thenReturn(u);
		
		mockMvc.perform(get("/login?email=empleado1@mail.com&password=paso"))
        .andExpect(redirectedUrlTemplate("/login/inicio"));
	}
	
	@Test
	public void loginTest3() throws Exception {
        Usuario u=new Usuario(1, "admin@admin.com",new Rol(1, "Administrador"), "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Admin","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null);
		when(us.verificarUsuario("admin@admin.com", "paso")).thenReturn(u);
		Configuracion c= new Configuracion(1,"adminLogado","0","Numero");
		when(cs.obtenerConfiguracion("adminLogado")).thenReturn(c);
		
		mockMvc.perform(get("/login?email=admin@admin.com&password=paso"))
        .andExpect(redirectedUrlTemplate("/login/password"));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
