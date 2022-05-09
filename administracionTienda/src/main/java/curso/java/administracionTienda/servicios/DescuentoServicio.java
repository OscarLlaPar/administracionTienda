package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Descuento;
import curso.java.administracionTienda.modelos.DescuentoRepositorio;

@Service
public class DescuentoServicio {

	@Autowired
	private DescuentoRepositorio dr;
	
	public List<Descuento> findAll(){
		return dr.findAll();
	}
	
	public void guardarDescuento(Descuento d) {
		dr.save(d);
	}
	
}
