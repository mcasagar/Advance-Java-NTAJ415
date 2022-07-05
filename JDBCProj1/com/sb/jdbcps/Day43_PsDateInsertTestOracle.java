package com.sb.jdbcps;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class Day43_PsDateInsertTestOracle {

	public static void main(String[] args) {
		final  String INSERT_DATE_QUERY ="INSERT INTO PERSON_INFO_DATES(PNAME,DOB,DOJ,DOM) VALUES(?,?,?,?)";
		Scanner sc = null;
		Connection con = null;
		PreparedStatement ps=null;
		
		try {
			//read inputs
			sc = new Scanner(System.in);
			int count=0;
			String pname=null, pdob=null, pdoj=null, pdom=null;
			if(sc!=null) {
				System.out.println("Enter Person Name :: ");
				pname = sc.next();
				System.out.println("Enter Person DOB(dd-MM-yyyy :: " );
				pdob=sc.next();
				System.out.println("Enter Person DOJ(yyy-MM-dd) :: ");
				pdoj=sc.next();
				System.out.println("Enter Person DOM(MMM-dd-yyyy) :: ");
				pdom=sc.next();
			}
			//Convert String date class values to java.sql.Date class object
			//for DOB (dd-MM-yyyy)
			  //Convert String date value to java.util.Date class obj
			   SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
			   java.util.Date udob = sdf.parse(pdob);
			   //converting java.util.Date class object to java.sql.Date calss obj
			   long ms = udob.getTime();
			   java.sql.Date sqdob = new java.sql.Date(ms);
			   
			   //for DOJ (yyyy-MM-dd -direct conversion)
			   //converting String date value to java.sql.Date class obj
			   java.sql.Date sqdoj = java.sql.Date.valueOf(pdoj);
			   
			   //for DOM (MMM-dd-yyyy)
			   //coverting String date value to java.util.Date class obj 
			   SimpleDateFormat sdf2 = new SimpleDateFormat("MMM-dd-yyyy");
			   java.util.Date udom = sdf2.parse(pdom);
			   //converting java.util.Date class object to java.sql.Date class obj
			   ms = udom.getTime();
			   java.sql.Date sqdom = new java.sql.Date(ms);
			
			
			//Established Connection 
			con = DriverManager.getConnection("jdbc:mysql:///NTAJ415DB1","root","root");
			
			//Create PreparedStatement object having pre-compiled SQL Query
			if(con!=null)
				ps = con.prepareStatement(INSERT_DATE_QUERY);
			
			//set values to sql param
			if(ps!=null) {
				ps.setString(1, pname);
				ps.setDate(2,sqdob);
				ps.setDate(3, sqdoj);
				ps.setDate(4, sqdom);
			}
			//execute query in db s/w
			if(ps!=null)
				count=ps.executeUpdate();
			
			//process the result
			if(count==0)
				System.out.println("Record are not inserted");
			else
				System.out.println("Record are inserted");
		}catch(SQLException se) {
			se.printStackTrace();
		}catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			//close all resources
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
