package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.DetallePedido;
import curso.java.administracionTienda.entidades.Pedido;
import curso.java.administracionTienda.modelos.DetallePedidoRepositorio;
import curso.java.administracionTienda.modelos.PedidoRepositorio;

@Service
public class DetallePedidoServicio {
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	
	@Autowired
	private DetallePedidoRepositorio detallePedidoRepositorio;
	
	/**
	 * 
	 * @return
	 */
	
	public int sumUnidades() {
		long numVentas=detallePedidoRepositorio.countEnviados();
		if(numVentas!=0) {
			return detallePedidoRepositorio.sumUnidades();
		}
		else {
			return 0;
		}
		
		
	}
	
	public DetallePedido guardarDetallePedido(DetallePedido p) {
		return detallePedidoRepositorio.save(p);
	}
	
	public List<DetallePedido> obtenerDetalles(int idPedido){
		return detallePedidoRepositorio.obtenerDetalles(idPedido);
	}
	
	public DetallePedido findById(int id) {
		return detallePedidoRepositorio.findById(id).get();
	}
	
	public List<DetallePedido> pendientesCancelacion(int idPedido){
		return detallePedidoRepositorio.pendientesCancelacion(idPedido);
	}
	
	public List<DetallePedido> pendientesEnvio(int idPedido){
		return detallePedidoRepositorio.pendientesEnvio(idPedido);
	}
	
}
