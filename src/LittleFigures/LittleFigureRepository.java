package LittleFigures;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import Configuration.SQLite;

public class LittleFigureRepository {

	LittleFigure lFigure;

	public void createTable() {
		
		String sql = "CREATE TABLE IF NOT EXISTS lFigure(\n"
				+ "id integer primary key,\n"
				+ "nome TEXT NOT NULL\n"
				+ ");";
		
		try (Connection conn = SQLite.getConnection(); 
			Statement stmt = conn.createStatement();) 
		{
			
			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void saveLF(LittleFigure lFigure) {
		
		String sql = "INSERT INTO lFigure(id, nome) VALUES(?, ?)";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1, lFigure.id);
		pstmt.setString(2, lFigure.name);
		pstmt.executeUpdate();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public void getLFById(Integer id) {

		String sql = "SELECT * FROM lFigure WHERE lFigure.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1,  id);
		
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			int lfId = rs.getInt("id");
			String name = rs.getString("nome");
			
			LittleFigure lf = new LittleFigure(lfId, name);	
			this.toString(lf);
		}
		
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}
	
	public void getAllLittleFigures() {
		
		String sql = "SELECT * FROM lFigure";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("nome");
            System.out.println("ID: " + id + ", Nome: " + name);
        }
			
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	public void deleteLFById(Integer id) {
		
		String sql = "DELETE FROM lFigure WHERE lFigure.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1,  id);
		pstmt.executeQuery();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}
	
	public void editLFById(Integer id, LittleFigure lFigure) {
		
		String sql = "UPDATE lFigure SET id = ?, nome = ? WHERE lFigure.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1,  lFigure.id);
		pstmt.setString(2,  lFigure.name);
		pstmt.setInt(3,  id);
		pstmt.executeQuery();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}
	
	public void toString(LittleFigure lFigure) {
		System.out.println(lFigure.getId() + " " + lFigure.getName());
	}
}
