package repositories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import configuration.SQLite;
import entities.LittleFigure;

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
		pstmt.setInt(1, lFigure.getId());
		pstmt.setString(2, lFigure.getName());
		pstmt.executeUpdate();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public Boolean getLFById(Integer id) {

		String sql = "SELECT * FROM lFigure WHERE lFigure.id = ?";
		LittleFigure lf = null;
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1,  id);
		
		
		ResultSet rs = pstmt.executeQuery();
		if(rs.next()) {
			int lfId = rs.getInt("id");
			String name = rs.getString("nome");
			
			lf = new LittleFigure(lfId, name);	
			this.toString(lf);
		}
		
		SQLite.closeConnection();
		
		return lf.getId() > 0;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			return false;
		}
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
		pstmt.setInt(1,  lFigure.getId());
		pstmt.setString(2,  lFigure.getName());
		pstmt.setInt(3,  id);
		pstmt.executeQuery();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}
	
	public void toString(LittleFigure lFigure) {
		System.out.println(lFigure.getId() + " " + lFigure.getName());
	}
}
