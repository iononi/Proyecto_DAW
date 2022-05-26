package data.dao;

import data.database.ConnectionDB;
import model.Estado;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EstadoDao implements CrudUtilities<Estado> {

    private LinkedList<Estado> statusList;
    private final ConnectionDB DBC;

    public EstadoDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        statusList = null;
    }

    public LinkedList<Estado> getStatusList() {
        return statusList;
    }

    @Override
    public boolean insert(Estado entity) {
        System.out.println("Registrando nuevo estado...");
        DBC.setConnection();
        DBC.createStmt();

        try {
            String insertionQuery = String.format("INSERT INTO Estado (estado) VALUES ('%s');", entity.getEstado());

            if ( DBC.executeQuery(insertionQuery) )
                System.out.println("Se ha registrado el nuevo estado.");
            else {
                System.out.println("No se pudo registrar el nuevo estado.");
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
        if ( statusList == null || statusList.isEmpty() ) {
            System.out.println("No existe el estado con ID: " + id);
            return false;
        }

        try {
            String deletionQuery = String.format("DELETE FROM Estado WHERE estadoid = %d;", id);
            if ( DBC.executeQuery(deletionQuery) )
                System.out.println("Se ha eliminado el estado.");
            else {
                System.out.println("Ocurrió un error al eliminar el estado.");
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
    public boolean update(Estado entity) {
        String updateQuery = String.format("UPDATE Estado SET estado = '%s' WHERE estadoid = %d;", entity.getEstado(),
                entity.getEstadoID());
        DBC.setConnection();
        DBC.createStmt();

        try {
            if ( DBC.executeQuery(updateQuery) )
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.INFO, "Estado actualizado correctamente.");
            else {
                System.out.println("No se pudo actualizar el estado.");
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
    public void select(int id) {
        DBC.setConnection();
        DBC.createStmt();

        System.out.println("Consultando lista de estados disponibles...");
        String selectionQuery = String.format("SELECT * FROM Estado WHERE estadoid = %d;", id);

        try {
            if ( DBC.runQuery(selectionQuery) )
                statusList = fetchData(DBC.getResultSet());
            if ( statusList.isEmpty() )
                System.out.println("No se encontró el estado con ID: " + id);
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage());
        } finally {
            DBC.closeStmt();
            DBC.closeResultSet();
            DBC.disconnect();
        }
    }

    @Override
    public void selectAll() {
        DBC.setConnection();
        DBC.createStmt();

        System.out.println("Consultando los estados disponibles...");
        try {
            if ( DBC.runQuery("SELECT * FROM Estado;") )
                statusList = fetchData(DBC.getResultSet());
            if (statusList.isEmpty())
                System.out.println("No existen estados disponibles.");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage());
        } finally {
            DBC.closeStmt();
            DBC.closeResultSet();
            DBC.disconnect();
        }
    }

    @Override
    public LinkedList<Estado> fetchData(ResultSet rs) {
        LinkedList<Estado> tempList = new LinkedList<>();
        try {
            while (rs.next()) {
                short estadoID = rs.getShort("estadoid");
                String estado = rs.getString("estado");

                tempList.add( new Estado(estadoID, estado) );
            }

            return tempList;
        } catch ( SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos del RS.");
            return null;
        }
    }
}
