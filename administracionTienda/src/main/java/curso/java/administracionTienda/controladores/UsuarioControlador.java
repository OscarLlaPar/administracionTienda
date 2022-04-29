package curso.java.administracionTienda.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@Controller
@RequestMapping("/login")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio us;
	
	@RequestMapping("")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession sesion) {
		Usuario u=us.verificarUsuario(email, password);
		if(u!=null) {
			sesion.setAttribute("usuarioAdministracion", u);
			return "pages/inicio";
		}
		else {
			return "index";
		}
		
	}
	
	@RequestMapping("/logout")
	public String cerrarSesion(HttpSession sesion) {
		sesion.removeAttribute("usuarioAdministracion");
		return "index";
	}
	
	@RequestMapping("/inicio")
	public String inicio(HttpSession sesion) {
		if(sesion.getAttribute("usuarioAdministracion")!=null) {
			return "pages/inicio";
		}
		else {
			return "index";
		}
	}
	
	@RequestMapping("/perfil")
	public String verPerfil() {
		return "pages/perfilUsuario";
	}
	
	@RequestMapping("/clientes")
	public String mostrarClientes(Model model) {
		model.addAttribute("clientes", us.mostrarUsuariosPorRol("Cliente"));
		return "pages/gestionClientes";
	}
	
	@RequestMapping("/empleados")
	public String mostrarEmpleados(Model model) {
		model.addAttribute("empleados", us.mostrarUsuariosPorRol("Empleado"));
		return "pages/gestionEmpleados";
	}
	
	@RequestMapping("/nuevoCliente")
	public String nuevoCliente() {
		return "pages/registro";
	}
	
}
