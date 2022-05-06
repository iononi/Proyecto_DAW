package data.dao;

import data.database.ConnectionDB;
import model.ReporteCliente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReporteClienteDao implements CrudUtilities<ReporteCliente> {

    private final ConnectionDB DBC;
    private LinkedList<ReporteCliente> reportList;

    public ReporteClienteDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        reportList = null;
    }

    @Override
    public void insert(ReporteCliente entity) {
        String paymentMethod = "SELECT public.metodopago.metodopago FROM reportecliente\n" +
                "INNER JOIN metodopago ON reportecliente.fk_metodopago = metodopago.metodoid\n" +
                "WHERE metodopago.metodoid = %d" + entity.getFkMetodoPago();
        System.out.println("Registrando reporte...");
        DBC.setConnection(); // establecemos conexión con la base de datos
        DBC.createStmt();   // creamos el statement necesario para ejecutar queries
        try {
            String insertion_query = String.format("INSERT INTO ReporteCliente(fk_cliente, fk_tiporesiduo, " +
                    "fk_metodopago, pagado) VALUES (%d, %d, %d, %b)", entity.getFkCliente(), entity.getFkTipoResiduo(),
                    entity.getFkMetodoPago(), false);

            // Se ejecuta la instrucción 'insertion_query' y, en caso de ser posible la inserción, devuelve un true.
            // Devuelve false en caso contrario y por lo tanto no se pudo insertar en la BD.
            if (DBC.getStatement().execute(insertion_query)) {

                try {
                    DBC.getStatement().executeQuery(paymentMethod);
                    if (DBC.getResultSet() != null) {
                        String payment = DBC.getResultSet().getString(1);
                        if (payment.equals("Tarjeta credito/debito")) {
                            // actualizar el campo pagado a true con update()
                        }
                    }
                } catch(SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println("La base de datos ha sido actualizada! :D");
            }
            else
                System.out.println("No se ha podido registrar el reporte :/");
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

        System.out.println("Eliminando reporte...");

        try {

            String delete_query = String.format("DELETE FROM ReporteCliente WHERE folio = %d;", id);

            if (DBC.getStatement().execute(delete_query)) // si el método execute() regresa true, se pudo eliminar.
                System.out.println("Se ha eliminado el reporte :D");
            else
                System.out.println("Ocurrió un error al eliminar el reporte :/");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el reporte.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.disconnect(); // Nos desconectamos de la BD
        }
    }

    @Override
    public void update(ReporteCliente entity) {

    }

    @Override
    public void select(int id) {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el Statement

        System.out.printf("Consultando los datos del reporte con folio: %d...\n", id);
        String select_query = String.format("SELECT * FROM ReporteCliente WHERE folio = %d;", id);

        try {
            if (DBC.runQuery(select_query)) // Si el método executeQuery() regresa true, se encontró al alumno
                reportList = fetchData(DBC.getResultSet()); // Obtiene los datos del ResultSet y lo guarda en reportList
            if (reportList.size() == 0)
                System.out.println("No se encontró el reporte con folio: " + id);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los datos del reporte especificado");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeStmt();
            DBC.closeResultSet();
            DBC.disconnect();
        }
    }

    @Override
    public void selectAll() {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el statement
        System.out.println("Recuperando reportes registrados...\n");
        try {
            if (DBC.runQuery("SELECT * FROM ReporteCliente;")) // Si se pudo ejecutar la consulta
                reportList = fetchData(DBC.getResultSet()); // recupera los datos del ResultSet
            if (reportList.size() == 0)
                System.out.println("No existen reportes registrados.");
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los reportes.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.closeResultSet();
            DBC.disconnect(); // Cerramos conexión con la BD
        }
    }

    @Override
    public LinkedList<ReporteCliente> fetchData(ResultSet rs) {
        LinkedList<ReporteCliente> tempList = new LinkedList<>();
        try {
            while (rs.next()) {
                int folio = rs.getInt("folio");
                int fkCliente = rs.getInt("fk_cliente");
                int fkResiduo = rs.getInt("fk_tiporesiduo");
                int fkPago = rs.getInt("fk_metodopago");
                boolean paidOut = rs.getBoolean("pagado");

                tempList.add( new ReporteCliente(folio, fkCliente, fkResiduo, fkPago, paidOut) );
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
