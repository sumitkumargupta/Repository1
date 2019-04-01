package com.nt.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/* Author 	: sumit gupta
 * Version 	: 1.0
 * 	project : using prepared Statement develop an Application to gather 
 * 			  "n" number of student from end user and insert them in database table as records
 */

public class PrepTest {

	public static void main(String[] args) {
		//declaration part
		Scanner sc=null;
		int count =0;
		
		Connection con=null;
		String insert_Query=null;
		PreparedStatement ps=null;
		
		int id=0;
		String name=null;
		String address=null;
					
		int result=0;
		ResultSet rs=null;
		String print_Query=null;
		
		try {
		 
			sc=new Scanner(System.in);
			
			//read input that how many insertion takes place.
			if(sc!=null)
				System.out.println("Enter the number of details you want to Insert ?");
			count=sc.nextInt();
			
			//registering Driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
		
			//Establishing connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
			
			//query= Insert into student values(?,?,?)
			insert_Query="INSERT INTO STUDENT VALUES(?,?,?)";
			
			//prepare Statement
			if(con!=null)
			ps=con.prepareStatement(insert_Query);
			
			//taking input from the user multiple time as user want
			if(sc!=null){
				for(int i=1;i<=count;i++) {
					System.out.println("Enter Id of the student ?");
					id=sc.nextInt();
					System.out.println("Enter Name of the student ?");
					name=sc.next();
					System.out.println("Enter Address of the student ?");
					address=sc.next();
					
					// set the input value read from end user as query parameter 
					ps.setInt(1, id);
					ps.setString(2, name);
					ps.setString(3, address);
					
					
					//Count the number of inserted data from the is entered
					if(ps!=null)
					result=ps.executeUpdate();
					
					//checking weather the data is entered in the database or not
					if(result==0)
						System.out.println("Insertion of Data fails");
					else
						System.out.println("Insertion of Data is Successfull");
					
				}//for
				System.out.println();
				System.out.println("Did you want to see the inserted Data ?  Y/N");
				if(sc!=null) {
					char ch=sc.next().charAt(0);
					
					print_Query="SELECT SID, SNAME, SADD FROM STUDENT";
				
				if(ch=='Y' || ch=='y') {
					
				rs=ps.executeQuery(print_Query);
					
					if(rs!=null) 
						//printing details of the student
							while(rs.next()) {
								System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3));
							};
						}
				if(ch=='N' || ch=='n') {
					System.out.println("Thank you");
				}
					
				}
			}//if
		}//try
		
		//catching the exception
		catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
		}
		
		catch(SQLException se) {
			se.printStackTrace();
		}
		
		catch(Exception e) {
			e.printStackTrace();
		}
		
		//Closing all objects
		finally {
			//closing prepare statement  class object
			try {
				if(ps!=null)
					ps.close();
			}
			catch(SQLException se){
				se.printStackTrace();
			}
			//closing connection class object
			try{
				if(con!=null)
					con.close();
				}
			catch(SQLException se) {
				se.printStackTrace();
			}
			//closing scanner class object
			try{
				if(sc!=null)
					sc.close();
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
	}//main

}//class
