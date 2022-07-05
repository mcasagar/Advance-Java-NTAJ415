package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class UpdateTest {

	public static void main(String[] args) {
		Scanner sc=null;
		String sname=null,sadd=null,avg=null,sno=null;
		Connection con=null;
		Statement st=null;
		try {
			//read input
			sc=new Scanner(System.in);
			if(sc!=null) {
				//read sname from end user
				System.out.println("Enter sname :: ");
				sname=sc.next();
				//read sadd from end user
				System.out.println("Enter sadd :: ");
				sadd=sc.next();
				//read avg from end user
				System.out.println("Enter avg :: ");
				avg=sc.next();
				System.out.println("Enter sno :: ");
				sno=sc.next();
			}
			//convert input as a sql query requirement 
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			
			//establieshed the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			if(con!=null)
				//Create Statement object (ready vehicle)
				st=con.createStatement();
			
			//prepare sql query
			// UPDATE STUDENT SET SNAME='rucha',sadd='pune',avg=77.98 WHERE SNO=104
			String query=" UPDATE STUDENT SET SNAME="+sname+",sadd="+sadd+",avg="+avg+"WHERE SNO="+sno;
			System.out.println(query);
			
			int count=0;
			if(con!=null) {
				//send and execute sql query to db s/w
				count=st.executeUpdate(query);
			}
			if(count!=0)
				System.out.println("no of record that are effect is :: "+count);
			else
				System.out.println("no record updated");
			
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
		}//finally

	}//main

}//class
