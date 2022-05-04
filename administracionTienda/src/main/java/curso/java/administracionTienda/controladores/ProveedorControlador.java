package curso.java.administracionTienda.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.servicios.ProveedorServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;
import curso.java.administracionTienda.utilidades.JsonUtil;

@Controller
@RequestMapping("/proveedores")
public class ProveedorControlador {
	
	@Autowired
	private ProveedorServicio ps;
	
	@Autowired
	private UsuarioServicio us;
	
	@RequestMapping("")
	public String mostrarProveedores(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		model.addAttribute("proveedores", ps.findAll());
		model.addAttribute("proveedorEnCurso", new Proveedor());
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		
		return "pages/gestionProveedores";
	}
	
	@RequestMapping("/editar")
	public String editarProveedor(@ModelAttribute Proveedor proveedorEnCurso) {
		System.out.println(proveedorEnCurso);
		ps.guardarProveedor(proveedorEnCurso);
		return "redirect:/proveedores";
	}
	
	@RequestMapping("/alta")
	public String alta(Model model) {
		
		model.addAttribute("proveedorEnCurso", new Proveedor());
		return "pages/altaProveedor";
	}
	
	@RequestMapping("/altaProveedor")
	public String altaProveedor(@ModelAttribute Proveedor proveedorEnCurso) {
		ps.guardarProveedor(proveedorEnCurso);
		
		return "redirect:/proveedores";
	}
	
	@RequestMapping("/baja")
	public String baja(@RequestParam int id) {
		
		ps.bajaProveedor(id);
		return "redirect:/proveedores";
	}
	
	@RequestMapping("/quitarBaja")
	public String quitarBaja(@RequestParam int id) {
		
		ps.quitarBajaProveedor(id);
		return "redirect:/proveedores";
	}
	
	
}
