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
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Despesa;

/**
 *
 * @author takedown
 */
public class DespesaDAO {

    private final Connection conexao;

    public DespesaDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Despesa despesa) {
        String sql = "INSERT INTO `financa`.`despesa` (`valor`,`data`,`descricao`,`pago`,`conta_id`,`categoria_id`) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setDouble(1, despesa.getValor());
            stmt.setDate(2, null, despesa.getData());
            stmt.setBoolean(3, despesa.isPago());
            stmt.setInt(4, despesa.getConta().getId());
            stmt.setInt(5, despesa.getCategoria().getId());
            
            stmt.execute();
            stmt.close();
            
                    } catch (SQLException ex) {
            Logger.getLogger(DespesaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double listaTotal(int idUsuario) {
        String sql = "SELECT sum(valor) as valor FROM financa.despesa JOIN financa.conta ON conta_id = conta.id where usuario_id = ?";
        try {
            double total = 0;
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                total = rs.getInt("valor");
            }
            rs.close();
            stmt.close();
            return total;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
    
    
}
