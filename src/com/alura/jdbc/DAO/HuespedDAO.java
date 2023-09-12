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

import com.alura.jdbc.Factory.ConnectionFactory;
import com.alura.jdbc.Modelo.Huesped;
import com.alura.jdbc.Modelo.Reserva;

public class HuespedDAO {
	
	private Connection con;
	
	
	public HuespedDAO(Connection con) {
		
		this.con = con;

	}


	public void guardarHuesped(Huesped reghuesped) {

		// TRAEMOS LA CONEXION DESDE EL CONTROLLER
	    
        try {
        	
            PreparedStatement statement;
                statement = con.prepareStatement(
                        "INSERT INTO Huesped "
                        + "(nombre,apellido,fechNaci,Nacionalidad,telefono,id_Reservas)"
                        + " VALUES (?, ?, ?, ?,?,?)", Statement.RETURN_GENERATED_KEYS);
    
            try (statement) {
            	
                statement.setString(1,reghuesped.getNombre());
                statement.setString(2,reghuesped.getApellido());
                statement.setDate(3, reghuesped.getFechNaci());
                statement.setString(4, reghuesped.getNacionalidad());
                statement.setString(5,reghuesped.getTelefono());
                statement.setInt(6,reghuesped.getId_huesped());
                statement.execute();
    
            /*    final ResultSet resultSet = statement.getGeneratedKeys();
    
                try (resultSet) {
                	
                    while (resultSet.next()) {
                    	reghuesped.setId_huesped( getId_huesped());
                        
                        System.out.println(String.format(" el numero de reserva es: %s", reghuesped));
                    }
                }*/
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


		
		
	}


	public List<Huesped> listar() {

		 List<Huesped> resultado = new ArrayList<>();

	        try {
	            final PreparedStatement statement = con
	                    .prepareStatement("SELECT ID,nombre,apellido,fechNaci,Nacionalidad,telefono,id_Reservas FROM huesped");
	    
	            try (statement) {
	              statement.execute();
	    
	                final ResultSet resultSet = statement.getResultSet();
	    
	                try (resultSet) {
	                    while (resultSet.next()) {
	                        resultado.add(new Huesped(
	                                resultSet.getInt("ID"),
	                                resultSet.getString("nombre"),
	                                resultSet.getString("apellido"),
	                                resultSet.getDate("fechNaci"),
	                                resultSet.getString("Nacionalidad"),
	                                resultSet.getString("telefono"),
	                                resultSet.getInt("id_Reservas")
	                                ));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	        return resultado;


	}
	
	
	public List<Huesped> listar(String ape) {

		 List<Huesped> resultado = new ArrayList<>();

	        try {
	            final PreparedStatement statement = con
	                    .prepareStatement("SELECT ID,nombre,apellido,fechNaci,Nacionalidad,telefono,id_Reservas "
	                    				 + "FROM huesped WHERE apellido = ? ");
	    
	            try (statement) {
	            statement.setString(1,ape);
	              statement.execute();
	    
	                final ResultSet resultSet = statement.getResultSet();
	            
	                // checar busqueda
	                try (resultSet) {
	                    while (resultSet.next()) {
	                        resultado.add(new Huesped(
	                                resultSet.getInt("ID"),
	                                resultSet.getString("nombre"),
	                                resultSet.getString("apellido"),
	                                resultSet.getDate("fechNaci"),
	                                resultSet.getString("Nacionalidad"),
	                                resultSet.getString("telefono"),
	                                resultSet.getInt("id_Reservas")
	                                ));
	                    }
	                }
	            }
	        } catch (SQLException e) {
	            throw new RuntimeException(e);
	        }

	        if (resultado.isEmpty()) {
	        	JOptionPane.showMessageDialog(null, "Verifique sus datos, no se encontro el registo", "Aviso!",
						JOptionPane.WARNING_MESSAGE);
			}
	        return resultado;


	}


	public int modificar(Integer id_reserva, String nombre, String ape, Date fechNaci, 
			String nacionalidad, String telegono,Integer id) throws SQLException {
		

		String updateString ="UPDATE huesped SET id = ? ,"
				+ "nombre = ?,"
				+ "apellido = ?,"
				+ "fechNaci = ?,"
				+ "Nacionalidad = ?,"
				+ "telefono = ?,"
				+ "id_Reservas = ? WHERE ID = ?";
		
	final	Connection con = new ConnectionFactory().recuperaConexion();
	
	try(con){
		
		final	PreparedStatement statement = con.prepareStatement (updateString);
		
		try(statement){
			
			statement.setInt(1,id);
			statement.setString(2,nombre);
			statement.setString(3,ape);
			statement.setDate(4,fechNaci);
			statement.setString(5,nacionalidad);
			statement.setString(6,telegono);
			statement.setInt(7,id_reserva);
			statement.setInt(8,id);
									
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
	
	
	// codigos sql
	
}
