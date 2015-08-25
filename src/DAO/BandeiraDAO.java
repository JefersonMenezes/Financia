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
import modelo.Bandeira;

/**
 *
 * @author takedown
 */
public class BandeiraDAO {

    private final Connection conexao;

    public BandeiraDAO() {
        this.conexao = new ConnectionFactory().getConnection();
    }

    public void inserir(Bandeira bandeira) {
        String sql = "INSERT INTO `financa`.`bandeira` (`nome`) VALUES (?)";
        try {
            PreparedStatement stmt = conexao.prepareStatement(sql);
            stmt.setString(1, bandeira.getNome());
            stmt.execute();
            stmt.close();
        } catch (SQLException ex) {
            Logger.getLogger(BandeiraDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Bandeira> listar() {

    String sql = "select * from bandeira";
        
        try {
            List<Bandeira> tipos = new ArrayList<Bandeira>();
            PreparedStatement stmt = conexao.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            
            while (rs.next()) {
                Bandeira tipo = new Bandeira();
                tipo.setId(rs.getInt("id"));
                tipo.setNome(rs.getString("nome"));
                tipos.add(tipo);
            }
            rs.close();
            stmt.close();
            return tipos;
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
    }
}
