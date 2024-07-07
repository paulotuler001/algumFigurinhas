package repositories;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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

	public LittleFigure getLFById(Integer id) {

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
		}
		
		SQLite.closeConnection();
		
		return lf;
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
			return null;
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
			
			count++;
        }
		SQLite.closeConnection();
		return lfs;
		}catch(SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
			return null;
		}
		
	}
	
	public ArrayList<ImageIcon> getAllPhotos() {
		
		String sql = "SELECT photo FROM lFigure";
		ArrayList<ImageIcon> lfs = new ArrayList<>();
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			ByteArrayInputStream bais = new ByteArrayInputStream(rs.getBytes(1));
			try {
				BufferedImage newImage = ImageIO.read(bais);
				lfs.add(new ImageIcon(newImage));
			} catch (IOException e) {
				e.printStackTrace();
			}
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
	
	public void deleteAllLFs() {
		String sql = "DELETE FROM lFigure;";
		try(Connection conn = SQLite.getConnection();
				Statement stmt = conn.createStatement();){
			System.out.println("All LFs were been delete successfully");
			stmt.executeUpdate(sql);
			SQLite.closeConnection();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
		}
	}

	public Integer getNewId() {
		String sql = "SELECT MAX(l.id) FROM lFigure l;";
		
		try(Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			ResultSet rs = pstmt.executeQuery();
			Integer returno = 1;
			if(rs.next()) {
				returno = rs.getInt(1) == 0 ? 1 : rs.getInt(1) == 1 ? 2 : rs.getInt(1)+1;
			}
			SQLite.closeConnection();
			return returno;
		}catch(SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
			return null;
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
	
	private static BufferedImage toBufferedImage(Image img) {
        if (img instanceof BufferedImage) {
            return (BufferedImage) img;
        }
        BufferedImage bImage = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2 = bImage.createGraphics();
        g2.drawImage(img, 0, 0, null);
        g2.dispose();

        return bImage;
    }
}
