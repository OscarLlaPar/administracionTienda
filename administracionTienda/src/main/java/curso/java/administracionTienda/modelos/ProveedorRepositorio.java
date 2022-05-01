package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Usuario;

public interface ProveedorRepositorio extends JpaRepository<Proveedor, Integer> {

}
