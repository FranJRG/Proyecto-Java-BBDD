package dbconnect.main.java.model;

import java.sql.Date;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Cliente {
	
	private int id;
	private String nombre;
	private String apellidos;
	private String dni;
	private String email;
	private Date fechaNacimiento;
	private String genero;
	
	
	public Cliente(int id, String nombre, String apellidos, String email, Date fechaNacimiento, String genero) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.fechaNacimiento = fechaNacimiento;
		this.genero = genero;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getDni() {
		return dni;
	}


	public void setDni(String dni) {
		this.dni = dni;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public int getEdad() {
		return  (int) ChronoUnit.YEARS.between(this.fechaNacimiento.toLocalDate(), LocalDate.now());
	}


	public String getGenero() {
		return genero;
	}


	public void setGenero(String genero) {
		this.genero = genero;
	}


	@Override
	public String toString() {
		return nombre+ " " + apellidos+ " " + email+ " " + getEdad()+ " " + genero;
	}
	
	
	

}

