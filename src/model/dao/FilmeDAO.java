package model.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;

import java.util.*; 

import connection.ConnectionFactory;
import model.bean.Filme;

public class FilmeDAO {
	
	public void create(Filme f) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		try {
			stmt = con.prepareStatement("INSERT INTO FILME (titulo, categoria, sinopse, tempo, imagem3d, dublado) VALUES"
					+ "(?,?,?,?,?,?)");
			stmt.setString(1, f.getTitulo());
			stmt.setString(2, f.getCategoria());
			stmt.setString(3, f.getSinopse());
			stmt.setInt(4, f.getTempo());
			stmt.setBoolean(5, f.isImagem3d());
			stmt.setBoolean(6, f.isDublado());
			
			stmt.executeUpdate();
			JOptionPane.showMessageDialog(null, "Filme Salvo com sucesso!");
		} catch(SQLException e) {
			JOptionPane.showMessageDialog(null, "Erro ao salvar: "+ e);
		}finally{
			ConnectionFactory.closeConnection(con, stmt);
		}
	}
	public List<Filme> read() {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null; 
		ResultSet rs = null;
		List<Filme> filmes = new ArrayList<>();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM filme;");
			rs = stmt.executeQuery();
			while(rs.next()) {
				Filme f = new Filme();
				f.setIdFilme(rs.getInt("idFilme"));
				f.setTitulo(rs.getString("titulo"));
				f.setTempo(rs.getInt("tempo"));
				f.setCategoria(rs.getString("categoria"));
				f.setDublado(rs.getBoolean("dublado"));
				f.setImagem3d(rs.getBoolean("imagem3d"));
				f.setSinopse(rs.getString("sinopse"));
				filmes.add(f);
			}
		}
		
		catch(SQLException e){
			JOptionPane.showMessageDialog(null, "erro ao buscar os informaçoes no banco:" + e);
			e.printStackTrace();	
		}
		
		finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return filmes;
	}
	
	public Filme read(int id){
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Filme f = new Filme();
		
		try {
			stmt = con.prepareStatement("SELECT * FROM filme WHERE IdFilme=? Limit 1");
			stmt.setInt(1, id);
			rs = stmt.executeQuery();
			if(rs != null && rs.next()) {
				f.setIdFilme(rs.getInt("idFilme"));
				f.setTitulo(rs.getString("titulo"));
				f.setTempo(rs.getInt("tempo"));
				f.setCategoria(rs.getString("categoria"));
				f.setDublado(rs.getBoolean("dublado"));
				f.setImagem3d(rs.getBoolean("imagem3d"));
				f.setSinopse(rs.getString("sinopse"));
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		finally {
			ConnectionFactory.closeConnection(con, stmt, rs);
		}
		return f;
	}
	
	public void update(Filme f) {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement stmt = null;
		
		
		try {
			stmt = con.prepareStatement("UPDATE `filme` SET `titulo`=?,`tempo`=?,`imagem3d`=?,`dublado`=?,`sinopse`=?,`categoria`=? WHERE idFilme =?");
			stmt.setString(1,f.getTitulo());
			stmt.setInt(2, f.getTempo());
			stmt.setBoolean(3, f.isImagem3d());
			stmt.setBoolean(4, f.isDublado());
			stmt.setString(5, f.getSinopse());
			stmt.setString(6, f.getCategoria());
			stmt.setInt(7, f.getIdFilme());
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
