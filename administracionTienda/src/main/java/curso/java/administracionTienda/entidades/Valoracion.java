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
@Table(name="valoraciones")
public class Valoracion {
	@Id @GeneratedValue
	private int id;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_producto")
	private Producto producto;
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="id_usuario")
	private Usuario usuario;
	@Column(name="valoracion")
	private int valoracion;
	@Column(name="comentario")
	private String comentario;
}
