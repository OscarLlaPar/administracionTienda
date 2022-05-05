package curso.java.administracionTienda.modelos;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.DetallePedido;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Integer> {
	@Query(value="SELECT SUM(unidades) FROM detalles_pedido d INNER JOIN pedidos p ON p.id=d.id_pedido AND p.estado='E'", nativeQuery= true)
	int sumUnidades();
	
	@Query(value="SELECT COUNT(*) FROM detalles_pedido d INNER JOIN pedidos p ON p.id=d.id_pedido AND p.estado='E'", nativeQuery= true)
	int countEnviados();
	
	@Query(value="SELECT * FROM detalles_pedido d WHERE id_pedido =?1", nativeQuery= true)
	List<DetallePedido> obtenerDetalles(int idPedido);
	
	@Query(value="SELECT * FROM detalles_pedido d WHERE estado='PC' AND id_pedido =?1", nativeQuery= true)
	List<DetallePedido> pendientesCancelacion(int idPedido);
	
	@Query(value="SELECT * FROM detalles_pedido d WHERE estado='PE' AND id_pedido =?1", nativeQuery= true)
	List<DetallePedido> pendientesEnvio(int idPedido);
	
}