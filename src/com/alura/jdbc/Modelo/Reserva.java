package com.alura.jdbc.Modelo;

import java.sql.Date;

public class Reserva {
	
	 private int id_reservas;
	 private Date fechEntrada;
	 private Date fechSalidad;

	 private int valor;
	 private String formpago;
	 
 public Reserva(Date fchEntrada,Date fchSalida,Integer valor,String frmpago) {
	
	 this.fechEntrada = fchEntrada;
	 this.fechSalidad = fchSalida;
	 this.valor = valor;
	 this.formpago = frmpago;
}

	 



	public Reserva(int id,Date fchEntrada,Date fchSalida,Integer valor,String frmpago) {

		this.id_reservas = id;
		 this.fechEntrada = fchEntrada;
		 this.fechSalidad = fchSalida;
		 this.valor = valor;
		 this.formpago = frmpago;
	}





	public int getId_reservas() {
		return id_reservas;
	}
	public void setId_reservas(int id_reservas) {
		this.id_reservas = id_reservas;
	}
	
	
	public Date getFechEntrada() {
		return fechEntrada;
	}
	public void setFechEntrada(Date fechEntrada) {
		this.fechEntrada = fechEntrada;
	}
	public Date getFechSalidad() {
		return fechSalidad;
	}
	public void setFechSalidad(Date fechSalidad) {
		fechSalidad = fechSalidad;
	}
	
	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}
	public String getFormpago() {
		return formpago;
	}
	public void setFormpago(String formpago) {
		this.formpago = formpago;
	}
	


}
