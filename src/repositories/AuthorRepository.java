package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.Author;
import enums.Role;

public class AuthorRepository {
	
	public void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS author(\n" 
		+ "id integer primary key,\n" 
		+ "active boolean,\n"
		+ "name TEXT NOT NULL,\n"
		+ "role TEXT NOT NULL,\n"
		+ "email TEXT NOT NULL,\n"
		+ "password TEXT NOT NULL,\n"
		+ "deleteDescription TEXT NOT NULL\n"
		+ ");";

		try (Connection conn = SQLite.getConnection(); Statement stmt = conn.createStatement();) {

			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveAuthor(Author author) {

	String sql = "INSERT INTO author(id, active, name, role, email, password, deleteDescription) VALUES(?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, author.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, author.getName());
			pstmt.setString(4, Role.AUTHOR.toString());
			pstmt.setString(5, author.getEmail());
			pstmt.setString(6, author.getPassword());
			pstmt.setString(7, author.getDeleteDescription());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Boolean getAuthorById(Integer id) {
		//todo - retornar o album
		String sql = "SELECT * FROM author WHERE album.id = ?";
		Author author = null;
			try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
				pstmt.setInt(1, id);
				
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					Integer colId = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String password = rs.getString("password");
					String deleteDescription = rs.getString("deleteDescription");
					
					author = new Author(colId, true, name, Role.AUTHOR, email, password,
							deleteDescription);
					
					this.toString(author);
				}
				
				SQLite.closeConnection();
				return author.getId() > 1 ;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return false ;
			}
}
	
	public void getAllAuthors() {
		
		String sql = "SELECT * FROM author";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		
		ResultSet rs = pstmt.executeQuery();
		
		while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
//            String tag = rs.getString("tag");
//			String photo = rs.getString("photo");
//			String description = rs.getString("description");
//			Integer page = rs.getInt("page");
//			Integer number = rs.getInt("number");
//			Integer ownerId = rs.getInt("ownerId");
//			Integer authorId = rs.getInt("authorId");
			System.out.println("ID: " + id + 
	                   ", Nome: " + name 
//	                   ", Tag: " + tag + 
//	                   ", Foto: " + photo + 
//	                   ", Descrição: " + description + 
//	                   ", Página: " + page + 
//	                   ", Número: " + number + 
//	                   ", ID do Proprietário: " + ownerId + 
//	                   ", ID do Autor: " + authorId
	                   );
        }
		
		SQLite.closeConnection();
		}catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		
		
	}

	public void deleteAuthorById(Integer id) {
		
		String sql = "DELETE FROM author WHERE author.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1,  id);
		pstmt.executeQuery();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public void editAuthorById(Integer id, Author author) {
		
		String sql = "UPDATE author SET id = ?, active = ?, name = ?, role = ?, email = ?, senha = ?, deleteDescription = ? WHERE author.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
			pstmt.setInt(1, author.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, author.getName());
			pstmt.setString(4, Role.COLLECTIONATOR.toString());
			pstmt.setString(5, author.getEmail());
			pstmt.setString(6, author.getPassword());
			pstmt.setString(7, author.getDeleteDescription());
			pstmt.setInt(8,  id);
			pstmt.executeQuery();
			SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public void toString(Author author) {
		System.out.println(author.getId() + " " + author.getName());
	}
}
