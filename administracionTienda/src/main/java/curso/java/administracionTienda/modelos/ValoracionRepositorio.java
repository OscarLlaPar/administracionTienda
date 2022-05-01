package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Valoracion;

public interface ValoracionRepositorio extends JpaRepository<Valoracion, Integer> {

}
