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
		+ "nome TEXT NOT NULL\n" 
		+ ");";

		try (Connection conn = SQLite.getConnection(); Statement stmt = conn.createStatement();) {

			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void saveUser(User user) {

		String sql = "INSERT INTO user(id, nome) VALUES(?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, user.getId());
			pstmt.setString(2, user.getName());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getUserById(Integer id) {

		String sql = "SELECT * FROM user WHERE user.id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int userId = rs.getInt("id");
				String userName = rs.getString("name");
				String role = rs.getString("role");
				User user = null;

				if (role == Role.ADM.toString()) {
					user = new Administrator(userId, userName, null, null, userName, userName, null, userName, userId);
				} else if (role == Role.AUTHOR.toString()) {
					user = new Author(userId, userName, null, null, userName, userName, null, userName, userId);
				} else {
					user = new Collectionator(userId, userName, null, null, userName, userName, null, userName, userId);
				}

				this.toString(user);
			}

			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public void getAllUsers() {

		String sql = "SELECT * FROM user";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("nome");
				System.out.println("ID: " + id + ", Nome: " + name);
			}

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

	public void toString(User user) {
		System.out.println(user.getId() + " " + user.getName());
	}
}
