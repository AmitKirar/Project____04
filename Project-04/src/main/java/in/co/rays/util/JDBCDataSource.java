package in.co.rays.util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import in.co.rays.exception.ApplicationException;

/**
 * JDBCDataSource is a singleton class that manages a connection pool using
 * c3p0. It loads database configuration from a resource bundle and provides
 * utility methods for getting and closing connections.
 * 
 * @author Amit
 * @version 1.0
 */

public final class JDBCDataSource {

	/**
	 * Private constructor to prevent instantiation.
	 */

	private JDBCDataSource() {
	}

	/** Singleton instance of JDBCDataSource */
	private static JDBCDataSource datasource;

	/** c3p0 ComboPooledDataSource instance */
	private ComboPooledDataSource cpds = null;

	/**
	 * Returns the singleton instance of JDBCDataSource. Initializes the connection
	 * pool on first call.
	 *
	 * @return JDBCDataSource instance
	 */

	public static JDBCDataSource getInstance() {
		if (datasource == null) {

			ResourceBundle rb = ResourceBundle.getBundle("in.co.rays.bundle.system");

			datasource = new JDBCDataSource();
			datasource.cpds = new ComboPooledDataSource();
			try {
				datasource.cpds.setDriverClass(rb.getString("driver"));
			} catch (Exception e) {
				e.printStackTrace();
			}
			datasource.cpds.setJdbcUrl(rb.getString("url"));
			datasource.cpds.setUser(rb.getString("username"));
			datasource.cpds.setPassword(rb.getString("password"));
			datasource.cpds.setInitialPoolSize(new Integer((String) rb.getString("initialpoolsize")));
			datasource.cpds.setAcquireIncrement(new Integer((String) rb.getString("acquireincrement")));
			datasource.cpds.setMaxPoolSize(new Integer((String) rb.getString("maxpoolsize")));
			datasource.cpds.setMaxIdleTime(new Integer((String) rb.getString("timeout")));
			datasource.cpds.setMinPoolSize(new Integer((String) rb.getString("minpoolsize")));

		}
		return datasource;
	}

	/**
	 * Gets a connection from the connection pool.
	 *
	 * @return a Connection object
	 * @throws SQLException if connection cannot be established
	 */

	public static Connection getConnection() throws SQLException {
		return getInstance().cpds.getConnection();
	}

	/**
	 * Closes the given connection if it is not null.
	 *
	 * @param connection the Connection to be closed
	 */

	public static void closeConnection(Connection connection) {
		if (connection != null) {
			try {
				connection.close();
			} catch (Exception e) {
			}
		}
	}

	/**
	 * Rolls back the transaction on the given connection.
	 *
	 * @param connection the Connection to roll back
	 * @throws ApplicationException if rollback fails
	 */

	public static void trnRollback(Connection connection) throws ApplicationException {
		if (connection != null) {
			try {
				connection.rollback();
			} catch (SQLException ex) {
				throw new ApplicationException(ex.toString());
			}
		}
	}

}
