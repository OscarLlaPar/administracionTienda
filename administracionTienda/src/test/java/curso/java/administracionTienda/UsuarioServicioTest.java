package curso.java.administracionTienda;

import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServicioTest {

	@Autowired
	private UsuarioServicio us;
	
	@MockBean
	private UsuarioRepositorio ur;
	
	
	@Test
	public void obtenerUsuarioRepositorioSimulado() {
		Optional<Usuario> ou=Optional.of(crearUsuario());
		
		when(ur.findById(1)).thenReturn(ou);
		
		assert us.buscarUsuarioPorId(1).getNombre().contains("Testing");
		
	}
	
	
	private Usuario crearUsuario() {
		Usuario usuario=new Usuario();
		usuario.setId(1);
		usuario.setEmail("test@mail.com");
		usuario.setNombre("Testing");
		return usuario;
	}
	
}
