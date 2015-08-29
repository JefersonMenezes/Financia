/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import DAO.CategoriaDAO;
import DAO.ContaDAO;
import DAO.UsuarioDAO;
import java.sql.Connection;
import java.util.List;
import modelo.Categoria;
import modelo.Conta;
import modelo.Usuario;

/**
 *
 * @author takedown
 */
public class TestaConexao {
    
    public static void main(String[] args) {
        alteraConta();
        
    }
    
    private void listaCategoria() {
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
    
     static void alteraConta() {
        Conta conta = new Conta();
        conta.setId(2);
        conta.setNome("Carteira");
        conta.setSaldoInicial(233.00);
        conta.setTipoConta(2);
        ContaDAO dao = new ContaDAO();
        dao.altera(conta);
        
    }
    
}
