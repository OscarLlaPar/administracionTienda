package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import curso.java.administracionTienda.entidades.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {
	@Query(value="SELECT SUM(total) FROM pedidos WHERE estado='E'", nativeQuery= true)
	double sumTotal();
	@Query(value="SELECT COUNT(*) FROM pedidos WHERE estado='E'", nativeQuery= true)
	int countEnviados();
}
