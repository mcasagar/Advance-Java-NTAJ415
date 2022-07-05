package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Day35_DropTableTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			// Established the connection
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			if(con!= null)
				//Create statement object
				st = con.createStatement();
			//prepare sql query;
			String query = "DROP TABLE TEMP_STUDENT";
			
			int count = 0;
			if(st!= null)
				//send and execute sql query in database software
				count = st.executeUpdate(query);
			System.out.println("Count :: "+count);
			if(count==0)
				System.out.println("Table is dropped");
			else
				System.out.println("Table not drop");
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
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
		}//close finally

	}//close main

}//close class
