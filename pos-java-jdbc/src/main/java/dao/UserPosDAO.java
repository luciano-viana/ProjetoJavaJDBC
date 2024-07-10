package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	// Contrutor
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	// ---------------------------------------------INSERT--------------------------------------------------------------------------
	// Método publico que não precisa retornar nada, método para fazer "Insert" no
	// BD
	public void salvar(Userposjava userposjava) {
		try {
			String sql = "insert into userposjava(id, nome, email) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql); // ele que irá fazer o insert, tem toda
			insert.setLong(1, userposjava.getId());
			insert.setString(2, userposjava.getNome());
			insert.setString(3, userposjava.getEmail());
			insert.execute();// Utilizado para executar o insert no Banco
			connection.commit();// Salva no Banco

		} catch (Exception e) {
			try {
				connection.rollback();// Se der erro irá reverter a operação no banco de dados
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	// ---------------------------------------------CONSULTA COMPLETA "SELECT *" -------------------------------------------------------
	// Método que retorna uma lista de objetos do tipo Userposjava
	public List<Userposjava> listar() throws Exception {
		List<Userposjava> list = new ArrayList<Userposjava>();// Instancia a lista para não dar erro

		String sql = "select * from userposjava";// sql de consulta no banco de dados

		PreparedStatement statement = connection.prepareStatement(sql);// passado o sql para o prepareStatement que
																		// prepara o sql
		ResultSet resultado = statement.executeQuery();// executado no banco de dados

		while (resultado.next()) {// Interar o resultado, enquanto tiver dados vai pecorrer a lista criando novos
									// objetos e adicionar na lista
			Userposjava userposjava = new Userposjava();
			userposjava.setId(resultado.getLong("id"));
			userposjava.setNome(resultado.getString("nome"));
			userposjava.setEmail(resultado.getString("email"));

			list.add(userposjava);
		}

		return list;
	}

	// -------------------------------------------CONSULTA SÓ DE UM OBJETO "SELECT * WHERE -----------------------------------------------
	public Userposjava buscar(Long id) throws Exception { // Não retorna lista, somente um objeto
		Userposjava retorno = new Userposjava();

		String sql = "select * from userposjava where id = " + id;// sql de consulta no banco de dados

		PreparedStatement statement = connection.prepareStatement(sql);// passado o sql para o prepareStatement que
																		// prepara o sql
		ResultSet resultado = statement.executeQuery();// executado no banco de dados

		while (resultado.next()) {// retornar apenas um ou nem um
			retorno.setId(resultado.getLong("id"));
			retorno.setNome(resultado.getString("nome"));
			retorno.setEmail(resultado.getString("email"));

		}

		return retorno;
	}

	// ----------------------------------------------------ATUALIZAR "UPDATE" ---------------------------------------------------------------
	public void atualizar(Userposjava userposjava) {// Atualizar tem que receber o objeto com os dados atualizados

		try {

			String sql = "update userposjava set nome = ? where id = " + userposjava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userposjava.getNome());

			statement.execute();
			connection.commit();

		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

}
