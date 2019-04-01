package com.nt.jdbc;

import java.sql.*;

public class SelectTest1 {

		public static void main(String[] args) throws Exception{
			//register thin driver
			
			Class.forName("oracle.jdbc.driver.OracleDriver");/*the static block of driver class has the logic is ther to create a driver class object 
															  *and to register that oject with  DriverManager Service
															*/
		
			//Establish the connection with the database software 
			Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","System","sumit");
			
			//create jdbc statement object  but statement is an interface which is 
			Statement st=con.createStatement();
		
			//execute the query and process the result
			ResultSet rs=st.executeQuery("select * from student");
		
			//print Database table record
			while(rs.next()!=false){
			System.out.println(rs.getInt("sid")+" "+rs.getString("sname")+" "+rs.getString("sadd"));
												//or
			//System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
			}
			
			//Close all the database Stream objects
			rs.close();
			st.close();
			con.close();
		
	 }
	}
		
		
