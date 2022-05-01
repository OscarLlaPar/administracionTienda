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
	
	public List<Producto> obtenerProductos() {
		return productoRepositorio.findAll();
	}
	
	public Producto obtenerProducto(int id) {
		return productoRepositorio.findById(id).get();
	}
	
	public void darDeBaja(int id) {
		Producto p= obtenerProducto(id);
		p.setFechaBaja(new Timestamp(System.currentTimeMillis()));
		productoRepositorio.save(p);
	}
	
	public void editarProducto(Producto p) {
		productoRepositorio.save(p);
	}
	public void guardarProductos(ArrayList<Producto> productos) {
		productoRepositorio.saveAll(productos);
	}
	
	public LinkedHashSet<Producto> findAllSortByValoracion(){
		return productoRepositorio.findAllSortByValoracion();
	}
	
	public LinkedHashSet<Producto> findAllSortByPedidos(){
		return productoRepositorio.findAllSortByPedidos();
	}
}
