package curso.java.administracionTienda.entidades;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="usuarios")
public class Usuario {
	@Id @GeneratedValue
	private int id;
	@Column(name="email", unique=true)
	private String email;
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	@JoinColumn(name="id_rol")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	private Rol rol;
	@Column(name="clave")
	private String clave;
	@Column(name="nombre")
	private String nombre;
	@Column(name="apellido1")
	private String apellido1;
	@Column(name="apellido2")
	private String apellido2;
	@Column(name="direccion")
	private String direccion;
	@Column(name="provincia")
	private String provincia;
	@Column(name="localidad")
	private String localidad;
	@Column(name="telefono")
	private String telefono;
	@Column(name="dni")
	private String dni;
	@Column(name="fecha_baja")
	private Timestamp fechaBaja;
	
}
