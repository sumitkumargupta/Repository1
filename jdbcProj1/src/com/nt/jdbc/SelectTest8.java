package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

/* Author 	: sumit gupta
 * Version	: 1.0
 * Date		: 10-03-2019
 * Project	: Getting total no of employee
 */

public class SelectTest8 {

	public static void main(String[] args) { 
	
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
				
		try {
			//Registering Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
			//Establishing Connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
		
			//creating Statement
		if(con!=null){
		 st=con.createStatement();
			}
		query="SELECT COUNT(*) FROM STUDENT";
			//execute query
		if(st!=null){
			rs=st.executeQuery(query);
		}
		if(rs.next()) {
			
			System.out.println("Number of StudentS : "+rs.getInt(1));
		}
		else {
			System.out.println("No record Found");
		}
		}//try
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
			if(rs!=null){
				rs.close();
				}
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			try {
				if(st!=null){
					st.close();
					}
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			try {
				if(con!=null){
					con.close();
					}
				}
				catch(SQLException se){
					se.printStackTrace();
				}
			
		}
	}

}
