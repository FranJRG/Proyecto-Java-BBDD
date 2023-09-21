package dbconnect.main.java.api;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import dbconnect.main.java.model.Cliente;
import dbconnect.main.java.model.Linea;
import dbconnect.main.java.model.Pedido;

public class BD {
	

	private List<Cliente> clientes;
	private List<Linea> lineas;
	private List<Pedido> pedidos;
	
	public BD() {
		clientes = new ArrayList<>();
		lineas = new ArrayList<>();
		pedidos = new ArrayList<>();
		
	}
	
	public void volcarDatosCliente() throws ClassNotFoundException, SQLException {
		Connector c = new Connector();
		c.connect(clientes);		
	}
	
	public void volcarDatosLinea() throws SQLException {
		Connector c = new Connector();
		c.connectLinea(lineas);
		
	}
	
	public void volcarDatosPedido() throws SQLException {
		Connector c = new Connector();
		c.connectPedido(pedidos);
	}
	
	
	public String mostrarPedidos() {
		StringBuilder sb = new StringBuilder();
		int cantidad = 0;
		int precio = 0;
		Collections.sort(this.lineas);
		for (Pedido p : this.pedidos) {
			sb.append(p.getCodigo()+" "+p.getStatus()+" ");
			for (Cliente c : this.clientes) {
				if (p.getIdCliente()==c.getId()) {
					sb.append(c.getNombre()+" "+c.getApellidos()+" ");
				}
			}
			for (Linea l : this.lineas) {
				if (l.getIdPedido()==p.getId()) {
					cantidad = l.getCantidad();
					precio = l.getPrecio();
				}
			}
			sb.append(cantidad+" "+precio);
			sb.append(System.lineSeparator());
		}		
		return sb.toString();
	}
	
	public String mostrarClientes() {
		StringBuilder sb = new StringBuilder();
		for (Cliente c : this.clientes) {
			sb.append(c.toString());
			sb.append(System.lineSeparator());
		}
		return sb.toString();
	}
	

	
	
	
	
}

