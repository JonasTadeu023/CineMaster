package model.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import connection.ConnectionFactory;
import model.bean.Cliente;
import model.bean.Filme;



public class ClienteDAO {
	public void create(Cliente c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		try {
			stmt = con.prepareStatement("INSERT INTO Cliente (clienteNome, clienteCPF, clienteEmail, clienteTelefone, clienteEndereco) VALUES (?,?,?,?,?);");
			stmt.setString(1, c.getClienteNome());
			stmt.setString(2,c.getClienteCPF());
			stmt.setString(3,c.getClienteEmail());
			stmt.setString(4,c.getClienteTelefone());
			stmt.setString(5,c.getClienteEndereco());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Cadastro na tabela cliente concluido!");
		}
		catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro no salvar no banco, tabela cliente: " + e);
			
	}
	finally {
		ConnectionFactory.closeConnection(con, stmt);
	}
		
}

	public List<Cliente> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		List<Cliente> clientes = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cliente;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Cliente c = new Cliente();
				c.setClienteId(rs.getInt("clienteId"));
				c.setClienteNome(rs.getString("clienteNome"));
				c.setClienteCPF(rs.getString("clienteCPF"));
				c.setClienteEmail(rs.getString("clienteEmail"));
				c.setClienteTelefone(rs.getString("clienteTelefone"));
				c.setClienteEndereco(rs.getString("clienteEndereco"));
				clientes.add(c);
			}
		}
		
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "erro ao buscar os informaçoes no banco:" + e);
			e.printStackTrace();	
		}
		
		finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return clientes;
	}
	
	
	public Cliente read(int id){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Cliente c = new Cliente();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM cliente WHERE clienteId=? Limit 1");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs != null && rs.next()) {
				c.setClienteId(rs.getInt("clienteId"));
				c.setClienteNome(rs.getString("clienteNome"));
				c.setClienteCPF(rs.getString("clienteCPF"));
				c.setClienteEmail(rs.getString("clienteEmail"));
				c.setClienteTelefone(rs.getString("clienteTelefone"));
				c.setClienteEndereco(rs.getString("clienteEndereco"));
				
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return c;
	}

	public void update(Cliente c) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		
		try {
			stmt = con.prepareStatement("UPDATE `cliente` SET `clienteNome`=?,`clienteCPF`=?,`clienteEmail`=?,`clienteTelefone`=?,`clienteEndereco`=? WHERE `clienteId`=? LIMIT 1");
			stmt.setString(1, c.getClienteNome());
			stmt.setString(2, c.getClienteCPF());
			stmt.setString(3, c.getClienteEmail());
			stmt.setString(4, c.getClienteTelefone());
			stmt.setString(5, c.getClienteEndereco());
			stmt.setInt(6, c.getClienteId());
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null,"Banco de dado atualizado com sucesso!");
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,"Erro ao Atualizar o Banco de Dados " + e);
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	
}
	
	

 