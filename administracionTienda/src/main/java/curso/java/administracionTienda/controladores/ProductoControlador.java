package curso.java.administracionTienda.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.servicios.CategoriaServicio;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.utilidades.ProductoUtil;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {
	
	@Autowired
	private ProductoServicio ps;
	
	@Autowired
	private CategoriaServicio cs;
	
	@RequestMapping("")
	public String cargarProductos(Model model, HttpSession sesion) {
		
		List<Producto> productos = ps.obtenerProductos();
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", cs.obtenerCategorias());
		model.addAttribute("productoEnCurso", new Producto());
		return "pages/gestionProductos";
	}
	
	@RequestMapping("/baja")
	public String darDeBaja(@RequestParam int id) {
		System.out.println("Pasa por baja "+id);
		ps.darDeBaja(id);
		return "redirect:/productos";
	}
	
	@RequestMapping("/editar")
	public String editar(@ModelAttribute Producto productoEnCurso ) {
		System.out.println("Pasa por editar "+productoEnCurso);
		ps.editarProducto(productoEnCurso);
		return "redirect:/productos";
	}
	
	@RequestMapping("/alta")
	public String alta(Model model) {
		model.addAttribute("productoEnCurso", new Producto());
		model.addAttribute("categorias", cs.obtenerCategorias());
		return "pages/altaProducto";
	}
	
	@RequestMapping("/altaProducto")
	public String altaProducto(@ModelAttribute Producto productoEnCurso) {
		System.out.println(productoEnCurso);
		ps.editarProducto(productoEnCurso);
		return "redirect:/productos";
	}
	
	@RequestMapping("/nuevaCategoria")
	public String nuevaCategoria(Model model) {
		model.addAttribute("categoriaEnCurso", new Producto());
		return "pages/altaCategoria";
	}
	
	@RequestMapping("/altaCategoria")
	public String altaCategoria(@ModelAttribute Categoria categoriaEnCurso) {
		System.out.println(categoriaEnCurso);
		cs.guardarCategoria(categoriaEnCurso);
		return "redirect:/productos";
	}
	
	@RequestMapping("/exportar")
	public String exportar() {
		ProductoUtil.escribirProductos(ps.obtenerProductos(), "prouctos.xls");
		return "redirect:/productos";
	}
	
	@RequestMapping("/importar")
	public String importar(@RequestParam MultipartFile archivo) throws IOException {
		File convertFile = new File( archivo.getOriginalFilename());
	      convertFile.createNewFile();
	      FileOutputStream fout = new FileOutputStream(convertFile);
	      fout.write(archivo.getBytes());
	      fout.close();
	      ArrayList<Producto> productos= ProductoUtil.leerFichero(convertFile);
	      ps.guardarProductos(productos);
		return "redirect:/productos";
	}
	
}
