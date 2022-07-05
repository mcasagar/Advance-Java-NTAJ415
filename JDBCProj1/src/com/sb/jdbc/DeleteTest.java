//DeleteTest.java  Lecture no 32
package com.sb.jdbc;

import java.util.Scanner;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DeleteTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String city=null;
		Connection con=null;
		Statement st=null;
		
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				//take city name from end user which he want to delete
				System.out.println("Enter City Name :: ");
				city=sc.next();
			}
			//convert input as required sql query
			city="'"+city+"'";
			
			//Established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			//create statement object (ready vehicle)
			if(con!=null)
				st=con.createStatement();
			
			//prepare sql query
			//DELETE FROM STUDENT WHERE SADD='KARLE'
			String query="DELETE FROM STUDENT WHERE SADD="+city;
			
			//send and execute sql query to database software
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("record not deleted");
			else
				System.out.println("no of record that are effected ::"+count);
							
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all jdbc objects
			try {
				if(sc!=null)
					sc.close();
			}
			catch(Exception e) {
				e.printStackTrace();
			}
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

	}//main

}//class

