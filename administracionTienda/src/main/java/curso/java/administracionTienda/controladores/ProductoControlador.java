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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import curso.java.administracionTienda.entidades.Categoria;
import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.servicios.CategoriaServicio;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.servicios.ProveedorServicio;
import curso.java.administracionTienda.servicios.UsuarioServicio;
import curso.java.administracionTienda.utilidades.ProductoUtil;
import jxl.Sheet;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.read.biff.BiffException;
import net.bytebuddy.matcher.ModifierMatcher.Mode;

@Controller
@RequestMapping("/productos")
public class ProductoControlador {
	
	@Autowired
	private ProductoServicio ps;
	
	@Autowired
	private CategoriaServicio cs;
	
	@Autowired
	private ProveedorServicio prs;
	
	@Autowired
	private UsuarioServicio us;
	
	@RequestMapping("")
	public String cargarProductos(Model model, HttpSession sesion) {
		if(!us.usuarioEnSesion(sesion)) {
			return "index";
		}
		
		if(model.getAttribute("altaProducto")!=null) {
			model.addAttribute("altaProducto", model.getAttribute("altaProducto"));
		}
		
		if(model.getAttribute("altaCategoria")!=null) {
			model.addAttribute("altaCategoria", model.getAttribute("altaCategoria"));
		}
		
		
		List<Producto> productos = ps.obtenerProductos();
		model.addAttribute("productos", productos);
		model.addAttribute("categorias", cs.obtenerCategorias());
		model.addAttribute("proveedores", prs.findAll());
		System.out.println(prs.findAll());
		model.addAttribute("productoEnCurso", new Producto());
		return "pages/gestionProductos";
	}
	
	@RequestMapping("/baja")
	public String darDeBaja(@RequestParam int id) {
		System.out.println("Pasa por baja "+id);
		ps.darDeBaja(id);
		return "redirect:/productos";
	}
	
	@RequestMapping("/quitarBaja")
	public String quitarBaja(@RequestParam int id) {
		ps.quitarBaja(id);
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
		model.addAttribute("proveedores", prs.findAllSinBaja());
		return "pages/altaProducto";
	}
	
	@RequestMapping("/altaProducto")
	public String altaProducto(@ModelAttribute Producto productoEnCurso, RedirectAttributes ra) {
		System.out.println(productoEnCurso);
		ps.editarProducto(productoEnCurso);
		ra.addFlashAttribute("altaProducto", "Nuevo producto añadido");
		return "redirect:/productos";
	}
	
	@RequestMapping("/nuevaCategoria")
	public String nuevaCategoria(Model model) {
		model.addAttribute("categoriaEnCurso", new Producto());
		return "pages/altaCategoria";
	}
	
	@RequestMapping("/altaCategoria")
	public String altaCategoria(@ModelAttribute Categoria categoriaEnCurso, RedirectAttributes ra) {
		System.out.println(categoriaEnCurso);
		cs.guardarCategoria(categoriaEnCurso);
		ra.addFlashAttribute("altaCategoria", "Nueva categoría añadida");
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
	      ArrayList<Producto> productos=leerFichero(convertFile);
	      ps.guardarProductos(productos);
		return "redirect:/productos";
	}
	
	public ArrayList<Producto> leerFichero(File archivo){
		ArrayList<Producto> listaProductos = new ArrayList<>();
        	
            Workbook libro;
			try {
				
				WorkbookSettings ws = new WorkbookSettings(); 
				ws.setEncoding("UTF-8");
				libro = Workbook.getWorkbook(archivo,ws);
				Sheet hoja = libro.getSheet(0);
	            for(int i=1; i<hoja.getRows(); i++){
	                
	                String nombre=hoja.getCell(1,i).getContents();
	                String descripcion=hoja.getCell(2,i).getContents();
	                int idCategoria=Integer.parseInt(hoja.getCell(3,i).getContents());
	                double precio=Double.parseDouble(hoja.getCell(4,i).getContents().replace(',','.'));
	                int stock=Integer.parseInt(hoja.getCell(5,i).getContents());
	                
	                
	                float impuesto=Float.parseFloat(hoja.getCell(8,i).getContents());
	                String imagen=hoja.getCell(9,i).getContents();
	                String audio=hoja.getCell(10,i).getContents();
	                int idProveedor=Integer.parseInt(hoja.getCell(11,i).getContents());
	                
	                Producto p=new Producto(0,idCategoria,nombre,descripcion,precio,stock,null,null,impuesto,imagen,audio, prs.findById(idProveedor));
	                listaProductos.add(p);
	            }
			} catch (BiffException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            
            return listaProductos;
    }
	
}
