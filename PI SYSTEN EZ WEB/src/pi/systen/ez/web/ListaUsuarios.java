
package pi.systen.ez.web;

import java.util.ArrayList;

public class ListaUsuarios {
    public static ArrayList<Usuario> lista = new ArrayList<Usuario>();

    public static ArrayList<Usuario> listar() {
        return lista;
    }

     public static boolean excluir(int id) {
        try {
            if (id >= 0 && id < lista.size()) {
                lista.remove(id);
                return true;
            }
        } catch (Exception e) {
            System.out.println("Error ao tentar excluir o item da lista: " + e.getMessage());
        }
        return false;
    }
}
