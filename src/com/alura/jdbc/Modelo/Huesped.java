package com.alura.jdbc.Modelo;

import java.sql.Date;

public class Huesped {

	private int id;
	private int	id_huesped;
	private String nombre;
	private String apellido;
	private Date fechNaci;
	private String 	nacionalidad;
	private String telefono;
	
	
	public Huesped(String name,String ape,Date birth,String nation,String phone,int id) {
	this.nombre = name;
	this.apellido = ape;
	this.fechNaci = birth;
	this.nacionalidad = nation;
	this.telefono = phone;
	this.id_huesped = id;
	}
	
	
	public Huesped(int id, String name,String ape,Date birth,String nation,String phone,int idreserva) {

		this.id = id;
		this.nombre = name;
		this.apellido = ape;
		this.fechNaci = birth;
		this.nacionalidad = nation;
		this.telefono = phone;
		this.id_huesped = idreserva;
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}

	public int getId_huesped() {
		return id_huesped;
	}
	public void setId_huesped(int id_huesped) {
		this.id_huesped = id_huesped;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public Date getFechNaci() {
		return fechNaci;
	}
	public void setFechNaci(Date fechNaci) {
		this.fechNaci = fechNaci;
	}
	public String getNacionalidad() {
		return nacionalidad;
	}
	public void setNacionalidad(String nacionalidad) {
		this.nacionalidad = nacionalidad;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
		

	
	
}
