package curso.java.administracionTienda.entidades;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
@Entity
@Table(name="descuentos")
public class Descuento {
	@Id @GeneratedValue
	private int id;
	@Column(name="codigo", unique=true)
	private String codigo;
	@Column(name="descuento")
	private double descuento;
	@Column(name="fecha_inicio")
	@DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
	private LocalDate fechaInicio;
	@Column(name="fecha_fin")
	@DateTimeFormat(pattern = "dd-MM-yyyy'T'HH:mm")
	private LocalDate fechaFin;
	
}
