package com.alura.jdbc.Controller;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

import com.alura.jdbc.DAO.HuespedDAO;
import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.Modelo.Huesped;

public class HuespedController {

	private HuespedDAO huespeddao;
	
	public HuespedController() {
		
			this.huespeddao = new HuespedDAO(new ConnectionFactory().recuperaConexion());
		
	}

	public void guardarHuesped(Huesped reghuesped) {

		huespeddao.guardarHuesped(reghuesped);		
	}

	public List<Huesped> listar() {
		
		return huespeddao.listar();
	}

	public List<Huesped> listar(String ape) {
		
		return huespeddao.listar(ape);
	}

	public int modificar(Integer id_reserva, String nombre, String ape, Date fechNaci, String nacionalidad, 
			String telegono,Integer id) throws SQLException {

		return huespeddao.modificar(id_reserva,nombre,ape,fechNaci,nacionalidad,telegono,id);
	}

	public int eliminar(Integer id) throws SQLException {
		
		return huespeddao.eliminar(id);
	}


	
}
