package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class InsertTest {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int sno=0;
		String sname=null;
		String sadd=null;
		float avg=0.0f;
		
		try {
			//read input 
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Sno :: ");
				sno=sc.nextInt();
				System.out.println("Enter sname :: ");
				sname=sc.next();
				System.out.println("Enter Sadd :: ");
				sadd=sc.next();
				System.out.println("Enter avg add percentage :: ");
				avg=sc.nextFloat();
			}
			//conversion as sql query required
			sname="'"+sname+"'";
			sadd="'"+sadd+"'";
			
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			//ready vehicle
			if(con!=null)
				st=con.createStatement();
			
			//preapare sql query
			// INSERT INTO STUDENT VALUES(116,'SURBHI','DHULE',55.45);
			String query=" INSERT INTO STUDENT VALUES("+sno+","+sname+","+sadd+","+avg+")";
			//send and execute sql query in db s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("Record not inserted");
			else
				System.out.println("Record successfully inserted :: "+count);
			
		}
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(sc!=null)
					sc.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
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

}//class1
