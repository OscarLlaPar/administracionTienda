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
@Table(name="proveedores")
public class Proveedor {
	@Id @GeneratedValue
	private int id;
	@Column(name="nombre")
	private String nombre;
	@Column(name="direccion")
	private String direccion;
	@Column(name="localidad")
	private String localidad;
	@Column(name="provincia")
	private String provincia;
	@Column(name="telefono")
	private String telefono;
	@Column(name="cif")
	private String cif;
	@Column(name="email")
	private String email;
}
