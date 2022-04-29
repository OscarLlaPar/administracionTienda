package curso.java.administracionTienda.servicios;

import java.util.List;

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
	
	public Usuario verificarUsuario(String email, String password) {
		
		Usuario u=usuarioRepositorio.findById(email).get();

		if (u!=null && u.getClave().equals(UsuarioUtil.obtenerSha2(u.getNombre()+password))&& !u.getRol().getRol().equals("Cliente")) {
			return u;
		}
		else {
			return null;
		}
		
	}
	
	public List <Usuario> mostrarUsuariosPorRol(String rol){
		return usuarioRepositorio.findAllByRol(rol);
	}

	
}
