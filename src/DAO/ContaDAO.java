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
import modelo.Conta;
/**
 *
 * @author takedown
 */
public class ContaDAO {
    
    private final Connection conexao;

    public ContaDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }
    
    public void inserir(Conta conta){
        String sql = "INSERT INTO `financa`.`conta` (`nome`,`saldo_inicial`,`usuario_id`,`tipo_conta_id`) VALUES (?,?,?,?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, conta.getNome());
            stmt.setDouble(2, conta.getSaldoInicial());
            stmt.setInt(3, conta.getUsuario());
            stmt.setInt(4, conta.getTipoConta());
            stmt.execute();
            
            stmt.close();
                    } catch (SQLException ex) {
            Logger.getLogger(ContaDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public double listaContas(int idUsuario) {
        String sql = "SELECT sum(saldo_inicial) FROM financa.conta where usuario_id = ?";
         try {
            double total = 0;
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                total = rs.getDouble("sum(saldo_inicial)");
            }
            rs.close();
            stmt.close();
            return total;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
