package curso.java.administracionTienda.controladores;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;

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
		
		if(model.getAttribute("altaDescuento")!=null) {
			model.addAttribute("altaDescuento", model.getAttribute("altaDescuento"));
		}
		
		if(model.getAttribute("editarDescuento")!=null) {
			model.addAttribute("editarDescuento", model.getAttribute("editarDescuento"));
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
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		LocalDate fechaInicio=LocalDate.parse(inicio,formato);
		LocalDate fechaFin=LocalDate.parse(fin,formato);
		
		System.out.println(fechaInicio);
		System.out.println(fechaFin);
		
		descuentoEnCurso.setFechaInicio(fechaInicio);
		descuentoEnCurso.setFechaFin(fechaFin);
		
		System.out.println(descuentoEnCurso);
		ds.guardarDescuento(descuentoEnCurso);
		
		ra.addFlashAttribute("altaDescuento", "Nuevo descuento añadido");
		return "redirect:/descuentos";
	}
	
	@RequestMapping("/editar")
	public String editar(@ModelAttribute Descuento descuentoEnCurso, @RequestParam String inicio, @RequestParam String fin, @RequestParam String porcentaje , RedirectAttributes ra) {
		
		DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		
		double descuento=Double.parseDouble(porcentaje);
		LocalDate fechaInicio=LocalDate.parse(inicio,formato);
		LocalDate fechaFin=LocalDate.parse(fin,formato);
		
		descuentoEnCurso.setFechaInicio(fechaInicio);
		descuentoEnCurso.setFechaFin(fechaFin);
		descuentoEnCurso.setDescuento(descuento);
		
		ds.guardarDescuento(descuentoEnCurso);
		
		ra.addFlashAttribute("editarDescuento", "Se han guardado los cambios");
		
		return "redirect:/descuentos";
	}
	
	
}
