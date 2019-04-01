// Package  jdbc inside nt package and nt package inside com package

package com.nt.jdbc;

/*Author  : Sumit Gupta
 *Version :	1.0
 *Project : Project about getting details of the student in the range of id given by user.
 */

import java.sql.*;
import java.util.*;

public class SelectTest2{
	public static void main(String[] args) throws Exception{
 	Scanner sc =new Scanner(System.in);
 
 	// Take input from  user to put the range of id .
 	
	System.out.println("Enter start range of studnet id");
	String start=sc.next();
	System.out.println("Enter end range of studnet id");
	String end=sc.next();
	
	// Register jdbc driver .
	Class.forName("oracle.jdbc.driver.OracleDriver");
	
	// Establish the connection .
	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
	
	// Create Statement .
	Statement st=con.createStatement();

	// Create Query to take details about student who come in range . 
	String query="SELECT * FROM STUDENT WHERE SID>="+start+" AND SID<="+end;
	
	//Printing the query .
	System.out.println(query);
	
	//Executing query
	ResultSet rs=st.executeQuery(query);

	boolean flag=false;
	//printing the  details of student
		while(rs.next()){
		flag=true;
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));	
		
		}
		if(flag==false){
		System.out.println("No record found");
		}
		//Closing all the connection.
		rs.close();
		st.close();
		con.close();
		sc.close();
	
	}
}	 