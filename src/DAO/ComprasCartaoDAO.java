/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.ComprasCartao;

/**
 *
 * @author takedown
 */
public class ComprasCartaoDAO {

    private final Connection conexao;

    public ComprasCartaoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(ComprasCartao compras) {
        String sql = "INSERT INTO `financa`.`compras_cartao` (`valor`,`data`,`descricao`,`parcelas`,`valor_parcela`,`cartao_credito_id`,`categoria_id`) VALUES (?,?,?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, compras.getValor());

        } catch (SQLException ex) {
            Logger.getLogger(ComprasCartaoDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double listarTotal(int idUsuario) {
        String sql = "SELECT sum(valor) as valor FROM financa.compras_cartao JOIN financa.cartao_credito ON cartao_credito_id = cartao_credito.id WHERE usuario_id = ?";
        try {
            double d = 0;
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, idUsuario);
            ResultSet rs = stmt.executeQuery();
            
            while(rs.next()){
                d = rs.getDouble("valor");
            }
            rs.close();
            stmt.close();
            return d;
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
