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

		if (u!=null && u.getClave().equals(UsuarioUtil.obtenerSha2(password))&& !u.getRol().getRol().equals("Cliente") && u.getFechaBaja()==null) {
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
	
	public void editarUsuario(Usuario u) {
		usuarioRepositorio.save(u);
	}
	
	/**
	 * 
	 * @param u
	 */
	
	public void bajaUsuario(Usuario u) {
		u.setFechaBaja(new Timestamp(System.currentTimeMillis()));
		editarUsuario(u);
	}
	
	public void quitarBajaUsuario(Usuario u) {
		u.setFechaBaja(null);
		editarUsuario(u);
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
	
}
