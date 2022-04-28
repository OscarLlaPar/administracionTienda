package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, String> {
	
}
