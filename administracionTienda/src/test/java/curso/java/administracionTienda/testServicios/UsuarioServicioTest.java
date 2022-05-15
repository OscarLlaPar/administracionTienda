package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.modelos.ProveedorRepositorio;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;
import curso.java.administracionTienda.servicios.ProveedorServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UsuarioServicioTest {
	@Autowired
	private UsuarioServicio us;
	
	@MockBean
	private UsuarioRepositorio ur;
	
	private static ArrayList<Usuario> usuarios = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		
		usuarios.add(new Usuario(1, "empleado1@mail.com",new Rol(2, "Empleado"), "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Empleado 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null));
		usuarios.add(new Usuario(2, "empleado2@mail.com",new Rol(2, "Empleado"), "paso","Empleado 2","Apellido 1","Apellido 2","Direccion 2","Zamora","Zamora","622222222","22222222B",new Timestamp(System.currentTimeMillis())));
		usuarios.add(new Usuario(3, "admin1@mail.com",new Rol(3, "Administrador"), "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Admin 1","Apellido 1","Apellido 2","Direccion 3","Zamora","Zamora","633333333","33333333C",null));
	}
	
	@Test
	public void verificarUsuarioTest() {
		Usuario u=usuarios.get(0);
		when(ur.findByEmail("empleado1@mail.com")).thenReturn(u);
		
		Usuario u2=us.verificarUsuario(u.getEmail(), "paso");
		assertNotNull(u2);
		
	}
	
	@Test
	public void verificarUsuarioTest2() {
		Usuario u=usuarios.get(1);
		when(ur.findByEmail("empleado2@mail.com")).thenReturn(u);
		
		Usuario u2=us.verificarUsuario(u.getEmail(), "paso");
		assertNull(u2);
		
	}
	
	@Test
	public void buscarUsuarioPorIdTest() {
		Usuario u=usuarios.get(0);
		when(ur.findById(1)).thenReturn(Optional.of(u));
		
		Usuario u2=us.buscarUsuarioPorId(1);
		assertNotNull(u2);
		assertEquals(u.getNombre(),u2.getNombre());
		
	}
	
	@Test
	public void buscarUsuarioPorEmail() {
		Usuario u=usuarios.get(0);
		when(ur.findByEmail("empleado1@mail.com")).thenReturn(u);
		
		Usuario u2=us.buscarUsuarioPorEmail(u.getEmail());
		assertNotNull(u2);
		assertEquals(u.getNombre(),u2.getNombre());
	}
	
	@Test
	public void mostrarUsuariosPorRolTest() {
		ArrayList<Usuario> lista=new ArrayList<>();
		lista.add(usuarios.get(0));
		lista.add(usuarios.get(1));
		when(ur.findAllByRol("Empleado")).thenReturn(lista);
		
		List<Usuario> lista2=us.mostrarUsuariosPorRol("Empleado");
		
		assertNotNull(lista2);
		assertTrue(lista2.size()==2);
		assertEquals(lista.get(0),lista2.get(0));
	}
	
	@Test
	public void editarUsuarioTest() {
		Usuario u=usuarios.get(0);
		u.setNombre("Usuario editado");
		when(ur.save(u)).thenReturn(u);
		
		Usuario u2=us.editarUsuario(u);
		assertNotNull(u2);
		assertEquals(u2.getNombre(),"Usuario editado");
	}
	
	@Test
	public void bajaUsuarioTest() {
		Usuario u=usuarios.get(0);
		when(ur.save(u)).thenReturn(u);
		
		Usuario u2=us.bajaUsuario(u);
		assertNotNull(u2);
		assertNotNull(u2.getFechaBaja());
		
	}
	
	@Test 
	public void quitarBajaUsuarioTest(){
		Usuario u=usuarios.get(1);
		when(ur.save(u)).thenReturn(u);
		
		Usuario u2=us.quitarBajaUsuario(u);
		assertNotNull(u2);
		assertNull(u2.getFechaBaja());
	}
	
	@Test
	public void encriptarClaveTest() {
		Usuario u=usuarios.get(1);
		String clave =us.encriptarClave(u);
		
		assertEquals(clave, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944");
	}
	
}
