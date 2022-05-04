package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Configuracion;
import curso.java.administracionTienda.modelos.ConfiguracionRepositorio;

@Service
public class ConfiguracionServicio {
	
	@Autowired
	private ConfiguracionRepositorio cr;
	
	/**
	 * 
	 * @param clave
	 * @return
	 */
	
	public Configuracion obtenerConfiguracion(String clave) {
		return cr.findByClave(clave);
	}
	
	/**
	 * 
	 * @return
	 */
	
	public List<Configuracion> obtenerTodaLaConfiguracion(){
		return cr.findAll();
	}
	
	/**
	 * 
	 * @param c
	 */
	
	public void guardarConfiguracion(Configuracion c) {
		cr.save(c);
	}
	
	/**
	 * 
	 */
	
	public void actualizarNumFacturas() {
		Configuracion c=obtenerConfiguracion("numFacturas");
		int numFacturas=Integer.parseInt(c.getValor());
		numFacturas++;
		c.setValor(String.valueOf(numFacturas));
		guardarConfiguracion(c);
	}
	

	
}
