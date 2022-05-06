package data.dao;


import data.database.ConnectionDB;
import model.MetodoPago;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MetodoPagoDao implements CrudUtilities<MetodoPago> {

    private LinkedList<MetodoPago> paymentList;
    private final ConnectionDB DBC;

    public MetodoPagoDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        paymentList = null;
    }

    @Override
    public void insert(MetodoPago entity) {
        System.out.println("Insertando método de pago...");
        DBC.setConnection(); // establecemos conexión con la base de datos
        DBC.createStmt();   // creamos el statement necesario para ejecutar queries
        try {

            String insertion_query = String.format("INSERT INTO MetodoPago(metodopago) VALUES ('%s');",
                    entity.getMetodoPago());

            // Se ejecuta la instrucción 'insertion_query' y, en caso de ser posible la inserción, devuelve un true.
            // Devuelve false en caso contrario y por lo tanto no se pudo insertar en la BD.
            if (DBC.executeQuery(insertion_query))
                System.out.println("La base de datos ha sido actualizada! :D");
            else
                System.out.println("No se ha podido insertar al método de pago :/");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al insertar.", ex);
        } finally {
            DBC.closeStmt(); // Independientemente de si se pudo realizar la operación de inserción o no, con este bloque
            DBC.disconnect(); // cerramos el statement y nos desconectamos de la BD.
        }
    }

    @Override
    public void delete(int id) {
        DBC.setConnection(); // establecemos conexión con la BD
        DBC.createStmt();   // creamos el statement para ejecutar queries

        System.out.println("Eliminando método de pago...");

        try {

            String delete_query = String.format("DELETE FROM MetodoPago WHERE metodoid = %d;", id);

            if (DBC.executeQuery(delete_query)) // si el método execute() regresa true, se pudo eliminar.
                System.out.println("Se ha eliminado el método de pago! :D");
            else
                System.out.println("Ocurrió un error al eliminar al método de pago :/");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el método de pago.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.disconnect(); // Nos desconectamos de la BD
        }
    }

    @Override
    public void update(MetodoPago entity) {
        String updateQuery = String.format("UPDATE MetodoPago SET metodopago = '%s' WHERE metodoid = %d",
                entity.getMetodoPago(), entity.getMetodoId());
        DBC.setConnection();
        DBC.createStmt();

        try {
            if ( DBC.executeQuery(updateQuery) )
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.INFO, "Metodo de pago actualizado.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al actualizar método de pago.", ex);
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
    }

    @Override
    public void select(int id) {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el Statement

        System.out.printf("Consultando el método de pago con ID: %d...\n", id);
        String select_query = String.format("SELECT * FROM MetodoPago WHERE metodoid = %d;", id);

        try {
            if (DBC.runQuery(select_query)) // Si el método runQuery() regresa true, se encontró al alumno
                paymentList = fetchData(DBC.getResultSet()); // Obtiene los datos del ResultSet y lo guarda en paymentList
            if (paymentList.size() == 0)
                System.out.println("No se encontró el método de pago con ID: " + id);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los métodos de pago.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.closeResultSet(); // Cerramos el resultset
            DBC.disconnect(); // Cerramos conexión con la BD
        }
    }

    @Override
    public void selectAll() {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el statement
        System.out.println("Recuperando los métodos de pago disponibles...\n");
        try {
            if (DBC.runQuery("SELECT * FROM MetodoPago;")) // Si se pudo ejecutar la consulta
                paymentList = fetchData(DBC.getResultSet()); // recupera los datos del ResultSet
            if (paymentList.size() == 0)
                System.out.println("No existen métodos de pago disponibles.");
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los métodos de pago disponibles.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.closeResultSet(); // Cerramos el resultset
            DBC.disconnect(); // Cerramos conexión con la BD
        }
    }

    @Override
    public LinkedList<MetodoPago> fetchData(ResultSet rs) {
        LinkedList<MetodoPago> tempList = new LinkedList<>();
        try {
            while (rs.next()) {
                int metodoID = rs.getInt("metodoid");
                String metodoPago = rs.getString("metodopago");

                tempList.add( new MetodoPago(metodoID, metodoPago) );
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
