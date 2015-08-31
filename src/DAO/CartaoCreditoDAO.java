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
import modelo.CartaoCredito;
import modelo.Conta;

/**
 *
 * @author takedown
 */
public class CartaoCreditoDAO {

    private final Connection conexao;

    public CartaoCreditoDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(CartaoCredito cartao) {
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

    public List<CartaoCredito> listarCartoesTipo(int idUsuario) {
        String sql = "SELECT cartao_credito.id, cartao_credito.descricao, cartao_credito.limite, cartao_credito.dia_paga, bandeira.nome\n"
                + " FROM financa.cartao_credito JOIN financa.bandeira ON cartao_credito.bandeira_id = bandeira.id WHERE usuario_id = ?";
        try {
            List<CartaoCredito> cartoes = new ArrayList<CartaoCredito>();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                CartaoCredito cartao = new CartaoCredito();
                cartao.setId(rs.getInt("id"));
                cartao.setDescricao(rs.getString("descricao"));
                cartao.setLimite(rs.getDouble("limite"));
                cartao.setDiaVencimento(rs.getInt("dia_paga"));
                cartao.setBandeiraNome(rs.getString("nome"));

                cartoes.add(cartao);

            }
            rs.close();
            stmt.close();
            return cartoes;

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

    public double totalDespesas(int idUsuario) {
        String sql = "SELECT sum(valor) FROM financa.compras_cartao JOIN financa.cartao_credito \n"
                + "ON cartao_credito.id = compras_cartao.cartao_credito_id  where usuario_id = ?";
        try {
            double retorno = 0;
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                retorno = rs.getDouble("sum(valor)");
            }
            rs.close();
            stmt.close();
            return retorno;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    public double despesasPorCartao(int idUsuario, int cartao) {
        String sql = "SELECT sum(valor) FROM financa.compras_cartao JOIN financa.cartao_credito ON cartao_credito.id = compras_cartao.cartao_credito_id  where usuario_id = ? and cartao_credito.id=?";
        try {
            double retorno = 0;
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setInt(1, idUsuario);
            stmt.setInt(1, cartao);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                retorno = rs.getDouble("sum(valor)");
            }
            rs.close();
            stmt.close();
            return retorno;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }

}
