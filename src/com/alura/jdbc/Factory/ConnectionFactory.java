package com.alura.jdbc.Factory;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class ConnectionFactory {
	private DataSource dataSource;
	
	public ConnectionFactory() {
		
		var pooledDataSource = new ComboPooledDataSource();
		
		pooledDataSource.setJdbcUrl("jdbc:mysql://localhost/hotelalura?useTimeZone=true&userTimeZone=UTC");
		pooledDataSource.setUser("root");
		pooledDataSource.setPassword("admin");
		pooledDataSource.setMaxPoolSize(10);	
		
		this.dataSource = pooledDataSource;
	}
	
	public Connection recuperaConexion()  {

		try {
			
			return this.dataSource.getConnection();
		
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}		
	
}



}
