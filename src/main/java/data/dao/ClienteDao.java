package data.dao;

import data.database.ConnectionDB;
import model.Cliente;
import model.Direccion;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ClienteDao implements CrudUtilities<Cliente> {

    private final ConnectionDB DBC;
    private LinkedList<Cliente> studentList; // Lista de alumnos.

    public ClienteDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        studentList = null;
    }

    public void insert(Cliente entity) {
        System.out.println("Insertando cliente...");
        DBC.setConnection(); // establecemos conexión con la base de datos
        DBC.createStmt();   // creamos el statement necesario para ejecutar queries
        try {

            Direccion clientDir = entity.getDir();

            String insertion_query = String.format("INSERT INTO Cliente (curp, rfc, nombre, apellidop, apellidom, correo, " +
                            "contraseña, telefono, extension, dir) VALUES " +
                    "('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', ROW('%s', '%s', '%s', '%s', %d, %d, '%s', '%s', '%s')) " +
                            "RETURNING clienteid", entity.getCurp(), entity.getRfc(), entity.getNombre(),
                    entity.getApellidop(), entity.getApellidom(), entity.getCorreo(), entity.getContrasenia(),
                    entity.getTelefono(), entity.getExtension(), clientDir.getCodigoPostal(), clientDir.getColonia(),
                    clientDir.getCalle(), clientDir.getReferencias(), clientDir.getNumeroExterior(), clientDir.getNumeroInterior(),
                    clientDir.getCiudad(), clientDir.getMunicipio(), clientDir.getEstado());

            // Se ejecuta la instrucción 'insertion_query' y, en caso de ser posible la inserción, devuelve un true.
            // Devuelve false en caso contrario y por lo tanto no se pudo insertar en la BD.
            if (DBC.getStatement().execute(insertion_query))
                System.out.println("La base de datos ha sido actualizada! :D");
            else
                System.out.println("No se ha podido insertar al cliente :/");
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

        System.out.println("Eliminando cliente...");

        try {

            String delete_query = String.format("DELETE FROM Cliente WHERE clienteid = %d;", id);

            if (DBC.getStatement().execute(delete_query)) // si el método execute() regresa true, se pudo eliminar.
                System.out.println("Se ha eliminado el cliente! :D");
            else
                System.out.println("Ocurrió un error al eliminar al cliente :/");
        } catch (SQLException ex) {
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Ocurrió un error al eliminar al cliente.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.disconnect(); // Nos desconectamos de la BD
        }
    }


    @Override
    public void update(int id) {
        DBC.setConnection();
        DBC.createStmt();


    }

    @Override
    public void select(int id) {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el Statement

        System.out.printf("Consultando datos del cliente con ID: %d...\n", id);
        String select_query = String.format("SELECT * FROM Cliente WHERE clienteid = %d;", id);

        try {
            if (DBC.runQuery(select_query)) // Si el método executeQuery() regresa true, se encontró al alumno
                studentList = fetchData(DBC.getResultSet()); // Obtiene los datos del ResultSet y lo guarda en studentList
            if (studentList.size() == 0)
                System.out.println("No se encontró al cliente con ID: " + id);
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los datos de la tabla cliente.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.disconnect();
            DBC.closeResultSet();
            DBC.closeStmt();
        }
    }


    @Override
    public void selectAll() {
        DBC.setConnection(); // Establecemos conexión con la BD
        DBC.createStmt();   // Creamos el statement
        System.out.println("Recuperando los datos de los 'Clientes'...\n");
        try {
            if (DBC.runQuery("SELECT * FROM Cliente;")) // Si se pudo ejecutar la consulta
                studentList = fetchData(DBC.getResultSet()); // recupera los datos del ResultSet
            if (studentList.size() == 0)
                System.out.println("No se ha registrado ningún cliente.");
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los datos de la tabla cliente.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos.", ex);
        } finally {
            DBC.closeStmt(); // Cerramos el statement
            DBC.closeResultSet();
            DBC.disconnect(); // Cerramos conexión con la BD
        }
    }

    @Override
    public LinkedList<Cliente> fetchData(ResultSet rs) {
        LinkedList<Cliente> tempList = new LinkedList<>();
        try {
            while (rs.next()) { // Mientras haya un registro en el ResultSet, obtén los datos del alumno
                int clienteID = rs.getInt("clienteid");
                String curp = rs.getString("curp");
                String rfc = rs.getString("rfc");
                String nombre = rs.getString("nombre");
                String apellidop = rs.getString("apellidop");
                String apellidom = rs.getString("apellidom");
                String correo = rs.getString("correo");
                String contrasenia = rs.getString("contraseña");
                String telefono = rs.getString("telefono");
                String extension = rs.getString("extension");
                Direccion direccion = (Direccion) rs.getObject("direction");


                Cliente alumno = new Cliente(clienteID, curp, rfc, nombre, apellidop, apellidom, correo, contrasenia,
                        extension, telefono, direccion);

                tempList.add(alumno);
            }

            return tempList;
        } catch (SQLException ex) {
            System.out.println("Error al recuperar los datos del result set.");
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "No se pudo recuperar los datos" +
                    " del ResultSet.", ex);
            return null;
        }
    }
}
