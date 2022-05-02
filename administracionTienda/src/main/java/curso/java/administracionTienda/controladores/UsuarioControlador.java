package curso.java.administracionTienda.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.servicios.DetallePedidoServicio;
import curso.java.administracionTienda.servicios.PedidoServicio;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.servicios.ProveedorServicio;
import curso.java.administracionTienda.servicios.RolServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;
import curso.java.administracionTienda.utilidades.JsonUtil;
import curso.java.administracionTienda.utilidades.UsuarioUtil;

@Controller
@RequestMapping("/login")
public class UsuarioControlador {
	
	@Autowired
	private UsuarioServicio us;
	
	@Autowired
	private RolServicio rs;
	
	@Autowired
	private ProductoServicio ps;
	
	@Autowired
	private DetallePedidoServicio dps;
	
	@Autowired
	private PedidoServicio pds;
	
	@Autowired
	private ProveedorServicio pvs;
	
	@RequestMapping("")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession sesion, Model model) {
		Usuario u=us.verificarUsuario(email, password);
		if(u!=null) {
			sesion.setAttribute("usuarioAdministracion", u);
			return "redirect:/login/inicio";
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
	public String inicio(HttpSession sesion, Model model) {
		if(sesion.getAttribute("usuarioAdministracion")!=null) {
			model.addAttribute("topProductosValoraciones", ps.findAllSortByValoracion());
			System.out.println(ps.findAllSortByValoracion());
			model.addAttribute("topProductosVentas", ps.findAllSortByPedidos());
			System.out.println(ps.findAllSortByPedidos());
			model.addAttribute("unidadesVendidas", dps.sumUnidades());
			model.addAttribute("totalVentas", String.format("%.2f", pds.sumTotal()));
			
			return "pages/inicio";
		}
		else {
			return "index";
		}
	}
	
	@RequestMapping("/perfil")
	public String verPerfil(Model model) {
		model.addAttribute("usuarioEnCurso", new Usuario());
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		return "pages/perfilUsuario";
	}
	
	
	@RequestMapping("/editarPerfil")
	public String editarPerfil(@ModelAttribute Usuario usuarioEnCurso, HttpSession sesion) {
		System.out.println(usuarioEnCurso);
		Usuario usuarioActual=(Usuario)sesion.getAttribute("usuarioAdministracion");
		System.out.println(usuarioActual);
		boolean esValido=true;
		
		if(!usuarioEnCurso.getEmail().equals(usuarioActual.getEmail()) && us.buscarUsuarioPorEmail(usuarioEnCurso.getEmail())!=null) {
			esValido=false;
			System.out.println("No valido");
		}
		
		if(esValido) {
			us.editarUsuario(usuarioEnCurso);
			sesion.setAttribute("usuarioAdministracion", usuarioEnCurso);
		}
		
		return "redirect:/login/perfil";
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
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		model.addAttribute("usuarioEnCurso", new Usuario());
		return "pages/registro";
	}
	
	@RequestMapping("/altaUsuario")
	public String altaUsuario(@ModelAttribute Usuario usuarioEnCurso, @RequestParam String confirmarClave ) {
		System.out.println(usuarioEnCurso);
		System.out.println(confirmarClave);
		
		if(usuarioEnCurso.getClave().equals(confirmarClave)&&us.buscarUsuarioPorEmail(usuarioEnCurso.getEmail())==null) {
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
	
	@RequestMapping("/password")
	public String password() {
		return "pages/cambiarPassword";
	}
	
	@RequestMapping("/cambiarPassword")
	public String cambiarPassword(@RequestParam String clave, @RequestParam String claveNueva, @RequestParam String confirmarClave, HttpSession sesion) {
		boolean esValido=true;
		Usuario usuarioActual=(Usuario)sesion.getAttribute("usuarioAdministracion");
		String claveUsuario=UsuarioUtil.obtenerSha2(usuarioActual.getId()+clave);
		if(!claveNueva.equals(confirmarClave)||!claveUsuario.equals(usuarioActual.getClave())) {
			esValido=false;
		}
		
		
		if(!esValido) {
			return "redirect:/login/password";
		}
		else {
			String claveValida=UsuarioUtil.obtenerSha2(usuarioActual.getId()+claveNueva);
			usuarioActual.setClave(claveValida);
			us.editarUsuario(usuarioActual);
			sesion.setAttribute("usuarioAdministracion", usuarioActual);
			return "redirect:/login/perfil";
		}
		
		
	}
	
}
