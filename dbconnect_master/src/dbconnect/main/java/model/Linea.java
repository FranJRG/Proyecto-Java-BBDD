package dbconnect.main.java.model;

public class Linea implements Comparable<Linea> {
	
	private int id; 
	private String codigo; 
	private String nombreProducto;
	private int idPedido; 
	private int cantidad;
	private int precio;
	
	public Linea(int id, String codigo, String nombreProducto, int idPedido, int cantidad, int precio) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nombreProducto = nombreProducto;
		this.idPedido = idPedido;
		this.cantidad= cantidad;
		this.precio = precio;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombreProducto() {
		return nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public int getIdPedido() {
		return idPedido;
	}

	public void setIdPedido(int idPedido) {
		this.idPedido = idPedido;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	@Override
	public String toString() {
		return "\nLinea [id=" + id + ", codigo=" + codigo + ", nombreProducto=" + nombreProducto + ", idPedido="
				+ idPedido + ", cantidad=" + cantidad + ", precio=" + precio + "]";
	}

	@Override
	public int compareTo(Linea o) {
		return this.precio-o.precio;
	}
	
	
	
	
}


