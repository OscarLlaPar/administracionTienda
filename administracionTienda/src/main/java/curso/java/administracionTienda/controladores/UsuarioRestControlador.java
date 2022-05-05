package curso.java.administracionTienda.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@RestController
@RequestMapping("/api")
public class UsuarioRestControlador {

	@Autowired
	private UsuarioServicio us;
	
	@GetMapping(value="/usuarios", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<Usuario> mostrarProductos(@RequestParam String nombre){
		return us.findByNombre(nombre);
	}
	
}
