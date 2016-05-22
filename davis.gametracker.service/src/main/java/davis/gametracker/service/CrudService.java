package davis.gametracker.service;

import java.util.List;

import davis.gametracker.domain.db.EntityBase;

/**
 * Standard CRUD service provider definition
 * 
 * @param <K>
 *            type of the entity primary key
 * @param <E>
 *            JPA entity type
 * @param <V>
 *            view entity type
 * @author Adam Davis
 */
public interface CrudService<K, E extends EntityBase<E, K, ?>, V>
{
    /**
     * Creates a new record in the database with the contents of the given view
     * 
     * @param view
     *            contents to save
     * @return newly created record
     */
    public E create(V view);

    /**
     * Updates the existing record with the given primary key with the contents
     * of the given view. If there is no record with the given key, an
     * {@link IllegalArgumentException} is thrown.
     * 
     * @param primaryKey
     *            key of the record to update
     * @param view
     *            contents to update
     * @return updated record
     * @throws IllegalArgumentException
     *             if no record exists for the given key
     */
    public E update(K primaryKey, V view) throws IllegalArgumentException;

    /**
     * Loads the record with the given key. If there is no record with the given
     * key, an {@link IllegalArgumentException} is thrown.
     * 
     * @param primaryKey
     *            key of the record to load
     * @return loaded record
     * @throws IllegalArgumentException
     *             if no record exists for the given key
     */
    public E load(K primaryKey) throws IllegalArgumentException;

    /**
     * Lists all records in the database for this entity
     * 
     * @return record list
     */
    public List<E> list();
}
