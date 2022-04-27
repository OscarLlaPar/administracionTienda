package curso.java.administracionTienda.controladores;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class UsuarioControlador {
	@RequestMapping("")
	public String login() {
		
		System.out.println("Pasa por servlet");
		return "pages/inicio";
	}
}
