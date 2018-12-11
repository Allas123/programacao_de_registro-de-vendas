/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import classes.bean.Clientes;
import classes.bean.Produtos;
import classes.connection.Conexao;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Aluno
 */
public class ClientesDAO {
    
     public void salvar(Clientes c) {
        
        try (Connection con = Conexao.getConnection()){
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO clientes (cpf_cliente, nome_completo_cliente,"
                    + "rua_endereco, complemento_endereco, cidade_endereco, setor_endereco, uf_endereco, cep_endereco,"
                    + "ddd_telefone, numero_telefone, tipo_telefone) values (?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setString(1, c.getCpf());
            pstmt.setString(2, c.getNome());
            pstmt.setString(3, c.getRua());
            pstmt.setString(4, c.getComplemento());
            pstmt.setString(5, c.getCidade());
            pstmt.setString(6, c.getSetor());
            pstmt.setString(7, c.getUf());
            pstmt.setString(8, c.getCep());
            pstmt.setString(9, c.getDdd());
            pstmt.setString(10, c.getNumero());
            pstmt.setString(11, c.getTipo());
            pstmt.executeUpdate();
   
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao salvar: " + ex);
           
        }
    }
    
    public List<Clientes> readForDesc(String desc) {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;

        List<Clientes> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE cpf_cliente LIKE ?");
            stmt.setString(1, "%"+desc+"%");
                 
            rs = stmt.executeQuery();

            while (rs.next()) {

                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("codigo_cliente"));
                cliente.setNome(rs.getString("nome_completo_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                clientes.add(cliente);
                
            }
           

        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }
    
    public List<Clientes> readForEnd(String desc) {

        Connection con = Conexao.getConnection();
        
        PreparedStatement stmt = null;
        ResultSet rs = null;
        PreparedStatement stmt2 = null;
        ResultSet rs2 = null;

        List<Clientes> clientes = new ArrayList<>();

        try {
            stmt = con.prepareStatement("SELECT * FROM clientes WHERE cpf_cliente LIKE ?");
            stmt.setString(1, "%"+desc+"%");              
            rs = stmt.executeQuery();
            
            
            
            while (rs.next()) {

                Clientes cliente = new Clientes();
                cliente.setId(rs.getInt("codigo_cliente"));
                cliente.setNome(rs.getString("nome_completo_cliente"));
                cliente.setCpf(rs.getString("cpf_cliente"));
                cliente.setRua(rs.getString("rua_endereco"));
                cliente.setComplemento(rs.getString("complemento_endereco"));
                cliente.setCidade(rs.getString("cidade_endereco"));
                cliente.setSetor(rs.getString("setor_endereco"));
                cliente.setUf(rs.getString("uf_endereco"));
                cliente.setCep(rs.getString("cep_endereco"));
                cliente.setDdd(rs.getString("ddd_telefone"));
                cliente.setNumero(rs.getString("numero_telefone"));
                cliente.setTipo(rs.getString("tipo_telefone"));
                clientes.add(cliente);
                
            }
            
           stmt2 = con.prepareStatement("SELECT * FROM enderecos WHERE codigo_endereco LIKE ?");  
           
         
        } catch (SQLException ex) {
            Logger.getLogger(ProdutosDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

        return clientes;

    }
    public void alterar(Clientes c) {
        
        try (Connection con = Conexao.getConnection()){
            PreparedStatement pstmt = con.prepareStatement("UPDATE clientes SET cpf_cliente = ?, nome_completo_cliente = ?,"
                    + "rua_endereco = ?, complemento_endereco = ?, cidade_endereco = ?, setor_endereco = ?, uf_endereco = ?, cep_endereco = ?,"
                    + "ddd_telefone = ?, numero_telefone = ?, tipo_telefone = ? WHERE codigo_cliente = ?");
            pstmt.setString(1, c.getCpf());
            pstmt.setString(2, c.getNome());
            pstmt.setString(3, c.getRua());
            pstmt.setString(4, c.getComplemento());
            pstmt.setString(5, c.getCidade());
            pstmt.setString(6, c.getSetor());
            pstmt.setString(7, c.getUf());
            pstmt.setString(8, c.getCep());
            pstmt.setString(9, c.getDdd());
            pstmt.setString(10, c.getNumero());
            pstmt.setString(11, c.getTipo());
            pstmt.setInt(12, c.getId());
            pstmt.executeUpdate();
   
            
            JOptionPane.showMessageDialog(null, "Atualizado com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao atualizar: " + ex);
           
        }
        
    }
    public void deletar(Clientes c) {
        
        try (Connection con = Conexao.getConnection()){
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM clientes WHERE codigo_cliente = ? ");
            pstmt.setInt(1, c.getId());
            pstmt.executeUpdate();
   
            JOptionPane.showMessageDialog(null, "Excluido com sucesso!");
            
        } catch (SQLException ex) {
            Logger.getLogger(ClientesDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "Erro ao excluir: " + ex);
           
        }
    }
}
