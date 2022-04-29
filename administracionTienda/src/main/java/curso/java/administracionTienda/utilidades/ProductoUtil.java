package curso.java.administracionTienda.utilidades;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import curso.java.administracionTienda.entidades.Producto;
import jxl.Sheet;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

public class ProductoUtil {
	public static ArrayList<Producto> leerFichero(String ruta){
		File archivo = new File(ruta);
		ArrayList<Producto> listaProductos = new ArrayList<>();
        
            Workbook libro;
			try {
				libro = Workbook.getWorkbook(archivo);
				Sheet hoja = libro.getSheet(0);
	            for(int i=1; i<hoja.getRows(); i++){
	                int id=Integer.parseInt(hoja.getCell(0,i).getContents());
	                String nombre=hoja.getCell(1,i).getContents();
	                String descripcion=hoja.getCell(2,i).getContents();
	                double precioSinIva=Double.parseDouble(hoja.getCell(3,i).getContents());
	                int iva=Integer.parseInt(hoja.getCell(4,i).getContents());
	                int stock=Integer.parseInt(hoja.getCell(5,i).getContents());
	                
	                Producto p=new Producto();
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
    
    public static void escribirProductos(List<Producto> lista, String ruta){
    	File archivo = new File(ruta); 
    	Workbook libro;
		try {
			libro = Workbook.getWorkbook(archivo);
			WritableWorkbook ww = Workbook.createWorkbook(archivo, libro);
	        WritableSheet hoja = ww.getSheet(0);
	    		for(Producto p: lista) {
	    			
	    	            
	    	            jxl.write.Number id = new jxl.write.Number(0, p.getId()+1, p.getId());
	    	            hoja.addCell(id);
	    	            
	    	            jxl.write.Label nombre = new jxl.write.Label(1, p.getId()+1, p.getNombre());
	    	            hoja.addCell(nombre);
	    	            
	    	            jxl.write.Label descripcion = new jxl.write.Label(2, p.getId()+1, p.getDescripcion());
	    	            hoja.addCell(descripcion);
	    	            
	    	            jxl.write.Number precio = new jxl.write.Number(3, p.getId()+1, p.getPrecio());
	    	            hoja.addCell(precio);
	    	            
	    	            jxl.write.Number stock = new jxl.write.Number(4, p.getId()+1, p.getStock());
	    	            hoja.addCell(stock);
	    	            
	    	            jxl.write.DateTime fechaAlta= new jxl.write.DateTime(5, p.getId()+1, p.getFechaAlta());
	    	            hoja.addCell(fechaAlta);
	    	            
	    	            jxl.write.DateTime fechaBaja= new jxl.write.DateTime(6, p.getId()+1, p.getFechaBaja());
	    	            hoja.addCell(fechaBaja);
	    	            
	    	            jxl.write.Number impuesto = new jxl.write.Number(7, p.getId()+1, p.getImpuesto());
	    	            hoja.addCell(impuesto);
	    	            
	    	            jxl.write.Label imagen = new jxl.write.Label(8, p.getId()+1, p.getImagen());
	    	            hoja.addCell(imagen);
	    	            
	    		}	
	           
	            ww.write();
	            ww.close();
		} catch (BiffException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
         
    }
}
