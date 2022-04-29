package curso.java.administracionTienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.modelos.RolRepositorio;

@Service
public class RolServicio {
	@Autowired
	private RolRepositorio rolRepositorio;
	
	public Rol obtenerRol(int id) {
		return rolRepositorio.findById(id).get();
	}
	
	public Rol obtenerRol(String rol) {
		return rolRepositorio.findByRol(rol);
	}
}
