package curso.java.administracionTienda.modelos;

import java.util.LinkedList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
	@Query(value="SELECT SUM(total) FROM pedidos WHERE estado='E'", nativeQuery= true)
	double sumTotal();
	@Query(value="SELECT COUNT(*) FROM pedidos WHERE estado='E'", nativeQuery= true)
	int countEnviados();
	@Query(value="SELECT * FROM pedidos ORDER BY fecha DESC", nativeQuery= true)
	LinkedList<Pedido> obtenerPedidos();
	@Query(value="SELECT COUNT(*) FROM pedidos WHERE estado='PE'", nativeQuery= true)
	int contarPendientesEnvio();
	@Query(value="SELECT COUNT(*) FROM pedidos WHERE estado='PC'", nativeQuery= true)
	int contarPendientesCancelacion();
	@Query(value="SELECT COUNT(*) FROM pedidos WHERE estado='PCD'", nativeQuery= true)
	int contarPendientesCancelacionDetalle();
}
