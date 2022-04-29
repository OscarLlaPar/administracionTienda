package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.DetallePedido;

public interface DetallePedidoRepositorio extends JpaRepository<DetallePedido, Integer> {

}
