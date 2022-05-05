package curso.java.administracionTienda.controladores;

import java.util.LinkedList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.administracionTienda.entidades.DetallePedido;
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
		LinkedList<Pedido> pedidos=ps.obtenerPedidosOrdenados();
		
		
		
		model.addAttribute("pedidos", pedidos);
		System.out.println(pedidos);
		return "pages/gestionPedidos";
	}
	
	@RequestMapping("/enviar")
	public String enviarPedido(@RequestParam int id) {
		Pedido pedidoEnCurso=ps.obtenerPedido(id);
		pedidoEnCurso.setEstado("E");
		ps.guardarPedido(pedidoEnCurso);
		ps.asignarNumeroFactura(pedidoEnCurso);
		List<DetallePedido> lista=dps.obtenerDetalles(id);
		for(DetallePedido detalle:lista) {
			if(detalle.getEstado().equals("PE")) {
				detalle.setEstado("E");
				dps.guardarDetallePedido(detalle);
			}
		}
		return "redirect:/pedidos";
	}
	
	@RequestMapping("/cancelar")
	public String cancelarPedido(@RequestParam int id) {
		Pedido pedidoEnCurso=ps.obtenerPedido(id);
		pedidoEnCurso.setEstado("C");
		ps.guardarPedido(pedidoEnCurso);
		List<DetallePedido> lista=dps.obtenerDetalles(id);
		for(DetallePedido detalle:lista) {
				detalle.setEstado("C");
				dps.guardarDetallePedido(detalle);
			
		}
		return "redirect:/pedidos";
	}
	
	@RequestMapping("/detalles")
	public String verDetalles(@RequestParam int id, Model model) {
		List<DetallePedido> lista=dps.obtenerDetalles(id);
		model.addAttribute("detalles", lista);
		return "pages/gestionDetalles";
	}
	
	@RequestMapping("/cancelarDetalle")
	public String cancelarDetalle(@RequestParam int id, @RequestParam int idPedido, RedirectAttributes ra) {
		DetallePedido detalle=dps.findById(id);
		detalle.setEstado("C");
		dps.guardarDetallePedido(detalle);
		Pedido p=ps.obtenerPedido(idPedido);
		p.setTotal(p.getTotal()-detalle.getTotal());
		
		
		if(dps.pendientesCancelacion(idPedido).size()==0) {
			p.setEstado("PE");
			
		}
		ps.guardarPedido(p);
		return "redirect:/pedidos/detalles?id="+idPedido;
	}
	
}
