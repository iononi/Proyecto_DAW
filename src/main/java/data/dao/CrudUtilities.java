package data.dao;

import model.ReporteAnonimo;

import java.sql.ResultSet;
import java.util.Collection;
import java.util.LinkedList;

/**
 * Interface with basic database operations/methods (CRUD).
 * Classes in this package may implement this interface and
 * override its methods.
 * @param <T> Database entity.
 * */
public interface CrudUtilities<T> {
    /**
     * Insert a new row on database.
     * In case of connection error, the state of database is unaltered.
     * @param entity Database entity.
     * @return {@code true} if insertion was successful, {@code false} otherwise.
     * */
    boolean insert(T entity);
    /**
     * Delete the row with the specified id.
     * @param id The id of row to delete.
     * @return {@code true} if deletion was successful, {@code false} otherwise.
     * */
    boolean delete(int id);
    /**
     * Update the specified row.
     * @param entity The entity to be updated.
     * @return {@code true} if update was successful, {@code false} otherwise.
     * */
    boolean update(T entity);
    /**
     * Retrieve the specified by id parameter.
     *
     * @param id The ID of the row to retrieve.
     * @return
     */
    LinkedList<T> select(int id);
    /**
     * Retrieve all data from a table.
     * */
    void selectAll();
    /**
     * Helper method used in select and selectAll methods
     * to retrieve data from Result Sets.
     * @param rs ResultSet from data will be retrieve.
     * @return Return a {@code LinkedList} with the rows from query.
     * */
    LinkedList<T> fetchData(ResultSet rs);
    /**
     * Prints the content of {@code list}. {@code list} is a list of {@code T} objects
     * contained in the resulting {@code ResultSet} after invoking {@code select()}
     * or {@code selectAll()} methods.
     * @param list Collection of {@code T} objects to print. The class of type {@code T} may
     *             override {@code toString()} declared in {@code Object} class.
     * @see Object
     * */
    default void printQueryResult(Collection<T> list) {
        if (list != null)
            list.forEach( element -> System.out.println(element + "\n"));
    }
}