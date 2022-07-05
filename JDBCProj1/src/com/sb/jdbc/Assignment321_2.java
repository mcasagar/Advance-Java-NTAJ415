package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Assignment321_2 {

	public static void main(String[] args) {
		Scanner sc=null;
		Connection con=null;
		Statement st=null;
		int acno=0;
		try {
			sc=new Scanner(System.in);
			if(sc!=null) {
				//take account no form user
				System.out.println("Enter Account No which you want to delete:-");
				acno=sc.nextInt();
			}
			
			//establised the connection
			con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//ready vehicle
			if(con!=null)
				st=con.createStatement();
			
			//prepared sql query for delete operation
			//delete bank where acno=113;
			String query="DELETE BANK WHERE ACNO="+acno;
			
			//process the operation
			int count=0;
			if(st!=null)
				count=st.executeUpdate(query);
			
			if(count==0)
				System.out.println("no record found");
			else
				System.out.println("no of record that are effected :: "+count);
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

	}//main(-)

}//class
