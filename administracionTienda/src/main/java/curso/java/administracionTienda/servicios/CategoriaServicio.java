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
	
	
	/**
	 * 
	 * @return
	 */
	
	public List<Categoria> obtenerCategorias(){
		return categoriaRepositorio.findAll();
	}
	
	/**
	 * 
	 * @param id
	 * @return
	 */
	
	public Categoria obtenerCategoria(int id) {
		return categoriaRepositorio.findById(id).get();
	}
	
	/**
	 * 
	 * @param c
	 */
	
	public Categoria guardarCategoria(Categoria c) {
		return categoriaRepositorio.save(c);
	}
	
}
