package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Pedido;

public interface PedidoRepositorio extends JpaRepository<Pedido, Integer> {

}
