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
import curso.java.administracionTienda.servicios.ProductoServicio;
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
	
	@Autowired
	private ProductoServicio prs;
	
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
		pedidoEnCurso.setTotal(0.0);
		ps.guardarPedido(pedidoEnCurso);
		List<DetallePedido> lista=dps.obtenerDetalles(id);
		for(DetallePedido detalle:lista) {
				detalle.setEstado("C");
				detalle.getProducto().setStock(detalle.getProducto().getStock()+detalle.getUnidades());
				prs.editarProducto(detalle.getProducto());
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
		
		detalle.getProducto().setStock(detalle.getProducto().getStock()+detalle.getUnidades());
		prs.editarProducto(detalle.getProducto());
		
		dps.guardarDetallePedido(detalle);
		
		
		Pedido p=ps.obtenerPedido(idPedido);
		p.setTotal(p.getTotal()-detalle.getTotal());
		
		
		if(dps.pendientesCancelacion(idPedido).size()==0 && dps.pendientesEnvio(idPedido).size()!=0) {
			p.setEstado("PE");
			
		}
		if(dps.pendientesCancelacion(idPedido).size()==0 && dps.pendientesEnvio(idPedido).size()==0) {
			p.setEstado("C");
			
		}
		
		ps.guardarPedido(p);
		return "redirect:/pedidos/detalles?id="+idPedido;
	}
	
	@RequestMapping("/busqueda")
	public String busqueda(@RequestParam String busqueda, Model model) {
		model.addAttribute("pedidos", ps.obtenerPedidosPorEstado(busqueda));
		model.addAttribute("busqueda", busqueda);
		return "pages/gestionPedidos";
	}
	
}
