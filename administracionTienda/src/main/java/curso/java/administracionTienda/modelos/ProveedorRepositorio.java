package curso.java.administracionTienda.modelos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Usuario;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {
	@Query(value="SELECT * FROM proveedores p WHERE fecha_baja IS NULL", nativeQuery= true)
	List<Proveedor> findAllSinBaja();
	
	@Query(value="SELECT * FROM proveedores p WHERE nombre LIKE %?1%", nativeQuery= true)
	List<Proveedor> buscarProveedoresPorNombre(String nombre);
}
