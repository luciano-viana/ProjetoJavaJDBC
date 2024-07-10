package dao;

import java.sql.Connection;
import conexaojdbc.SingleConnection;

public class UserPosDAO {

	private Connection connection;
	
	//Contrutor
	public UserPosDAO() {
		connection = SingleConnection.getConnection();
	}
}
