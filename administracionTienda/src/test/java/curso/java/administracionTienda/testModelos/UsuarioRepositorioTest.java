package curso.java.administracionTienda.testModelos;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.modelos.ConfiguracionRepositorio;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;

@DataJpaTest
public class UsuarioRepositorioTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private UsuarioRepositorio ur;
	
	
	@Test
	public void findAllByRolTest() {
		entityManager.persistAndFlush(new Usuario(0, "empleado1@mail.com",new Rol(0, "Empleado"), "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Empleado 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		
		List<Usuario> lista=ur.findAllByRol("Empleado");
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.get(0).getEmail()).isEqualTo("empleado1@mail.com");
	}
	
	@Test
	public void findByEmailTest() {
		entityManager.persistAndFlush(new Usuario(0, "empleado1@mail.com",new Rol(0, "Empleado"), "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Empleado 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		
		Usuario u=ur.findByEmail("empleado1@mail.com");
		
		assertThat(u).isNotNull();
		assertThat(u.getEmail()).isEqualTo("empleado1@mail.com");
	}
	
	@Test
	public void mostrarAdministradoresTest() {
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		Rol empleado= new Rol(0,"Empleado");
		entityManager.persistAndFlush(empleado);
		Rol administrador=new Rol(0,"Administrador");
		entityManager.persistAndFlush(administrador);
		
		entityManager.persistAndFlush(new Usuario(0, "empleado1@mail.com",empleado, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Empleado 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		entityManager.persistAndFlush(new Usuario(0, "admin1@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Admin 1","Apellido 1","Apellido 2","Direccion 2","Zamora","Zamora","622222222","22222222B",null));
		entityManager.persistAndFlush(new Usuario(0, "admin2@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Admin","Apellido 1","Apellido 2","Direccion 3","Zamora","Zamora","633333333","33333333C",null));
		
		List<Usuario> lista=ur.mostrarAdministradores();
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(1);
		assertThat(lista.get(0).getEmail()).isEqualTo("admin1@mail.com");
	}
	
	@Test
	public void findByNombreTest() {
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		Rol empleado= new Rol(0,"Empleado");
		entityManager.persistAndFlush(empleado);
		Rol administrador=new Rol(0,"Administrador");
		entityManager.persistAndFlush(administrador);
		
		entityManager.persistAndFlush(new Usuario(0, "empleado1@mail.com",empleado, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		entityManager.persistAndFlush(new Usuario(0, "admin1@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 2","Apellido 1","Apellido 2","Direccion 2","Zamora","Zamora","622222222","22222222B",null));
		entityManager.persistAndFlush(new Usuario(0, "admin2@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Admin","Apellido 1","Apellido 2","Direccion 3","Zamora","Zamora","633333333","33333333C",null));
		
		List<Usuario> lista=ur.findByNombre("Nombre");
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombre()).contains("Nombre");
		assertThat(lista.get(1).getNombre()).contains("Nombre");
	}
	
	@Test
	public void buscarClientesPorNombreTest() {
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		Rol empleado= new Rol(0,"Empleado");
		entityManager.persistAndFlush(empleado);
		Rol administrador=new Rol(0,"Administrador");
		entityManager.persistAndFlush(administrador);
		
		entityManager.persistAndFlush(new Usuario(0, "cliente1@mail.com",cliente, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		entityManager.persistAndFlush(new Usuario(0, "cliente2@mail.com",cliente, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 2","Apellido 1","Apellido 2","Direccion 2","Zamora","Zamora","622222222","22222222B",null));
		entityManager.persistAndFlush(new Usuario(0, "admin1@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 3","Apellido 1","Apellido 2","Direccion 3","Zamora","Zamora","633333333","33333333C",null));
		
		List<Usuario> lista=ur.buscarClientesPorNombre("Nombre");
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombre()).contains("Nombre");
		assertThat(lista.get(1).getNombre()).contains("Nombre");
		
	}
	
	@Test
	public void buscarEmpleadosPorNombreTest() {
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		Rol empleado= new Rol(0,"Empleado");
		entityManager.persistAndFlush(empleado);
		Rol administrador=new Rol(0,"Administrador");
		entityManager.persistAndFlush(administrador);
		
		entityManager.persistAndFlush(new Usuario(0, "empleado1@mail.com",empleado, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		entityManager.persistAndFlush(new Usuario(0, "empleado2@mail.com",empleado, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 2","Apellido 1","Apellido 2","Direccion 2","Zamora","Zamora","622222222","22222222B",null));
		entityManager.persistAndFlush(new Usuario(0, "admin1@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 3","Apellido 1","Apellido 2","Direccion 3","Zamora","Zamora","633333333","33333333C",null));
		
		List<Usuario> lista=ur.buscarEmpleadosPorNombre("Nombre");
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombre()).contains("Nombre");
		assertThat(lista.get(1).getNombre()).contains("Nombre");
	}
	
	@Test
	public void buscarAdministradoresPorNombre() {
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		Rol empleado= new Rol(0,"Empleado");
		entityManager.persistAndFlush(empleado);
		Rol administrador=new Rol(0,"Administrador");
		entityManager.persistAndFlush(administrador);
		
		entityManager.persistAndFlush(new Usuario(0, "cliente1@mail.com",cliente, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		entityManager.persistAndFlush(new Usuario(0, "admin1@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 2","Apellido 1","Apellido 2","Direccion 2","Zamora","Zamora","622222222","22222222B",null));
		entityManager.persistAndFlush(new Usuario(0, "admin2@mail.com",administrador, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 3","Apellido 1","Apellido 2","Direccion 3","Zamora","Zamora","633333333","33333333C",null));
		
		List<Usuario> lista=ur.buscarAdministradoresPorNombre("Nombre");
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombre()).contains("Nombre");
		assertThat(lista.get(1).getNombre()).contains("Nombre");
	}
	
}
