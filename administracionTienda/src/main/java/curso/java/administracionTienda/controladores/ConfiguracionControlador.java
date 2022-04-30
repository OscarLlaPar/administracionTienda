package curso.java.administracionTienda.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.servicios.ConfiguracionServicio;

@Controller
@RequestMapping("/config")
public class ConfiguracionControlador {
	
	@Autowired
	private ConfiguracionServicio cs;
	
	@RequestMapping("")
	public String mostrarConfiguraciones(Model model) {
		model.addAttribute("confEnCurso", new Configuracion());
		model.addAttribute("configuracion", cs.obtenerTodaLaConfiguracion());
		
		return "pages/gestionConfiguracion";
	}
	
	@RequestMapping("/editar")
	public String editarConfiguracion(@ModelAttribute Configuracion confEnCurso) {
		System.out.println(confEnCurso);
		cs.guardarConfiguracion(confEnCurso);
		return "redirect:/config";
	}
}
