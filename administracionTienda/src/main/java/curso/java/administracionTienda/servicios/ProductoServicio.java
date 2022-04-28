package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
	
}
