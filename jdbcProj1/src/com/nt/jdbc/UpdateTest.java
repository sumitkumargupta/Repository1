package com.nt.jdbc;
/* Author  : Sumit Gupta
 * Version : 1.0
 * project : Checking the Designation of job present in company DataBase
 *  		 or not and if available the u can ask  to see the details of that designation
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;


public class UpdateTest {

	public static void main(String[] args) {
				//Declaring all he variable with default values
				Scanner sc=null;
				String desg=null;
				String desg1=null;
				Connection con=null;
				Statement st=null;
				ResultSet rs=null;
				String updateQuery=null;
				String selectQuery=null;
				int count=0;
				boolean flag=false;
				
				try {
				// Taking input from user 
					sc=new Scanner(System.in);
					System.out.println("Enter Designation");
					desg=sc.next();
					desg1=desg;
					desg="'"+desg+"'";
		
			//register driver
					Class.forName("oracle.jdbc.driver.OracleDriver");
					
			//Establish Connection
					con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
					
			//Create Statement
					if(con!=null)
						st=con.createStatement();
					
			//prepare update Query
					updateQuery="UPDATE EMP SET SAL=SAL+SAL*0.1 WHERE JOB="+desg;
					System.out.println(updateQuery);
					if(st!=null) {
					count=st.executeUpdate(updateQuery);
					}
					if(count==0)
						System.out.println(desg1+" Designation is not present");
					
					//In else condition we see detail also if the data is updated but by taking permission by the user
					else {
						System.out.println(count+" data of Desingnation "+desg1+" updated");
			
						//prepare select query
						selectQuery="SELECT ENAME, JOB, SAL FROM EMP WHERE JOB="+desg;
			
						//Taking input from user the to see updated details
						System.out.println();
						System.out.println("Did you want to see the updated detail ? Y/N ");
						char ch=sc.next().charAt(0);
					
					// check the codition wheather user want to see details or not
					if(ch=='Y' || ch=='y') {
						if(st!=null)
							rs=st.executeQuery(selectQuery);
							while(rs.next()) {
								flag=true;
								System.out.println(rs.getString(1)+" "+rs.getString(2)+" "+rs.getInt(3));
							}
					}
					if(ch=='N' || ch=='n') {
						System.out.println("Thank u !");
					}
					
					}			

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


}//main
}//class