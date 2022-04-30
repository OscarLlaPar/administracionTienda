package curso.java.administracionTienda.controladores;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.utilidades.ProductoUtil;

@RestController
public class SubidaControlador {
	
	@Autowired
	private ProductoServicio ps;
	
	@RequestMapping("/subida")
	public String fileUpload(@RequestParam MultipartFile archivo) throws IOException {
	      File convertFile = new File( archivo.getOriginalFilename());
	      convertFile.createNewFile();
	      ArrayList<Producto> productos= ProductoUtil.leerFichero(convertFile);
	      ps.guardarProductos(productos);
	      return "redirect:/productos";
	   }
}
