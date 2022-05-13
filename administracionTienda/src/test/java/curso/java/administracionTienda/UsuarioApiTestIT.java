package curso.java.administracionTienda;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.util.MimeTypeUtils;


import com.fasterxml.jackson.databind.ObjectMapper;

import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;

@AutoConfigureMockMvc
@SpringBootTest
public class UsuarioApiTestIT {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	
	@Test
	public void buscarUsuariosPorNombre() throws Exception{
		var usuario=crearUsuario();
		
		/*mockMvc.perform(
				MockMvcRequestBuilders.get("/api/usuarios?nombre=admin")
				.contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(usuario))
				).andExpect(status().isOk());*/
		
		var findByNombre=mockMvc.perform(
				get("/api/usuarios?nombre=admin").accept(MimeTypeUtils.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk())
				.andReturn();
		
		var u = objectMapper.readValue(findByNombre.getResponse().getContentAsString(),Usuario[].class);
		
		System.out.println("U = "+ u);
		
		assert u[0].getNombre().equalsIgnoreCase(usuario.getNombre());
				
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
