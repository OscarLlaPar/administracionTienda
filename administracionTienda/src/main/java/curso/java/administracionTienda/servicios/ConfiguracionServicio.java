package curso.java.administracionTienda.servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.modelos.ConfiguracionRepositorio;

@Service
public class ConfiguracionServicio {
	
	@Autowired
	private ConfiguracionRepositorio cr;
	
	public Configuracion obtenerConfiguracion(String clave) {
		return cr.findByClave(clave);
	}
}
