package curso.java.administracionTienda.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.entidades.Pedido;
import curso.java.administracionTienda.servicios.DetallePedidoServicio;
import curso.java.administracionTienda.servicios.PedidoServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@Controller
@RequestMapping("/pedidos")
public class PedidoControlador {
	
	@Autowired
	private PedidoServicio ps;
	
	@Autowired
	private DetallePedidoServicio dps;
	
	@Autowired
	private UsuarioServicio us;
	
	@RequestMapping("")
	public String mostrarPedidos(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
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
