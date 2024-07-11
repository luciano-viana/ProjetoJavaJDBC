package pos_java_jdbc.pos_java_jdbc;

import java.util.List;

import org.junit.Test;
import conexaojdbc.SingleConnection;
import dao.UserPosDAO;
import model.Userposjava;

public class TesteBancoJdbc {

	// -----------------------INSERT-----------------------
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

	// -----------------------SELECT *-----------------------
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

	// -----------------------SELECT * WHERE-----------------------
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
	
	//-----------------------ATUALIZAR POR UPDATE-----------------------
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
	
	//--------------------------- DELETE -------------------------------
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
}
