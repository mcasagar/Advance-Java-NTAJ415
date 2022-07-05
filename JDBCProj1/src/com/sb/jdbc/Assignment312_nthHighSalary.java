package com.sb.jdbc;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Assignment312_nthHighSalary {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		ResultSet rs=null;
		int nth_highSalary=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				System.out.println("Enter no to get nth salary :: ");
				nth_highSalary=sc.nextInt();
			}
			//ready for vehicle
			if(con!=null) {
				st=con.createStatement();
			}
			//prepare SQL Query
			String query="select * from (select EMPNO ,ENAME, JOB , SAL, rank() over (order by SAL DESC) ranking from emp) where ranking="+nth_highSalary;
			
			//sent and execute query to database software
			if(st!=null){
				rs=st.executeQuery(query);
			}
			
			//process the resultset object
			if(rs!=null) {
				while(rs.next()) {
					System.out.println(rs.getInt(1)+" "+rs.getString(2)+" "+rs.getString(3)+" "+rs.getFloat(4));
				}
			}
		}//try
		catch(SQLException e) {
			e.printStackTrace();
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
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			try {
				if(con!=null) 
					con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}//finally

	}//main

}//class
