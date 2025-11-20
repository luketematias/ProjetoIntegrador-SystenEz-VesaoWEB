package pi.systen.ez.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDAO {

    public static boolean cadastrar(Produto prod) {

        String sql = "INSERT INTO produto (nome, preco_custo, preco_venda, categoria, quantidade) VALUES (?, ?, ?, ?, ?)";

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

            ps.setString(1, prod.getNome());
            ps.setString(2, prod.getPrecoCusto());
            ps.setString(3, prod.getPrecoVenda());
            ps.setString(4, prod.getCategoria());
            ps.setString(5, String.valueOf(prod.getQntdEstoque()));

            int atualizacaoTabela = ps.executeUpdate();
            conexao.desconectar();
            return atualizacaoTabela > 0;
        } catch (SQLException se) {
            System.err.println("Error ao cadastrar produto: " + se.getMessage());
            return false;
        }
    }

    public static List<Produto> listarProdutos() {
        List<Produto> prods = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, categoria, preco_venda FROM produto";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecoVenda(rs.getString("preco_venda"));

                prods.add(p);
            }
            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar produtos: " + se.getMessage());
        }

        return prods;
    }

    public static boolean excluir(int id) {
        try {

            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "DELETE FROM produto WHERE id=?;";
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

    public static Produto buscarPorId(int id) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, categoria, preco_custo, preco_venda, quantidade FROM produto WHERE id = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecoCusto(rs.getString("preco_custo"));
                p.setPrecoVenda(rs.getString("preco_venda"));
                p.setQntdEstoque(rs.getInt("quantidade"));
                return p;
            }
            conexao.desconectar();
        } catch (SQLException se) {
            System.out.println("Erro: " + se);
        }

        return null;
    }

    public static boolean atualizar(Produto p) {
        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "UPDATE produto SET nome = ?, categoria = ?, preco_custo = ?, preco_venda = ?, quantidade = ? WHERE id = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

            ps.setString(1, p.getNome());
            ps.setString(2, p.getCategoria());
            ps.setString(3, p.getPrecoCusto());
            ps.setString(4, p.getPrecoVenda());
            ps.setString(5, String.valueOf(p.getQntdEstoque()));
            ps.setInt(6, p.getId());

            int rowsAffected = ps.executeUpdate();
            conexao.desconectar();
            return rowsAffected > 0;
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar o registro no banco de dados." + e);
            return false;
        }
    }

    public static List<Produto> listarEstoque() {
        List<Produto> prods = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "call relatorioEstoque";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQntdEstoque(rs.getInt("quantidade"));

                prods.add(p);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar produtos: " + se.getMessage());
        }

        return prods;
    }

    public static List<Produto> buscarPorNomeProduto(String nome) {
        List<Produto> prods = new ArrayList();

        try {

            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, categoria, preco_custo, preco_venda FROM produto WHERE nome LIKE ?";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setCategoria(rs.getString("categoria"));
                p.setPrecoCusto(rs.getString("preco_custo"));
                p.setPrecoVenda(rs.getString("preco_venda"));

                prods.add(p);
            }
            conexao.desconectar();
        } catch (SQLException se) {
            System.err.println("Erro ao filtrar o produto: " + se);
        }
        return prods;
    }

    public static List<Produto> buscarPorNomeProduto_Relatorio(String nome) {
        List<Produto> prods = new ArrayList();

        try {

            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, quantidade FROM produto WHERE nome LIKE ?";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setQntdEstoque(rs.getInt("quantidade"));

                prods.add(p);
            }
            conexao.desconectar();
        } catch (SQLException se) {
            System.err.println("Erro ao filtrar o produto: " + se);
        }
        return prods;
    }

    public static List<Produto> listarMargem() {
        List<Produto> prods = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "call relatorioMargemDeLucro";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPrecoCusto(rs.getString("preco_custo"));
                p.setPrecoVenda(rs.getString("preco_venda"));
                rs.getString("margem_de_lucro");

                prods.add(p);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar produtos: " + se.getMessage());
        }

        return prods;
    }

    public static boolean atualizarEstoque(int idProduto, int novaQuantidade) {
        String sql = "UPDATE produto SET quantidade = ? WHERE id = ?";

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, novaQuantidade);
            ps.setInt(2, idProduto);

            int rows = ps.executeUpdate();

            conexao.desconectar();

            return rows > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar estoque: " + e.getMessage());
            return false;
        }
    }

    public static int consultarEstoque(int idProduto) {
        int estoque = -1;

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT quantidade FROM produto WHERE id = ?";
            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, idProduto);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                estoque = rs.getInt("quantidade");
            }

            conexao.desconectar();
        } catch (SQLException se) {
            System.err.println("Erro ao consultar estoque: " + se.getMessage());
        }

        return estoque;
    }
    
    public static List<Produto> buscarPorNomeProduto_MargemDeLucro(String nome) {
        List<Produto> prods = new ArrayList();
        try {

            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "SELECT id, nome, preco_venda, preco_custo FROM produto WHERE nome LIKE ?";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ps.setString(1, "%" + nome + "%");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Produto p = new Produto();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPrecoVenda(rs.getString("preco_venda"));
                p.setPrecoCusto(rs.getString("preco_custo"));

                prods.add(p);
            }
            conexao.desconectar();
        } catch (SQLException se) {
            System.err.println("Erro ao filtrar o produto: " + se);
        }
        return prods;
    }

   
}

