package com.sb.jdbcps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class Day44_PsDateRetrieveOracleTest {
	private static final String RETREIVE_DATE = "SELECT PID,PNAME,DOB,DOJ,DOM FROM PERSON_INFO_DATES";
	public static void main(String[] args) {
		Connection con=null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			//Established the connection 
			con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","manager");
			//create PreparedStatement Object
			if(con!=null)
				ps = con.prepareStatement(RETREIVE_DATE);
			
			//set and execute query in database software
			if(ps!=null) {
				rs = ps.executeQuery();
			}
			//process the ResultSet
//			if(rs!=null) {	
//				while(rs.next()) {
//					System.out.println(rs.getInt(1)+" "+rs.getString(2)+ " "+rs.getString(3)+" "+rs.getString(4)+" "+rs.getString(5));
//				}//while
//			}//if
			if(rs!=null) {
				while(rs.next()) {
					int pno = rs.getInt(1);
					String pname = rs.getString(2);
					java.sql.Date dob = rs.getDate(3);
					java.sql.Date doj = rs.getDate(4);
					java.sql.Date dom = rs.getDate(5);
					System.out.println(pno + "   "+pname+ "   "+dob+ "   "+doj+ "   "+dom);
					
					//Convert java.sql.Date object to String date values
					SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
					String dob1=sdf.format(dob);
					String doj2 = sdf.format(doj);
					String dom2 = sdf.format(dom);
					System.out.println(pno + "   "+pname+ "   "+dob1+ "   "+doj2+ "   "+dom2);
				}
			}
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all resources
			try {
				if(rs!=null)
					rs.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
			try {
				if(ps!=null)
					ps.close();
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
