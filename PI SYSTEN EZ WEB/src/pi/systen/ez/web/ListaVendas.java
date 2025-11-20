
package pi.systen.ez.web;

import java.util.ArrayList;

public class ListaVendas {
    private static ArrayList<Venda> vendas = new ArrayList<>();

    public static void adicionar(Venda v) {
        vendas.add(v);
    }

    public static ArrayList<Venda> listar() {
        return vendas;
    }
    
     public static boolean excluir(int id) {
        try {
            if (id >= 0 && id < vendas.size()) {
                vendas.remove(id);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error ao tentar excluir o item da lista: " + e.getMessage());
        }
        return false;
    }
}
