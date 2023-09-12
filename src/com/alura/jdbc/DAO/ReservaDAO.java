package com.alura.jdbc.DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.alura.jdbc.Modelo.Reserva;
import com.alura.jdbc.Factory.ConnectionFactory;

import views.RegistroHuesped;


public class ReservaDAO {

	private Connection con;
	
	
	public ReservaDAO(Connection con) {
		
		this.con = con;

	}
	
	public void guardarReserva(Reserva reserva) {
		
// TRAEMOS LA CONEXION DESDE EL CONTROLLER
			    
	        try {
	        	
	            PreparedStatement statement;
	                statement = con.prepareStatement(
	                        "INSERT INTO RESERVAS "
	                        + "(fechEntrada, fechSalida,valor,formPago)"
	                        + " VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
	    
	            try (statement) {
	            	
	                statement.setDate(1,reserva.getFechEntrada());
	                statement.setDate(2,reserva.getFechSalidad());
	                statement.setInt(3, reserva.getValor());
	                statement.setString(4, reserva.getFormpago());
	    
	                statement.execute();
	    
	                final ResultSet resultSet = statement.getGeneratedKeys();
	    
	                try (resultSet) {
	                	
	                    while (resultSet.next()) {
	                        reserva.setId_reservas(getIdReserva());
	                        
	                        System.out.println(String.format(" el numero de reserva es: %s", reserva.toString()));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	}

	public int getIdReserva() {
		
      Integer idInteger = 0;

        try {
            final PreparedStatement statement = con
                    .prepareStatement("SELECT ID FROM reservas");
    
            try (statement) {
                statement.execute();
    
                final ResultSet resultSet = statement.getResultSet();
    
                try (resultSet) {
                    while (resultSet.next()) {
                    	
                      idInteger = resultSet.getInt("ID");                               
                    }
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        if(idInteger<1) {
        	return 1;
        	}else
        return idInteger;
	}

	public List<Reserva> listar(Integer id_reserva) {

	 List<Reserva> resultado = new ArrayList<>();

		        try {
		            final PreparedStatement statement = con
		                    .prepareStatement("SELECT ID,fechEntrada,fechSalida,valor,formPago "
		                    		+ "FROM reservas WHERE id = ?");
		    
		            try (statement) {
		            	statement.setInt(1,id_reserva);
		               statement.execute();
		    
		               final ResultSet resultSet = statement.getResultSet();
		    
		                try (resultSet) {
		                    while (resultSet.next()) {
		                        resultado.add(new Reserva(
		                                resultSet.getInt("ID"),
		                                resultSet.getDate("fechEntrada"),
		                                resultSet.getDate("fechSalida"),
		                                resultSet.getInt("valor"),
		                                resultSet.getString("formPago")
		                                ));
		                    }
		                }
		            }
		        } catch (SQLException e) {
		            throw new RuntimeException(e);
		        }

		        if(resultado.isEmpty()) {
		        	
		        	JOptionPane.showMessageDialog(null, "Verifique sus datos, no se encontro el registo", "Aviso!",
							JOptionPane.WARNING_MESSAGE);
		        }
		        return resultado;


	}
	
	public List<Reserva> listar() {

		 List<Reserva> resultado = new ArrayList<>();

			        try {
			            final PreparedStatement statement = con
			                    .prepareStatement("SELECT ID,fechEntrada,fechSalida,valor,formPago FROM reservas");
			    
			            try (statement) {
			              statement.execute();
			    
			                final ResultSet resultSet = statement.getResultSet();
			    
			                try (resultSet) {
			                    while (resultSet.next()) {
			                        resultado.add(new Reserva(
			                                resultSet.getInt("ID"),
			                                resultSet.getDate("fechEntrada"),
			                                resultSet.getDate("fechSalida"),
			                                resultSet.getInt("valor"),
			                                resultSet.getString("formPago")
			                                ));
			                    }
			                }
			            }
			        } catch (SQLException e) {
			            throw new RuntimeException(e);
			        }

			        return resultado;


		}

	public int modificar(Integer id, Date fechEntrada, Date fechSalida, Integer valor, String formpago)
			throws SQLException {

		// to do modificar , mandar los datos
		

		String updateString ="UPDATE reservas SET id = ? ,"
				+ "fechEntrada = ? ,"
				+ "fechSalida = ?,"
				+ "valor =?,"
				+ "formPago =? WHERE ID = ?";
		
	final	Connection con = new ConnectionFactory().recuperaConexion();
	
	try(con){
		
		final	PreparedStatement statement = con.prepareStatement (updateString);
		
		try(statement){
			
			statement.setInt(1,id);
			statement.setDate(2,fechEntrada);
			statement.setDate(3,fechSalida);
			statement.setInt(4,valor);
			statement.setString(5,formpago);
			statement.setInt(6,id);
						
			statement.execute();
			System.out.println(updateString);
			  int updateCount = statement.getUpdateCount();
		return updateCount;	
			
		}
	}
		
		
	}

	public int eliminar(Integer id) throws SQLException {
		final	Connection con = new ConnectionFactory().recuperaConexion(); 
		
	try(con){
		
		final PreparedStatement statement = con.prepareStatement("DELETE FROM huesped WHERE ID_RESERVAS = ?");
		final	PreparedStatement statement2 = con.prepareStatement("DELETE FROM reservas WHERE ID = ?");
		
		try(statement){
			
			statement.setInt(1,id);
			statement2.setInt(1,id);
			statement.execute();
			statement2.execute();
			int updatecount = statement.getUpdateCount();
			con.close();
			return updatecount;
			
		}
	}
		
		
	}
	
	

	// codigo sql


}
