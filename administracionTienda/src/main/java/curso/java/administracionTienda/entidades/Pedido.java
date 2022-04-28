package curso.java.administracionTienda.entidades;

import java.sql.Timestamp;

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
@Table(name="pedidos")
public class Pedido {
	@Id @GeneratedValue
	private int id;
	@Column(name="email_usuario")
	private String usuario;
	@Column(name="fecha")
	private Timestamp fecha;
	@Column(name="metodo_pago")
	private String metodoPago;
	@Column(name="estado")
	private String estado;
	@Column(name="num_factura")
	private String numFactura;
	@Column(name="total")
	private double total;
}
