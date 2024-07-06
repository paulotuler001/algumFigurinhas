package configuration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLite {
	private static final String DB_NAME = "test.db";
	private static final String DB_PATH = System.getProperty("user.dir") + File.separator + DB_NAME;
	private static final String URL = "jdbc:sqlite:" + DB_PATH;
	private static Connection conn = null;

	public SQLite() {
		File dbFile = new File(DB_NAME);
		if (!dbFile.exists()) {
			try (InputStream is = SQLite.class.getResourceAsStream("/" + DB_NAME)) {
				if (is == null) {
					throw new IOException("Database file not found in JAR");
				}
				Files.copy(is, dbFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
				System.out.println("Database extracted to: " + dbFile.getAbsolutePath());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(URL);
			} catch (SQLException sql) {
				System.out.println("Erro ao conectar com o banco: " + sql.getMessage());
			}
		}
		return conn;
	}

	public static void closeConnection() {
		if (conn != null) {
			try {
				conn.close();
				conn = null;
			} catch (SQLException sql) {
				System.out.println("Erro ao desconectar com o banco: " + sql.getMessage());
			}
		}
	}
}
