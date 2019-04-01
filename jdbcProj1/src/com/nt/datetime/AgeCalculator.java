package com.nt.datetime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class AgeCalculator {
		 
		//creating a variable of selecting date query which cant be changed anywhere.
		  
		
		public static void main(String[] args) {
					//Declaration variable
					Scanner sc=null;
					int no=0;
					double age=0.0;
					String query=null;
					Connection con=null;
					PreparedStatement st=null;
					ResultSet rs=null;
					boolean flag=false;
					
								try {
									sc=new Scanner(System.in);
									System.out.println("Enter id no of Employee ?");
									no=sc.nextInt();
									
									//register Driver
									Class.forName("oracle.jdbc.driver.OracleDriver");
									
									//establishing connection
									con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
									
									//Query
									query="SELECT (SYSDATE-DOB)/365 FROM EMPLOYEE WHERE NO="+"'"+no+"'";
									
									//creating Statement
									if(con!=null)
									st=con.prepareStatement(query);
									
									//execute query
									if(st!=null)
									rs=st.executeQuery();
									
									//calculating age of employee
									if(rs!=null) {
										if(rs.next()) {
										 flag=true;
											age=rs.getDouble(1);
											}
										if(flag==false)
											System.out.println("No Employee found of this id");
										else
											System.out.println(" Employee age is : "+age);
											
									}
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
						if(st!=null)
							st.close();
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
						
						
						
					}//finally

	}

}
