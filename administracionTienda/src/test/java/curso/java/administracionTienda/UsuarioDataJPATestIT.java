package curso.java.administracionTienda;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;


@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioDataJPATestIT {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	private UsuarioRepositorio ur;
	
	@Test
	public void buscarUsuarioPorEmailDevolverUsuario() {
		Usuario usuario=crearUsuario();
		
		entityManager.persist(usuario);
		entityManager.flush();
		
		
		Usuario u=ur.findByEmail("test@mail.com");
		
		assertThat(u.getEmail()).isEqualTo(usuario.getEmail());
		
		
	}
	
	private Usuario crearUsuario() {
		Usuario usuario=new Usuario();
		usuario.setId(0);
		usuario.setEmail("test@mail.com");
		return usuario;
	}
	
	
}
