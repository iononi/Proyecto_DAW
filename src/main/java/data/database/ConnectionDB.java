package data.database;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConnectionDB extends Credentials {

    private Statement stmt;
    private Connection con;
    private ResultSet rs;

    public ConnectionDB(String db, String owner, String pwd) {
        super(db, owner, pwd);
    }

    public Statement getStatement() {
        return this.stmt;
    }

    public Connection getConnection() {
        return this.con;
    }

    public ResultSet getResultSet() {
        return  this.rs;
    }

    public void createStmt() {
        try {
            stmt = con.createStatement();
        } catch (SQLException e) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al crear el Statement.", e);
        }
    }

    public void closeStmt() {
        try {
            stmt.close();
        } catch (SQLException e) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al cerrar el Statement.", e);
        }
    }

    public boolean executeQuery(String query) throws SQLException {
        try {
            stmt.execute(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al realizar la inserción", ex);
            return false;
        }
        return true;
    }

    public boolean runQuery(String query) throws SQLException {
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al realizar la consulta", ex);
            return false;
        }
        return true;
    }

    public void closeResultSet() {
        try {
            rs.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
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