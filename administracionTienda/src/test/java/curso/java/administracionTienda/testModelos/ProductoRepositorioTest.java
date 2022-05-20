package curso.java.administracionTienda.testModelos;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.entidades.Proveedor;
import curso.java.administracionTienda.entidades.Rol;
import curso.java.administracionTienda.entidades.Usuario;
import curso.java.administracionTienda.entidades.Valoracion;
import curso.java.administracionTienda.modelos.ProductoRepositorio;
import curso.java.administracionTienda.modelos.UsuarioRepositorio;
import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.entidades.DetallePedido;
import curso.java.administracionTienda.entidades.Pedido;

@DataJpaTest
public class ProductoRepositorioTest {
	@Autowired
	private TestEntityManager entityManager;
	@Autowired
	private ProductoRepositorio pr;
	
	@Test
	public void findAllSortByValoracionTest() {
		Proveedor pv=new Proveedor(0,"Proveedor 1","a","a","a","a","a","a",null);
		entityManager.persistAndFlush(pv);
		
		Categoria c=new Categoria(0,"Categoria 1","Descripcion");
		entityManager.persistAndFlush(c);
		
		Producto p1= new Producto(0,c,"Producto 1", "Pues un producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen1","/audio1",pv);
		entityManager.persistAndFlush(p1);
		Producto p2= new Producto(0,c,"Producto 2", "Pues otro producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen2","/audio2",pv);
		entityManager.persistAndFlush(p2);
		Producto p3= new Producto(0,c,"asdfasdfasdfasd", "Pues otro producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen3","/audio3",pv);
		entityManager.persistAndFlush(p3);
		
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		
		Usuario u=new Usuario(0, "cliente1@mail.com",cliente, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null);
		entityManager.persistAndFlush(u);
		
		Valoracion v1= new Valoracion(0,p1,u,5,"a");
		entityManager.persistAndFlush(v1);
		Valoracion v2= new Valoracion(0,p2,u,10,"a");
		entityManager.persistAndFlush(v2);
		
		LinkedHashSet<Producto> lista=pr.findAllSortByValoracion();
		Iterator<Producto> it=lista.iterator();
		
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(2);
		assertThat(it.next().getNombre()).isEqualTo("Producto 2");
		assertThat(it.next().getNombre()).isEqualTo("Producto 1");
		
		
	}
	
	@Test
	public void findAllSortByPedidosTest() {
		Proveedor pv=new Proveedor(0,"Proveedor 1","a","a","a","a","a","a",null);
		entityManager.persistAndFlush(pv);
		
		Categoria c=new Categoria(0,"Categoria 1","Descripcion");
		entityManager.persistAndFlush(c);
		
		Producto p1= new Producto(0,c,"Producto 1", "Pues un producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen1","/audio1",pv);
		entityManager.persistAndFlush(p1);
		Producto p2= new Producto(0,c,"Producto 2", "Pues otro producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen2","/audio2",pv);
		entityManager.persistAndFlush(p2);
		Producto p3= new Producto(0,c,"asdfasdfasdfasd", "Pues otro producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen3","/audio3",pv);
		entityManager.persistAndFlush(p3);
		
		Rol cliente=new Rol(0,"Cliente");
		entityManager.persistAndFlush(cliente);
		
		Usuario u=new Usuario(0, "cliente1@mail.com",cliente, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944","Nombre 1","Apellido 1","Apellido 2","Direccion 1","Zamora","Zamora","611111111","11111111A",null);
		entityManager.persistAndFlush(u);
		
		Pedido pd1=new Pedido(0,u,new Timestamp(System.currentTimeMillis()),"Tarjeta","E",null,100,null);
		entityManager.persistAndFlush(pd1);
		Pedido pd2=new Pedido(0,u,new Timestamp(System.currentTimeMillis()),"Tarjeta","E",null,100,null);
		entityManager.persistAndFlush(pd2);
		
		DetallePedido dp1=new DetallePedido(0,pd1,p1,12,1,12,12,"E");
		entityManager.persistAndFlush(dp1);
		DetallePedido dp2=new DetallePedido(0,pd1,p2,12,1,12,12,"E");
		entityManager.persistAndFlush(dp2);
		DetallePedido dp3=new DetallePedido(0,pd2,p2,12,1,12,12,"E");
		entityManager.persistAndFlush(dp3);
		
		LinkedHashSet<Producto> lista=pr.findAllSortByPedidos();
		Iterator<Producto> it=lista.iterator();
		
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(2);
		assertThat(it.next().getNombre()).isEqualTo("Producto 2");
		assertThat(it.next().getNombre()).isEqualTo("Producto 1");
	}
	
	@Test
	public void findByNombreTest() {
		Proveedor pv=new Proveedor(0,"Proveedor 1","a","a","a","a","a","a",null);
		entityManager.persistAndFlush(pv);
		
		Categoria c=new Categoria(0,"Categoria 1","Descripcion");
		entityManager.persistAndFlush(c);
		
		Producto p1= new Producto(0,c,"Producto 1", "Pues un producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen1","/audio1",pv);
		entityManager.persistAndFlush(p1);
		Producto p2= new Producto(0,c,"Producto 2", "Pues otro producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen2","/audio2",pv);
		entityManager.persistAndFlush(p2);
		Producto p3= new Producto(0,c,"asdfasdfasdfasd", "Pues otro producto",12,12,new Timestamp(System.currentTimeMillis()), null, 12, "/imagen3","/audio3",pv);
		entityManager.persistAndFlush(p3);
		
		List<Producto> lista=pr.findByNombre("Producto");
		assertThat(lista.size()).isNotEqualTo(0);
		assertThat(lista.size()).isEqualTo(2);
		assertThat(lista.get(0).getNombre()).contains("Producto");
		assertThat(lista.get(1).getNombre()).contains("Producto");
	}
}
