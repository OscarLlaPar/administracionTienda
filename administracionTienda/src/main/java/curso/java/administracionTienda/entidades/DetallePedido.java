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
@Table(name="detalles_pedido")
public class DetallePedido {
	@Id @GeneratedValue
	private int id;
	@Column(name="id_pedido")
	private int pedido;
	@Column(name="id_producto")
	private int producto;
	@Column(name="precio_unidad")
	private float precioUd;
	@Column(name="unidades")
	private int unidades;
	@Column(name="impuesto")
	private float impuesto;
	@Column(name="total")
	private double total;
}
