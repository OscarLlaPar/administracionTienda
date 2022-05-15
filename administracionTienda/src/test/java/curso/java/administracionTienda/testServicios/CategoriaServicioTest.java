package curso.java.administracionTienda.testServicios;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.modelos.CategoriaRepositorio;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;
import curso.java.administracionTienda.servicios.CategoriaServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CategoriaServicioTest {

	@Autowired
	private CategoriaServicio cs;
	
	@MockBean
	private CategoriaRepositorio cr;
	
	private static ArrayList<Categoria> categorias = new ArrayList<>();
	
	@BeforeAll
	public static void beforeAll() {
		categorias.add(new Categoria(1, "Categoria1", "Primera categoria"));
		categorias.add(new Categoria(2, "Categoria2", "Segunda categoria"));
		categorias.add(new Categoria(3, "Categoria3", "Tercera categoria"));
	}
	
	
	@Test
	public void obtenerCategoriasTest() {
		List <Categoria> lista=categorias;
		when(cr.findAll()).thenReturn(lista);
		assertNotNull(cs.obtenerCategorias());
		assertTrue(cs.obtenerCategorias().size()>0);
	}
	
	@Test
	public void obtenerCategoriaIdTest() {
		Categoria c=categorias.get(0);
		when(cr.findById(1)).thenReturn(Optional.of(c));
		Categoria c2=cs.obtenerCategoria(1);
		assertNotNull(c2);
		assertEquals(c2.getNombre(), "Categoria1");
		
	}
	
	@Test
	public void guardarCategoriaTest() {
		Categoria nueva=new Categoria(4, "Categoria4", "Cuarta categoria");
		when(cr.save(nueva)).thenReturn(nueva);
		List <Categoria> lista=categorias;
		when(cr.findAll()).thenReturn(lista);
		Categoria insertada = cs.guardarCategoria(nueva);
		assertNotNull(insertada);
		assertEquals(insertada.getNombre(),"Categoria4");
	}
	
	
}
