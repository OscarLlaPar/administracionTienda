package curso.java.administracionTienda.servicios;

import java.time.LocalDate;
import java.util.LinkedList;
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
	private DetallePedidoServicio dps;
	
	@Autowired
	private PedidoRepositorio pedidoRepositorio;
	
	@Autowired
	private DetallePedidoRepositorio detallePedidoRepositorio;
	
	/**
	 * 
	 * @return
	 */
	
	public List<Pedido> obtenerPedidos(){
		return pedidoRepositorio.findAll();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	public Pedido obtenerPedido(int id) {
		return pedidoRepositorio.findById(id).get();
	}
	
	
	
	/**
	 * 
	 * @param p
	 */
	
	public void guardarPedido(Pedido p) {
		pedidoRepositorio.save(p);
	}
	
	/**
	 * 
	 * @param p
	 */
	
	public void asignarNumeroFactura(Pedido p) {
		Configuracion c=cs.obtenerConfiguracion("numFacturas");
		String anyoActual=String.valueOf(LocalDate.now().getYear());
		p.setNumFactura(anyoActual+c.getValor());
		pedidoRepositorio.save(p);
		cs.actualizarNumFacturas();
	}
	
	/**
	 * 
	 * @return
	 */
	
	public double sumTotal() {
		if(pedidoRepositorio.countEnviados()!=0) {
			return pedidoRepositorio.sumTotal();
		}
		else {
			return 0;
		}
	}
	
	public LinkedList<Pedido> obtenerPedidosOrdenados(){
		return pedidoRepositorio.obtenerPedidos();
	}
	
	
	public int contarPendientesEnvio() {
		return pedidoRepositorio.contarPendientesEnvio();
	}
	
	public int contarPendientesCancelacion() {
		return pedidoRepositorio.contarPendientesCancelacion();
	}
	
	public int contarPendientesCancelacionDetalle() {
		return pedidoRepositorio.contarPendientesCancelacionDetalle();
	}
	
	public LinkedList<Pedido> obtenerPedidosPorEstado(String estado){
		return pedidoRepositorio.obtenerPedidosPorEstado(estado);
	}
	
	
}
