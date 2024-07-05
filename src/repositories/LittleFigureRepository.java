package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.LittleFigure;

public class LittleFigureRepository {

	LittleFigure lFigure;

	public LittleFigureRepository() {
		
		String sql = "CREATE TABLE IF NOT EXISTS lFigure(\n"
				+ "id integer primary key,\n"
				+ "name TEXT NOT NULL,\n"
				+ "tag TEXT NOT NULL,\n"
				+ "photo BLOB NOT NULL,\n"
				+ "description TEXT,\n"
				+ "page TEXT NOT NULL,\n"
				+ "number INTEGER NOT NULL,\n"
				+ "ownerId INTEGER NOT NULL\n"
				+ ");";
		
		try (Connection conn = SQLite.getConnection(); 
			Statement stmt = conn.createStatement();) 
		{
			
			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}
	
	public void saveLF(LittleFigure lFigure) {
		
		String sql = "INSERT INTO lFigure(id, name, tag, photo, description, page, number, ownerId) VALUES(?, ?, ?, ?, ?, ?, ?, ?)";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1, lFigure.getId());
		pstmt.setString(2, lFigure.getName());
		pstmt.setString(3, lFigure.getTag());
		pstmt.setBytes(4, lFigure.getPhoto());
		pstmt.setString(5, lFigure.getDescription());
		pstmt.setInt(6, lFigure.getPage());
		pstmt.setInt(7, lFigure.getNumber());
		pstmt.setInt(8, lFigure.getOwnerId());
		pstmt.executeUpdate();
		SQLite.closeConnection();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
		}
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
			String name = rs.getString("name");
			String tag = rs.getString("tag");
			byte[] photo = rs.getBytes("photo");
			String description = rs.getString("description");
			Integer page = rs.getInt("page");
			Integer number = rs.getInt("number");
			Integer ownerId = rs.getInt("ownerId");
			
			lf = new LittleFigure(lfId, name, tag, photo, description, page, number, ownerId);	
			this.toString(lf);
		}
		
		SQLite.closeConnection();
		
		return lf != null;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
			return false;
		}
	}
	
	public Object[][] getAllLittleFigures() {
		
		String sql = "SELECT * FROM lFigure";
		Object[][] lfs = new Object[100][3];
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		
		ResultSet rs = pstmt.executeQuery();
		int count = 0;
		while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
			Integer page = rs.getInt("page");
			String idd = String.format("%03d", id);
			
			lfs[count][0] = idd;
			lfs[count][1] = name;
			lfs[count][2] = page;
        }
		SQLite.closeConnection();
		return lfs;
		}catch(SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	public void deleteLFById(Integer id) {
		
		String sql = "DELETE FROM lFigure WHERE lFigure.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1,  id);
		pstmt.executeUpdate();
		SQLite.closeConnection();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
		}
	}
	
	public void editLFById(Integer id, LittleFigure lFigure) {
		
		String sql = "UPDATE lFigure SET id = ?, name = ?, tag = ?, photo = ?, description = ?, page = ?, number = ?, ownerId = ? WHERE lFigure.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, lFigure.getId());
			pstmt.setString(2, lFigure.getName());
			pstmt.setString(3, lFigure.getTag());
			pstmt.setBytes(4, lFigure.getPhoto());
			pstmt.setString(5, lFigure.getDescription());
			pstmt.setInt(6, lFigure.getPage());
			pstmt.setInt(7, lFigure.getNumber());
			pstmt.setInt(8, lFigure.getOwnerId());
			pstmt.setInt(9,  id);
			pstmt.executeUpdate();
			SQLite.closeConnection();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
		}
	}
	
	public void toString(LittleFigure lFigure) {
		System.out.println(lFigure.getId() + " " + lFigure.getName());
	}
}
