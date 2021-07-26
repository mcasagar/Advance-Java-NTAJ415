//SelectTest4.java
package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest4 {

	public static void main(String[] args) {
			Scanner sc=null;
			Connection con=null;
			Statement st=null;
			ResultSet rs=null;
		try {
			//read input
			sc=new Scanner(System.in);
			int no=0;
			if(sc!=null) {
				System.out.println("Enter the DEPTNO :: ");
				no=sc.nextInt();//gives deptno from enduser
				
			}
			//Established the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null) {
				//Create statement object (ready vehicle)
				st=con.createStatement();
				
			}
			//prepare SQL query
			//SELECT EMPNO,ENAME,JOB,DEPTNO FROM EMP WHERE DEPTNO=10;
			String query="SELECT EMPNO,ENAME,JOB,DEPTNO FROM EMP WHERE DEPTNO="+no;
			if(st!=null) {
				//Send and execute SQL query to db s/w
				rs=st.executeQuery(query);
			}
			if(rs!=null) {
				//process the ResultSet Object
				if(rs.next())
					System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getInt(4));
				else
					System.out.println("no record found");
			}
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=900 && se.getErrorCode()<=999)
				System.out.println("Please Enter Proper table name,coloumn name, sql query");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all JDBC Objects
			try {
				if(sc!=null)
					sc.close();
			}//try1
			catch(Exception e) {
				e.printStackTrace();
			}
			try {
				if(st!=null)
					st.close();
			}//try2
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(rs!=null)
					rs.close();
			}//try3
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(con!=null)
					con.close();
			}//try4
			catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
	}//main

}//class
