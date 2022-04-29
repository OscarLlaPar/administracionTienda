package curso.java.administracionTienda.modelos;

import org.springframework.data.jpa.repository.JpaRepository;

import curso.java.administracionTienda.entidades.Rol;

public interface RolRepositorio extends JpaRepository<Rol, Integer> {
	Rol findByRol(String rol);
}
