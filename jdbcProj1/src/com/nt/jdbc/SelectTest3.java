package com.nt.jdbc;

import java.sql.*;
import java.util.*;

public class SelectTest3{
	public static void main(String[] args) throws Exception{
 	Scanner sc =new Scanner(System.in);

	System.out.println("Enter initial letter of student name :\t");
	String initialChars=sc.next();
	initialChars="'"+initialChars+"%'";
	Class.forName("oracle.jdbc.driver.OracleDriver");

	Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");

	Statement st=con.createStatement();

	String query="SELECT * FROM STUDENT WHERE SNAME LIKE"+initialChars;
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