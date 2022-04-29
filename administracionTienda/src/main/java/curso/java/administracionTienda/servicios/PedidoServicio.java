package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Pedido;
import curso.java.administracionTienda.modelos.DetallePedidoRepositorio;
import curso.java.administracionTienda.modelos.PedidoRepositorio;

@Service
public class PedidoServicio {
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	
	@Autowired
	private DetallePedidoRepositorio detallePedidoRepositorio;
	
	public List<Pedido> obtenerPedidos(){
		return pedidoRepositorio.findAll();
	}
	
	public Pedido obtenerPedido(int id) {
		return pedidoRepositorio.findById(id).get();
	}
	
}
