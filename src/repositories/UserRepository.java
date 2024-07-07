package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.mindrot.jbcrypt.*;

import configuration.SQLite;
import entities.User;
import enums.Role;

public class UserRepository {

	public UserRepository() {

		String sql = "CREATE TABLE IF NOT EXISTS user(\n" + "id integer primary key,\n" + "active boolean,\n"
				+ "name TEXT NOT NULL,\n" + "role TEXT NOT NULL,\n" + "email TEXT NOT NULL,\n"
				+ "password TEXT NOT NULL);";

		try (Connection conn = SQLite.getConnection(); Statement stmt = conn.createStatement();) {
			
			stmt.execute(sql);
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public void save(User user) {

		String sql = "INSERT INTO user(id, active, name, role, email, password) VALUES(?, ?, ?, ?, ?, ?)";
		
		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, user.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getRole().toString());
			pstmt.setString(5, user.getEmail());
			String bcryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			pstmt.setString(6, bcryptedPassword);
			pstmt.executeUpdate();
			System.out.println("salvo");
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}

	public User getUserById(Integer id) {
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
				if(role.equals("COLLECTIONATOR"))
					user = new User(colId, true, name, Role.COLLECTIONATOR, email, password);
				else if(role.equals("AUTHOR"))
					user = new User(colId, true, name, Role.AUTHOR, email, password);
				else
					user = new User(colId, true, name, Role.ADM, email, password);
				this.toString(user);
			}

			SQLite.closeConnection();
			return user;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
			return null;
		}
	}

	public Object[][] getAllUsers() {

		String sql = "SELECT id, email, role FROM user";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {

			ResultSet rs = pstmt.executeQuery();
			int count = 0;
			Object[][] users = new Object[100][100];
			while (rs.next()) {
				int id = rs.getInt("id");
				String email = rs.getString("email");
				String role = rs.getString("role");
				users[count][0] = id;
				users[count][1] = email;
				users[count][2] = role;
				count++;
			}
			
			SQLite.closeConnection();
			return users;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			SQLite.closeConnection();
			return null;
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

		String sql = "UPDATE user SET id = ?, active = ?, name = ?, role = ?, email = ?, password = ? WHERE id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, user.getId());
			pstmt.setBoolean(2, true);
			pstmt.setString(3, user.getName());
			pstmt.setString(4, user.getRole().toString());
			pstmt.setString(5, user.getEmail());
			String bcryptedPassword = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
			pstmt.setString(6, bcryptedPassword);
			pstmt.setInt(7, id);
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
		}
	}
	
	public Integer getNewId() {
		String sql = "SELECT MAX(u.id) FROM user u;";
		
		try(Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)){
			
			ResultSet rs = pstmt.executeQuery();
			Integer returno = 1;
			if(rs.next()) {
				returno = rs.getInt(1) == 0 ? 1 : rs.getInt(1);
			}
			returno++;
			SQLite.closeConnection();
			return returno;
		}catch(SQLException e) {
			SQLite.closeConnection();
			System.out.println(e.getMessage());
			return null;
		}
	}

	public void toString(User user) {
		System.out.println(user.getId() + " " + user.getName());
	}
}