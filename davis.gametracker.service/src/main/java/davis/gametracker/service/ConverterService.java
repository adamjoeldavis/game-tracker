package davis.gametracker.service;

import davis.gametracker.domain.db.EntityBase;

/**
 * Standard converter service definition. Implementors are responsible for
 * converting between view and JPA instances of a data object
 * 
 * @param <E>
 *            type of JPA entity
 * @param <V>
 *            type of view entity
 * 
 * @author Adam Davis
 */
public interface ConverterService<E extends EntityBase<E, ?, ?>, V>
{
    /**
     * Populates the given JPA entity with the contents of the given view
     * 
     * @param record
     *            JPA entity to populate
     * @param view
     *            contents to populate into the record
     */
    public void populate(E record, V view);

    /**
     * Converts the given view instance into its matching JPA entity counterpart
     * 
     * @param view
     *            view to convert
     * @return converted record
     */
    public E toRecord(V view);

    /**
     * Converts the given JPA entity into its matching view counterpart
     * 
     * @param record
     *            JPA entity to convert
     * @return converted view
     */
    public V toView(E record);
}
