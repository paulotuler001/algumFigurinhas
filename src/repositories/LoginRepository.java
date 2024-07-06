package repositories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import configuration.SQLite;
import entities.User;
import enums.Role;

public class LoginRepository {

	public User login(String login, String password) {

		String sql = "select * from user WHERE role = 'ADM'";
		User adm = null;
			try (Connection conn = SQLite.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql);) {
				
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					int userId = rs.getInt("id");
					String userName = rs.getString("name");
					adm = new User(userId, true, userName, Role.ADM, "", "");
				}
				SQLite.closeConnection();
				return adm;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				SQLite.closeConnection();
				return null;
			}
	}
	
	public User loginUser(String login, String password) {
		User user = null;
		String selectLogin = "select * from user where email = ?";
		
		try (Connection conn = SQLite.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(selectLogin);) {
				pstmt.setString(1, login);
//				pstmt.setString(2, password);
				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					Integer colId = rs.getInt("id");
					String name = rs.getString("name");
					String email = rs.getString("email");
					String role = rs.getString("role");
					String passwordd = rs.getString("password");
					
					if(!BCrypt.checkpw(password, passwordd)) {
						throw new Error("Wrong Answer");
					}
					
					if (role.equals("COLLECTIONATOR"))
						user = new User(colId, true, name, Role.COLLECTIONATOR, email, passwordd);
					else if(role.equals("AUTHOR"))
						user = new User(colId, true, name, Role.AUTHOR, email, passwordd);
					else 
						user = new User(colId, true, name, Role.ADM, email, passwordd);
				}
				SQLite.closeConnection();
				return user;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				SQLite.closeConnection();
				return null;
			}catch(Error e) {
				System.out.println(e.getMessage());
				SQLite.closeConnection();
				return null;
			}
	}
}
