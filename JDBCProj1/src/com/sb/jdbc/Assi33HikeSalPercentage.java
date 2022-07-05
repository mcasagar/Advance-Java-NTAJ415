package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Assi33HikeSalPercentage {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		float percentage=0.0f;
		String desg1=null,desg2=null,desg3=null;
		try {
			//take input
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter Desg1 :: ");
				desg1=sc.next().toUpperCase();
				System.out.println("Enter Desg2:: ");
				desg2=sc.next().toUpperCase();
				System.out.println("Enter Desg3:: ");
				desg3=sc.next().toUpperCase();
				System.out.println("Enter Hike Percentage :: ");
				percentage=sc.nextInt();
			}
			//conversion as sql query requirement
			desg1="'"+desg1+"'";
			desg2="'"+desg2+"'";
			desg3="'"+desg3+"'";
			
			//established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			//ready vehicle
			if(con!=null)
				st=con.createStatement();
			
			//prepared sql query
			//UPDATE EMP SET SAL=SAL+(SAL*10/100) WHERE JOB IN ('CLERK','MANAGER','ANALYST');
			String query="UPDATE EMP SET SAL=SAL+(SAL*"+percentage/100+") WHERE JOB IN ("+desg1+","+desg2+","+desg3+")";
			//print query
			System.out.println(query);
			//send and execute query in database s/w
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==1)
				System.out.println("no of record that are effected:: "+count);
			else
				System.out.println("no record updated");
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=999 && se.getErrorCode()<=900)
				System.out.println("invalid colom name,table name or SQL keyword");
			else if(se.getErrorCode()==12899)
				System.out.println("do not insert more than col size data to sname,sadd,cols");
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

}//class
