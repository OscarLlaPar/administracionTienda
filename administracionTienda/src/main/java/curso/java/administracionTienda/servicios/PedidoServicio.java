package curso.java.administracionTienda.servicios;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.entidades.Pedido;
import curso.java.administracionTienda.modelos.DetallePedidoRepositorio;
import curso.java.administracionTienda.modelos.PedidoRepositorio;

@Service
public class PedidoServicio {
	
	@Autowired
	private ConfiguracionServicio cs;
	
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
	
	public void guardarPedido(Pedido p) {
		pedidoRepositorio.save(p);
	}
	
	public void asignarNumeroFactura(Pedido p) {
		Configuracion c=cs.obtenerConfiguracion("numFacturas");
		String anyoActual=String.valueOf(LocalDate.now().getYear());
		p.setNumFactura(anyoActual+c.getValor());
		pedidoRepositorio.save(p);
		cs.actualizarNumFacturas();
	}
}
