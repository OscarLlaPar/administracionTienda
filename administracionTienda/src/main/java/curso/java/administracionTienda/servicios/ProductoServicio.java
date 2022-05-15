package curso.java.administracionTienda.servicios;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties.Sort;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.modelos.CategoriaRepositorio;
import curso.java.administracionTienda.modelos.ProductoRepositorio;

@Service
public class ProductoServicio {
	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	@Autowired
	private ProductoRepositorio productoRepositorio;
	
	/**
	 * 
	 * @return
	 */
	
	public List<Producto> obtenerProductos() {
		return productoRepositorio.findAll();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	public Producto obtenerProducto(int id) {
		return productoRepositorio.findById(id).get();
	}
	
	/**
	 * 
	 * @param id
	 */
	
	public Producto darDeBaja(int id) {
		Producto p= obtenerProducto(id);
		p.setFechaBaja(new Timestamp(System.currentTimeMillis()));
		return productoRepositorio.save(p);
		
	}
	
	public Producto quitarBaja(int id) {
		Producto p= obtenerProducto(id);
		p.setFechaBaja(null);
		return productoRepositorio.save(p);
	}
	
	/**
	 * 
	 * @param p
	 */
	
	public Producto editarProducto(Producto p) {
		return productoRepositorio.save(p);
	}
	
	/**
	 * 
	 * @param productos
	 */
	
	public List<Producto> guardarProductos(ArrayList<Producto> productos) {
		return productoRepositorio.saveAll(productos);
	}
	
	/**
	 * 
	 * @return
	 */
	
	public LinkedHashSet<Producto> findAllSortByValoracion(){
		return productoRepositorio.findAllSortByValoracion();
	}
	
	/**
	 * 
	 * @return
	 */
	
	public LinkedHashSet<Producto> findAllSortByPedidos(){
		return productoRepositorio.findAllSortByPedidos();
	}
	
	public List<Producto> findByNombre(String nombre){
		return productoRepositorio.findByNombre(nombre);
	}
	
}
