package com.nt.jdbc;
/* Author  : Sumit Gupta
 * Version : 1.0
 * project : Login Application Development 
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class LoginApp {

	public static void main(String[] args) {
		//Declaring all he variable with default values
		Scanner sc=null;
		String uname=null;
		String pwd=null;
		Connection con=null;
		Statement st=null;
		String query=null;
		ResultSet rs=null;
		int count=0;
		
		try {
		// Taking input from user 
			sc=new Scanner(System.in);
				System.out.println("Enter Username");
				uname=sc.nextLine();
				System.out.println("Enter Password");
				pwd=sc.nextLine();
				
		//making uname and pwd fit for query
				uname="'"+uname+"'";
				pwd="'"+pwd+"'";
				
		//register driver
				Class.forName("oracle.jdbc.driver.OracleDriver");
				
		//Establish Connection
				con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
				
		//Create Statement
				if(con!=null)
					st=con.createStatement();
				
		//prepare Query
				query="SELECT COUNT(*) FROM USERLIST WHERE UNAME="+uname+" AND PWD="+pwd;
				System.out.println(query);		
		//Execute Query
				if(st!=null)
				rs=st.executeQuery(query);
				
		//Checking the condition weather username  and password is correct or  not
				if(rs!=null) {
					if(rs.next()) 
						count=rs.getInt(1);
					System.out.println("No of record : "+count);
				}
					if(count==0)
						System.out.println("Invalid Credential");
					else
						System.out.println("Valid Credential");
				
			}//try
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();	
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	finally {
		if(rs!=null)
			try {
				rs.close();
			}
		catch(SQLException se){
			se.printStackTrace();
		}
		if(st!=null)
			try {
				st.close();
			}
		catch(SQLException se){
			se.printStackTrace();
		}
		if(con!=null)
			try {
				con.close();
			}
		catch(SQLException se){
			se.printStackTrace();
		}
		if(sc!=null)
			try {
				sc.close();
			}
		catch(Exception e){
			e.printStackTrace();
		}
	}
		

	}

}
