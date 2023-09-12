package com.alura.jdbc.Controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.alura.jdbc.DAO.ReservaDAO;
import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.Modelo.Reserva;
import com.toedter.calendar.JDateChooser;

import views.RegistroHuesped;
import views.ReservasView;

public class ReservaContoller {
	
	private ReservaDAO reservadao;
	
	public ReservaContoller() {
		
			this.reservadao = new ReservaDAO(new ConnectionFactory().recuperaConexion());
		
	}
	
	
	public void guardarReserva(Reserva reserva) {

	reservadao.guardarReserva(reserva);
		
	}

	public int getIdReserva() {
		return reservadao.getIdReserva();
	}


	public List<Reserva> listar() {
		return reservadao.listar();
		
	}
	public List<Reserva> listar(Integer id_reserva) {
		return reservadao.listar(id_reserva);
		
	}


	public int modificar(Integer id, Date fechEntrada, Date fechSalida, Integer valor, String formpago) 
			throws SQLException {
		return reservadao.modificar(id,fechEntrada,fechSalida,valor,formpago);
	}


	public int eliminar(Integer id) throws SQLException {
		
		return reservadao.eliminar(id);
	}	
	
}
