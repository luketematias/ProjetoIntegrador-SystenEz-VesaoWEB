
package pi.systen.ez.web;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    
    public Usuario autenticar(String login, String senha) {
        Usuario usuario = null;

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT * FROM usuario WHERE nome = ? AND senha = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, login);
            ps.setString(2, senha);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                usuario = new Usuario();
                usuario.setId(rs.getInt("id"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
                usuario.setTipo(rs.getString("tipo"));
            }

            conexao.desconectar();

        } catch (SQLException e) {
            System.out.println("Erro ao autenticar: " + e);
        }

        return usuario;
    }
    
    public static boolean cadastrar(Usuario u) {

        String sql = "INSERT INTO usuario (nome, senha, tipo) VALUES (?, ?, ?)";

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setString(3, u.getTipo());


            int atualizacaoTabela = ps.executeUpdate();
            conexao.desconectar();
            return atualizacaoTabela > 0;
        } catch (SQLException se) {
            System.err.println("Error ao cadastrar usuário: " + se.getMessage());
            return false;
        }
    }
    
    public static List<Usuario> listarUsuario() {
        List<Usuario> usi = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, senha, tipo FROM usuario";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setTipo(rs.getString("tipo"));
                
                usi.add(u);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar usuarios: " + se.getMessage());
        }

        return usi;
    }
    
    public static boolean excluir(int id) {
        try {

            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "DELETE FROM usuario WHERE id=?;";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

            ps.setInt(1, id);

            ps.execute();

            conexao.desconectar();
            return true;
        } catch (SQLException s) {
            System.out.println("Erro ao deletar registro no banco de dados!" + s);
            return false;
        }
    }
    
    public static Usuario buscarPorId(int id) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, senha, tipo FROM usuario WHERE id = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setTipo(rs.getString("tipo"));
                return u;
            }
            conexao.desconectar();
        } catch (SQLException se) {
            System.out.println("Erro: " + se);
        }

        return null;
    }
    
    public static List<Usuario> buscarPorNomeUsuario(String nome) {
        List<Usuario> usi = new ArrayList();

        try {

            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, senha, tipo FROM usuario WHERE nome LIKE ?";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNome(rs.getString("nome"));
                u.setSenha(rs.getString("senha"));
                u.setTipo(rs.getString("tipo"));

                usi.add(u);
            }
            conexao.desconectar();
        } catch (SQLException se) {
            System.err.println("Erro ao filtrar o usuário: " + se);
        }
        return usi;
    }
    
    public static boolean atualizar(Usuario u) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "UPDATE usuario SET nome = ?, senha = ?, tipo = ? WHERE id = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

            ps.setString(1, u.getNome());
            ps.setString(2, u.getSenha());
            ps.setString(3, u.getTipo());
            ps.setInt(4, u.getId());


            int rowsAffected = ps.executeUpdate();
            conexao.desconectar();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o registro no banco de dados." + e);
            return false;
        }
    }
}
