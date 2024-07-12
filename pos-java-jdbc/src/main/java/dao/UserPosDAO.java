package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class UserPosDAO {

	private Connection connection;

	// Contrutor
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}

	// ---------------------------------------------INSERT TABELA USUARIO--------------------------------------------------------------------------
	// Método publico que não precisa retornar nada, método para fazer "Insert" no
	// BD
	public void salvar(Userposjava userposjava) {
		try {
			//String sql = "insert into userposjava(id, nome, email) values (?,?,?)";
			String sql = "insert into userposjava(nome, email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql); // ele que irá fazer o insert, tem toda
			//insert.setLong(1, userposjava.getId());
			insert.setString(1, userposjava.getNome());
			insert.setString(2, userposjava.getEmail());
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
	
	// ---------------------------------------------INSERT TABELA TELEFONE--------------------------------------------------------------------------
	public void salvarTelefone(Telefone telefone) {
		try {
			
			String sql = "INSERT INTO telefoneuser(numero, tipo, usuariopessoa) VALUES (?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, telefone.getNumero());
			preparedStatement.setString(2, telefone.getTipo());
			preparedStatement.setLong(3, telefone.getUsuario());
			preparedStatement.execute();
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
	//Método para fazer uma consulta no banco de dados com uma condição Where
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

	// -------------------------------------------CONSULTA SÓ DE UM OBJETO "SELECT COM INNER JOIN -------------------------------------------
	//Métido para fazer uma consulta no banco de dados entre 2 tabelas com INNER JOIN
	public List<BeanUserFone> lisBeanUserFones(Long idUser){
		
		List<BeanUserFone> beanUserFones = new ArrayList<BeanUserFone>();
		//Efeutar o inner join
		String sql = " select nome, numero, email from telefoneuser tl ";
		sql += " INNER JOIN userposjava uj ";
		sql += " ON tl.usuariopessoa = uj.id ";
		sql += " where uj.id = " + idUser;
		
		try {
			//Fazer consulta no banco
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultSet = statement.executeQuery(); //para trazer os resultados
			
			//Para linha vai estanciar os objetos
			while (resultSet.next()) {
				BeanUserFone userFone = new BeanUserFone();
				userFone.setNome(resultSet.getString("nome"));
				userFone.setNumero(resultSet.getString("numero"));
				userFone.setEmail(resultSet.getString("email"));
				
				//Vai adicionar na lista
				beanUserFones.add(userFone);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return beanUserFones;
	}
	
	// ----------------------------------------------------ATUALIZAR "UPDATE" ---------------------------------------------------------------
	//Método para atualizar dados no banco de dados
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
	
	// ---------------------------------------------------------- DELETE ---------------------------------------------------------------------
	//Método para deletar dados no banco de dados
	public void deletar(Long id) {
		try {
			
			String sql = "delete from userposjava where id = " + id;
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.execute();
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

	
	// ---------------------------------------------------------- DELETE 2 ---------------------------------------------------------------------
		//Método para deletar dados em tabelas que estão relacionadas com outros "Restrições de exclusão dos dados"
	public void deleFonesPorUser(Long idUser) {
		try {
			
				String sqlFone = "delete from telefoneuser where usuariopessoa = " + idUser;
				String sqlUser = "delete from userposjava where id = " + idUser;
				
				PreparedStatement preparedStatement = connection.prepareStatement(sqlFone);
				preparedStatement.executeUpdate();
				connection.commit();
				
				preparedStatement = connection.prepareStatement(sqlUser);
				preparedStatement.executeUpdate();
				connection.commit();
			
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
	}
}
