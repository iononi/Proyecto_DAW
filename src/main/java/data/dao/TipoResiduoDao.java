package data.dao;

import data.database.ConnectionDB;
import model.TipoResiduo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TipoResiduoDao implements CrudUtilities<TipoResiduo> {

    private final ConnectionDB DBC;
    private LinkedList<TipoResiduo> trashList;

    public TipoResiduoDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        trashList = null;
    }

    @Override
    public void insert(TipoResiduo entity) {
        System.out.println("Insertando tipo de residuo...");
        DBC.setConnection(); // establecemos conexión con la base de datos
        DBC.createStmt();   // creamos el statement necesario para ejecutar queries
        try {

            String insertion_query = String.format("INSERT INTO TipoResiduo(tiporesiduo) VALUES ('%s');",
                    entity.getTipoResiduo());

            // Se ejecuta la instrucción 'insertion_query' y, en caso de ser posible la inserción, devuelve un true.
            // Devuelve false en caso contrario y por lo tanto no se pudo insertar en la BD.
            if (DBC.getStatement().execute(insertion_query))
                System.out.println("La base de datos ha sido actualizada! :D");
            else
                System.out.println("No se ha podido insertar el tipo de residuo :/");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al insertar.", ex);
        } finally {
            DBC.closeStmt(); // Independientemente de si se pudo realizar la operación de inserción o no, con este bloque
            DBC.disconnect(); // cerramos el statement y nos desconectamos de la BD.
        }
    }

    @Override
    public boolean delete(int id) {
        DBC.setConnection(); // establecemos conexión con la BD
        DBC.createStmt();   // creamos el statement para ejecutar queries

        System.out.println("Eliminando tipo de residuo...");

        try {

            String delete_query = String.format("DELETE FROM TipoResiduo WHERE residuoid = %d;", id);

            if (DBC.executeQuery(delete_query)) // si el método execute() regresa true, se pudo eliminar.
                System.out.println("Se ha eliminado la categoría de residuo! :D");
            else {
                System.out.println("Ocurrió un error al eliminar la categoría del residuo :/");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el tipo de residuo.", ex);
            return false;
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.disconnect(); // Nos desconectamos de la BD
        }
        return true;
    }

    @Override
    public void update(TipoResiduo entity) {
        String updateQuery = String.format("UPDATE TipoResiduo SET tiporesiduo = '%s' WHERE residuoid = %d;",
                entity.getTipoResiduo(), entity.getResiduoId());

        DBC.setConnection();
        DBC.createStmt();

        try {
            if ( DBC.runQuery(updateQuery) )
                System.out.println("El tipo de residuo ha sido actualizado exitosamente!");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo actualizar el tipo de residuo",
                    ex);
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
    }

    @Override
    public void select(int id) {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el Statement

        System.out.printf("Consultando la categoría de residuo con ID: %d...\n", id);
        String select_query = String.format("SELECT * FROM TipoResiduo WHERE residuoid = %d;", id);

        try {
            if (DBC.executeQuery(select_query)) // Si el método executeQuery() regresa true, se encontró al alumno
                trashList = fetchData(DBC.getResultSet()); // Obtiene los datos del ResultSet y lo guarda en trashList
            if (trashList == null)
                System.out.println("No se encontró la categoría de residuo con ID: " + id);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar las categorías de residuos.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        }
    }

    @Override
    public void selectAll() {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el statement
        System.out.println("Recuperando los tipos de residuo registrados...\n");
        try {
            if (DBC.executeQuery("SELECT * FROM TipoResiduo;")) // Si se pudo ejecutar la consulta
                trashList = fetchData(DBC.getResultSet()); // recupera los datos del ResultSet
            if (trashList == null)
                System.out.println("No hay información disponible.");
        } catch (SQLException ex) {
            System.out.println("Error al recuperar las categorías de residuos registrados.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.disconnect(); // Cerramos conexión con la BD
        }
    }

    @Override
    public LinkedList<TipoResiduo> fetchData(ResultSet rs) {
        LinkedList<TipoResiduo> tempList = new LinkedList<>();
        try {
            while (rs.next()) {
                int residuoID = rs.getInt("residuoid");
                String tipoResiduo = rs.getString("tiporesiduo");

                tempList.add( new TipoResiduo(residuoID, tipoResiduo) );
            }

            return tempList;
        } catch(SQLException ex) {
            System.out.println("Error al recuperar los datos del result set.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos" +
                    " del ResultSet.", ex);
            return null;
        }
    }
}
