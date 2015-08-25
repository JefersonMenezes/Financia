/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import DAO.CategoriaDAO;
import DAO.UsuarioDAO;
import java.sql.Connection;
import java.util.List;
import modelo.Categoria;
import modelo.Usuario;

/**
 *
 * @author takedown
 */
public class TestaConexao {

    public static void main(String[] args) {
        CategoriaDAO dao = new CategoriaDAO();
        List<Categoria> categorias = dao.listaDespesas();
        for (Categoria categoria : categorias) {
            System.out.println("Codigo :" + categoria.getId());

            System.out.println("Nome :" + categoria.getNome());

            System.out.println("Tipo :" + categoria.getTipo());
            
            System.out.println("---------------------------------------");
            ;
        }

    }

}
