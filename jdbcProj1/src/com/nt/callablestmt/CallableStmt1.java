package com.nt.callablestmt;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

/*
 * project 		: Using CallableStatement object to get employee name , salary, job, based on given empno 
 * 			 		by calling PL/SQL procedure of oracle database.
 * PL/SQL code	: create or replace procedure p_get_empdetail(no in number, name out varchar2,desig out varchar2,salary out number)as
 *					begin
 *					select ename,job, sal into name,desig, salary from emp where empno=no;
 *					end; 
 */
public class CallableStmt1 {
	private final static String p_call_query= "{CALL P_GET_EMPDETAIL(?,?,?,?)}";
	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		CallableStatement cs=null;
		int no=0;
		try {
			//enter employee id
			sc=new Scanner(System.in);
			System.out.println("Enter the ID of employee ? ");
			no=sc.nextInt();
			
			//register driver
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Establish connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","system","sumit");
			
			//create Callable Statement
			if(con!=null) {
				cs=con.prepareCall(p_call_query);
				
				//Register out parameter with jdbc drive
				cs.registerOutParameter(2,Types.VARCHAR);
				cs.registerOutParameter(3,Types.VARCHAR);
				cs.registerOutParameter(4,Types.INTEGER);
				
				//set value to  IN parameter
				cs.setInt(1, no);
				
				//Call PL/SQL procedure
				cs.execute();
				
				//gather result from out parameter
				System.out.println("\t\tEmployee Detail");
				System.out.println("\t\t--------------------------");
				System.out.println("\t\tName "+cs.getString(2));
				System.out.println("\t\tDesignation "+cs.getString(3));
				System.out.println("\t\tSalary "+cs.getInt(4));
				
			}//try
		}
		catch(SQLException se) {
			if(se.getErrorCode()==1403)System.out.println("No Record Found");;
		}
		catch(ClassNotFoundException cnf){
			cnf.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(cs!=null)
					cs.close();
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
			catch(Exception e) {
				e.printStackTrace();
			}
			
		}//finally

	}//main

}//class
