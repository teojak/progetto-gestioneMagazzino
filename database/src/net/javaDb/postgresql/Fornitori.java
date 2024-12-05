package net.javaDb.postgresql;
import java.sql.*;
import java.util.Properties;

public class Fornitori {
	private String nome;
	private String mail;
	private String note;
	
	public Fornitori(String url, String username, String password) throws SQLException 
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS fornitori"
				+"(id SERIAL PRIMARY KEY, "
				+"nome VARCHAR(255) UNIQUE,"
				+"mail VARCHAR(255), "
				+"note VARCHAR(255))";
		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("fornitori creato");
		System.out.println("");
	}
	
	public void InserisciFornitori(String nome, String mail, String note, String url, String username, String password) throws SQLException 
	{

		Connection conn = DriverManager.getConnection(url, username, password);
		
		String query = "INSERT INTO fornitori(nome, mail, note) VALUES (?, ?, ?) "+"ON CONFLICT (nome) DO NOTHING";
		PreparedStatement st = conn.prepareStatement(query);
		st.setObject(1, nome);
		st.setObject(2, mail);
		st.setObject(3, note);
		st.executeUpdate();
		st.close();
	}
	
	public void EliminaFornitore(String nome, String url, String username, String password) throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
        PreparedStatement p=null;
        
        String sql="delete from fornitori where nome='"+nome+"'";
        p =conn.prepareStatement(sql);
        p.execute();
        p.close();
        System.out.println("eliminato prodotto con nome " + nome);
        System.out.println("");
	}
	
	public void cercaFornitore(String nome, String url, String username, String password) throws SQLException
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String sql = "SELECT magazzino.barcode, marche.marca, magazzino.modello, magazzino.price, fornitori.nome, "
				+ "magazzino.quantita, magazzino.sale, magazzino.datasale, magazzino.note, tipo.settore, tipo.categoria "
				+ "FROM magazzino "
				+ "RIGHT JOIN marche ON magazzino.id_marca = marche.id "
				+ "INNER JOIN fornitori ON magazzino.id_dealer = fornitori.id "
				+ "INNER JOIN tipo ON magazzino.id_sett_cat = tipo.id "
				+ "WHERE fornitori.nome=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, nome);
		
		ResultSet rs = pstmt.executeQuery();
		
		 while (rs.next()) {
             // Retrieve and print data by column name or index
             int barcode = rs.getInt("barcode");
             String marca = rs.getString("marca");
             String modello = rs.getString("modello");
             float price = rs.getFloat("price");
             int quantita = rs.getInt("quantita");
             int sale = rs.getInt("sale");
             String datasale = rs.getString("datasale");
             String note = rs.getString("note");
             String settore = rs.getString("settore");
             String categoria = rs.getString("categoria");
             //Print retrieved data
             System.out.printf("Barcode: %d, Marca: %s, Modello: %s, Price: %f, Dealer: %s, Quantita: %d, Sale: %d, Datasale: %s, Note: %s, Settore: %s,"
             		+ " Categoria %s%n", barcode, marca, modello, price, nome,quantita, sale, datasale, note, settore, categoria);
         }
         
         System.out.println("");

         // Close the ResultSet and Statement
         rs.close();
         pstmt.close();
	}
}
