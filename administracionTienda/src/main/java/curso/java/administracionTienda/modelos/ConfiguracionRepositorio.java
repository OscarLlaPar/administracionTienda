package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Configuracion;

public interface ConfiguracionRepositorio extends JpaRepository<Configuracion, Integer> {
	Configuracion findByClave(String clave);
}
