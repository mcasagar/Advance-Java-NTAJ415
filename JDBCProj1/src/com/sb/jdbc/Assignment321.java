//Assignment321.java
package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Assignment321 {

	public static void main(String[] args) {
		Scanner sc=null;
		String sno=null;
		Connection con=null;
		Statement st=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				//take sno from end user
				System.out.println("Enter the sno :: ");
				sno=sc.next();
			}
						
			//Established the connection 
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			if(con!=null)
				//create statement object (readhy vehicle)
				st=con.createStatement();
			
			//prepare sql query
			//DELETE FROM STUDENT WHERE SNO=20
			String query="DELETE FROM STUDENT WHERE SNO="+sno;
			int count=0;
			if(st!=null) {
				count=st.executeUpdate(query);
			}
			if(count!=0)
				System.out.println("no. of record that are effected is :: "+count);
			else
				System.out.println("No record are deleted");
		}
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
		}//Finally

	}//main

}//class
