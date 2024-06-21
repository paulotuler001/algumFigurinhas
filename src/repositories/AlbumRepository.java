package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.Administrator;
import entities.Album;
import entities.Author;
import entities.Collectionator;
import entities.User;
import enums.Role;

public class AlbumRepository {
	
	public void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS album(\n" 
		+ "id integer primary key,\n" 
		+ "name TEXT NOT NULL\n"
		+ "authorId INTEGER UNIQUE"
		+ "totalLFigures INTEGER"
		+ ");";

		try (Connection conn = SQLite.getConnection(); Statement stmt = conn.createStatement();) {

			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveAlbum(Album album) {

		String sql = "INSERT INTO album(id, name, authorId, totalLFigures) VALUES(?, ?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, album.getId());
			pstmt.setString(2, album.getName());
			pstmt.setInt(3, album.getAuthorId());
			pstmt.setInt(4, album.getTotalLFigures());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Boolean getAlbumById(Integer id) {

		String sql = "SELECT * FROM album WHERE album.id = ?";
		Album album = null;
		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int albumId = rs.getInt("id");
				String albumName = rs.getString("name");
				int authorId = rs.getInt("authorId");
				int totalLFigures = rs.getInt("totalLFigures");
				album = new Album(albumId, albumName, authorId, totalLFigures);
				
				this.toString(album);
			}
			
			SQLite.closeConnection();
			return album.getId() > 1 ;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false ;
		}
	}

	public void getAllAlbums() {

		String sql = "SELECT * FROM album";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int authorId = rs.getInt("authorId");
				int totalLFigures = rs.getInt("totalLFigures");
				System.out.println("ID: " + id + ", Nome: " + name + ", AuthorId: " + authorId + ", Total Little Figures: " + totalLFigures);
			}

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void deleteAlbumById(Integer id) {

		String sql = "DELETE FROM album WHERE album.id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editAlbumById(Integer id, Album album) {

		String sql = "UPDATE album SET id = ?, name = ?, authorId = ?, totalLFigures = ? WHERE album.id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, album.getId());
			pstmt.setString(2, album.getName());
			pstmt.setInt(3, album.getAuthorId());
			pstmt.setInt(4, album.getTotalLFigures());
			pstmt.setInt(5, id);
			pstmt.executeQuery();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void toString(Album album) {
		System.out.println(album.getId() + " " + album.getName());
	}
}
