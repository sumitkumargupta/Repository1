package com.nt.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SelectTest1 {

		public static void main(String[] args) throws Exception {
			
			//register thin driver
			
				System.out.println("resgistered11");
			Class.forName("com.mysql.jdbc.Driver");/*the static block of driver class has the logic is ther to create a driver class object 
															  *and to register that oject with  DriverManager Service
															*/
			System.out.println("resgistered");
			//Establish the connection with the database software 
			Connection con=DriverManager.getConnection("jdbc:mysql:///db1","root","root");
			System.out.println("connection established");
			//create jdbc statement object  but statement is an interface which is 
			
			Statement st=con.createStatement();
			System.out.println("statemn");
			//execute the query and process the result
		
			 ResultSet rs=st.executeQuery("select * from student");
		
			//print Database table record
			while(rs.next()!=false){
			System.out.println(rs.getInt("sid")+" "+rs.getString("sname")+" "+rs.getString("sadd"));
												//or
			//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			
			rs.close();
			st.close();
			con.close();
		
			}
	 }
	
		
		
