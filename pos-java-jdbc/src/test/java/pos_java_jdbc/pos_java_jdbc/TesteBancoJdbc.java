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

		userposjava.setId(5L);
		userposjava.setNome("Patricia teste");
		userposjava.setEmail("patricia@gmail.com");

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

}
