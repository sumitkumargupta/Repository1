package com.nt.text;



//Importing  all the required class and interfaces.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;

/* Author : Sumit gupta
* Version: 1.0
* Date	  : 2019/03/23
* Project : getting student details sno, sname, sadd from file1.csv
*/

public class TextDbSelect {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		String query=null;
		boolean flag=false;
		try {
			
			// Register JDBC driver
			Class.forName("com.hxtt.sql.text.TextDriver");
			
			// Establishing the Connection
			con=DriverManager.getConnection("jdbc:Text:///E:\\eclipse\\textfiles");
			
			//create statement
			if(con!=null) {
			st=con.createStatement();
			}
			
			//create Query to get employee details empno,ename,job,sal based on designation
			query="SELECT * FROM FILE2.CSV";
			
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
				System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getString(3)); // why sno is not printed by getInt() ?
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
