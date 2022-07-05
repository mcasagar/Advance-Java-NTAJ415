package com.sb.jdbcps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class Day40_PsInsertTestSurrogatePK {

	public static void main(String[] args) {
		final  String INSERT_STUDENT_QUERY ="INSERT INTO STUDENT VALUES(SNO_01.NEXTVAL,?,?,?)";
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps=null;
		
		try {
			//read inputs
			sc = new Scanner(System.in);
			int count=0;
			if(sc!=null) {
				System.out.println("Enter Total studens no :: ");
				count = sc.nextInt();
			}
			
			//Established Connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			//Create PreparedStatement object having pre-compiled SQL Query
			if(con!=null)
				ps = con.prepareStatement(INSERT_STUDENT_QUERY);
			
			//read input value from end-user, set them to query param value and execute the pre-compiled sql
			// query for multiple times
			if(ps!=null && sc!=null) {
				for(int i =1; i<=count; i++) {
					//read Each student input value
					System.out.println("Enter  "+i+" Student details :: ");
					System.out.println("Enter student name :: ");
					String name = sc.next();
					System.out.println("Enter student address ::");
					String add = sc.next();
					System.out.println("Enter Student Average :: ");
					float avr = sc.nextFloat();	
					//Set each student details as per pre-compiled sql query 
					ps.setString(1,name);ps.setString(2, add);ps.setFloat(3, avr);
					//execute pre-compiled sql query each time
					int result =ps.executeUpdate();
					//process execution result of pre-compiled sql query
					if(result==0)
						System.out.println(i+ " Student details is not inserted");
					else
						System.out.println( i+ " Student details are inserted");
				}//for
			}//if
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all resources
			try {
				if(ps!=null)
					ps.close();
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
