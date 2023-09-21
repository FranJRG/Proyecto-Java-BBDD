package dbconnect.main.java.api;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Properties;

import dbconnect.main.java.model.Cliente;
import dbconnect.main.java.model.Linea;
import dbconnect.main.java.model.Pedido;

public class Connector {
	
	
	private static String USER;
	private final static String DB_USER_KEY="db.user";

	private static String PASS;
	private final static String DB_PASS_KEY="db.pass";
	
	private static String JDBC_URL;
	private final static String DB_URL_KEY="db.url";
	
	private final static String PROPERTIES_URI = "./resources/db.properties";
	
	public Connector() {
		super();
		loadProperties();
	}
	
	public void loadProperties() {
		try {
			Properties properties = new Properties();
			properties.load(new FileReader(PROPERTIES_URI));
			USER = properties.getProperty(DB_USER_KEY);
			PASS = properties.getProperty(DB_PASS_KEY);
			JDBC_URL = properties.getProperty(DB_URL_KEY);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	public void connect(List<Cliente> clientes) throws SQLException, ClassNotFoundException {
		
		

		Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASS); 
		Statement statement=connection.createStatement();  
		
		
	/*	//Inserción de datos parametrizada
		PreparedStatement ps = connection.prepareStatement("INSERT INTO Cliente (nombre, apellido, email, fechaNacimiento, genero) "
																+ " VALUES (?, ?, ?, ?, ?)");
		ps.setString(1, "Manuel");
		ps.setString(2, "Leunam");
		ps.setString(3, "mm@gmail.com");
		ps.setString(4, "1985-11-01");
		ps.setString(5, "F");
		ps.executeUpdate();
		//ps.close();
		
		// Ejecuta query de eliminación, actualización o insercioń (DELETE, UPDATE, INSERT)
		statement.executeUpdate("INSERT INTO Cliente (nombre, apellido, email, fechaNacimiento, genero) "
								+ "VALUES ('Rigoberto', 'Ricciardiello', 'rr0@yelp.com', '1983-04-15', 'M');\n");
		
*/
		
		ResultSet rs=statement.executeQuery("select * from Cliente"); 
		
		while(rs.next()) {//Avanza de posición en el listado de registros y devuelve true si existe tal
			Cliente alumno = new Cliente(Integer.valueOf(rs.getString(1)), rs.getString(2), rs.getString(3), 
										rs.getString(4), rs.getDate(5), rs.getString(6));
			clientes.add(alumno);
		}
		
		connection.close();  
		
	

	}
	
	
	
	public void connectLinea(List<Linea> lineas) throws SQLException {
		Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASS); 
		Statement statement=connection.createStatement(); 
		
		ResultSet rs1 = statement.executeQuery("SELECT * from Linea");
		
		while (rs1.next()) {
			Linea linea = new Linea(Integer.valueOf(rs1.getString(1)), rs1.getString(2), rs1.getString(3),
					Integer.valueOf(rs1.getString(4)) , Integer.valueOf(rs1.getString(5)), Integer.valueOf(rs1.getString(6)));
			lineas.add(linea);
		}
		
		connection.close();
		
	}
	
	
	
	public void connectPedido(List<Pedido> pedidos) throws SQLException {
		Connection connection = DriverManager.getConnection(JDBC_URL, USER, PASS); 
		Statement statement=connection.createStatement();
		
		ResultSet rs2 = statement.executeQuery("select * from Pedido");
		
		
		while (rs2.next()) {
			Pedido pedido = new Pedido(Integer.valueOf(rs2.getString(1)), rs2.getString(2), rs2.getString(3), 
					Integer.valueOf(rs2.getString(4)));
			pedidos.add(pedido);
			
		}
		connection.close();
		
		
		
	}
	
	public boolean anadirCliente(String nombre, String apellidos, String email, Date fechaNacimiento, String genero) {
		boolean res = false;
		
		Connection connection;
		try {
			connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
			Statement statement=connection.createStatement();
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Cliente (nombre, apellido, email, fechaNacimiento, genero) "
			+ " VALUES (?, ?, ?, ?, ?)");
			ps.setString(1, nombre);
			ps.setString(2, apellidos);
			ps.setString(3, email);
			ps.setDate(4, fechaNacimiento);
			ps.setString(5, genero);
			ps.executeUpdate();
			ps.close();
			res=true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	
		
		return res;
	}
	
	public void eliminarCliente(int id, int idPedido)  {
		Connection connection;
		try {
			connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
			Statement statement=connection.createStatement();
			String sql3 = "DELETE FROM Linea WHERE idPedido="+idPedido;
			String sql2 = "DELETE FROM Pedido WHERE idCliente="+id;
			String sql = "DELETE FROM Cliente WHERE id="+id;
			statement.executeUpdate(sql3);
			statement.executeUpdate(sql2);
			statement.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean annadirPedido(int id, String codigo, String status, int idCliente) {
		boolean res = false;
		Connection connection;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
			Statement statement=connection.createStatement();
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Pedido (id, codigo, status, idCliente) "
					+ " VALUES (?, ?, ?, ?)");
			
			ps.setInt(1, id);
			ps.setString(2, codigo);
			ps.setString(3, status);
			ps.setInt(4, idCliente);
			ps.executeUpdate();
			ps.close();
			res=true;
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return res;
		
	}
	
	public boolean actualizarCliente(int id, String campoActualizar, String valorCampo) {
		boolean res = false;
		Connection connection;
		try {
			connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
			
			Statement statement=connection.createStatement();
			String sql =( "UPDATE Cliente SET "+campoActualizar+"='" +valorCampo+ "' where id="+id);
			PreparedStatement ps = connection.prepareStatement(sql);
			statement.executeUpdate(sql);
			ps.close();
			res=true;
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return res; 
	}
	
	public void incluirLineas(Linea l) throws Exception {
		
		Connection connection;
		
		try {
			connection = DriverManager.getConnection(JDBC_URL, USER, PASS);
			Statement statement=connection.createStatement();
			
			PreparedStatement ps = connection.prepareStatement("INSERT INTO Linea (id, codigo, nombreProducto, idPedido, cantidad, precio) "
					+ " VALUES (?, ?, ?, ?, ?, ?)");
			PreparedStatement ps2 = connection.prepareStatement("SELECT status FROM Pedido where id="+l.getIdPedido());	
			ResultSet rs2 = ps2.executeQuery();
			rs2.next();
			if (rs2.getString(1).equals("PROCESANDO")) {
				ps.setInt(1, l.getId());
				ps.setString(2, l.getCodigo());
				ps.setString(3, l.getNombreProducto());
				ps.setInt(4, l.getIdPedido());
				ps.setInt(5, l.getCantidad());
				ps.setInt(6, l.getPrecio());
				ps.executeUpdate();
				ps.close();
			}else {
				throw new Exception("El estado del pedido no es procesando");
			}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
	}

}

