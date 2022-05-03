package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.OpcionMenu;
import curso.java.administracionTienda.modelos.OpcionMenuRepositorio;

@Service
public class OpcionMenuServicio {
	
	@Autowired
	private OpcionMenuRepositorio omr;
	
	public List<OpcionMenu> findAll(String rol){
		
		return omr.findAllByRol(rol);
	}
	
	
	
}
