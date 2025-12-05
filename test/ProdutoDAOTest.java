import org.junit.Test;
import pi.systen.ez.web.Produto;
import pi.systen.ez.web.ProdutoDAO;
import static org.junit.Assert.assertTrue;

public class ProdutoDAOTest {

    @Test
    public void testeCadastroProduto(){

        Produto prod = new Produto();
        prod.setId(999);
        prod.setNome("Chocolate");
        prod.setPrecoCusto("1");
        prod.setPrecoVenda("3");
        prod.setCategoria("Doces");
        prod.setQntdEstoque(10);

        boolean resultado = ProdutoDAO.cadastrar(prod);
        assertTrue(resultado);


    }
}