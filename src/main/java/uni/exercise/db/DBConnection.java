package uni.exercise.db;


import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DBConnection {
    private Connection conn = null;

    public Connection getConnection() {
        DataSource source;
        InitialContext ic;

        try {
            ic = new InitialContext();
            source = (DataSource) ic.lookup("java:comp/env/jdbc/postgresql_resource");
            this.conn = source.getConnection();
        }
        catch (NamingException | SQLException e ) {
            e.printStackTrace();
        }
        return this.conn;
    }

    public void closeConnection() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
