package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.Collectionator;
import enums.Role;

public class CollectionatorRepository {
		
	public void createTable() {

			String sql = "CREATE TABLE IF NOT EXISTS collectionator(\n" 
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

	public void saveCollectionator(Collectionator col) {

		String sql = "INSERT INTO collectionator(id, active, name, role, email, password, deleteDescription) VALUES(?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, col.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, col.getName());
			pstmt.setString(4, Role.COLLECTIONATOR.toString());
			pstmt.setString(5, col.getEmail());
			pstmt.setString(6, col.getPassword());
			pstmt.setString(7, col.getDeleteDescription());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Boolean getCollectionatorById(Integer id) {
		//todo - retornar o album
		String sql = "SELECT * FROM collectionator WHERE collectionator.id = ?";
		Collectionator col = null;
		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			
			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Integer colId = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String deleteDescription = rs.getString("deleteDescription");
				
				col = new Collectionator(colId, true, name, Role.COLLECTIONATOR, email, password,
						deleteDescription);
				
				this.toString(col);
			}
			
			SQLite.closeConnection();
			return col.getId() > 1 ;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false ;
		}
	}
	
	public void getAllCollectionator() {
			
			String sql = "SELECT * FROM collectionator";
			
			try(
			Connection conn = SQLite.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql);
			){
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
	            int id = rs.getInt("id");
	            String name = rs.getString("name");
//	            String tag = rs.getString("tag");
//				String photo = rs.getString("photo");
//				String description = rs.getString("description");
//				Integer page = rs.getInt("page");
//				Integer number = rs.getInt("number");
//				Integer ownerId = rs.getInt("ownerId");
//				Integer authorId = rs.getInt("authorId");
				System.out.println("ID: " + id + 
		                   ", Nome: " + name 
//		                   ", Tag: " + tag + 
//		                   ", Foto: " + photo + 
//		                   ", Descrição: " + description + 
//		                   ", Página: " + page + 
//		                   ", Número: " + number + 
//		                   ", ID do Proprietário: " + ownerId + 
//		                   ", ID do Autor: " + authorId
	            );
	        }
			SQLite.closeConnection();
			}catch(SQLException e) {
				System.out.println(e.getMessage());
			}
			
			
		}

	public void deleteCollectionatorById(Integer id) {
		
		String sql = "DELETE FROM collectionator WHERE collectionator.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1,  id);
		pstmt.executeQuery();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}
	
	public void editCollectionatorById(Integer id, Collectionator col) {
		
		String sql = "UPDATE collectionator SET id = ?, active = ?, name = ?, role = ?, email = ?, senha = ?, deleteDescription = ? WHERE collectionator.id = ?";
		
		try(
		Connection conn = SQLite.getConnection();
		PreparedStatement pstmt = conn.prepareStatement(sql);
		){
		pstmt.setInt(1, col.getId());
		pstmt.setBoolean(2, true);
		pstmt.setString(3, col.getName());
		pstmt.setString(4, Role.COLLECTIONATOR.toString());
		pstmt.setString(5, col.getEmail());
		pstmt.setString(6, col.getPassword());
		pstmt.setString(7, col.getDeleteDescription());
		pstmt.setInt(8,  id);
		pstmt.executeQuery();
		SQLite.closeConnection();
		}catch(SQLException e) {System.out.println(e.getMessage());}
	}

	public void toString(Collectionator col) {
		System.out.println(col.getId() + " " + col.getName());
	}
}

