package br.com.javajunior.factory;

public class ConexaoFactory {
  
  
	private static final String USUARIO="root";
	private static final String SENHA="yusuke";
	private static final String URL="jdbc:mysql://localhost:3306/agenda";
	
	public static Connection conectar() throws SQLException{
		Connection conexao = DriverManager.getConnection(URL,USUARIO,SENHA);
		return conexao;
	}

}
