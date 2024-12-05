package net.javaDb.postgresql;
import java.sql.*;
import java.util.Properties;

public class Marche {
	private int id;
	private String marca;

	public Marche(String url, String username, String password) throws SQLException 
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS marche"
				+"(id SERIAL PRIMARY KEY, "
				+"marca VARCHAR(255) UNIQUE)";
		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("marca creato");
		System.out.println("");
	}
	
	public void InserisciMarca(int id,String marca, String url, String username, String password) throws SQLException {
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String query = "INSERT INTO marche(id, marca) VALUES (?,?) "
				+"ON CONFLICT (id) DO NOTHING ";
		PreparedStatement st = conn.prepareStatement(query);
		st.setObject(1, id);
		st.setObject(2, marca);
		st.executeUpdate();
		st.close();
	}
	
	public void EliminaMarca(String marca, String url, String username, String password) throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
        PreparedStatement p=null;
        
        String sql="DELETE FROM marche WHERE marca='"+ marca+"'";
        p =conn.prepareStatement(sql);
        p.execute();
        p.close();
        System.out.println("eliminato prodotto con marca " + marca);
        System.out.println("");
	}
	
	public void OrdinaMarche(String url, String username, String password) throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
		Statement stmt = conn.createStatement();
		String sql="SELECT * FROM marche"
				+ " ORDER BY marca ";
		 ResultSet rs = stmt.executeQuery(sql);

         // Process the result set
         while (rs.next()) {
             // Retrieve and print data by column name or index
             int id = rs.getInt("id"); 
             String marca = rs.getString("marca");
             // Print retrieved data
             System.out.printf("ID: %d, Marca: %s%n", id, marca);
         }
         
         System.out.println("");

         // Close the ResultSet and Statement
         rs.close();
         stmt.close();
	}
	
	public void cercaMarca(String marca, String url, String username, String password) throws SQLException
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String sql = "SELECT magazzino.barcode, marche.marca, magazzino.modello, magazzino.price, fornitori.nome, "
				+ "magazzino.quantita, magazzino.sale, magazzino.datasale, magazzino.note, tipo.settore, tipo.categoria "
				+ "FROM magazzino "
				+ "RIGHT JOIN marche ON magazzino.id_marca = marche.id "
				+ "INNER JOIN fornitori ON magazzino.id_dealer = fornitori.id "
				+ "INNER JOIN tipo ON magazzino.id_sett_cat = tipo.id "
				+ "WHERE marche.marca=?";
		PreparedStatement pstmt = conn.prepareStatement(sql);
		
		pstmt.setString(1, marca);
		
		ResultSet rs = pstmt.executeQuery();
		
		 while (rs.next()) {
             // Retrieve and print data by column name or index
             int barcode = rs.getInt("barcode");
             String modello = rs.getString("modello");
             float price = rs.getFloat("price");
             String nome = rs.getString("nome");
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
