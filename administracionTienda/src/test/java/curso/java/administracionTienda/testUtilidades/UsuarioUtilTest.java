package curso.java.administracionTienda.testUtilidades;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import curso.java.administracionTienda.utilidades.UsuarioUtil;

public class UsuarioUtilTest {

	@Test
	public void comprobarObtenerSha2() {
		String encriptacion = UsuarioUtil.obtenerSha2("paso");
		assertEquals(encriptacion, "4dd09b8f659e27847f94782920fb7e41b2c5afbd7f419a4a3ed8ab7aa5b7f944");
	}

}
