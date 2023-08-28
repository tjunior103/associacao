/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author silvio.junior
 */
public class ClienteDaoImpl implements ClienteDao {

    private Connection conexao;
    private PreparedStatement preparaSql;
    private ResultSet resultado;

    @Override  // CRUD 
    public void salvar(Cliente cliente) throws SQLException {  //SQL injection
        String sql = "INSERT INTO cliente(nome, nascimento, salario)"
                + " VALUES(?, ?, ?)";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaSql = conexao.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            preparaSql.setString(1, cliente.getNome());
            preparaSql.setDate(2, new Date(cliente.getNascimento()
                    .getTime()));
            preparaSql.setDouble(3, cliente.getSalario());
            preparaSql.executeUpdate();
            //pegando a chave PK que gerado no banco
            resultado = preparaSql.getGeneratedKeys();
            resultado.next();
            cliente.setId(resultado.getInt(1));
        } catch (Exception e) {
            System.out.println("Erro ao salvar cliente " + e.getMessage());
        } finally {
            conexao.close();
            preparaSql.close();
            resultado.close();
        }

    }

    @Override
    public void alterar(Cliente cliente) throws SQLException {
        String sql = "UPDATE cliente SET nome = ?, nascimento = ?, "
                + "salario = ? WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaSql = conexao.prepareStatement(sql);
            preparaSql.setString(1, cliente.getNome());
            preparaSql.setDate(2, new Date(cliente.getNascimento()
                    .getTime()));
            preparaSql.setDouble(3, cliente.getSalario());
            preparaSql.setInt(4, cliente.getId());
            preparaSql.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao alterar cliente "
                    + e.getMessage());
        } finally {
            conexao.close();
            preparaSql.close();
        }
    }

    @Override
    public void excluir(int id) throws SQLException {
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaSql = conexao
                    .prepareStatement("DELETE FROM cliente WHERE id = ?");
            preparaSql.setInt(1, id);
            preparaSql.executeUpdate();
        } catch (Exception e) {
            System.out.println("Erro ao excluir cliente "
                    + e.getMessage());
        } finally {
            conexao.close();
            preparaSql.close();
        }
    }

    @Override
    public Cliente pesquisarPorId(int id) throws SQLException {
        Cliente cliente = null;
        String consulta = "SELECT * FROM cliente WHERE id = ?";
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaSql = conexao.prepareStatement(consulta);
            preparaSql.setInt(1, id);
            resultado = preparaSql.executeQuery();
            if (resultado.next()) {
                cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setNascimento(resultado.getDate("nascimento"));
                cliente.setSalario(resultado.getDouble("salario"));
            }

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar por id "
                    + e.getMessage());
        } finally {
            conexao.close();
            preparaSql.close();
            resultado.close();
        }
        return cliente;
    }

    @Override
    public List<Cliente> pesquisarPorNome(String nome) throws SQLException {
        String consulta = "SELECT * FROM cliente";
        List<Cliente> clientes = new ArrayList<>();
        try {
            conexao = FabricaConexao.abrirConexao();
            preparaSql = conexao.prepareStatement(consulta);
//            preparaSql.setString(1, "%" + nome + "%");
            resultado = preparaSql.executeQuery();
            Cliente cliente;
            while (resultado.next()) {
                cliente = new Cliente();
                cliente.setId(resultado.getInt("id"));
                cliente.setNome(resultado.getString("nome"));
                cliente.setNascimento(resultado.getDate("nascimento"));
                cliente.setSalario(resultado.getDouble("salario"));
                clientes.add(cliente);
            }

        } catch (Exception e) {
            System.out.println("Erro ao pesquisar por nome "
                    + e.getMessage());
        } finally {
            conexao.close();
            preparaSql.close();
            resultado.close();
        }
        return clientes;
    }

}
