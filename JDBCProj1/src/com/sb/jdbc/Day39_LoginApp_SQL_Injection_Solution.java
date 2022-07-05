package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Day39_LoginApp_SQL_Injection_Solution {

	public static void main(String[] args) {
		final String INSERT_INTO_STUDENT = "SELECT COUNT(*) FROM IRCTC_TAB WHERE UNAME=? AND PWD=?";
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		
		try {
			//read input from end user
			sc = new Scanner(System.in);
			String username =null, password = null;
			
			if(sc!=null) {
				System.out.println("Enter User Name :: ");
				username = sc.nextLine(); //gives raja
				System.out.println("Enter Password");
				password = sc.nextLine(); // gives rani
			}//if
			
			//Established connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			//create jdbc PreparedStatement Object
			if(con!= null)
					ps = con.prepareStatement(INSERT_INTO_STUDENT);
			
			//Set the values to the param of pre-compiled sql query
			if(con!=null && ps!=null) {
				ps.setString(1, username);
				ps.setString(2,password);
				//sent and execute the SQL query in Db software
				rs = ps.executeQuery();
			}
			
			//process the resultset object
			if(rs!=null) {
				rs.next();
				int count = rs.getInt(1);
				if(count==0)
					System.out.println("INVALID CREDENTIAL");
				else
					System.out.println("VALID CREDENTIAL");
			}//if
			
				
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}//catch
		finally {
			//close all connections
			
			try {
				if(ps!=null) {
					ps.close();
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
