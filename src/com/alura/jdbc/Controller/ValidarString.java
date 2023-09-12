package com.alura.jdbc.Controller;

import java.sql.Date;

import com.toedter.calendar.JDateChooser;

public class ValidarString {
	
	
	public ValidarString() {
		// TODO Auto-generated constructor stub
	}
	
	public boolean isNumeric(String data) {
		
		return data.matches("[0-9]+$");
	}
	
	public boolean isLetter(String data) {
		
		return data.matches("[a-zA-Z]+$");
	}
	
	public boolean isAlphaNumeric(String data) {
		return data.matches("[0-9a-zA-Z]+$");
	}
	public boolean isDate(Date date) {
		
		return date != null;
	}
	
	public java.sql.Date getDate(JDateChooser date) {
		
		java.sql.Date fchEntrada = new java.sql.Date(date.getDate().getTime());		
		return fchEntrada ;
	}
	 

}
