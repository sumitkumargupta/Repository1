package com.nt.datetime;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;


public class DateRetrieve {
	//creating a variable of selecting date query which cant be changed anywhere.
	private static final String DATE_SELECT_QUERY="SELECT  NO,NAME,DOB,DOJ,DOM FROM EMPLOYEE";  
	
	public static void main(String[] args) {
		//Declaration variable
				int no=0;
				String name=null;
				SimpleDateFormat sdf=null;
				String	sdob=null,sdoj=null,sdom=null;
				java.util.Date udob=null, udom=null, udoj=null;
				java.sql.Date sqdob=null, sqdoj=null, sqdom=null;
				
				Connection con=null;
				PreparedStatement ps=null;
				ResultSet rs=null;
				boolean flag=false;
				
							try {
								//register Driver
								Class.forName("oracle.jdbc.driver.OracleDriver");
								
								//establishing connection
								con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
								
								
							   //prepare statement
								if(con!=null)
								ps=con.prepareStatement(DATE_SELECT_QUERY);
								
								//set values of prepared statement
								
								
								//execute query
								if(ps!=null)
									rs=ps.executeQuery();
								
								//process the result
								if(rs!=null) {
								while(rs.next()) {
									flag=true;
									no=rs.getInt(1);
									name=rs.getString(2);
									sqdob=rs.getDate(3);
									sqdoj=rs.getDate(4);
									sqdom=rs.getDate(5);
									
									/*If we print these details the by default in resultset
									 * object then it will maintain java.sql.Date having pattern 
									 * yyyy-MM-dd 
									 */
									
																		
									//converting sql formated dates to  java.util.Date
									udob=(java.util.Date)sqdob; 
									udoj=(java.util.Date)sqdoj;
									udom=(java.util.Date)sqdom;
									
									/* in place of this we can also write
		 							 *udob=sqdob;
		 							 *udoj=sqdoj;
		 							 *udom=sqdom;
		 							 */
									
									//printing the util formated date which print same sql date format(yyyy-MM-dd)
									
									//Converting java.util.Date to String date values
									//creating object of simpledateformat who follow the format of date as(yyy-MM-dd) 
										sdf=new SimpleDateFormat("MMM-dd-yy");
										if(sdf!=null) {
											sdob=sdf.format(udob);
											sdoj=sdf.format(udoj);
											sdom=sdf.format(udom);
										}
										System.out.println(no+" "+name+" "+sdob+" "+sdoj+" "+sdom);
									}//while
								
								if(flag==false)
									System.out.println("Record not Found");
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
					
					
					
				}//finally

			}

		}



