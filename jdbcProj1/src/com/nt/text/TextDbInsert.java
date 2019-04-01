package com.nt.text;



	//Importing  all the required class and interfaces.
	import java.sql.Connection;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.Scanner;
	import java.sql.DriverManager;

	/* Author : Sumit gupta
	* Version: 1.0
	* Date	  : 2019/03/23
	* Project : Inserting college student details sno, sname, sadd.
	*/

	public class TextDbInsert {
		
			 
		public static void main(String[] args) {
			Scanner sc=null;
			int sno=0;
			String sname=null;
			String sadd=null;
			Connection con=null;
			PreparedStatement ps=null;
			
			Statement st=null;
			ResultSet rs=null;
			String query=null;
			
			int result=0;
			
			try {
				sc=new Scanner(System.in);
				
				//Enter details of student which u want to insert?
				if(sc!=null) {
			System.out.println("Enter student ID");
			sno=sc.nextInt();
			System.out.println("Enter student Name");
			sname=sc.next();
			System.out.println("Enter student Address");
			sadd=sc.next();
				}
				// Register JDBC driver
				Class.forName("com.hxtt.sql.text.TextDriver");
				
				// Establishing the Connection
				con=DriverManager.getConnection("jdbc:Text:///E:\\eclipse\\textfiles");
				
				//create Query to insert student details
				query="INSERT INTO FILE2.CSV VALUES(?,?,?)";
				
							
				//create statement
				if(con!=null) {
				ps=con.prepareStatement(query);			
				}
				
				//execute prepare statement
				if(ps!=null) {
				ps.setInt(1, sno);
				ps.setString(2, sname);
				ps.setString(3, sadd);
				
				result=ps.executeUpdate();
				
				if(result==0) System.out.println("Record not inserted");
				else System.out.println("Record inserted");
					
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
					if(ps!=null)
						ps.close();
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
