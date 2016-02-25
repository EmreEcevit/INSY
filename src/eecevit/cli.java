package eecevit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.io.PrintStream;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

public class cli {
	
	public static void main(String[]args){

		/**
		 * Mittels der Klasse Options wird die Möglichkeit erstellt
		 * verschiedene Kürzel zu generieren welche dann aus den
		 * Programmargumenten herausgelesen werden kann.
		 */
		Options options = new Options();
		options.addOption("u",true,"Benutzername");
		options.addOption("pw",true,"Passwort");
		options.addOption("d",true, "Datenbank");
		options.addOption("s",true,"IP-Adresse");
		options.addOption("p",true,"Port");

		/**
		 * Es wird mit der Klasse Properties eine angesagt,
		 * dass es ein .properties file exestiert
		 *
		 * mit dem FileInputStream wird dann der
		 * Inhalt von dem .properties file gelesen
		 */
		Properties defaultProps = new Properties();
		FileInputStream in;

		try {


			in = new FileInputStream("src/properties.properties");		//hier wird mit dem FileInputStream eine Verbindung zu dem .properties file aufgebaut
			defaultProps.load(in);										//defaultProps bekommt die Information wo sich das .properties file befindet und bekommt den Inhalt
			
						
			in.close();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CommandLineParser parser = new DefaultParser();					//Die Argumente welche eingegebn werden kann mithilfe dieses Paresers abgelesen werden
		try {
			CommandLine cmd =  parser.parse( options, args);			//

			/**
			 * Es folgen nun mehrere Abfragen
			 * Als erstes wird ueberprueft, ob sich eines dieser Argumente
			 * schon eingegeben wurd.
			 * Wenn dies nicht der Fall ist, wird aus dem .properties file
			 * ein default wert genommen
			 */
			if(cmd.hasOption("u")){
				SQL.u = cmd.getOptionValue("u");
			}else{
				SQL.u = defaultProps.getProperty("user");
			}
			
			if(cmd.hasOption("s")){
				SQL.s = cmd.getOptionValue("s");
			}else{
				SQL.s = defaultProps.getProperty("host");
			}
			
			if(cmd.hasOption("p")){
				SQL.p = cmd.getOptionValue("p");
			}else{
				
			}
			
			if(cmd.hasOption("pw")){
				SQL.pw = cmd.getOptionValue("pw");
			}else{
				SQL.pw = defaultProps.getProperty("password");
			}
			if(cmd.hasOption("d")){
				SQL.d = cmd.getOptionValue("d");
			}else{
				SQL.d = defaultProps.getProperty("database");
			}
			SQL con = new SQL();
			con.connect();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}
