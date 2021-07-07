//SelectTest3.javas
package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SelectTest3 {

	public static void main(String[] args) {
				Scanner sc=null;
				Connection con=null;
				Statement st=null;
				String intChar=null;
				ResultSet rs=null;
		try {
			//read inputs
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter The First char :: ");
				intChar=sc.next().toUpperCase();//gives first character of name
			}
			//conversion as the SQL query requirement
			intChar="'"+intChar+"%'";
			
			//Established Connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			if(con!=null)
				//statement object (ready vehicle)
				st=con.createStatement();
			
			//prepare SQL query as as a SQL query requirement
			// SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE 'S%';
			String query="SELECT EMPNO,ENAME,JOB,SAL FROM EMP WHERE ENAME LIKE"+intChar;
			
			//Send and execute SQL qery to databse software and get ResultSet obj
			if(st!=null)
				rs=st.executeQuery(query);
			boolean flag=false;
			while(rs.next()) {
				flag=true;
				System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4));
			}
			if(flag==false)
				System.out.println("No Record found");
			
			
			
		}//try
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
