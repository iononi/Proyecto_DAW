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
    public boolean insert(ReporteCliente entity) {
        String paymentMethod = "SELECT public.metodopago.metodopago FROM reportecliente\n" +
                "INNER JOIN metodopago ON reportecliente.fk_metodopago = metodopago.metodoid\n" +
                "WHERE metodopago.metodoid = %d" + entity.getFkMetodoPago();
        System.out.println("Registrando reporte...");
        DBC.setConnection(); // establecemos conexión con la base de datos
        DBC.createStmt();   // creamos el statement necesario para ejecutar queries
        try {
            String insertion_query = String.format("INSERT INTO ReporteCliente(fk_cliente, fk_tiporesiduo, " +
                    "fk_metodopago, pagado, fk_estado) VALUES (%d, %d, %d, %b, %d)", entity.getFkCliente(), entity.getFkTipoResiduo(),
                    entity.getFkMetodoPago(), entity.getPagado(), entity.getFkEstado());

            // Se ejecuta la instrucción 'insertion_query' y, en caso de ser posible la inserción, devuelve un true.
            // Devuelve false en caso contrario y por lo tanto no se pudo insertar en la BD.
            if ( DBC.executeQuery(insertion_query) ) {
                System.out.println("La base de datos ha sido actualizada! :D");
            } else {
                System.out.println("No se ha podido registrar el reporte :/");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al insertar.", ex);
        } finally {
            DBC.closeStmt(); // Independientemente de si se pudo realizar la operación de inserción o no, con este bloque
            DBC.disconnect(); // cerramos el statement y nos desconectamos de la BD.
        }
        return true;
    }

    @Override
    public boolean delete(int id) {
        DBC.setConnection(); // establecemos conexión con la BD
        DBC.createStmt();   // creamos el statement para ejecutar queries

        System.out.println("Eliminando reporte...");

        try {

            String delete_query = String.format("DELETE FROM ReporteCliente WHERE folio = %d;", id);

            if (DBC.executeQuery(delete_query)) // si el método execute() regresa true, se pudo eliminar.
                System.out.println("Se ha eliminado el reporte :D");
            else {
                System.out.println("Ocurrió un error al eliminar el reporte :/");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar el reporte.", ex);
            return false;
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.disconnect(); // Nos desconectamos de la BD
        }
        return true;
    }

    @Override
    public boolean update(ReporteCliente entity) {
        String updateQuery = String.format("UPDATE ReporteCliente SET fk_cliente = %d, fk_tiporesiduo = %d, " +
                "fk_metodopago = %d, pagado = %b, fk_estado = %d WHERE folio = %d;", entity.getFkCliente(), entity.getFkTipoResiduo(),
                entity.getFkMetodoPago(), entity.getPagado(), entity.getFkEstado(), entity.getFolio());

        return executeUpdate(updateQuery, DBC, entity.getFolio());
    }

    public static boolean executeUpdate(String updateQuery, ConnectionDB dbc, long folio) {
        dbc.setConnection();
        dbc.createStmt();

        try {
            if ( dbc.executeQuery(updateQuery) )
                Logger.getLogger(ConnectionDB.class.getName()).log(Level.INFO,
                        "El reporte con folio " + folio + " ha sido actualizado.");
            else
                return false;
        } catch(SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al actualizar el reporte con folio " + folio,
                    ex);
            return false;
        } finally {
            dbc.closeStmt();
            dbc.disconnect();
        }
        return true;
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
                short fkEstado = rs.getShort("fk_estado");

                tempList.add( new ReporteCliente(folio, fkCliente, fkResiduo, fkPago, paidOut, fkEstado) );
            }

            return tempList;
        } catch(SQLException ex) {
            System.out.println("Error al recuperar los datos del result set.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos" +
                    " del ResultSet.", ex);
            return null;
        }
    }

    public int retrieveLastReportId() {
        int folio = -1;
        DBC.setConnection();
        DBC.createStmt();

        String selectLastOne = "SELECT folio FROM reportecliente\n" +
                "ORDER BY folio DESC\n" +
                "LIMIT 1;";

        try {
            if ( DBC.runQuery(selectLastOne) ) {
                while ( DBC.getResultSet().next() )
                    folio = DBC.getResultSet().getInt("folio");
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } finally {
            DBC.closeResultSet();
            DBC.closeStmt();
            DBC.disconnect();
        }
        return folio;
    }
}
