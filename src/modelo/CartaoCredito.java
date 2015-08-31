/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author takedown
 */
public class CartaoCredito {
    
    private int id;
    private String descricao;
    private double limite;
    private int diaFechamento;
    private int diaVencimento;
    private int usuario;
    private int bandeira;
    private String bandeiraNome;

    public CartaoCredito() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    public int getDiaFechamento() {
        return diaFechamento;
    }

    public void setDiaFechamento(int diaFechamento) {
        this.diaFechamento = diaFechamento;
    }

    public int getDiaVencimento() {
        return diaVencimento;
    }

    public void setDiaVencimento(int diaVencimento) {
        this.diaVencimento = diaVencimento;
    }

    public int getUsuario() {
        return usuario;
    }

    public void setUsuario(int usuario) {
        this.usuario = usuario;
    }

    public int getBandeira() {
        return bandeira;
    }

    public void setBandeira(int bandeira) {
        this.bandeira = bandeira;
    }

    public void setBandeiraNome(String string) {
        this.bandeiraNome = string;
    }
    
    public String getBandeiraNome(){
        return this.bandeiraNome;
    }
    
    
    
}
