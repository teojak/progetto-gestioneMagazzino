package net.javaDb.postgresql;
import java.sql.*;
import java.util.Properties;

public class Tipo {
	private int id;
	private String settore;
	private String categoria;
	
	public Tipo(String url, String username, String password) throws SQLException 
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS tipo"
				+"(id SERIAL PRIMARY KEY, "
				+"settore VARCHAR(255), "
				+"categoria VARCHAR(255) UNIQUE)";
		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("tipo creato");
		System.out.println("");
	}
	
	public void InserisciTipo(String settore, String categoria, String url, String username, String password) throws SQLException {
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String query = "INSERT INTO tipo(settore, categoria) VALUES (?, ?) "+"ON CONFLICT (categoria) DO NOTHING";
		PreparedStatement st = conn.prepareStatement(query);
		st.setObject(1, settore);
		st.setObject(2, categoria);
		st.executeUpdate();
		st.close();
	}
	
	public void EliminaTipo(int id, String url, String username, String password) throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
        PreparedStatement p=null;
        
        String sql="delete from tipo where id="+id;
        p =conn.prepareStatement(sql);
        p.execute();
        p.close();
        System.out.println("eliminato prodotto con id " + id);
        System.out.println("");
	}
	public void OrdinaTipo(String url, String username, String password) throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
		Statement stmt = conn.createStatement();
		String sql="SELECT * FROM tipo"
				+ " ORDER BY settore , categoria ";
		 ResultSet rs = stmt.executeQuery(sql);

         // Process the result set
         while (rs.next()) {
             // Retrieve and print data by column name or index
             int id = rs.getInt("id"); 
             String settore = rs.getString("settore");
             String categoria = rs.getString("categoria");
             // Print retrieved data
             System.out.printf("ID: %d, Settore: %s, Categoria: %s%n", id, settore, categoria);   
         }
         
         System.out.println("");
         
         // Close the ResultSet and Statement
         rs.close();
         stmt.close();
	}
	
	/*public void cercaSettore(String settore, String url, String username, String password) throws SQLException
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM tipo WHERE settore='"+settore+"'"+"ORDER BY categoria";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			 int id = rs.getInt("id"); 
             String categoria = rs.getString("categoria");
             // Print retrieved data
             System.out.printf("ID: %d, Settore: %s, Categoria: %s%n", id, settore, categoria);
		}
		System.out.println("");
		
		rs.close();
		stmt.close();
	}
	
	public void cercaCategoria(String categoria, String url, String username, String password) throws SQLException
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		Statement stmt =conn.createStatement();
		String sql = "SELECT * FROM tipo WHERE categoria='"+categoria+"' ORDER BY settore";
		ResultSet rs = stmt.executeQuery(sql);
		
		while(rs.next()) {
			 int id = rs.getInt("id"); 
            String settore = rs.getString("settore");
            // Print retrieved data
            System.out.printf("ID: %d, Settore: %s, Categoria: %s%n", id, settore, categoria);
		}
		System.out.println("");
		
		rs.close();
		stmt.close();
	}*/
	
	public void cercaSettoreCategoria(String settore, String categoria, String url, String username, String password) throws SQLException
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		
		if(categoria.equals("")) {
			String sql = "SELECT magazzino.barcode, marche.marca, magazzino.modello, magazzino.price, fornitori.nome, "
				+ "magazzino.quantita, magazzino.sale, magazzino.datasale, magazzino.note, tipo.settore, tipo.categoria "
				+ "FROM magazzino "
				+ "RIGHT JOIN marche ON magazzino.id_marca = marche.id "
				+ "INNER JOIN fornitori ON magazzino.id_dealer = fornitori.id "
				+ "INNER JOIN tipo ON magazzino.id_sett_cat = tipo.id "
				+ "WHERE tipo.settore=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, settore);
			
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
	             // Retrieve and print data by column name or index
	             int barcode = rs.getInt("barcode");
	             String marca = rs.getString("marca");
	             String modello = rs.getString("modello");
	             float price = rs.getFloat("price");
	             String nome = rs.getString("nome");
	             int quantita = rs.getInt("quantita");
	             int sale = rs.getInt("sale");
	             String datasale = rs.getString("datasale");
	             String note = rs.getString("note");
	             String categoria2 = rs.getString("categoria");
	             
	             //Print retrieved data
	             System.out.printf("Barcode: %d, Marca: %s, Modello: %s, Price: %f, Dealer: %s, Quantita: %d, Sale: %d, Datasale: %s, Note: %s, Settore: %s,"
	             		+ " Categoria %s%n", barcode, marca, modello, price, nome,quantita, sale, datasale, note, settore, categoria2);
	         }
			System.out.println("");

	         // Close the ResultSet and Statement
	         rs.close();
	         pstmt.close();
		}
		else {
			String sql = "SELECT magazzino.barcode, marche.marca, magazzino.modello, magazzino.price, fornitori.nome, "
					+ "magazzino.quantita, magazzino.sale, magazzino.datasale, magazzino.note, tipo.settore, tipo.categoria "
					+ "FROM magazzino "
					+ "RIGHT JOIN marche ON magazzino.id_marca = marche.id "
					+ "INNER JOIN fornitori ON magazzino.id_dealer = fornitori.id "
					+ "INNER JOIN tipo ON magazzino.id_sett_cat = tipo.id "
					+ "WHERE tipo.settore=? AND tipo.categoria=?";
			PreparedStatement pstmt = conn.prepareStatement(sql);
				
			pstmt.setString(1, settore);
			pstmt.setString(2, categoria);
				
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
	             // Retrieve and print data by column name or index
	             int barcode = rs.getInt("barcode");
	             String marca = rs.getString("marca");
	             String modello = rs.getString("modello");
	             float price = rs.getFloat("price");
	             String nome = rs.getString("nome");
	             int quantita = rs.getInt("quantita");
	             int sale = rs.getInt("sale");
	             String datasale = rs.getString("datasale");
	             String note = rs.getString("note");
	             
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
}
