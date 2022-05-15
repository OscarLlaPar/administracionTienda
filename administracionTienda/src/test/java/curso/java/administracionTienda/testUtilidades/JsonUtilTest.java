package curso.java.administracionTienda.testUtilidades;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import curso.java.administracionTienda.utilidades.JsonUtil;

public class JsonUtilTest {
	
	@Test
	public void comprobarJson() {
		ArrayList<String> provincias=JsonUtil.obtenerProvincias();
		
		assertNotNull(provincias);
		assertTrue(provincias.size()==52);
	}
	
}
