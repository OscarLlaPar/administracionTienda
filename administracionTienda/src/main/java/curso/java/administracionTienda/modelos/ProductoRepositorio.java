package curso.java.administracionTienda.modelos;

import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.Producto;

public interface ProductoRepositorio extends JpaRepository<Producto, Integer> {
	@Query(value="SELECT * FROM productos p INNER JOIN valoraciones v ON p.id=v.id_producto GROUP BY id_producto ORDER BY AVG(valoracion) DESC LIMIT 5", nativeQuery= true)
	LinkedHashSet<Producto> findAllSortByValoracion();
	
	@Query(value="SELECT * FROM productos p INNER JOIN detalles_pedido d ON p.id=d.id_producto INNER JOIN pedidos pd ON pd.id=d.id_pedido AND pd.estado='E' GROUP BY id_producto ORDER BY SUM(unidades) DESC LIMIT 5", nativeQuery= true)
	LinkedHashSet<Producto> findAllSortByPedidos();
	
	@Query(value="SELECT * FROM productos p WHERE nombre LIKE %?1%", nativeQuery= true)
	List<Producto> findByNombre(String nombre);
}
