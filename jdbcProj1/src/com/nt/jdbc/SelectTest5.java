// Package is created inside eclipse workspace in drive E jdbcproj1 folder which contain src folder. 
//This src contain com folder inside it in folder and inside it jdbc folder where our SelectTest5.java file is there.
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
 * Project : getting employee details based on designation
 */
 
public class SelectTest5 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		boolean flag=false;
		try {
			
			// Rsgister JDBC driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// Establishing the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
			
			//create statement
			if(con!=null) {
			st=con.createStatement();
			}
			
			//create Query to get employee details empno,ename,job,sal based on designation
			query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE JOB IN ('CLERK','SALESMAN','MANAGER') ORDER BY JOB";
			
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
				if(flag==false) {
					System.out.println("No record Found");
				}//if
			}//while
			}//if	
	}//try
		catch(SQLException se) {// known Exception
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