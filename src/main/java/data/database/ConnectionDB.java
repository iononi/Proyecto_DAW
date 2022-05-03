package data.database;

import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.SQLException;

public class ConnectionDB extends Credentials {

    private Statement stmt;
    private Connection con;

    public ConnectionDB(String db, String owner, String pwd) {
        super(db, owner, pwd);
    }

    public Statement getStatement() {
        return this.stmt;
    }

    public Connection getConnection() {
        return this.con;
    }

    public void createStmt() {
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al crear el Statement.", e);
        }
    }

    public boolean executeQuery(String query) {
        this.setConnection();
        this.createStmt();
        try {
            this.getStatement().execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al realizar la inserción", ex);
            return false;
        } finally {
            this.disconnect();
        }
        return true;
    }

    @Override
    public void setConnection() {
        try {
            Class.forName("org.postgresql.Driver");

            con = DriverManager.getConnection(super.getUrl(), super.getOwner(), super.getPassword());

        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al establecer la conexión.", ex);
        }
    }

    @Override
    public void disconnect() {
        try {
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al desconectarse de la BD.", ex);
        }
    }
}