package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.Album;

public class AlbumRepository {
	
	public AlbumRepository() {

		String sql = "CREATE TABLE IF NOT EXISTS album(\n" 
		+ "id integer primary key,\n" 
		+ "name TEXT NOT NULL,\n"
		+ "totalLFigures INTEGER"
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

		String sql = "INSERT INTO album(id, name, totalLFigures) VALUES(?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, album.getId());
			pstmt.setString(2, album.getName());
			pstmt.setInt(3, album.getTotalLFigures());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public Boolean getAlbumById(Integer id) {
//todo - retornar o album
		String sql = "SELECT * FROM album WHERE album.id = ?";
		Album album = null;
		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int albumId = rs.getInt("id");
				String albumName = rs.getString("name");
				int totalLFigures = rs.getInt("totalLFigures");
				album = new Album(albumId, albumName, totalLFigures);
				
				this.toString(album);
			}
			
			SQLite.closeConnection();
			return album.getId() > 1 ;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
			return false ;
		}
	}

	public void toString(Album album) {
		System.out.println(album.getId() + " " + album.getName());
	}
}
