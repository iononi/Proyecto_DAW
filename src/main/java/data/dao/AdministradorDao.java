package data.dao;

import data.database.ConnectionDB;
import model.Administrador;
import model.Direccion;
import org.postgresql.util.PGobject;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdministradorDao implements CrudUtilities<Administrador> {

    private LinkedList<Administrador> adminList;
    private final ConnectionDB DBC;

    public AdministradorDao() {
        DBC = new ConnectionDB("basura", "postgres", "lalo123");
        adminList = null;
    }

    @Override
    public boolean insert(Administrador entity) {
        System.out.println("Registrando administrador...");
        String insertionQuery = String.format("INSERT INTO Administrador (curp, rfc, nombre, apellidop, apellidom, correo, " +
                        "contrasenia, telefono, \"Extension\", direction) VALUES ('%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', " +
                        "ROW('%s', '%s', '%s', '%s', %d, %d, '%s', '%s', '%s'))", entity.getCurp(), entity.getRfc(),
                entity.getNombre(), entity.getApellidop(), entity.getApellidom(), entity.getCorreo(), entity.getContrasenia(),
                entity.getTelefono(), entity.getExtension(), entity.getDir().getCodigoPostal(), entity.getDir().getColonia(),
                entity.getDir().getCalle(), entity.getDir().getReferencias(), entity.getDir().getNumeroExterior(),
                entity.getDir().getNumeroInterior(), entity.getDir().getCiudad(), entity.getDir().getMunicipio(),
                entity.getDir().getEstado());

        DBC.setConnection();
        DBC.createStmt();

        try {
            if (DBC.executeQuery(insertionQuery))
                System.out.println("Nuevo administrador: " + entity.getNombre());
            else {
                System.out.println("No se pudo registrar al administrador.");
                return false;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al registrar administrador.", ex);
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
        if (adminList == null || adminList.size() == 0) {
            System.out.println("No existe registro del administrador con ID: " + id);
            return false;
        }
        String deletionQuery = String.format("DELETE FROM Administrador WHERE adminid = %d;", id);
        DBC.setConnection();
        DBC.createStmt();

        try {
            if (DBC.executeQuery(deletionQuery))
                System.out.println("Se ha eliminado al administrador.");
            else {
                System.out.println("Ocurrió un error al eliminar al administrador.");
                return false;
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al eliminar al administrador.", ex);
            return false;
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
         return true;
    }

    @Override
    public void update(Administrador entity) {
        String updateQuery = String.format("UPDATE Administrador SET curp = '%s', rfc = '%s', nombre = '%s', apellidop = '%s', " +
                        "apellidom = '%s', correo = '%s', telefono = '%s', \"Extension\" = '%s', direction = ('%s', '%s', '%s', '%s', %d, %d, '%s', '%s', '%s') " +
                        "WHERE clienteid = %d",
                entity.getCurp(), entity.getRfc(), entity.getNombre(), entity.getApellidop(), entity.getApellidom(),
                entity.getCorreo(), entity.getTelefono(), entity.getExtension(), entity.getDir().getCodigoPostal(),
                entity.getDir().getColonia(), entity.getDir().getCalle(), entity.getDir().getReferencias(),
                entity.getDir().getNumeroExterior(), entity.getDir().getNumeroInterior(), entity.getDir().getCiudad(),
                entity.getDir().getMunicipio(), entity.getDir().getEstado(), entity.getAdminId());
        DBC.setConnection();
        DBC.createStmt();

        try {
            if (DBC.executeQuery(updateQuery))
                System.out.println("Los datos del cliente " + entity.getNombre() + " han sido actualizados.");
            else
                System.out.println("Ocurrió un error al actualizar los datos del cliente " + entity.getNombre());
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al actualizar administrador.", ex);
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
    }

    @Override
    public void select(int id) {
        DBC.setConnection();
        DBC.createStmt();

        System.out.println("Consultando los datos del administrador con ID: " + id);
        String selectionQuery = String.format("SELECT * FROM Administrador WHERE adminid = %d;", id);

        try {
            if (DBC.runQuery(selectionQuery))
                adminList = fetchData(DBC.getResultSet());
            if (adminList == null || adminList.size() == 0)
                System.out.println("No se encontró al administrador con ID: " + id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al buscar el administrador.", ex);
        } finally {
            DBC.closeStmt();
            DBC.disconnect();
        }
    }

    @Override
    public void selectAll() {
        DBC.setConnection();
        DBC.createStmt();
        System.out.println("Recuperando los registros de los administradores...");
        try {
            if (DBC.runQuery("SELECT * FROM Administrador"))
                adminList = fetchData(DBC.getResultSet());
            if (adminList == null || adminList.isEmpty())
                System.out.println("No se encontraron registros de administradores.");
        } catch (SQLException ex) {
            ex.printStackTrace();
            Logger.getLogger(ConnectionDB.class.getName()).log(Level.SEVERE, "Error al consultar los administradores.", ex);
        }
    }

    @Override
    public LinkedList<Administrador> fetchData(ResultSet rs) {
        LinkedList<Administrador> tempList = new LinkedList<>();
        try {
            while (rs.next()) { // Mientras haya un registro en el ResultSet, obtén los datos del cliente
                int clienteID = rs.getInt("adminid");
                String curp = rs.getString("curp");
                String rfc = rs.getString("rfc");
                String nombre = rs.getString("nombre");
                String apellidop = rs.getString("apellidop");
                String apellidom = rs.getString("apellidom");
                String correo = rs.getString("correo");
                String contrasenia = rs.getString("contraseña");
                String telefono = rs.getString("telefono");
                String extension = rs.getString("extension");

                PGobject direction = (PGobject) rs.getObject("direction");
                String myDir = direction.getValue().replaceFirst("\\(", "").replaceFirst("\\)", "");
                myDir = myDir.replaceAll("\"", "");
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

                Direccion direccion = new Direccion(codigoPostal, colonia, calle, ref, numeroExterior, numeroInterior,
                        ciudad, municipio, estado);


                Administrador admin = new Administrador(clienteID, curp, rfc, nombre, apellidop, apellidom, correo, contrasenia,
                        extension, telefono, direccion);
                tempList.add(admin);
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
