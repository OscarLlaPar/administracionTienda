package curso.java.administracionTienda.modelos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.OpcionMenu;

public interface OpcionMenuRepositorio extends JpaRepository<OpcionMenu, Integer> {
	@Query(value="SELECT * FROM opciones_menu o INNER JOIN roles r ON r.id=o.id_rol AND r.rol=?1", nativeQuery= true)
	List<OpcionMenu> findAllByRol(String rol);
	
	
}
