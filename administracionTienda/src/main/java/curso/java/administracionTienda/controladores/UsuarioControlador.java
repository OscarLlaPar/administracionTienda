package curso.java.administracionTienda.controladores;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.servicios.ConfiguracionServicio;
import curso.java.administracionTienda.servicios.DetallePedidoServicio;
import curso.java.administracionTienda.servicios.OpcionMenuServicio;
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
	private ConfiguracionServicio cs;
	
	@Autowired
	private OpcionMenuServicio oms;
	
	@RequestMapping("")
	public String login(@RequestParam String email, @RequestParam String password, HttpSession sesion, Model model) {
		Usuario u=us.verificarUsuario(email, password);
		if(u!=null) {
			sesion.setAttribute("usuarioAdministracion", u);
			Configuracion adminLogado=cs.obtenerConfiguracion("adminLogado");
			if(u.getNombre().equals("Admin")&&adminLogado.getValor().equals("0")) {
				return "redirect:/login/password";
			}
			
			return "redirect:/login/inicio";
		}
		else {
			model.addAttribute("errorLogin", "Email y/o contraseña incorrectos");
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
	
		if(model.getAttribute("passCambiada")!=null) {
			model.addAttribute("passCambiada", model.getAttribute("passCambiada"));
		}
		
		if(sesion.getAttribute("usuarioAdministracion")!=null) {
			Usuario u= (Usuario) sesion.getAttribute("usuarioAdministracion");
			Configuracion adminLogado=cs.obtenerConfiguracion("adminLogado");
			if(u.getNombre().equals("Admin")&&adminLogado.getValor().equals("0")) {
				return "redirect:/login/password";
			}
			
			model.addAttribute("topProductosValoraciones", ps.findAllSortByValoracion());
			System.out.println(ps.findAllSortByValoracion());
			model.addAttribute("topProductosVentas", ps.findAllSortByPedidos());
			//System.out.println(ps.findAllSortByPedidos());
			model.addAttribute("unidadesVendidas", dps.sumUnidades());
			model.addAttribute("totalVentas", String.format("%.2f", pds.sumTotal()));
			
			model.addAttribute("opciones",oms.findAll(u.getRol().getRol()));
			
			return "pages/inicio";
		}
		else {
			return "index";
		}
	}
	
	@RequestMapping("/perfil")
	public String verPerfil(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		if(model.getAttribute("passCambiada")!=null) {
			model.addAttribute("passCambiada", model.getAttribute("passCambiada"));
		}
		
		Usuario u= (Usuario) sesion.getAttribute("usuarioAdministracion");
		Configuracion adminLogado=cs.obtenerConfiguracion("adminLogado");
		if(u.getNombre().equals("Admin")&&adminLogado.getValor().equals("0")) {
			return "redirect:/login/password";
		}
		
		model.addAttribute("usuarioEnCurso", new Usuario());
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		return "pages/perfilUsuario";
	}
	
	
	@RequestMapping("/editarPerfil")
	public String editarPerfil(@ModelAttribute Usuario usuarioEnCurso, HttpSession sesion) {
		System.out.println(usuarioEnCurso);
		Usuario usuarioActual=(Usuario)sesion.getAttribute("usuarioAdministracion");
		usuarioEnCurso.setClave(usuarioActual.getClave());
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
	public String mostrarClientes(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		if(model.getAttribute("altaUsuario")!=null) {
			model.addAttribute("altaUsuario", model.getAttribute("altaUsuario"));
		}
		
		model.addAttribute("clientes", us.mostrarUsuariosPorRol("Cliente"));
		model.addAttribute("usuarioEnCurso", new Usuario());
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		return "pages/gestionClientes";
	}
	
	@RequestMapping("/empleados")
	public String mostrarEmpleados(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		if(model.getAttribute("altaUsuario")!=null) {
			model.addAttribute("altaUsuario", model.getAttribute("altaUsuario"));
		}
		
		model.addAttribute("empleados", us.mostrarUsuariosPorRol("Empleado"));
		model.addAttribute("usuarioEnCurso", new Usuario());
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		return "pages/gestionEmpleados";
	}
	
	@RequestMapping("/alta")
	public String nuevoCliente(@RequestParam String rol, Model model,  HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		Rol r=rs.obtenerRol(rol);
		System.out.println(r);
		model.addAttribute("rol", r);
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		model.addAttribute("usuarioEnCurso", new Usuario());
		return "pages/registro";
	}
	
	@RequestMapping("/altaUsuario")
	public String altaUsuario(@ModelAttribute Usuario usuarioEnCurso, @RequestParam String confirmarClave, HttpSession sesion, RedirectAttributes ra ) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		System.out.println(usuarioEnCurso);
		System.out.println(confirmarClave);
		
		if(usuarioEnCurso.getClave().equals(confirmarClave)&&us.buscarUsuarioPorEmail(usuarioEnCurso.getEmail())==null) {
			usuarioEnCurso.setClave(us.encriptarClave(usuarioEnCurso));
			us.editarUsuario(usuarioEnCurso);
			if(usuarioEnCurso.getRol().getRol().equals("Cliente")) {
				ra.addFlashAttribute("altaUsuario", "Nuevo cliente añadido");
				return "redirect:/login/clientes";
			}
			else if(usuarioEnCurso.getRol().getRol().equals("Empleado")) {
				ra.addFlashAttribute("altaUsuario", "Nuevo empleado añadido");
				return "redirect:/login/empleados";
			}
			else if(usuarioEnCurso.getRol().getRol().equals("Administrador")) {
				ra.addFlashAttribute("altaUsuario", "Nuevo administrador añadido");
				return "redirect:/login/administradores";
			}
		}
		
			return "redirect:/login/alta";
		
		
	}
	
	@RequestMapping("/editarUsuario")
	public String editarUsuaio(@ModelAttribute Usuario usuarioEnCurso, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		System.out.println(usuarioEnCurso);
		Usuario usuarioReal=us.buscarUsuarioPorId(usuarioEnCurso.getId());
		usuarioEnCurso.setClave(usuarioReal.getClave());
		us.editarUsuario(usuarioEnCurso);
		
		
		if(usuarioEnCurso.getRol().getRol().equals("Cliente")) {
			return "redirect:/login/clientes";
		}
		if(usuarioEnCurso.getRol().getRol().equals("Empleado")) {
			return "redirect:/login/empleados";
		}
		
		return "redirect:/login/administradores";
		
	}
	
	@RequestMapping("/baja")
	public String baja(@RequestParam int id) {
		Usuario u=us.buscarUsuarioPorId(id);
		System.out.println(u);
		us.bajaUsuario(u);
		if(u.getRol().getRol().equals("Cliente")) {
			return "redirect:/login/clientes";
		}
		if(u.getRol().getRol().equals("Empleado")) {
			return "redirect:/login/empleados";
		}
		return "redirect:/login/administradores";
	}
	
	@RequestMapping("/quitarBaja")
	public String quitarBaja(@RequestParam int id) {
		Usuario u=us.buscarUsuarioPorId(id);
		us.quitarBajaUsuario(u);
		if(u.getRol().getRol().equals("Cliente")) {
			return "redirect:/login/clientes";
		}
		if(u.getRol().getRol().equals("Empleado")) {
			return "redirect:/login/empleados";
		}
		return "redirect:/login/administradores";
	}
	
	@RequestMapping("/password")
	public String password(HttpSession sesion, Model model) {
		if(model.getAttribute("errorPassword")!=null) {
			model.addAttribute("errorPassword", model.getAttribute("errorPassword"));
		}
		
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		return "pages/cambiarPassword";
	}
	
	@RequestMapping("/cambiarPassword")
	public String cambiarPassword(@RequestParam String clave, @RequestParam String claveNueva, @RequestParam String confirmarClave, HttpSession sesion, RedirectAttributes ra) {
		boolean esValido=true;
		Usuario usuarioActual=(Usuario)sesion.getAttribute("usuarioAdministracion");
		String claveUsuario=UsuarioUtil.obtenerSha2(clave);
		if(!claveUsuario.equals(usuarioActual.getClave())) {
			esValido=false;
			ra.addFlashAttribute("errorPassword", "Contraseña incorrecta");	
		}
		if(!claveNueva.equals(confirmarClave) && esValido) {
			esValido=false;
			ra.addFlashAttribute("errorPassword", "Las contraseñas no coinciden");	
		}
		
		if(!esValido) {
			
			return "redirect:/login/password";
		}
		else {
			String claveValida=UsuarioUtil.obtenerSha2(claveNueva);
			usuarioActual.setClave(claveValida);
			us.editarUsuario(usuarioActual);
			Configuracion adminLogado=cs.obtenerConfiguracion("adminLogado");
			sesion.setAttribute("usuarioAdministracion", usuarioActual);
			ra.addFlashAttribute("passCambiada", "La contraseña ha sido cambiada");
			if(usuarioActual.getNombre().equals("Admin") && adminLogado.getValor().equals("0")) {
				adminLogado.setValor("1");
				cs.guardarConfiguracion(adminLogado);
				return "redirect:/login/inicio";
			}
			
			
			return "redirect:/login/perfil";
		}
		
		
	}
	
	@RequestMapping("/administradores")
	public String mostrarAdministradores(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		if(model.getAttribute("altaUsuario")!=null) {
			model.addAttribute("altaUsuario", model.getAttribute("altaUsuario"));
		}
		
		model.addAttribute("administradores", us.mostrarAdministradores());
		model.addAttribute("usuarioEnCurso", new Usuario());
		model.addAttribute("provincias", JsonUtil.obtenerProvincias());
		return "pages/gestionAdministradores";
	}
	
	@RequestMapping("/busqueda")
	public String busquedaUsuarios() {
		return "pages/mostrarUsuarios";
	}
	
}
