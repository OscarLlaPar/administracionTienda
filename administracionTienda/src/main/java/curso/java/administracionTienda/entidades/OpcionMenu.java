package curso.java.administracionTienda.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="opciones_menu")
public class OpcionMenu {
	
	@Id @GeneratedValue
	private int id;
	@Column(name="id_rol")
	private int rol;
	@Column(name="nombre_opcion")
	private String nombreOpcion;
	@Column(name="url_opcion")
	private String urlOpcion;
}
