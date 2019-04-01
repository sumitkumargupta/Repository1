package com.nt.datetime;
/* Author 	: sumit gupta
 * Version 	: 1.0
 * Project	: inserting date value in database
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class DateInsert {

	public static void main(String[] args) {
		//Declaration variable
		Scanner sc=null;
		int no=0;
		String name=null, dob=null, doj=null, dom=null;
		SimpleDateFormat sdf=null;
		java.util.Date udob=null, udom=null;
		java.sql.Date sqdob=null, sqdoj=null, sqdom=null;
		long ms=0;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		String query=null;
		
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				// taking input from user
				
				
				System.out.println("Enter the id number ");
				no=sc.nextInt();
				
				System.out.println("Enter Name ? ");
				name=sc.next();
				
				System.out.println("Enter DOB (dd-MM-yyyy) ");
				dob=sc.next();
				
				System.out.println("Enter DOJ (yyyy-MM-dd) ");
				doj=sc.next();
				
				System.out.println("Enter DOM (MM-dd-yyyy) ");
				dom=sc.next();
			}
			
			//For DOB-> As DOB is not in sql format(yyyy-MM-dd) so we have to first convert
			
			sdf=new SimpleDateFormat("dd-MM-yyyy");
			
			if(sdf!=null)
				udob=sdf.parse(dob);// gives java.util.Date object
				if(udob!=null)
					ms=udob.getTime();
					sqdob=new java.sql.Date(ms);// gives java.sql.Date class object
					
			//DOJ
					sqdoj=java.sql.Date.valueOf(doj);
			//DOM		
					sdf=new SimpleDateFormat("MM-dd-yyyy");
					if(sdf!=null)
						udom=sdf.parse(dom);
					if(udom!=null)
						ms=udom.getTime();
						sqdom=new java.sql.Date(ms);
					
						//register Driver
						Class.forName("oracle.jdbc.driver.OracleDriver");
						
						//establishing connection
						con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
						
						
						query="INSERT INTO EMPLOYEE VALUES(?,?,?,?,?)";
						//prepare statement
						if(con!=null)
						ps=con.prepareStatement(query);
						
						//set values of prepared statement
						if(ps!=null)
						{
							ps.setInt(1, no);
							ps.setString(2, name);
							ps.setDate(3, sqdob);
							ps.setDate(4, sqdoj);
							ps.setDate(5, sqdom);
						}
						
						//execute query
						if(ps!=null)
							result=ps.executeUpdate();
						
						//process the result
						if(result==0)
							System.out.println("Record not inserted");
						else
							System.out.println("Record Inserted");
			}//try
		
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		catch(SQLException se){
			se.printStackTrace();
		}
		catch(Exception e ) {
			e.printStackTrace();
		}
		
		finally {
			//close jdbc objects
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
			try {
				if(sc!=null)
					sc.close();
				}
				catch(Exception se) {
					se.printStackTrace();
				}
			
			
		}//finally

	}//main

}//class
