package curso.java.administracionTienda.controladores;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import curso.java.administracionTienda.servicios.ProductoServicio;
import curso.java.administracionTienda.utilidades.ProductoUtil;

@RestController
public class DescargaControlador {
	
	@Autowired
	private ProductoServicio ps;
	
	@RequestMapping("/descarga") 
	   public ResponseEntity<Object> downloadFile() throws IOException  {  
	      File file = ProductoUtil.escribirProductos(ps.obtenerProductos(), "prouctos.xls");
	      InputStreamResource resource = new InputStreamResource(new FileInputStream(file));
	      HttpHeaders headers = new HttpHeaders();
	      
	      headers.add("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
	      headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
	      headers.add("Pragma", "no-cache");
	      headers.add("Expires", "0");
	      
	      ResponseEntity<Object> 
	      responseEntity = ResponseEntity.ok().headers(headers).contentLength(
	         file.length()).contentType(MediaType.parseMediaType("application/txt")).body(resource);
	      
	      return responseEntity;
	   }
}
