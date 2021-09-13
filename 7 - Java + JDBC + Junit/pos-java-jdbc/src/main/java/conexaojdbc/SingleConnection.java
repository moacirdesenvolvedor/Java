package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

public class SingleConnection {
	
	//Url do banco de dados
	private static String url = "jdbc:postgresql://localhost:5432/posjava";
	
	//Senha do banco de dados
	private static String password = "admin";
	
	//User do banco de dados
	private static String user = "postgres";
	
	// Classe de conexão com o banco de dados
	private static Connection connection = null;
	
	static {
		conectar();
	}
	
	public SingleConnection() {
	 conectar();
	}
	
	private static void conectar(){
		try {
			
			// Verifica se já existe a conexão
			if (connection == null){
				
				/// Registra o driver do banco de dados
				Class.forName("org.postgresql.Driver");
				
				// Faz a conexão com o banco de dados
				connection = DriverManager.getConnection(url, user, password);
				
				// Configuração para não commitar automaticamente os dados no banco de dados
				connection.setAutoCommit(false);
				
				System.out.println("Conectou com sucesso");
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection(){
		return connection;
	}

}
