package com.nt.jdbc;

//jdbc application to get department details based on the given start and end range of department number


import java.sql.*;
import java.util.*;

public class SelectTest4{

	public static void main(String[] args) throws Exception{

	Scanner sc=new Scanner(System.in);

	System.out.println("Enter start range of department id  ?");	
	String start=sc.next();

	System.out.println("Enter end range of department id  ?");
	String end=sc.next();

	Class.forName("oracle.jdbc.driver.OracleDriver");
	Connection con=
		DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");

      Statement st=con.createStatement();

	String query="SELECT * FROM DEPARTMENT WHERE DID>="+start+" AND DID<="+end; 
	
	System.out.println(query);
	ResultSet rs=st.executeQuery(query);
	boolean flag=false;
		while(rs.next()){
	   	flag=true;
		System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
		
		}
		if(flag==false){
		System.out.println("No record found");
		}
		rs.close();
		st.close();
		con.close();
		sc.close();
	}
}