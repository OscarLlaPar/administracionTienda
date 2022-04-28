package curso.java.administracionTienda.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.servicios.ProductoServicio;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {
	
	@Autowired
	private ProductoServicio ps;
	
	@RequestMapping("")
	public String cargarProductos(Model model, HttpSession sesion) {
		
		List<Producto> productos = ps.obtenerProductos();
		model.addAttribute("productos", productos);
		return "pages/gestionProductos";
	}
	
}
