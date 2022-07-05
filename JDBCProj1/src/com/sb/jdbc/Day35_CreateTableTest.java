//CreateTableTest.java
package com.sb.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.SQLException;

public class Day35_CreateTableTest {

	public static void main(String[] args) {
		Connection con = null;
		Statement st = null;
		
		try {
			//Established connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//Create jdbc satement object
			if(con!=null) {
				st = con.createStatement();
			}
			//prepare sql Query 
			String query = "CREATE TABLE TEMP_STUDENT (sno number(5) primary key, sname varchar2(20))";
			
			// send and execute SQL query in database software
			int count = 0;
			if(st!=null){
				count = st.executeUpdate(query);
			}
			System.out.println("Count :: "+count);
			if(count==0)
				//process the result
				System.out.println("databse is created");
			else
				System.out.println("database is not created");
			
		}catch(SQLException e) {
			e.printStackTrace();
			if(e.getErrorCode()==955)
				System.out.println("Database already created ra dirty fellow");
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(st!= null)
					st.close();
			}catch(SQLException e) {
				e.printStackTrace();
				
			}
			try {
				if(con!= null)
					con.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}//finally close
	}//main close

}//class close
