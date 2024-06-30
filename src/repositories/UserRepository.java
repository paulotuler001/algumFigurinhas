package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.User;
import enums.Role;

public class UserRepository {

	public UserRepository() {

		String sql = "CREATE TABLE IF NOT EXISTS user(\n" + "id integer primary key,\n" + "active boolean,\n"
				+ "name TEXT NOT NULL,\n" + "role TEXT NOT NULL,\n" + "email TEXT NOT NULL,\n"
				+ "password TEXT NOT NULL,\n" + "deleteDescription TEXT NOT NULL\n" + ");";

		try (Connection conn = SQLite.getConnection(); Statement stmt = conn.createStatement();) {
			
			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public void save(User user) {

		String sql = "INSERT INTO user(id, active, name, role, email, password, deleteDescription) VALUES(?, ?, ?, ?, ?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, user.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getRole().toString());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPassword());
			pstmt.setString(7, user.getDeleteDescription());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public Boolean getUserById(Integer id) {
		// todo - retornar o album
		String sql = "SELECT * FROM user WHERE id = ?";
		User user = null;
		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				Integer colId = rs.getInt("id");
				String name = rs.getString("name");
				String email = rs.getString("email");
				String role = rs.getString("role");
				String password = rs.getString("password");
				String deleteDescription = rs.getString("deleteDescription");
				if(role.equals("COLLECTIONATOR"))
					user = new User(colId, true, name, Role.COLLECTIONATOR, email, password, deleteDescription);
				else
					user = new User(colId, true, name, Role.AUTHOR, email, password, deleteDescription);
					
				this.toString(user);
			}

			SQLite.closeConnection();
			return user != null;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
			return false;
		}
	}

	public void getAllUsers() {

		String sql = "SELECT * FROM user";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

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
				System.out.println("ID: " + id + ", Nome: " + name
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
		}
	}

	public void deleteUserById(Integer id) {

		String sql = "DELETE FROM user WHERE id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public void editUserById(Integer id, User user) {

		String sql = "UPDATE user SET id = ?, active = ?, name = ?, role = ?, email = ?, password = ?, deleteDescription = ? WHERE id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, user.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getRole().toString());
			pstmt.setString(5, user.getEmail());
			pstmt.setString(6, user.getPassword());
			pstmt.setString(7, user.getDeleteDescription());
			pstmt.setInt(8, id);
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public void toString(User user) {
		System.out.println(user.getId() + " " + user.getName());
	}
}