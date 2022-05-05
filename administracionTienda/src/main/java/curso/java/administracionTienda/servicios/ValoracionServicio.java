package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Valoracion;
import curso.java.administracionTienda.modelos.ValoracionRepositorio;

@Service
public class ValoracionServicio {

	@Autowired
	private ValoracionRepositorio vr;
	
	public List<Valoracion> obtenerValoraciones(){
		return vr.findAll();
	}
	
	public void eliminarValoracion(int id) {
		vr.deleteById(id);
	}
	
}
