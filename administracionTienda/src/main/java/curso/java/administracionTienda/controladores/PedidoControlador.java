package curso.java.administracionTienda.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.entidades.Pedido;
import curso.java.administracionTienda.servicios.DetallePedidoServicio;
import curso.java.administracionTienda.servicios.PedidoServicio;

@Controller
@RequestMapping("/pedidos")
public class PedidoControlador {
	
	@Autowired
	private PedidoServicio ps;
	
	@Autowired
	private DetallePedidoServicio dps;
	
	@RequestMapping("")
	public String mostrarPedidos(Model model) {
		System.out.println(ps.obtenerPedidos());
		model.addAttribute("pedidos", ps.obtenerPedidos());
		return "pages/gestionPedidos";
	}
	
	@RequestMapping("/enviar")
	public String enviarPedido(@RequestParam int id) {
		Pedido pedidoEnCurso=ps.obtenerPedido(id);
		pedidoEnCurso.setEstado("E");
		ps.guardarPedido(pedidoEnCurso);
		ps.asignarNumeroFactura(pedidoEnCurso);
		return "redirect:/pedidos";
	}
	
	@RequestMapping("/cancelar")
	public String cancelarPedido(@RequestParam int id) {
		Pedido pedidoEnCurso=ps.obtenerPedido(id);
		pedidoEnCurso.setEstado("C");
		ps.guardarPedido(pedidoEnCurso);
		
		return "redirect:/pedidos";
	}
	
}
