package curso.java.administracionTienda.modelos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	@Query(value="SELECT * FROM usuarios u INNER JOIN roles r ON r.id=u.id_rol AND r.rol=?1", nativeQuery= true)
	List<Usuario> findAllByRol(String rol);
	
	Usuario findByEmail(String email);
	
}
