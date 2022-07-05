package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class Day35_Assig1_AlterTableTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			//Established connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			
			if(con!=null)
				//Create JDBC	 statement object
				st = con.createStatement();
			
			// Prepared sql query 
			String query = "ALTER TABLE TEMP_STUDENT ADD SNAME VARCHAR2(20)";
			
			int count =0;
			//Send and execute sql query in database software
			if(st!=null)
				count = st.executeUpdate(query);
			System.out.println(count);
			if(count==0)
				System.out.println("Table is updated");
			else
				System.out.println("'Table is not updated");
			
		}catch(SQLException se) {
			//se.printStackTrace();
			if(se.getErrorCode()==1430)
				System.out.println("ORA-01430: column being added already exists in table");
		}catch(Exception e) {
			
		}
		finally {
			//close statement object
			try {
				if(st!=null)
					st.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
			//close connection object
			try {
				if(con!=null)
					con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}//finally
		
	}//main(-)

}//class
