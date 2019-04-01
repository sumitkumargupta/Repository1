package com.jdbc.tranferdata;

//Importing  all the required class and interfaces.
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

/* Author : Sumit gupta
* Version: 1.0
* Date	  : 2019/03/23
* Project : getting college student details sno, sname, sadd.
*/

public class ExcelToOracle{

	public static void main(String[] args) {
		Connection xlsCon=null;
		Connection oraCon=null;
		
		PreparedStatement ps=null;
		Statement st=null;
		ResultSet rs=null;
		
		String insert_Query=null;
		String sel_Query=null;
		
		int no=0;
		String sname=null;
		String sadd=null;
		
		boolean flag=false;
		
		try {
			
			// Register oracle JDBC driver
		    Class.forName("oracle.jdbc.driver.OracleDriver");
			
		    //Register excel JDBC driver
		    Class.forName("com.hxtt.sql.excel.ExcelDriver");
			
		    // Establishing the Connection b/w oracle and jdbc
		 	oraCon=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
		    
		 	// Establishing the Connection b/w excel and jdbc
			xlsCon=DriverManager.getConnection("jdbc:excel:///E:\\eclipse\\exceldb");
			
			insert_Query="INSERT INTO STUDENT VALUES(?,?,?)";
			//create statement
			if(oraCon!=null) {
			ps=oraCon.prepareStatement(insert_Query);
			}
			//create statement
			if(xlsCon!=null) {
			st=xlsCon.createStatement();
			}
			
			//create Query to get employee details empno,ename,job,sal based on designation
			sel_Query="SELECT * FROM COLLEGE.SHEET1";
			
			
			//execute Query
			if(st!=null) {
			rs=st.executeQuery(sel_Query);
			}
			
			//send and execute SQL query in database software
			if(rs!=null || ps!=null ) {
				int count=0;
			while(rs.next()) {
				flag=true;
				//getting details of student from excel sheet 
				no=rs.getInt(1);
				sname=rs.getString(2);
				sadd=rs.getString(3);
				
				//setting details of student to the oracle database student table
				ps.setInt(1, no);
				ps.setString(2, sname);
				ps.setString(3, sadd);
				
				//execute insert query
				ps.executeUpdate();
				count=1;
			}
			//to check wheather data is inserted in database or not.
				if(count>0) System.out.println("Record copied to oracle database successfully");
				if(flag==false) {
					System.out.println("No record Found");
				}//if
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
				if(ps!=null) 
				ps.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
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
				if(xlsCon!=null) 
				xlsCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(oraCon!=null) 
				oraCon.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
