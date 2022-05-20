package curso.java.administracionTienda.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_rol")
	private Rol rol;
	@Column(name="nombre_opcion")
	private String nombreOpcion;
	@Column(name="url_opcion")
	private String urlOpcion;
}
