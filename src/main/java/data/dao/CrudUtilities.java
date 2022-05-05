package data.dao;

import model.Cliente;

import java.sql.ResultSet;
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
     * */
    void insert(T entity);
    /**
     * Delete the row with the specified id.
     * @param id The id of row to delete.
     * */
    void delete(int id);
    /**
     * Update the row specified by id parameter.
     * @param id ID of row to update.
     * */
    void update(int id);
    /**
     * Retrieve the specified by id parameter.
     * @param id The ID of the row to retrieve.
     * */
    void select(int id);
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
     * @param list List of {@code T} objects to print. The class of type {@code T} may
     *             override {@code toString()} declared in {@code Object} class.
     * @see Object
     * */
    void printQueryResult(LinkedList<T> list);
}