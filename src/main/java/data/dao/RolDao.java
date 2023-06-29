package data.dao;

import data.database.ConnectionDB;
import model.ReporteAnonimo;
import model.Rol;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RolDao implements CrudUtilities<Rol> {

    private LinkedList<Rol> roleList;
    private final ConnectionDB DBC;

    public RolDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        roleList = null;
    }

    public LinkedList<Rol> getRoleList() {
        return roleList;
    }

    @Override
    public boolean insert(Rol entity) {
        System.out.println("Registrando nuevo rol...");
        DBC.setConnection();
        DBC.createStmt();

        try {
            String insertionQuery = String.format("INSERT INTO Rol (nombrerol) VALUES ('%s');", entity.getNombreRol());

            if ( DBC.executeQuery(insertionQuery) )
                System.out.println("Se ha registrado el nuevo rol.");
            else {
                System.out.println("No se pudo registrar el nuevo rol.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage());
            return false;
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        select(id);
        if ( roleList == null || roleList.isEmpty() ) {
            System.out.println("No existe el estado con ID: " + id);
            return false;
        }

        try {
            String deleteQuery = String.format("DELETE FROM Rol WHERE rolid = %d;", id);
            if ( DBC.executeQuery(deleteQuery) )
                System.out.println("Se ha eliminado el rol.");
            else {
                System.out.println("Ocurrió un error al eliminar el rol.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage());
            return false;
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
         return true;
    }

    @Override
    public boolean update(Rol entity) {
        String updateQuery = String.format("UPDATE Rol SET nombrerol= '%s' WHERE rolid = %d;", entity.getNombreRol(),
                entity.getRolID());

        DBC.setConnection();
        DBC.createStmt();

        try {
            if ( DBC.executeQuery(updateQuery) )
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Rol actualizado correctamente.");
            else {
                System.out.println("No se pudo actualizar el rol.");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage());
            return false;
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
        return true;
    }

    @Override
    public LinkedList<Rol> select(int id) {
        DBC.setConnection();
        DBC.createStmt();

        System.out.println("Consultando los roles disponibles...");
        String selectQuery = String.format("SELECT * FROM Rol WHERE rolid = %d;", id);

        try {
            if ( DBC.runQuery(selectQuery) )
                roleList = fetchData(DBC.getResultSet());
            if ( roleList.isEmpty() )
                System.out.println("No se encontró el rol con ID: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage());
        } finally {
            DBC.closeStmt();
            DBC.closeResultSet();
            DBC.disconnect();
        }
        return null;
    }

    @Override
    public void selectAll() {
        DBC.setConnection();
        DBC.createStmt();

        System.out.println("Consultando los roles disponibles...");
        try {
            if ( DBC.runQuery("SELECT * FROM Rol;") )
                roleList = fetchData(DBC.getResultSet());
            if ( roleList.isEmpty() )
                System.out.println("No existen roles disponibles.");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage());
        } finally {
            DBC.closeResultSet();
            DBC.closeStmt();
            DBC.disconnect();
        }
    }

    @Override
    public LinkedList<Rol> fetchData(ResultSet rs) {
        LinkedList<Rol> tempList = new LinkedList<>();
        try {
            while (rs.next()) {
                short rolID = rs.getShort("rolid");
                String rol = rs.getString("nombrerol");

                tempList.add( new Rol(rolID, rol) );
            }

            return tempList;
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar la info del RS.");
            return null;
        }
    }
}
