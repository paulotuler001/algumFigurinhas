package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.Album;
import entities.LittleFigure;

public class AlbumRepository {
	
	public AlbumRepository() {

		String sql = "CREATE TABLE IF NOT EXISTS album(\n" 
		+ "id integer primary key,\n" 
		+ "name TEXT NOT NULL,\n"
		+ "pages INTEGER,"
		+ "cape BLOB"
		+ ");";

		try (Connection conn = SQLite.getConnection(); Statement stmt = conn.createStatement();) {

			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public void saveAlbum(Album album) {

		String sql = "INSERT INTO album(id, name, pages, cape) VALUES(?, ?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, album.getId());
			pstmt.setString(2, album.getName());
			pstmt.setInt(3, album.getPages());
			pstmt.setBytes(4, album.getCape());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}
	
		public void editAlbum(Album album) {
		
			String sql = "UPDATE album SET id = ?, name = ?, pages = ?, cape = ? WHERE album.id = 1";
			
			try(
			Connection conn = SQLite.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
				pstmt.setInt(1, album.getId());
				pstmt.setString(2, album.getName());
				pstmt.setInt(3, album.getPages());
				pstmt.setBytes(4, album.getCape());
				pstmt.executeUpdate();
				SQLite.closeConnection();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
				SQLite.closeConnection();
			}
		}

	public Album getAlbumById(Integer id) {
		
		String sql = "SELECT * FROM album WHERE album.id = ?";
		Album album = null;
		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int albumId = rs.getInt("id");
				String albumName = rs.getString("name");
				int pages = rs.getInt("pages");
				byte[] cape = rs.getBytes("cape");
				album = new Album(albumId, albumName, pages, cape);
			}
			SQLite.closeConnection();
			return album;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
			return null;
		}
	}

	public void toString(Album album) {
		System.out.println(album.getId() + " " + album.getName());
	}
}
