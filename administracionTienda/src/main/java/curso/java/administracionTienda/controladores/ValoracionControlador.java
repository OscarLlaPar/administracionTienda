package curso.java.administracionTienda.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.servicios.ValoracionServicio;

@Controller
@RequestMapping("/valoraciones")
public class ValoracionControlador {

	@Autowired
	private ValoracionServicio vs;
	
	@RequestMapping("")
	public String mostrarValoraciones(Model model) {
		model.addAttribute("valoraciones", vs.obtenerValoraciones());
		return "pages/gestionValoraciones";
	}
	
	@RequestMapping("/eliminar")
	public String eliminarValoracion(@RequestParam int id) {
		vs.eliminarValoracion(id);
		return "redirect:/valoraciones";
	}
	
}
