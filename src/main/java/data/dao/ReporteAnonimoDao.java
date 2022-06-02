package data.dao;

import model.Direccion;
import model.ReporteAnonimo;
import data.database.ConnectionDB;
import org.postgresql.util.PGobject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ReporteAnonimoDao implements CrudUtilities<ReporteAnonimo> {

    private final ConnectionDB DBC;
    private LinkedList<ReporteAnonimo> reportList;
    private LinkedList<ReporteClienteDao.User> reportAux;

    public ReporteAnonimoDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        reportList = null;
        reportAux = null;
    }

    public LinkedList<ReporteAnonimo> getReportList() {
        return reportList;
    }

    public LinkedList<ReporteClienteDao.User> getReportAux() {
        return reportAux;
    }

    @Override
    public boolean insert(ReporteAnonimo entity) {
        System.out.println("Registrando reporte anónimo...");
        DBC.setConnection(); // establecemos conexión con la base de datos
        DBC.createStmt();   // creamos el statement necesario para ejecutar queries
        try {


            String insertion_query = String.format("INSERT INTO ReporteAnonimo(nombre, apellidop, apellidom, telefono, direccion, \"Extension\"," +
                            " fk_tiporesiduo, fk_metodopago, pagado, fk_estado, correo) VALUES ('%s', '%s', '%s', '%s', ROW('%s', '%s', '%s', '%s', %d, %d, '%s', '%s', '%s'), " +
                            "'%s', %d, %d, %b, %d, '%s');",
                    entity.getNombre(), entity.getApellidop(), entity.getApellidom(), entity.getTelefono(), entity.getDir().getCodigoPostal(),
                    entity.getDir().getColonia(), entity.getDir().getCalle(), entity.getDir().getReferencias(),
                    entity.getDir().getNumeroExterior(), entity.getDir().getNumeroInterior(), entity.getDir().getCiudad(),
                    entity.getDir().getMunicipio(), entity.getDir().getEstado(), entity.getExtension(),
                    entity.getFkTipoResiduo(), entity.getFkMetodoPago(), entity.isPagado(), entity.getFk_estado(), entity.getCorreo());

            // Se ejecuta la instrucción 'insertion_query' y, en caso de ser posible la inserción, devuelve un true.
            // Devuelve false en caso contrario y por lo tanto no se pudo insertar en la BD.
            if (DBC.executeQuery(insertion_query)) {
                System.out.println("La base de datos ha sido actualizada! :D");
            } else {
                System.out.println("No se ha podido registrar el reporte :/");
                return false;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al insertar.", ex);
            return false;
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

            String delete_query = String.format("DELETE FROM ReporteAnonimo WHERE folio = %d;", id);

            if (DBC.getStatement().execute(delete_query)) // si el método execute() regresa true, se pudo eliminar.
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
    public boolean update(ReporteAnonimo entity) {
        String updateQuery = String.format("UPDATE ReporteAnonimo SET nombre = '%s', apellidop = '%s', apellidom = '%s', " +
                "telefono = '%s', \"Extension\" = '%s', fk_tiporesiduo = %d, fk_metodopago = %d, pagado = %b, " +
                "direccion = ROW('%s', '%s', '%s', '%s', %d, %d, '%s', '%s', '%s'), fk_estado = %d, correo = '%s' WHERE folio = %d;", entity.getNombre(),
                entity.getApellidop(), entity.getApellidom(), entity.getTelefono(), entity.getExtension(), entity.getFkTipoResiduo(),
                entity.getFkMetodoPago(), entity.isPagado(), entity.getDir().getCodigoPostal(), entity.getDir().getColonia(),
                entity.getDir().getCalle(), entity.getDir().getReferencias(), entity.getDir().getNumeroExterior(),
                entity.getDir().getNumeroInterior(), entity.getDir().getCiudad(), entity.getDir().getMunicipio(),
                entity.getDir().getCiudad(), entity.getFk_estado(), entity.getCorreo(), entity.getFolio());

        return ReporteClienteDao.executeUpdate(updateQuery, DBC, entity.getFolio());
    }

    @Override
    public void select(int id) {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el Statement

        System.out.printf("Consultando los datos del reporte con folio: %d...\n", id);
        String select_query = String.format("select reporteanonimo.folio, reporteanonimo.nombre, reporteanonimo.apellidop as \"Apellido Paterno\", reporteanonimo.apellidom as \"Apellido Materno\",\n" +
                "       reporteanonimo.telefono, reporteanonimo.\"Extension\", reporteanonimo.telefono, reporteanonimo.correo,\n" +
                "       concat_ws(', ', (direccion).calle, (direccion).colonia, (direccion).codigoPostal, (direccion).municipio,\n" +
                "           (direccion).estado) as \"Direccion\", tiporesiduo.tiporesiduo, metodopago.metodopago, reporteanonimo.pagado,\n" +
                "       estado.estado from reporteanonimo\n" +
                "inner join tiporesiduo on reporteanonimo.fk_tiporesiduo = tiporesiduo.residuoid\n" +
                "inner join estado on reporteanonimo.fk_estado = estado.estadoid\n" +
                "inner join metodopago on reporteanonimo.fk_metodopago = metodopago.metodoid\n" +
                "where reporteanonimo.folio = %d;", id);

        try {
            if (DBC.runQuery(select_query)) // Si el método executeQuery() regresa true, se encontró al alumno
                reportAux = ReporteClienteDao.fetchDataUser(DBC.getResultSet()); // Obtiene los datos del ResultSet y lo guarda en reportList
            if (reportAux == null || reportAux.isEmpty())
                System.out.println("No se encontró el reporte con folio: " + id);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los datos del reporte especificado");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeResultSet();
            DBC.closeStmt();
            DBC.disconnect();
        }
    }

    @Override
    public void selectAll() {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el statement
        System.out.println("Recuperando reportes anónimos registrados...\n");
        try {
            if (DBC.runQuery("SELECT * FROM ReporteAnonimo;")) // Si se pudo ejecutar la consulta
                reportList = fetchData(DBC.getResultSet()); // recupera los datos del ResultSet
            if (reportList.size() == 0)
                System.out.println("No existen reportes registrados.");
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los reportes anónimos.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.closeResultSet();
            DBC.disconnect(); // Cerramos conexión con la BD
        }
    }

    @Override
    public LinkedList<ReporteAnonimo> fetchData(ResultSet rs) {
        LinkedList<ReporteAnonimo> tempList = new LinkedList<>();
        try {
            while (rs.next()) {
                int folio = rs.getInt("folio");
                String nombre = rs.getString("nombre");
                String apellidop = rs.getString("apellidop");
                String apellidom = rs.getString("apellidom");
                String telefono = rs.getString("telefono");
                String extension = rs.getString("extension");
                int fkResiduo = rs.getInt("fk_tiporesiduo");
                int fkPago = rs.getInt("fk_metodopago");
                boolean paidOut = rs.getBoolean("pagado");
                PGobject direction = (PGobject) rs.getObject("direction");
                String myDir = direction.getValue().replaceFirst("\\(", "").replaceFirst("\\)", "");
                String[] dir = myDir.split(",");
                String codigoPostal = dir[0];
                String colonia = dir[1];
                String calle = dir[2];
                String ref = dir[3];
                short numeroExterior = Short.parseShort(dir[4]);
                short numeroInterior = Short.parseShort(dir[5]);
                String ciudad = dir[6];
                String municipio = dir[7];
                String estado = dir[8];
                short fkEstado = rs.getShort("fk_estado");
                String correo = rs.getString("correo");

                tempList.add( new ReporteAnonimo(folio, nombre, apellidop, apellidom, telefono, extension,
                        new Direccion(codigoPostal, colonia, calle, ref, numeroExterior, numeroInterior, ciudad, municipio,
                                estado), fkResiduo, fkPago, paidOut, fkEstado, correo) );
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

        String selectLastOne = "SELECT folio FROM reporteanonimo\n" +
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
