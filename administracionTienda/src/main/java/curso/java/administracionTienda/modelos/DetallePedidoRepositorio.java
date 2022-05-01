package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.DetallePedido;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Integer> {
	@Query(value="SELECT SUM(unidades) FROM detalles_pedido d INNER JOIN pedidos p ON p.id=d.id_pedido AND estado='E'", nativeQuery= true)
	int sumUnidades();
	
	@Query(value="SELECT COUNT(*) FROM detalles_pedido d INNER JOIN pedidos p ON p.id=d.id_pedido AND estado='E'", nativeQuery= true)
	int countEnviados();
	
}