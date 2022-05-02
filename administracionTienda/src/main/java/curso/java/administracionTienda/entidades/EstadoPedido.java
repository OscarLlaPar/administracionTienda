package curso.java.administracionTienda.entidades;

public enum EstadoPedido {
	PE("Pendiente de envío"),
	PC("Pendiente de cancelación"),
	E("Enviado"),
	C("Cancelado");
	
	private String estado;
	
	private EstadoPedido(String estado) {
		this.estado=estado;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}
	
	
}
