package configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLite
{
    private static final String URL = "jdbc:sqlite:test.db";
    private static Connection conn = null;
    
    public static Connection getConnection() {
    	if(conn == null) {
    		try {
    			conn = DriverManager.getConnection(URL);
    		}catch(SQLException sql) {
    			System.out.println("Erro ao conectar com o banco: " + sql.getMessage());
    		}
    	}
    	return conn;
    }
    
    public static void closeConnection() {
    	if(conn != null) {
    		try {
    			conn.close();
    			conn = null;
    		}catch(SQLException sql) {
    			System.out.println("Erro ao desconectar com o banco: " + sql.getMessage());
    		}
    	}
    }
}

