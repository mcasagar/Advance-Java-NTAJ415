package com.sb.jdbc;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SelectTest5 {

	public static void main(String[] args) {
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		try {
			//loading oracle jdbc driver class (now a days it is optional)
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			
			//Established connection to oracle database software
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			if(con!=null) {
				//statement object (ready vehicle)
				st=con.createStatement();
			}//if
			
			//prepare for sql query
			//SELECT COUNT(*) FROM STUDENT;
			String query="SELECT COUNT(*) FROM STUDENT";
			if(st!=null) {
				//send and execute sql query to oracle database software and get ResultSet obj
				rs=st.executeQuery(query);
			}
			
			if(rs!=null) {
				//process the result (record 0 or 1)
				rs.next();
				//int count=rs.getInt(1);
				int count=rs.getInt("COUNT(*)");  //We can also use aggregate function as a column name
				if(count!=0)
					System.out.println("The total record of Student table is :-"+count);
				else
					System.out.println("No Record found");
			}//if
		}//try
		catch(SQLException se) {
			if(se.getErrorCode()>=9000 && se.getErrorCode()<=999)
				System.out.println("pls check colomn name,table name,sql query");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all jdbc objects
			try {
				if(st!=null)
					st.close();
			}
			catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(rs!=null)
					rs.close();
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
