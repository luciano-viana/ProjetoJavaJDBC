package conexaojdbc;

import java.sql.Connection;
import java.sql.DriverManager;

//Somente essa classe será do tipo static para ela ser única.
public class SingleConnection {

	private static String url = "jdbc:postgresql://localhost:5433/posjava"; // Url do banco de dados
	private static String password = "admin"; // Senha do banco de dados
	private static String user = "postgres"; // Usuário do banco de dados
	private static Connection connection = null; // Classe de conexão com o banco de dados

	// Sempre de qualquer lugar que invocar a classe de conexão ele vai chamar o
	// conectar()
	static {
		conectar();
	}

	// Contrutor
	public SingleConnection() {
		conectar();
	}

	// Método privado para ser acessível somente dentro do objeto
	private static void conectar() {
		try {
			/*
			 * Escrever parte de conexão se conexão for = nula irá conectar se não, não irá
			 * fazer nada, conexão tem que ser feita somente uma vez e mantida, o que irá
			 * abrir e fechar depois são seções no banco de dados
			 */
			if (connection == null) {// Verifica se já existe a conexão
				Class.forName("org.postgresql.Driver");// Registra o driver do banco de dados
				connection = DriverManager.getConnection(url, user, password);// Faz a conexão com o banco de dados
				connection.setAutoCommit(false);// Utilizado para não salvar automáticamente
				System.out.println("Conectou com Sucesso!");

			}

		} catch (Exception e) {
			e.printStackTrace();// Para imprimir no console caso aja algum erro
		}
	}

	// Método que irá retornar a conexão, é publico porque tem que ser acessível de
	// outros lugares
	public static Connection getConnection() {
		return connection;
	}

}
