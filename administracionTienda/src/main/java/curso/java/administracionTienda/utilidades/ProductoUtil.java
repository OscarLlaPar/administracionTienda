package curso.java.administracionTienda.utilidades;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;

import curso.java.administracionTienda.entidades.Producto;
import curso.java.administracionTienda.servicios.CategoriaServicio;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ProductoUtil {
	
	@Autowired
	private CategoriaServicio cs;
	
	public static ArrayList<Producto> leerFichero(File archivo){
		ArrayList<Producto> listaProductos = new ArrayList<>();
        
            Workbook libro;
			try {
				libro = Workbook.getWorkbook(archivo);
				Sheet hoja = libro.getSheet(0);
	            for(int i=1; i<hoja.getRows(); i++){
	                int id=Integer.parseInt(hoja.getCell(0,i).getContents());
	                int idCategoria=Integer.parseInt(hoja.getCell(1,i).getContents());
	                String nombre=hoja.getCell(2,i).getContents();
	                String descripcion=hoja.getCell(3,i).getContents();
	                double precio=Double.parseDouble(hoja.getCell(4,i).getContents());
	                int stock=Integer.parseInt(hoja.getCell(5,i).getContents());
	                
	                
	                float impuesto=Float.parseFloat(hoja.getCell(8,i).getContents());
	                String imagen=hoja.getCell(9,i).getContents();
	                String audio=hoja.getCell(10,i).getContents();
	                
	                Producto p=new Producto(id,idCategoria,nombre,descripcion,precio,stock,null,null,impuesto,imagen,audio,null);
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
    
    public static File escribirProductos(List<Producto> lista, String ruta) {
    	
		try  {
			File archivo = new ClassPathResource(ruta).getFile(); 
	    	archivo.createNewFile();
	    	System.out.println( archivo.getAbsolutePath());
	    	/*Workbook libro;
			libro = Workbook.getWorkbook(archivo);*/
			WritableWorkbook ww = Workbook.createWorkbook(new ClassPathResource(ruta).getFile());
	        WritableSheet hoja = ww.createSheet("Productos",0);
	    	
	        jxl.write.Label col1 = new jxl.write.Label(0,0,"ID");
	        hoja.addCell(col1);
	        jxl.write.Label col2 = new jxl.write.Label(1,0,"Nombre");
	        hoja.addCell(col2);
	        jxl.write.Label col3 = new jxl.write.Label(2,0,"Descripción");
	        hoja.addCell(col3);
	        jxl.write.Label col4 = new jxl.write.Label(3,0,"Precio");
	        hoja.addCell(col4);
	        jxl.write.Label col5 = new jxl.write.Label(4,0,"Stock");
	        hoja.addCell(col5);
	        jxl.write.Label col6 = new jxl.write.Label(5,0,"Fecha de alta");
	        hoja.addCell(col6);
	        jxl.write.Label col7 = new jxl.write.Label(6,0,"Fecha de baja");
	        hoja.addCell(col7);
	        jxl.write.Label col8 = new jxl.write.Label(7,0,"Impuesto");
	        hoja.addCell(col8);
	        jxl.write.Label col9 = new jxl.write.Label(8,0,"Ruta de la imagen");
	        hoja.addCell(col9);
	       
	        for(Producto p: lista) {
	    			
	    	            
	    	            jxl.write.Number id = new jxl.write.Number(0, p.getId(), p.getId());
	    	            hoja.addCell(id);
	    	            
	    	            jxl.write.Label nombre = new jxl.write.Label(1, p.getId(), p.getNombre());
	    	            hoja.addCell(nombre);
	    	            
	    	            jxl.write.Label descripcion = new jxl.write.Label(2, p.getId(), p.getDescripcion());
	    	            hoja.addCell(descripcion);
	    	            
	    	            jxl.write.Number precio = new jxl.write.Number(3, p.getId(), p.getPrecio());
	    	            hoja.addCell(precio);
	    	            
	    	            jxl.write.Number stock = new jxl.write.Number(4, p.getId(), p.getStock());
	    	            hoja.addCell(stock);
	    	            
	    	            jxl.write.Label fechaAlta= new jxl.write.Label(5, p.getId(), p.getFechaAlta().toString());
	    	            hoja.addCell(fechaAlta);
	    	            if(p.getFechaBaja()!=null){
	    	            jxl.write.Label fechaBaja= new jxl.write.Label(6, p.getId(), p.getFechaBaja().toString());
	    	            hoja.addCell(fechaBaja);
	    	            }
	    	            jxl.write.Number impuesto = new jxl.write.Number(7, p.getId(), p.getImpuesto());
	    	            hoja.addCell(impuesto);
	    	            
	    	            jxl.write.Label imagen = new jxl.write.Label(8, p.getId(), p.getImagen());
	    	            hoja.addCell(imagen);
	    	            
	    	            jxl.write.Label audio = new jxl.write.Label(9, p.getId(), p.getAudio());
	    	            hoja.addCell(audio);
	    	            
	    	            jxl.write.Label proveedor = new jxl.write.Label(10, p.getId(), p.getProveedor().getNombre());
	    	            hoja.addCell(proveedor);
	    	            
	    		}	
	           
	            ww.write();
	            ww.close();
	            return archivo;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RowsExceededException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (WriteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
         
    }
}
