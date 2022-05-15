package curso.java.administracionTienda;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.SpringVersion;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MimeTypeUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import curso.java.administracionTienda.controladores.UsuarioRestControlador;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@WebMvcTest(UsuarioRestControlador.class)
public class UsuarioApiTest2IT {

	@Autowired
	 private MockMvc mockMvc;
	
	@MockBean
	private UsuarioServicio us;
	
	@Autowired
	  private ObjectMapper objectMapper;
	
	
	@Test
	 public void devolverUsuario_buscarPorId() throws Exception{
		Usuario usuario= crearUsuario();
		ArrayList<Usuario> lista=new ArrayList<>();
		lista.add(usuario);
		
		System.out.println(SpringVersion.getVersion());
		when(us.findByNombre("Admin")).thenReturn(lista);
		
		var findById = mockMvc.perform(
	            get("/api/usuarios?nombre=Admin")
	                .accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
	        .andExpect(status().isOk())
	        .andReturn();
	    var u = objectMapper.readValue(findById.getResponse().getContentAsString(), Usuario[].class);
	    Usuario uLista=lista.get(0);
	    assert u[0].getNombre().equalsIgnoreCase(uLista.getNombre());
		
		
	}
	
	private Usuario crearUsuario() {
		Usuario usuario=new Usuario();
		usuario.setId(1);
		usuario.setRol(new Rol(3,"Administrador"));
		usuario.setEmail("test2@mail.com");
		usuario.setNombre("Admin");
		return usuario;
	}
	
	
}
