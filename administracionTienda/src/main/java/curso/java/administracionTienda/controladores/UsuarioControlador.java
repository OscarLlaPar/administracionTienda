package curso.java.administracionTienda.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.servicios.RolServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@Controller
@RequestMapping("/login")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio us;
	
	@Autowired
	private RolServicio rs;
	
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
		model.addAttribute("usuarioEnCurso", new Usuario());
		return "pages/gestionClientes";
	}
	
	@RequestMapping("/empleados")
	public String mostrarEmpleados(Model model) {
		model.addAttribute("empleados", us.mostrarUsuariosPorRol("Empleado"));
		model.addAttribute("usuarioEnCurso", new Usuario());
		return "pages/gestionEmpleados";
	}
	
	@RequestMapping("/alta")
	public String nuevoCliente(@RequestParam String rol, Model model) {
		Rol r=rs.obtenerRol(rol);
		System.out.println(r);
		model.addAttribute("rol", r);
		model.addAttribute("usuarioEnCurso", new Usuario());
		return "pages/registro";
	}
	
	@RequestMapping("/altaUsuario")
	public String altaUsuario(@ModelAttribute Usuario usuarioEnCurso, @RequestParam String confirmarClave ) {
		System.out.println(usuarioEnCurso);
		System.out.println(confirmarClave);
		
		if(usuarioEnCurso.getClave().equals(confirmarClave)) {
			usuarioEnCurso.setClave(us.encriptarClave(usuarioEnCurso));
			us.editarUsuario(usuarioEnCurso);
			if(usuarioEnCurso.getRol().getRol().equals("Cliente")) {
				return "redirect:/login/clientes";
			}
			else {
				return "redirect:/login/empleados";
			}
		}
		else {
			return "redirect:/login/alta";
		}
		
	}
	
	@RequestMapping("/editarUsuario")
	public String editarUsuaio(@ModelAttribute Usuario usuarioEnCurso) {
		System.out.println(usuarioEnCurso);
		us.editarUsuario(usuarioEnCurso);
		if(usuarioEnCurso.getRol().getRol().equals("Cliente")) {
			return "redirect:/login/clientes";
		}
		else {
			return "redirect:/login/empleados";
		}
		
	}
	
	@RequestMapping("/baja")
	public String baja(@RequestParam String email) {
		Usuario u=us.buscarUsuarioPorEmail(email);
		System.out.println(u);
		us.bajaUsuario(u);
		if(u.getRol().getRol().equals("Cliente")) {
			return "redirect:/login/clientes";
		}
		else {
			return "redirect:/login/empleados";
		}
	}
}
