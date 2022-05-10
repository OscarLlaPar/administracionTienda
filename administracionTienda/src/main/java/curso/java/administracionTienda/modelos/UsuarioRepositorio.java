package curso.java.administracionTienda.modelos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.entidades.Usuario;

public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {
	@Query(value="SELECT * FROM usuarios u INNER JOIN roles r ON r.id=u.id_rol AND r.rol=?1", nativeQuery= true)
	List<Usuario> findAllByRol(String rol);
	
	Usuario findByEmail(String email);
	
	@Query(value="SELECT * FROM usuarios u WHERE id_rol=3 AND nombre!='Admin'", nativeQuery= true)
	List<Usuario> mostrarAdministradores();
	
	@Query(value="SELECT * FROM usuarios u WHERE nombre LIKE %?1%", nativeQuery= true)
	List<Usuario> findByNombre(String nombre);
	
	@Query(value="SELECT * FROM usuarios u WHERE id_rol=1 AND nombre LIKE %?1%", nativeQuery= true)
	List<Usuario> buscarClientesPorNombre(String nombre);
	
	@Query(value="SELECT * FROM usuarios u WHERE id_rol=2 AND nombre LIKE %?1%", nativeQuery= true)
	List<Usuario> buscarEmpleadosPorNombre(String nombre);
	
	@Query(value="SELECT * FROM usuarios u WHERE id_rol=3 AND nombre LIKE %?1% AND nombre!='Admin'", nativeQuery= true)
	List<Usuario> buscarAdministradoresPorNombre(String nombre);
	
}
