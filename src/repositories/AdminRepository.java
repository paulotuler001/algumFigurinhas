package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import configuration.SQLite;
import entities.Administrator;
import enums.Role;

public class AdminRepository {

	public void createTable() {

		String sql = "CREATE TABLE IF NOT EXISTS admin(\n" 
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

	public void saveAdmin(Administrator adm) {

		String sql = "INSERT INTO admin(id, name, role) VALUES(?, ?, ?)";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, adm.getId());
			pstmt.setString(2, adm.getName());
			pstmt.setString(3, adm.getRole().toString());
			pstmt.executeUpdate();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}

	public Boolean getAdminById(Integer id) {

		String sql = "SELECT * FROM admin WHERE admin.id = ?";
		Administrator adm = null;

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
			pstmt.setInt(1, id);

			ResultSet rs = pstmt.executeQuery();
			if (rs.next()) {
				int userId = rs.getInt("id");
				String userName = rs.getString("name");
				String role = rs.getString("role");

				if (role.equals(Role.ADM.toString())) 
					adm = new Administrator(userId, userName, Role.ADM);
				

				this.toString(adm);
			}
			
			SQLite.closeConnection();

			return adm.toString().length()>0;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return false;
			
		}
	}

	public void editAdminById(Integer id, Administrator adm) {

		String sql = "UPDATE admin SET id = ?, name = ? WHERE admin.id = ?";

		try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
			pstmt.setInt(1, adm.getId());
			pstmt.setString(2, adm.getName());
			pstmt.setInt(3, id);
			pstmt.executeQuery();
			SQLite.closeConnection();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void login(String login, String senha) {
		
		if(login.equals("admin") && senha.equals("coxinha123")) {
			System.out.println("entrou");
		}else{
			System.out.println("login ou senha incorreto");
		}
		
	}
	

	public void toString(Administrator adm) {
		System.out.println(adm.getId() + " " + adm.getName());
	}
}
