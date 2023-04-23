package conexiones.servidor;

import java.sql.*;

public class Conexion {

	//private static final String URL = "jdbc:mysql://localhost:3306/tokyo2021";
	private static final String URL = "jdbc:mysql://sql10.freemysqlhosting.net/sql10600890";
	private static final String USER = "sql10600890";
	private static final String PASSWORD = "G3FZpRP2Y5";
	private static final String CONTROLADOR = "com.mysql.cj.jdbc.Driver";

	static {

		try {
			Class.forName(CONTROLADOR);

		} catch (ClassNotFoundException e) {
			System.out.println("Error al cargar controlador");
			e.printStackTrace();
		}

	}
	
	public Connection conectar() {
		Connection conexion = null;
		
		try {
			conexion = DriverManager.getConnection(URL,USER,PASSWORD);
			System.out.println("Connexion établie.");
		}catch(SQLException e) {
			System.out.println("Erreur de connexion");
			e.printStackTrace();
		}
		return conexion;
	}
}
