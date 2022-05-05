package curso.java.administracionTienda.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.servicios.ProductoServicio;

@RestController
@RequestMapping("/api")
public class ProductoRestControlador {

	@Autowired
	private ProductoServicio ps;
	
	@GetMapping(value="/productos", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Producto> mostrarProductos(@RequestParam String nombre){
		return ps.findByNombre(nombre);
	}
	
}
