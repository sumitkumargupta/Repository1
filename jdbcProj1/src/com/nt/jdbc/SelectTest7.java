// Package is created inside eclipse workspace in drive E jdbcproj1 folder which contain src folder. 
//This src cotain com folder inside it nt folder and iside it jdbc folder where our SelectTest5.java file is there.
package com.nt.jdbc;

// Importing  all the required class and interfaces.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;


/* Author : Sumit gupta
 * Version: 1.0
 * Date	  : 2109/03/06
 * project to get detail of employee having higest salary 
 */
 
public class SelectTest7 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		boolean flag=false;

		try {
	    	// Register JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establishing the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
			
			//create statement
			if(con!=null) {
			st=con.createStatement();
			}
			
			//create Query to get employee details empno,ename,job based on their higest salary
			query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE SAL=(SELECT MAX(SAL) FROM EMP)";
			
			//print the query
			System.out.println(query);
			
			//execute Query
			if(st!=null) {
			rs=st.executeQuery(query);
			}
			
			//send and execute SQL query in database software
			if(rs!=null) {
				while(rs.next()) {
				flag=true;
				
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getInt(4));

			}//while
				if(flag==false) {
					System.out.println("No record Found");
				}//if
			}//if	
	}//try
		catch (SQLException se) {// known Exception
			se.printStackTrace();
		}
		catch(ClassNotFoundException cnf){ // known Exceptions
			cnf.printStackTrace();
		}
		catch(Exception e){// Unknown Exception
			e.printStackTrace();
		}
		finally{
			//close Objects
			try {
				if(rs!=null)
					rs.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null) 
				con.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}