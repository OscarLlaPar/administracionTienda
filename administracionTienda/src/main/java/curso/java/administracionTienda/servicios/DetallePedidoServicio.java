package curso.java.administracionTienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.modelos.DetallePedidoRepositorio;
import curso.java.administracionTienda.modelos.PedidoRepositorio;

@Service
public class DetallePedidoServicio {
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	
	@Autowired
	private DetallePedidoRepositorio detallePedidoRepositorio;
	
	public int sumUnidades() {
		return detallePedidoRepositorio.sumUnidades();
	}
	
}
