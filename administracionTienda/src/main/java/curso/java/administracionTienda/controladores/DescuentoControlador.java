package curso.java.administracionTienda.controladores;

import java.sql.Timestamp;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.administracionTienda.entidades.Descuento;
import curso.java.administracionTienda.servicios.DescuentoServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@Controller
@RequestMapping("/descuentos")
public class DescuentoControlador {
	
	@Autowired
	private DescuentoServicio ds;
	
	@Autowired
	private UsuarioServicio us;
	
	
	@RequestMapping("")
	public String mostrarDescuentos(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		model.addAttribute("descuentos", ds.findAll());
		model.addAttribute("descuentoEnCurso", new Descuento());
		
		return "pages/gestionDescuentos";
	}
	
	@RequestMapping("/alta")
	public String alta(Model model) {
		model.addAttribute("descuentoEnCurso", new Descuento());
		return "pages/altaDescuento";
	}
	
	@RequestMapping("/altaDescuento")
	public String altaDescuento(@ModelAttribute Descuento descuentoEnCurso, @RequestParam String inicio, @RequestParam String fin, RedirectAttributes ra) {
		System.out.println(inicio);
		System.out.println(fin);
		
		Timestamp fechaInicio=Timestamp.valueOf(inicio+":00");
		Timestamp fechaFin=Timestamp.valueOf(fin+":00");
		descuentoEnCurso.setFechaInicio(fechaInicio);
		descuentoEnCurso.setFechaFin(fechaFin);
		
		
		ds.guardarDescuento(descuentoEnCurso);
		
		ra.addFlashAttribute("altaDescuento", "Nuevo descuento añadido");
		return "redirect:/descuentos";
	}
	
}
