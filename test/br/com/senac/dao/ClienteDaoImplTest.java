/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senac.dao;

import br.com.senac.entidade.Cliente;
import br.com.senac.util.GeradorUtil;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author silvio.junior
 */
public class ClienteDaoImplTest {
    
    private Cliente cliente;
    private ClienteDao clienteDao;
    
    
    public ClienteDaoImplTest() {
        clienteDao = new ClienteDaoImpl();
    }

//    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        cliente = new Cliente(
                GeradorUtil.gerarNome(),
                new Date(),
                Double.parseDouble(GeradorUtil.gerarNumero(3))
        );
        clienteDao.salvar(cliente);
        assertNotNull(cliente.getId());
    }

//    @Test
    public void testAlterar() throws Exception {
        System.out.println("alterar");
        buscarClienteBd();
        cliente.setNome(GeradorUtil.gerarNome() + " alt");
        clienteDao.alterar(cliente);
        //TODO validar se realmente alterou no BD
        Cliente clienteAlterado = clienteDao
                                   .pesquisarPorId(cliente.getId());
        assertEquals(cliente.getNome(), clienteAlterado.getNome());
    }

//    @Test
    public void testExcluir() throws Exception {
        System.out.println("excluir");
        buscarClienteBd();
        clienteDao.excluir(cliente.getId());
        
        Cliente clienteExcluido = clienteDao
                                    .pesquisarPorId(cliente.getId());
        assertNull(clienteExcluido);
    }

//    @Test
    public void testPesquisarPorId() throws Exception {
        System.out.println("pesquisarPorId");
        buscarClienteBd();
        Cliente clienteBd  = clienteDao.pesquisarPorId(cliente.getId());
        assertNotNull(clienteBd);
    }

    @Test
    public void testPesquisarPorNome() throws Exception {
        System.out.println("pesquisarPorNome");
        buscarClienteBd();
        String nome = cliente.getNome();
        List<Cliente> clientes = clienteDao.pesquisarPorNome(nome);
        assertTrue(!clientes.isEmpty());
//        assertTrue(clientes.size() > 0);
    }
    
    public Cliente buscarClienteBd() throws Exception{
        String consulta = "SELECT * FROM cliente";
        Connection conn = FabricaConexao.abrirConexao();
        PreparedStatement psmt = conn.prepareStatement(consulta);
        ResultSet rs = psmt.executeQuery();
        if(rs.next()){
            cliente = new Cliente();
            cliente.setId(rs.getInt("id"));
            cliente.setNome(rs.getString("nome"));
            cliente.setNascimento(rs.getDate("nascimento"));
            cliente.setSalario(rs.getDouble("salario"));
        }else{
            testSalvar();
        }
        return cliente;
    }
    
}
