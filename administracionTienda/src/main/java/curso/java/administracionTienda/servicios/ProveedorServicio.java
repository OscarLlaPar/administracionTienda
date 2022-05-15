package curso.java.administracionTienda.servicios;

import java.sql.Timestamp;
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
	
	public Proveedor guardarProveedor(Proveedor p) {
		return proveedorRepositorio.save(p);
	}
	
	/**
	 * 
	 * @param id
	 */
	
	public Proveedor bajaProveedor(int id) {
		Proveedor p=findById(id);
		p.setFechaBaja(new Timestamp(System.currentTimeMillis()));
		proveedorRepositorio.save(p);
		return p;
	}
	
	public Proveedor findById(int id) {
		return proveedorRepositorio.findById(id).get();
	}
	
	public Proveedor quitarBajaProveedor(int id) {
		Proveedor p=findById(id);
		p.setFechaBaja(null);
		proveedorRepositorio.save(p);
		return p;
	}
	
	public List<Proveedor> findAllSinBaja(){
		return proveedorRepositorio.findAllSinBaja();
	}
	
	public List<Proveedor> buscarProveedoresPorNombre(String nombre){
		return proveedorRepositorio.buscarProveedoresPorNombre(nombre);
	}
	
}
