package curso.java.administracionTienda.servicios;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.modelos.CategoriaRepositorio;

@Service
public class CategoriaServicio {

	@Autowired
	private CategoriaRepositorio categoriaRepositorio;
	
	public List<Categoria> obtenerCategorias(){
		return categoriaRepositorio.findAll();
	}
	
	public void guardarCategoria(Categoria c) {
		categoriaRepositorio.save(c);
	}
	
}
