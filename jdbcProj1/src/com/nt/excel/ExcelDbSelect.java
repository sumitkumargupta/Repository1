package com.nt.excel;


//Importing  all the required class and interfaces.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

/* Author : Sumit gupta
* Version: 1.0
* Date	  : 2019/03/23
* Project : getting college student details sno, sname, sadd.
*/

public class ExcelDbSelect {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		boolean flag=false;
		try {
			
			// Register JDBC driver
			Class.forName("com.hxtt.sql.excel.ExcelDriver");
			
			// Establishing the Connection
			con=DriverManager.getConnection("jdbc:excel:///E:\\eclipse\\exceldb");
			
			//create statement
			if(con!=null) {
			st=con.createStatement();
			}
			
			//create Query to get employee details empno,ename,job,sal based on designation
			query="SELECT * FROM COLLEGE.SHEET1";
			
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
				System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
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
