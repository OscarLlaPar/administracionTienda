package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Descuento;

public interface DescuentoRepositorio extends JpaRepository<Descuento, Integer> {

}
