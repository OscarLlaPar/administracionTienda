package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.modelos.ProveedorRepositorio;

@Service
public class ProveedorServicio {
	@Autowired
	private ProveedorRepositorio proveedorRepositorio;
	
	/**
	 * 
	 * @return
	 */
	
	public List<Proveedor> findAll(){
		return proveedorRepositorio.findAll();
	}
	
	/**
	 * 
	 * @param p
	 */
	
	public void guardarProveedor(Proveedor p) {
		proveedorRepositorio.save(p);
	}
	
	/**
	 * 
	 * @param id
	 */
	
	public void bajaProveedor(int id) {
		proveedorRepositorio.deleteById(id);
	}
	
}
