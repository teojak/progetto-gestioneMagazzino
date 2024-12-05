package net.javaDb.postgresql;
import java.sql.*;
import java.util.Properties;
public class jdbcDemo {
	public static void main(String[] args) throws SQLException {
		String url = "jdbc:postgresql://localhost:5432/myDb";
		String username = "postgres";
		String password = "caramella";
		Connection conn = DriverManager.getConnection(url, username, password);
		System.out.println("Connessione funzionante");
		
		//-------------------------------------------------------------------------------
		Magazzino magazzino1;
		magazzino1= new Magazzino(url, username, password);
		
		Tipo tipo1;
		tipo1= new Tipo(url, username, password);
		
		Fornitori fornitori1;
		fornitori1= new Fornitori(url, username, password);
		
		Marche marca1;
		marca1= new Marche(url, username, password);
		//-------------------------------------------------------------------------------
		
		magazzino1.InserisciMagazzino(1234567892, 1, "modello", (float) 49.99, 31, 1, 0, null, "note", 7, url, username, password);
		
		tipo1.InserisciTipo("sett1", "b", url, username, password);
		
		fornitori1.InserisciFornitori("b2b", "mail", "note", url, username, password);
		
		marca1.InserisciMarca(3, "bosh", url, username, password);
		
		System.out.println("Inserimento effettuato!");
		
		//-------------------------------------------------------------------------------

		//magazzino1.EliminaProdotto(1234567890, url, username, password);
		//tipo1.EliminaTipo(20, url, username, password);
		//fornitori1.EliminaFornitore("b2b", url, username, password);
		//marca1.EliminaMarca("bosh", url, username, password);
		
		//-------------------------------------------------------------------------------
		
		tipo1.OrdinaTipo(url, username, password);
		marca1.OrdinaMarche(url, username, password);
		
		//tipo1.cercaSettore("settore", url, username, password);
		//tipo1.cercaCategoria("categoria", url, username, password);
		
		tipo1.cercaSettoreCategoria("settore", "f", url, username, password);
		marca1.cercaMarca("marca", url, username, password);
		fornitori1.cercaFornitore("fornitori", url, username, password);
		
		DBTablePrinter.printTable(conn, "magazzino");
		DBTablePrinter.printTable(conn, "tipo");
		DBTablePrinter.printTable(conn, "fornitori");
		DBTablePrinter.printTable(conn, "marche");
	}	
}
