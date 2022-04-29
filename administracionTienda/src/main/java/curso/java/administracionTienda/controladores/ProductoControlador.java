package curso.java.administracionTienda.controladores;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.servicios.CategoriaServicio;
import curso.java.administracionTienda.servicios.ProductoServicio;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {
	
	@Autowired
	private ProductoServicio ps;
	
	@Autowired
	private CategoriaServicio cs;
	
	@RequestMapping("")
	public String cargarProductos(Model model, HttpSession sesion) {
		
		List<Producto> productos = ps.obtenerProductos();
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", cs.obtenerCategorias());
		model.addAttribute("productoEnCurso", new Producto());
		return "pages/gestionProductos";
	}
	
	@RequestMapping("/baja")
	public String darDeBaja(@RequestParam int id) {
		System.out.println("Pasa por baja "+id);
		ps.darDeBaja(id);
		return "redirect:/productos";
	}
	
	@RequestMapping("/editar")
	public String editar(@ModelAttribute Producto productoEnCurso ) {
		System.out.println("Pasa por editar "+productoEnCurso);
		ps.editarProducto(productoEnCurso);
		return "redirect:/productos";
	}
}
