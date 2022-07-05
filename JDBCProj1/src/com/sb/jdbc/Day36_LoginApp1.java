package com.sb.jdbc;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Day36_LoginApp1 {

	public static void main(String[] args) {
		Scanner sc = null;
		Connection con = null;
		Console cons = null;
		Statement st = null;
		ResultSet rs = null;
		
		try {
			//read input from end user
			cons=System.console();  //java.util.Console not working in eclipse environment
			//sc = new Scanner(System.in);
			String username =null, password = null;
			
			if(cons!=null) {
				System.out.println("Enter User Name :: ");
				username = cons.readLine(); // gives raja rao
				System.out.println("Enter Password");
				password =new String(cons.readPassword()); // gives rani
			}
			//convert input values as sql query requirements
			username = "'"+username+"'"; // gives 'raja'
			password = "'"+password+"'"; //gives 'rani'
			
			//Established connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			if(con!= null)
				//create statement object (ready vehicle
				st = con.createStatement();
			
			//prepared sql query
			String query = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME="+username+" AND PWD="+password;
			System.out.println(query);
			
			//send and execute query in database software
			if(st!=null)
				rs = st.executeQuery(query);
			//System.out.println("after execute query"+rs);
			
			if(rs!=null) {
				//process the ResultSet object
				rs.next();
				int count = 2; 
				count = rs.getInt(1);	//get first column value of that first record
				//System.out.println(count);
				if(count == 0)
					System.out.println("Invalid Creadential");
				else
					System.out.println("Valid creadential");
			}//if
				
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}//catch 
		finally {
			//close all connections
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(st!=null) {
					st.close();
				}
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally

	}//main

}//class
