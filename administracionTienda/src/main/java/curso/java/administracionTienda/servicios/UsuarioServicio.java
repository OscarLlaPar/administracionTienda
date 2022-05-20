package curso.java.administracionTienda.servicios;

import java.sql.Timestamp;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.modelos.RolRepositorio;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;
import curso.java.administracionTienda.utilidades.UsuarioUtil;

@Service
public class UsuarioServicio{
	@Autowired
	private RolRepositorio rolRepositorio;
	
	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	
	
	
	
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	
	
	
	
	public Usuario verificarUsuario(String email, String password) {
		
		Usuario u=usuarioRepositorio.findByEmail(email);

		if (u!=null && u.getClave().equals(UsuarioUtil.obtenerSha2(password))&& 
				!u.getRol().getRol().equals("Cliente") && u.getFechaBaja()==null) {
			return u;
		}
		else {
			return null;
		}
		
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	public Usuario buscarUsuarioPorId(int id) {
		return usuarioRepositorio.findById(id).get();
	}
	
	/**
	 * 
	 * @param email
	 * @return
	 */
	
	public Usuario buscarUsuarioPorEmail(String email) {
		return usuarioRepositorio.findByEmail(email);
	}
	
	/**
	 * 
	 * @param rol
	 * @return
	 */
	
	public List <Usuario> mostrarUsuariosPorRol(String rol){
		return usuarioRepositorio.findAllByRol(rol);
	}
	
	/**
	 * 
	 * @param u
	 */
	
	public Usuario editarUsuario(Usuario u) {
		return usuarioRepositorio.save(u);
	}
	
	/**
	 * 
	 * @param u
	 */
	
	public Usuario bajaUsuario(Usuario u) {
		u.setFechaBaja(new Timestamp(System.currentTimeMillis()));
		return editarUsuario(u);
	}
	
	public Usuario quitarBajaUsuario(Usuario u) {
		u.setFechaBaja(null);
		return editarUsuario(u);
	}
	
	/**
	 * 
	 * @param u
	 * @return
	 */
	
	public String encriptarClave(Usuario u) {
		return UsuarioUtil.obtenerSha2(u.getClave());
	}
	
	public boolean usuarioEnSesion(HttpSession sesion) {
		if(sesion.getAttribute("usuarioAdministracion")==null) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public List<Usuario> mostrarAdministradores(){
		return usuarioRepositorio.mostrarAdministradores();
	}
	
	public List<Usuario> findByNombre(String nombre){
		return usuarioRepositorio.findByNombre(nombre);
	}
	
	public List<Usuario> buscarClientesPorNombre(String nombre){
		return usuarioRepositorio.buscarClientesPorNombre(nombre);
	}
	
	public List<Usuario> buscarEmpleadosPorNombre(String nombre){
		return usuarioRepositorio.buscarEmpleadosPorNombre(nombre);
	}
	
	public List<Usuario> buscarAdministradoresPorNombre(String nombre){
		return usuarioRepositorio.buscarAdministradoresPorNombre(nombre);
	}
	
	
}
