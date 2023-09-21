package dbconnect.main.java.model;

public class Pedido {
	
	private int id; 
	private String codigo; 
	private String status; 
	private int idCliente;
	
	public Pedido(int id, String codigo, String status, int idCliente) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.status = status;
		this.idCliente = idCliente;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(int idCliente) {
		this.idCliente = idCliente;
	}

	@Override
	public String toString() {
		return "\nPedido [id=" + id + ", codigo=" + codigo + ", status=" + status + ", idCliente=" + idCliente + "]";
	}

	
	
	
	
	
	
}
