//SelectTest6.java
package com.nt.jdbc;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
/* Author 	: Sumit gupta
 * version	: 1.0
 * Date 	: 07:03:2019
 * project 	: getting details of student based on 3 city name 
 */

public class SelectTest6 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		Scanner sc=null;
		boolean flag=false;

		String query=null;
		
		String city1=null;
		String city2=null;
		String city3=null;
		
		sc =new Scanner(System.in);
		
		//Getting city names from the user
		System.out.println("Enter first city");
		city1=sc.next();// getting first city 
		System.out.println("Enter second city");
		city2=sc.next();// getting second city
		System.out.println("Enter third city");
		city3=sc.next();// getting third city 
	
		city1="'"+city1+"'";
		city2="'"+city2+"'";
		city3="'"+city3+"'";
		
		try {
			//Register JDBC driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//Establish connection
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
		
		//Create Statement
		if(con!=null) {
			st=con.createStatement();
		}
		query="SELECT SID, SNAME, SADD FROM STUDENT WHERE SADD="+city1+" OR SADD="+city2+" OR SADD="+city3;
		
		//Execute query
		if(st!=null) {
		rs=st.executeQuery(query);
		}
		
		//Printing query 
		System.out.println("Query used : \t"+query);
		
		//printing result
		if(rs!=null) {
		while(rs.next()) {
			flag=true;
			System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));	
		}
		if(flag==false) {
			System.out.println("Record not found");
		}
		}//if
		}//try
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			try{
				if(rs!=null) {
				rs.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try{
				if(st!=null) {
				st.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try{
				if(con!=null) {
				con.close();
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try{
				if(sc!=null) {
				sc.close();
				}
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		}

	}//main

}//class
