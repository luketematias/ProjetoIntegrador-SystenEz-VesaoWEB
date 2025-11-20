
package pi.systen.ez.web;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoJDBC {
    
    private Connection conexao;

    public Connection getConexao() {
        return conexao;
    }
    
  
    public void conectar() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection("jdbc:mysql://localhost/systenez", "root", "12345");
            System.out.println("SUCESSO DE CONEXÃO!");
        } catch (ClassNotFoundException cn) {
            System.out.println("Error ao conectar com o banco " + cn);
        } catch (SQLException sql) {
            System.out.println("Eror de SQL: " + sql);
        }
    }

    public void desconectar() {
        try {
            if (conexao != null && !conexao.isClosed()) {
                conexao.close();
                System.out.println("DESCONECTADO");
            }
        } catch (SQLException se) {
            System.out.println("Probelma ao desconectar do banco: " + se);
        }
    }
}
