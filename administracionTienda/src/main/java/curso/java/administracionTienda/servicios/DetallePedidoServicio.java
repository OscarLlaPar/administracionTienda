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
	
}
