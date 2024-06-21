package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.Administrator;
import entities.Author;
import entities.Collectionator;
import entities.User;
import entities.LittleFigure;
import enums.Role;

public class UserRepository {

	public void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS user(\n" 
		+ "id integer primary key,\n" 
		+ "name TEXT NOT NULL,\n"
		+ "role TEXT NOT NULL\n"
		+ ");";

		try (Connection conn = SQLite.getConnection(); Statement stmt = conn.createStatement();) {

			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveUser(User user) {

		String sql = "INSERT INTO user(id, name, role) VALUES(?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setString(3, user.getRole().toString());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public User getUserById(Integer id) {

		String sql = "SELECT * FROM user WHERE user.id = ?";
		User user = null;

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int userId = rs.getInt("id");
				String userName = rs.getString("name");
				String role = rs.getString("role");

				if (role.equals(Role.ADM.toString())) {
					user = new Administrator(userId, userName, null, null, userName, userName, null, userName, userId);
				} else if (role.equals(Role.AUTHOR.toString())) {
					user = new Author(userId, userName, null, null, userName, userName, null, userName, userId);
				} else {
					user = new Collectionator(userId, userName, null, null, userName, userName, null, userName, userId);
				}

				this.toString(user);
			}
			
			SQLite.closeConnection();

			return user;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return user;
			
		}
	}

	public void getAllUsers() {

		String sql = "SELECT * FROM user";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				System.out.println("ID: " + id + ", Nome: " + name);
			}
			
			SQLite.closeConnection();

		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}

	}

	public void deleteUserById(Integer id) {

		String sql = "DELETE FROM user WHERE user.id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);
			pstmt.executeQuery();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void editUserById(Integer id, User user) {

		String sql = "UPDATE user SET id = ?, nome = ? WHERE user.id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.setInt(3, id);
			pstmt.executeQuery();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void login(User user) {
		
		String sql = "SELECT * FROM user WHERE user.email = ? and user.password = ?";
		
		try(Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, user.getEmail());
			pstmt.setString(2, user.getPassword());
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
	}
	

	public void toString(User user) {
		System.out.println(user.getId() + " " + user.getName());
	}
}
