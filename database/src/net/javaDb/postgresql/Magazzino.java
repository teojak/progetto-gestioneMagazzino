package net.javaDb.postgresql;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.Properties;

public class Magazzino {

	private int barcode;
	private String marca;
	private String modello;
	private float price;
	private int id_dealer;
	private int quantita;
	private int sale;
	private String note;
	private LocalDateTime dataSale;
	private int id_sett_cat;
	
	public Magazzino(String url, String username, String password) throws SQLException
	{
		Connection conn = DriverManager.getConnection(url, username, password);
		
		Statement stmt = conn.createStatement();
		String sql = "CREATE TABLE IF NOT EXISTS magazzino "
			+ "(barcode SERIAL PRIMARY KEY, "
			+ " id_marca INTEGER, "
			+ " modello VARCHAR(255), "
			+ " price VARCHAR(255), "
			+ " id_dealer INTEGER, "
			+ " quantita VARCHAR(255), "
			+ " sale VARCHAR(255), "
			+ " dataSale VARCHAR(255), "
			+ " note VARCHAR(255), "
			+ " id_sett_cat INTEGER) ";
		stmt.executeUpdate(sql);
		stmt.close();
		System.out.println("magazzino creato");
		System.out.println("");
	}
	
	public void EliminaProdotto(int barcode, String url, String username, String password) throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
        PreparedStatement p=null;
        
        String sql="delete from magazzino where barcode="+barcode;
        p =conn.prepareStatement(sql);
        p.execute();
        p.close();
        System.out.println("eliminato prodotto con barcode " + barcode);
        System.out.println("");
	}
	
	public void InserisciMagazzino(int barcode, int id_marca, String modello, float price, int id_dealer, int quantita, int sale, LocalDateTime dataSale, String note, int id_sett_cat, String url, String username, String password) throws SQLException 
	{
		
		Connection conn = DriverManager.getConnection(url, username, password);
		
		String query = "INSERT INTO magazzino(barcode, id_marca, modello, price, id_dealer, quantita, sale, dataSale, note, id_sett_cat) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?) "
				+"ON CONFLICT (barcode) DO NOTHING";
		PreparedStatement st = conn.prepareStatement(query);
		st.setObject(1, barcode);
		st.setObject(2, id_marca);
		st.setObject(3, modello);
		st.setObject(4, price);
		st.setObject(5, id_dealer);
		st.setObject(6, quantita);
		st.setObject(7, sale);
		st.setObject(8, dataSale);
		st.setObject(9, note);
		st.setObject(10, id_sett_cat);
		st.executeUpdate();
		st.close();
	}
    
}
