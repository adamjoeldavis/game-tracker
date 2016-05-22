package davis.gametracker.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import davis.gametracker.domain.db.EntityBase;
import davis.gametracker.service.ConverterService;
import davis.gametracker.service.CrudService;

/**
 * Parent class of standard CRUD operation controllers. Handles all standard
 * CRUD ops. Implementors of this are expected to override every public method
 * to decorate with the appropriate spring web annotations. In most cases, the
 * body of this overridden method will simply be a call to the <b>super</b>
 * version.
 * 
 * @param <K>
 *            data type of the backing entity's primary key
 * @param <E>
 *            data type of the backing entity
 * @param <V>
 *            view of the backing entity - this is what will get sent across the
 *            pipe to the client
 * @param <S>
 *            CRUD service that handles database communications
 * @param <C>
 *            converter service that handles converting between instances of E
 *            and V
 * 
 * @author Adam Davis
 */
public abstract class CrudController<K, E extends EntityBase<E, K, ?>, V, S extends CrudService<K, E, V>, C extends ConverterService<E, V>>
{
	protected final Logger	log	= LoggerFactory.getLogger(getClass());

	/**
	 * CRUD service provider
	 */
	private S				service;

	/**
	 * Conversion service provider
	 */
	private C				converter;

	/**
	 * @param service
	 * @param converter
	 */
	public CrudController(S service, C converter)
	{
		assert (service != null);
		assert (converter != null);

		this.service = service;
		this.converter = converter;
	}

	/**
	 * Lists all records in the backing entity table
	 * 
	 * @return all records
	 */
	public List<V> list()
	{
		log.info("Method: list()");

		return service.list().stream()
				.map(converter::toView)
				.collect(Collectors.toList());
	}

	/**
	 * Loads and returns the existing record with the given primary key
	 * 
	 * @param primaryKey
	 *            key of the record to load
	 * @return loaded record
	 */
	public V get(K primaryKey)
	{
		log.info("Method: get({})", primaryKey);

		return converter.toView(service.load(primaryKey));
	}

	/**
	 * Adds a new record to the database with the given details
	 * 
	 * @param details
	 *            contents of the record to add
	 * @return successfully added record
	 */
	public V add(V details)
	{
		log.info("Method: add({})", details);

		return converter.toView(service.create(details));
	}

	/**
	 * Updates the existing record with the given key using the given details
	 * 
	 * @param primaryKey
	 *            key of the existing record
	 * @param details
	 *            data to update the existing record with
	 * @return successfully updated record
	 */
	public V update(K primaryKey, V details)
	{
		log.info("Method: update({}, {})", primaryKey, details);

		return converter.toView(service.update(primaryKey, details));
	}
}
