package com.nt.imageInsert;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;
import java.sql.PreparedStatement;



/*
 * Author   : Sumit kumar gupta
 * Version  : 1.0
 * Project  : Photo inserting in database using BLOB datatype by using try with resource 
 * Table 	: studDetail with values ID,NAME,ADDR,IMAGE
 */
public class PhotoInsert {

	public static void main(String[] args) {
		String insert_query=null;
		int id=0;
		String name=null;
		String addr=null;
		String image_path=null;
		int result=0;
		InputStream is=null;
		File file=null;
		Connection con=null;
		Scanner sc=null;
		PreparedStatement ps=null;
		try{
			sc=new Scanner(System.in);

			System.out.print("Enter Student Id \t:\t");
			id=sc.nextInt();
			
			System.out.print("Enter Student name \t:\t");
			name=sc.next();
			
			System.out.print("Enter Student address \t:\t");
			addr=sc.next();
			
			System.out.print("Enter Image path\t:\t");
			image_path=sc.next().substring(1);
			
			//create InputStream by locating file based on photopath
			file=new File(image_path);
			is=new FileInputStream(file);
		
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish the Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
			
			if(con!=null)
				insert_query="INSERT INTO STUDDETAIL VALUES(?,?,?,?)";
				ps=con.prepareStatement(insert_query);
					
				if(ps!=null) {
					ps.setInt(1, id);
					ps.setString(2, name);
					ps.setString(3, addr);
					ps.setBinaryStream(4,is,file.length());
				}
				if(ps!=null) {
					result=ps.executeUpdate();
					
					if(result==0) System.out.println("Record not inserted");
					else System.out.println("Record Inserted");
				}
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			catch(ClassNotFoundException cnf) {
			cnf.printStackTrace();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
		finally {
			try {
				if(ps!=null)
					ps.close();
				}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
				}
			catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(sc!=null)
					sc.close();
				}
			catch(Exception e) {
				e.printStackTrace();
			}
		}//finally
		

	}//main

}//class
