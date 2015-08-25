/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.CartaoCredito;

/**
 *
 * @author takedown
 */
public class CartaoCreditoDAO {
    
    private final Connection conexao;

    public CartaoCreditoDAO(Connection conexao) {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    public void inserir(CartaoCredito cartao){
        String sql = "INSERT INTO `financa`.`cartao_credito` (`descricao`, `limite`, `dia_fecha`, `dia_paga`, `usuario_id`, `bandeira_id`) VALUES (?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, cartao.getDescricao());
            stmt.setDouble(2, cartao.getLimite());
            stmt.setInt(3, cartao.getDiaFechamento());
            stmt.setInt(4, cartao.getDiaVencimento());
            stmt.setInt(5, cartao.getUsuario());
            stmt.setInt(6, cartao.getBandeira());
            
            stmt.execute();
            stmt.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(CartaoCreditoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    
    
}
