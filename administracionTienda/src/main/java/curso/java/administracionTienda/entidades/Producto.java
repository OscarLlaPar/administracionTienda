package curso.java.administracionTienda.entidades;

import java.sql.Timestamp;

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
@Table(name="productos")
public class Producto {
	@Id @GeneratedValue
	private int id;
	@Column(name="id_categoria")
	private int categoria;
	@Column(name="nombre")
	private String nombre;
	@Column(name="descripcion")
	private String descripcion;
	@Column(name="precio")
	private double precio;
	@Column(name="stock")
	private int stock;
	@Column(name="fecha_alta")
	private Timestamp fechaAlta;
	@Column(name="fecha_baja")
	private Timestamp fechaBaja;
	@Column(name="impuesto")
	private float impuesto;
	@Column(name="imagen")
	private String imagen;
	@Column(name="audio")
	private String audio;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="id_proveedor")
	private Proveedor proveedor;
}
