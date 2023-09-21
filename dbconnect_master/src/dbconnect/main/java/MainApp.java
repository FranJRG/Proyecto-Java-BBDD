package dbconnect.main.java;

import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Scanner;

import dbconnect.main.java.api.BD;
import dbconnect.main.java.api.Connector;
import dbconnect.main.java.model.Cliente;
import dbconnect.main.java.model.Linea;

public class MainApp {
	
	public static final String MENU = "				TIENDA				"
									+ "\n	1. Mostrar información sobre los clientes"
									+ "\n	2. Mostrar pedidos"
									+ "\n	3. Añadir cliente"
									+ "\n	4. Actualizar cliente"
									+ "\n	5. Eliminar cliente"
									+ "\n	6. Añadir pedido"
									+ "\n	7. Incluir líneas"
									+ "\n	8. Salir";

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			BD b = new BD();
			Connector c = new Connector();
			Scanner sc = new Scanner(System.in);
			int opcion = 0; 
			b.volcarDatosCliente();
			b.volcarDatosLinea();
			b.volcarDatosPedido();
			while (opcion!=8) {
				System.out.println(MENU);
				System.out.println("Elije una opción(1-7): ");
				opcion = Integer.valueOf(sc.nextLine());
				switch (opcion) {
				case 1:
					System.out.println(b.mostrarClientes());
					break;
				case 2: 
					System.out.println(b.mostrarPedidos());
					break; 
				case 3: 
					System.out.print("Añade nombre cliente: ");
					String nombre = sc.nextLine();
					System.out.print("Añade apellidos del cliente: ");
					String apellidos = sc.nextLine();
					System.out.print("Añade el email del cliente: ");
					String email = sc.nextLine();
					System.out.print("Añade año de nacimiento del cliente: ");
					int year = Integer.valueOf(sc.nextLine());
					System.out.print("Añade mes de nacimiento del cliente: ");
					int mes = Integer.valueOf(sc.nextLine());
					System.out.print("Añade dia de nacimiento del cliente: ");
					int dia = Integer.valueOf(sc.nextLine());
					Date fechaNacimiento = Date.valueOf(LocalDate.of(year, mes, dia));
					System.out.print("Añade genero del cliente: ");
					String genero = sc.nextLine();
					c.anadirCliente(nombre, apellidos, email, fechaNacimiento, genero);
					b.volcarDatosCliente();
					break; 
				case 4: 
					System.out.println("Id del cliente a actualizar: ");
					int idPersona = Integer.valueOf(sc.nextLine());
					System.out.println("Campo a actualizar: ");
					String campoActualizar = sc.nextLine();
					System.out.println("Valor a actualizar: ");
					String valorActualizar = sc.nextLine();
					c.actualizarCliente(idPersona, campoActualizar, valorActualizar);
					b.volcarDatosCliente();
					break; 
					
				case 5: 
					System.out.println("Id del cliente a eliminar: ");
					int id = Integer.valueOf(sc.nextLine());
					System.out.println("Id del pedido a eliminar: ");
					int id_pedido = Integer.valueOf(sc.nextLine());
					c.eliminarCliente(id,id_pedido);
					b.volcarDatosCliente();
					break; 
					
				case 6:
					System.out.println("Añade id del pedido: ");
					int idPedido = Integer.valueOf(sc.nextLine());
					System.out.println("Añade codigo del pedido: ");
					String codigo = sc.nextLine();
					System.out.println("Añade estado del pedido: ");
					String status = sc.nextLine();
					System.out.println("Añade id del cliente: ");
					int idCliente = Integer.valueOf(sc.nextLine());
					c.annadirPedido(idPedido, codigo, status, idCliente);
					b.volcarDatosPedido();
					break;
				
				case 7: 
					System.out.println("Añade id de linea: ");
					int idLinea = Integer.valueOf(sc.nextLine());
					System.out.println("Añade codigo de linea: ");
					String codigoLinea = sc.nextLine();
					System.out.println("Añade nombre del producto: ");
					String nombreProducto = sc.nextLine();
					System.out.println("Añade id del pedido: ");
					int idPedido2 = Integer.valueOf(sc.nextLine());
					System.out.println("Añade la cantidad: ");
					int cantidad =  Integer.valueOf(sc.nextLine());
					System.out.println("Añade el precio: ");
					int precio= Integer.valueOf(sc.nextLine());
					try {
						c.incluirLineas(new Linea(idLinea, codigoLinea, nombreProducto, idPedido2, cantidad, precio));
					} catch (Exception e) {
						e.printStackTrace();
					}
					break; 
				case 8:
					opcion = 8;
					break;
				default:
					System.out.println("Elije una opción correcta");
					break;
				}
			}
			
			
		}
}