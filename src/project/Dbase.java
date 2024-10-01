package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Dbase {

	Connection conn;
	PreparedStatement stmt;
	public Dbase() {
		try {
			conn = DriverManager.getConnection("jdbc:oracle:thin:@coestudb.qu.edu.qa:1521/STUD.qu.edu.qa",
					"fe2104034","fe2104034");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
		
		
	
	
	// on keys and non keys 
	// for equality and range
	public String linearSearch(String table, String field, String condition) {
		PreparedStatement stmt;
		ResultSet rs;
		String sql1 = "select index_type from metadata where Table_name = ? and column_name = ?";
		String sql2 = "select total_rows from statistics where Table_name = ?";
		String index = "";
		int records =0;
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1,table);
			stmt.setString(2,field);
			
			rs = stmt.executeQuery();
			if (rs.next())
				index = rs.getString("index_type");
			
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1,table);
			rs = stmt.executeQuery();
			
			if (rs.next())
				records = rs.getInt("total_rows");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (index.equals("Primary")) {
			System.out.println("records in linear "+records);
			double result = (double)(records/2);
			return Double.toString(result);
		}
		else {
			double result = records;
			return Double.toString(result);
		}
		
	}
	
	
	// for keys and non keys
	// for equality search and range(only for keys)
	// bfr = 1
	// condition = [=, <, >]
	public String binarySearch(String table, String field, String condition, String value) {
		
		PreparedStatement stmt;
		ResultSet rs;	
		String sql1 = "select index_type from metadata where Table_name = ? and column_name = ?";
		String sql2 = "select total_rows from statistics where Table_name = ?";
		String sql3 = "select distinct_values from metadata where Table_name = ? and column_name = ?";
		String sql4 = "select max_val from metadata where Table_name = ? and column_name = ?";
		String sql5 = "select min_val from metadata where Table_name = ? and column_name = ?";
		String index = "";
		int records =0;
		int bfr = 1;
		double s =0;
		int exactValues = 0;
		double selectivity = 0;
		int max =0;
		int min =0;
	
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			if (rs.next())
				index = rs.getString("index_type");
			
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, table);
			rs = stmt.executeQuery();
			
			if (rs.next())
				records = rs.getInt("total_rows");
			
			
			stmt = conn.prepareStatement(sql3);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				exactValues = rs.getInt("distinct_values");
			}
			
			stmt = conn.prepareStatement(sql4);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				max = rs.getInt("max_val");
			}
			
			stmt = conn.prepareStatement(sql5);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			
			if (rs.next()) {
				min = rs.getInt("min_val");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (index.equals("Primary") && condition.equals("=")) {
			double result = (double)(Math.log(records)/Math.log(2));
			result = (int) Math.ceil(result);
			return Double.toString(result);
		}
		
		// key range
		// we will assume that it will read the average of blocks
		else if (index.equals("Primary")){
			selectivity = (double) ((max-Integer.parseInt(value))/(max-min));
			s = Math.ceil(selectivity*records);
			double result = (Math.log(records)/Math.log(2)) + s;
			result = (int) Math.ceil(result);
			return Double.toString(result);
		}
		
		return "";
		

	}
	
	
	
	// equality on key
	public String primaryIndex(String table, String field, String condition) {
		if (!condition.equals("="))
			return "";
		PreparedStatement stmt;
		ResultSet rs;	
		String sql1 = "select index_type from metadata where Table_name = ? and column_name = ?";
		String sql2 = "select blevel from metadata where Table_name = ? and column_name = ?";
		String index = "";
		int tl = 0;
		
		try {
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			if (rs.next())
				index = rs.getString("index_type");
			
			
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			if (rs.next())
				tl = rs.getInt("blevel");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (index.equals("Primary"))
			return String.valueOf(tl+1);
		
		return "";
	}
	
	
	// Range on key
	public String orderingIndex(String table, String field, String condition) {
		PreparedStatement stmt;
		ResultSet rs;	
		String sql1 = "select index_type from metadata where Table_name = ? and column_name = ?";
		String sql2 = "select total_rows from statistics where Table_name = ?";
		String sql3 = "select blevel from metadata where Table_name = ? and column_name = ?";
		String index = "";
		int records =0;
		int tl = 0;
		
		if (condition.equals("="))
			return "";
		
		try {
			stmt = conn.prepareStatement(sql3);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			if (rs.next())
				tl = rs.getInt("blevel");
			
			
			stmt = conn.prepareStatement(sql1);
			stmt.setString(1, table);
			stmt.setString(2, field);
			
			rs = stmt.executeQuery();
			if (rs.next())
				index = rs.getString("index_type");
			
			if (index.equals("Primary")) {
				stmt = conn.prepareStatement(sql2);
				stmt.setString(1, table);
				rs = stmt.executeQuery();
				if (rs.next())
					records = rs.getInt("total_rows");
				
			}
			
			// we will return empty string to indicate that this field is not a key
			else {
				return "";
			}
				
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		double result = tl + (records/2);
		return String.valueOf(result);
}
	
	
	public String Btree(String table, String field, String condition) {
		System.out.println("condition "+ condition);
		PreparedStatement stmt;
		ResultSet rs;	
		String sql1 = "select index_type from metadata where Table_name = ? and column_name = ?";
		String sql2 = "select total_rows from statistics where Table_name = ?";
		String sql3 = "select distinct_values from metadata where Table_name = ? and column_name = ?";
		String sql4 = "select blevel from metadata where Table_name = ? and column_name = ?";
		String index = "";
		int records =0;
		double s = 0;
		int exactValues = 0;
		double selectivity =0;
		int tl =0;
		
		try {
			
			stmt = conn.prepareStatement(sql4);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			if (rs.next())
				tl = rs.getInt("blevel");
			System.out.println("blevel "+tl);
			
			
			stmt = conn.prepareStatement(sql2);
			stmt.setString(1, table);
			rs = stmt.executeQuery();
			
			if (rs.next())
				records = rs.getInt("total_rows");
			System.out.println("total "+records);
			
			
			stmt = conn.prepareStatement(sql3);
			stmt.setString(1, table);
			stmt.setString(2, field);
			rs = stmt.executeQuery();
			if (rs.next())
				exactValues = rs.getInt("distinct_values");
			System.out.println("exact values "+ exactValues);
			
			if (condition.equals("=")) {
				stmt = conn.prepareStatement(sql1);
				stmt.setString(1, table);
				stmt.setString(2, field);
				rs = stmt.executeQuery();
				if (rs.next())
					index = rs.getString("index_type");
				
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		// for range query
		if (!condition.equals("=")) {
			double result = tl + (records/2);
			return "(b|1)/2 +"+result;
		}
		
		// for equality on key
		else if (index.equals("Primary")) {
			int result = tl +1;
			System.out.println("i entered");
			return String.valueOf(result);
		}
		
		// for equality on non key
		else {
			selectivity = Math.floor(1/exactValues);
			s = Math.floor(selectivity*records);
			double result = 1 + s + tl;
			return String.valueOf(result);
		}
		
		
	}
	
	public int nestedLoop() throws SQLException {
		String sql = "select total_rows from statistics where Table_name = 'Instructor'";
		String sq2 = "select total_rows from statistics where Table_name = 'Section'";
		int numOfRows1 = 0;
		int numOfRows2 = 0;
		PreparedStatement stmt;
		ResultSet rs1;
		ResultSet rs2;
		
		stmt = conn.prepareStatement(sql);
		rs1 = stmt.executeQuery();
		if (rs1.next()) {
			numOfRows1 = rs1.getInt("total_rows");
		}
		
		stmt = conn.prepareStatement(sq2);
		rs2 = stmt.executeQuery();
		if (rs2.next()) {
			numOfRows2 = rs2.getInt("total_rows");
		}
		return numOfRows1+(numOfRows1*numOfRows2);
	} 
	
	public int sortMerge() throws SQLException {
		String sql = "select total_rows from statistics where Table_name = 'Instructor'";
		String sq2 = "select total_rows from statistics where Table_name = 'Section'";
		int numOfRows1 = 0;
		int numOfRows2 = 0;
		PreparedStatement stmt;
		ResultSet rs1;
		ResultSet rs2;
		
		stmt = conn.prepareStatement(sql);
		rs1 = stmt.executeQuery();
		if (rs1.next()) {
			numOfRows1 = rs1.getInt("total_rows");
		}
		
		stmt = conn.prepareStatement(sq2);
		rs2 = stmt.executeQuery();
		if (rs2.next()) {
			numOfRows2 = rs2.getInt("total_rows");
		}
		return numOfRows1+numOfRows2;
	}
		
	
}

