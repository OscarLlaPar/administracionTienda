package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
	
}
