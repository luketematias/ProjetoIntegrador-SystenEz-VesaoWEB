package pi.systen.ez.web;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class VendaDAO {

    public static boolean registrarVenda(Venda v) {

        String sql = "INSERT INTO venda (produto_id, data_venda, quantidade_vendida) VALUES (?, ?, ?)";

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);

            ps.setInt(1, v.getIdProduto());

            DateTimeFormatter entrada = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            DateTimeFormatter saida = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String dataConvertida = LocalDate.parse(v.getData(), entrada).format(saida);

            ps.setString(2, dataConvertida);
            ps.setInt(3, v.getQuantidadeVendida());

            int atualizacaoTabela = ps.executeUpdate();
            System.out.println("Venda registrada com sucesso!");
            conexao.desconectar();
            return atualizacaoTabela > 0;

        } catch (SQLException se) {
            System.err.println("Error ao registrar venda: " + se.getMessage());
            return false;
        }

    }

    public static List<Venda> listarVendas() {
        List<Venda> vends = new ArrayList();

        try {
            ConexaoJDBC conexao = new ConexaoJDBC();
            conexao.conectar();

            String sql = "call relatorioVendas";

            PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Venda v = new Venda();
                Produto p = new Produto();
                v.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                v.setQuantidadeVendida(rs.getInt("quantidade_vendida"));

                String dataSQL = rs.getString("data_venda");
                LocalDate data = LocalDate.parse(dataSQL);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
                String dataFormatada = data.format(formatter);
                v.setData(dataFormatada);
                v.setTotal(rs.getDouble("total"));

                p.setNome(rs.getString("nome"));
                v.setProduto(p);

                vends.add(v);
            }

            conexao.desconectar();

        } catch (SQLException se) {
            System.err.println("Erro ao listar vendas: " + se.getMessage());
        }

        return vends;
    }
    
    public static List<Venda> buscarPorProdutoVenda(String nome) {
    List<Venda> vends = new ArrayList<>();

    try {
        ConexaoJDBC conexao = new ConexaoJDBC();
        conexao.conectar();

        String sql = "SELECT v.id, p.nome, v.quantidade_vendida, v.data_venda, (v.quantidade_vendida * p.preco_venda) AS total "
                   + "FROM venda v "
                   + "JOIN produto p ON v.produto_id = p.id "
                   + "WHERE p.nome LIKE ?";

        PreparedStatement ps = conexao.getConexao().prepareStatement(sql);
        ps.setString(1, "%" + nome + "%");

        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Venda v = new Venda();
            Produto p = new Produto();

            v.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            v.setQuantidadeVendida(rs.getInt("quantidade_vendida"));

            String dataSQL = rs.getString("data_venda");
            LocalDate data = LocalDate.parse(dataSQL);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            String dataFormatada = data.format(formatter);
            v.setData(dataFormatada);

            v.setTotal(rs.getDouble("total"));
            v.setProduto(p);

            vends.add(v);
        }

        conexao.desconectar();

    } catch (SQLException se) {
        System.err.println("Erro ao buscar vendas por produto: " + se.getMessage());
    }

    return vends;
}



}
