package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Categoria;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {

}
