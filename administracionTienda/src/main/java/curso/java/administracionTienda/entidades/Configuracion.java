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
@Table(name="configuracion")
public class Configuracion {
	@Id @GeneratedValue
	private int id;
	@Column(name="clave", unique=true)
	private String clave;
	@Column(name="valor")
	private String valor;
	@Column(name="tipo")
	private String tipo;
}
