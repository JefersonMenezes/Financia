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
import java.util.ArrayList;
import java.util.List;
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

    public void inserir(Conta conta) {
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

    public List<Conta> listaContasTipo(int i) {
        String sql = "SELECT conta.id, conta.nome, conta.saldo_inicial, tipo_conta.tipo FROM financa.conta \n"
                + "JOIN financa.tipo_conta ON conta.tipo_conta_id = tipo_conta.id WHERE usuario_id = ?";
        try {
            List<Conta> contas = new ArrayList<Conta>();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, i);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Conta conta = new Conta();
                conta.setId(rs.getInt("id"));
                conta.setNome(rs.getString("nome"));
                conta.setSaldoInicial(rs.getDouble("saldo_inicial"));
                conta.setTipoContaNome(rs.getString("tipo"));

                contas.add(conta);

            }
            rs.close();
            stmt.close();
            return contas;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
