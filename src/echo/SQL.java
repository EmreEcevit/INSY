package echo;

import java.sql.*;
import org.postgresql.ds.PGSimpleDataSource;



public class SQL {
	public long startTime;
	public long endTime;

	public static  String u;
	public static String s;
	public static String p;
	public static String pw;
	public static String d;

	public int start;
	public int end;



	
	public void connect(){
		CRUD crud = new CRUD();
		/*
		* Mit PGSimpleDataSource wird ein einfaches Datasource erstellt
		*
		* via dem Befehl ServerName("...") wird die IP von der Datenbank angegeben
		* via dem Befehl DatabaseName("...") wird angegeben auf welche Datenbank zugegriffen wird
		* via dem Befehl setUser("...") wird festgelegt mit welchem User auf diese Datenbank zugegriffen werden soll
		* via dem Befehl setPassword("...") wird angegeben welchs Password der User in der Datenbank verwendet
		*
		*/
		PGSimpleDataSource ds = new PGSimpleDataSource();
		   ds.setServerName(s);
		   ds.setDatabaseName(d);
		   ds.setUser(u);
		   ds.setPassword(pw);

			// Verbindung herstellen
		   try(	Connection con = ds.getConnection();
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("SELECT * FROM person");

				/*
				 * 1.) 	Dank der neuen Moeglichkeit die einzelnen Variablen im parameter vom
				 * 		try eingeben koenen, muss man es am ende von try{...} nicht mehr einzeln
				 * 		schliessen
				 * 		
				 * 		Connection 		-->  	eine Verbindung zu der Datenbank wird aufgebaut
				 * 		Statement 		-->		ist ein Befehl um weitere Informationen zu senden
				 * 		executeQuery	-->		hier wird einfach der SQL Befehl eingegegeben und im rs abgespeichert
				 *
				 */
				){

				start = 41; // es wird ab dem Wert 41 gestartet da schon 40 Personen in der Datenbank eingetragen sind
			    end = 10041;
			   long sec = 1000000000;

			   startTime = System.nanoTime();

			   for(int i=start; i<end; i++)
					 crud.insert(i, "max" + i, "mustermann" + i,con);
			   endTime = System.nanoTime();
			   System.out.println();
			   // Die Zeit wird berechnet indem die minimal zeit (startTime) von der maximal zeit (endTime) abgezogen
			   long insertTime = endTime - startTime;

			   crud.select(con);

			   startTime = System.nanoTime();
			    for(int i=start; i<end; i++)
			    	crud.update("john"+i, "doe"+i, i,con);
			   System.out.println();
			   endTime = System.nanoTime();
			   // Die Zeit wird berechnet indem die minimal zeit (startTime) von der maximal zeit (endTime) abgezogen
			   long updateTime = endTime - startTime;

			    crud.select(con);

			   startTime = System.nanoTime();
			   	for(int i=start; i<end; i++)
			   		crud.delete(i,con);
			   System.out.println();
			   endTime = System.nanoTime();
			   // Die Zeit wird berechnet indem die minimal zeit (startTime) von der maximal zeit (endTime) abgezogen
			   long deleteTime = endTime - startTime;

			   crud.select(con);

			   System.out.println();
			   System.out.println("insert Time: "+insertTime/sec+" sekunden");
			   System.out.println("update Time: "+updateTime/sec+" sekunden");
			   System.out.println("delete Time: "+deleteTime/sec+" sekunden");

				   }catch(SQLException e){
					   /*
					    * es ist wichtig eine Exception einzubauen, da es immer zu Fehlern kommen kann
					    * und damit es nicht dramatische folgen haben soll wird dieser gefiltert.
					    */
					   e.printStackTrace();
				   }

	}
}
 