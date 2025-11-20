package pi.systen.ez.web;


public class Venda {
    
    private int id;
    private int idProduto;
    private int quantidadeVendida;
    private String data;
    private Produto produto;
    private double total;

    public Venda() {
    }

    public Venda(int idProduto, int quantidadeVendida, String data) {
        this.idProduto = idProduto;
        this.quantidadeVendida = quantidadeVendida;
        this.data = data;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    
    
    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidadeVendida() {
        return quantidadeVendida;
    }

    public void setQuantidadeVendida(int quantidadeVendida) {
        this.quantidadeVendida = quantidadeVendida;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    
    
    
    
    
}
