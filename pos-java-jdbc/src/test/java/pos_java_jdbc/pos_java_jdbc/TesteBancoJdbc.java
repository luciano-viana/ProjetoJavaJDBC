package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;
import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.BeanUserFone;
import model.Telefone;
import model.Userposjava;

public class TesteBancoJdbc {

	// -----------------------INSERT TABELA USUARIO-----------------------
	// Método para chamar o SingleConnection
	@Test
	public void initBanco() {// metodo de insert
		UserPosDAO userPosDAO = new UserPosDAO();
		Userposjava userposjava = new Userposjava();

		/*Criado no bd sequenciador de id com..
		 * create SEQUENCE usersequence
		   increment 1
		   minvalue 1
           maxvalue 9223372036854775807
           start 6;*
		 *alter table userposjava ALTER column id set default nextval('usersequence'::regclass);
		 */
		//userposjava.setId(5L);
		userposjava.setNome("Pedro");
		userposjava.setEmail("pedro@gmail.com");

		userPosDAO.salvar(userposjava);
	}

	// -----------------------SELECT * TABELA USUARIO-----------------------
	// Método
	@Test
	public void initListar() {
		UserPosDAO dao = new UserPosDAO();
		try {
			List<Userposjava> list = dao.listar();

			for (Userposjava userposjava : list) {// for para barrer os objetos
				System.out.println(userposjava);
				System.out.println("-----------------------------------------");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	// -----------------------SELECT * WHERE TABELA USUARIO-----------------------
	// Método
	@Test
	public void initbuscar() {

		UserPosDAO dao = new UserPosDAO(); // Instanciar objeto UserPosDAO

		try {
			Userposjava userposjava = dao.buscar(5L); // irá retornar um objeto
			System.out.println(userposjava);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//-----------------------ATUALIZAR POR UPDATE TABELA USUARIO-----------------------
	//Método
	@Test
	public void initAtualizar() {
		try {
		UserPosDAO dao = new UserPosDAO();
		Userposjava objetoBanco = dao.buscar(5L);
		objetoBanco.setNome("Nome mudado com o metodo atualizar");
		dao.atualizar(objetoBanco);
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//--------------------------- DELETE TABELA USUARIO -------------------------------
	//Método
	@Test
	public void iniDeletar() {
		try {
			UserPosDAO dao = new UserPosDAO();
			dao.deletar(8L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	// -----------------------INSERT TABELA TELEFONE-----------------------
	//Método
	@Test
	public void testeInsertTelefone() {
		
		Telefone telefone = new Telefone();
		telefone.setNumero("(21)99599-2050");
		telefone.setTipo("celular");
		telefone.setUsuario(12L);//Tem que ser um id de usuário que já existe no banco de dados
		
		//Instanciar o objeto
		UserPosDAO dao = new UserPosDAO();
		dao.salvarTelefone(telefone);
	}
	
	// -----------------------SELECT NAS TABELAS COM INNER JOIN-----------------------
	//Método
	public void testeCarregaFoneUser() {
		
		//precisa sempre ter um dao instanciado para acessar as camadas de persistencia
		UserPosDAO dao = new UserPosDAO();
		
		//Lista para receber o retorno
		List<BeanUserFone> beanUserFones  = dao.lisBeanUserFones(11L);
		
		for (BeanUserFone beanUserFone : beanUserFones) {
			System.out.println(beanUserFone);
			System.out.println("-------------------------------------------");
		}
		
	}
	
}
