/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Usuario;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author professor
 */
public class UsuarioDaoImpl {
    
    private Connection conexao;
    private PreparedStatement preparaSql;
    private ResultSet resultado;
    
    public boolean logar(String login, String senha){
        String sql = "SELECT * FROM usuario WHERE login = ? and senha = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaSql = conexao.prepareStatement(sql);
            preparaSql.setString(1, login);
            preparaSql.setString(2, senha);            
            resultado = preparaSql.executeQuery();
            
            if(resultado.next()){
                return true;
            }        
            
        } catch (Exception e) {
            System.out.println("Erro ao salvar cliente " + e.getMessage());
        } finally {
            
        }
        return false;
    }
        
    
}
