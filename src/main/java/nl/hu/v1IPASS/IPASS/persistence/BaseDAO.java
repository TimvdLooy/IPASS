package nl.hu.v1IPASS.IPASS.persistence;

import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class BaseDAO {
	//Connectie maken met de database. Voorkomt stacken van connecties.
	protected static Connection getConnection() {
	    Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			//Heroku database gegevens. voer localen gegevens in, voor lokaal gebruik.
			String username = "ytfykccyqnuxnr";
			String password = "7c73b3455d3c874e6988bd42c57c71bbffc69362875824b7734736361234f0ac";
		    String dbUrl = "jdbc:postgresql://ec2-79-125-118-221.eu-west-1.compute.amazonaws.com:5432/dfoo2gfmef89ab";

		    con = DriverManager.getConnection(dbUrl, username, password);
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return con;
	}
}
