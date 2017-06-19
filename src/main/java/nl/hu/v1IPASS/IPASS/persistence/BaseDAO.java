package nl.hu.v1IPASS.IPASS.persistence;

import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;

public class BaseDAO {
	private DataSource connectionPool;
	
	public BaseDAO() {
	
		try {
			final String DATABASE_URL_PROP = System.getenv("DATABASE_URL");
			if (DATABASE_URL_PROP != null) {
				URI dbUri = new URI(DATABASE_URL_PROP);
				String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + dbUri.getPath();
				BasicDataSource pool = new BasicDataSource();
				if (dbUri.getUserInfo() != null) {
					pool.setUsername(dbUri.getUserInfo().split(":")[0]);
					pool.setPassword(dbUri.getUserInfo().split(":")[1]);
				}
				pool.setDriverClassName("org.postgresql.Driver");
				pool.setUrl(dbUrl);
				pool.setInitialSize(1);
	
				connectionPool = pool;
			} else {
				InitialContext ic = new InitialContext();
				connectionPool = (DataSource) ic.lookup("java:comp/env/jdbc/PostgresDS");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	protected static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
}
