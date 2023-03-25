package org.lab5.collection;

import org.lab5.models.Flat;

import java.util.HashMap;
import java.util.List;

/**
 * An abstract class that serves as the base for all collection managers.
 *
 * @param <T> the type of element stored in the collection
 */
public abstract class CollectionManager<T> {
    /**
     * The underlying collection.
     */
    protected HashMap<Long, T> collection = new HashMap<>();

    /**
     * Adds an element to the collection.
     *
     * @param obj the element to add
     * @return true if the element was added successfully, false otherwise
     */
    public abstract boolean add(Object obj);

    /**
     * Removes an element from the collection.
     *
     * @param obj the element to remove
     * @return true if the element was removed successfully, false otherwise
     */
    public abstract boolean remove(Object obj);

    /**
     * Clears the collection.
     *
     * @return true if the collection was cleared successfully, false otherwise
     */
    public abstract boolean clear();

    /**
     * Clears the collection.
     *
     * @return true if the collection was cleared successfully, false otherwise
     */
    public abstract boolean contains(Object obj);
    /**
     * Inserts an element with a specified key into the collection.
     *
     * @param key  the key to insert the element with
     * @param flat the element to insert
     */
    public abstract void insert(Long key, Flat flat);
    /**
     * Returns the size of the collection.
     *
     * @return the size of the collection
     */
    public abstract int size();
    /**
     * Returns a list of all elements in the collection.
     *
     * @return a list of all elements in the collection
     */
    public abstract List<Flat> all();
    /**
     * Returns a list of all elements in the collection that have the specified number of rooms.
     *
     * @param numberOfRooms the number of rooms to filter by
     * @return a list of all elements in the collection that have the specified number of rooms
     */
    public abstract List<Flat> filterByNumberOfRooms(int numberOfRooms);
    /**
     * Returns a string containing information about the collection.
     *
     * @return a string containing information about the collection
     */
    public abstract String getCollectionInfo();
    /**
     * Checks if the collection contains an element with the specified key.
     *
     * @param key the key to check for
     * @return true if the collection contains an element with the specified key, false otherwise
     */
    public abstract boolean containsKey(Long key);
    /**
     * Checks if the collection is empty.
     *
     * @return true if the collection is empty, false otherwise
     */
    public abstract boolean isEmpty();
    /**
     * Removes all elements from the collection that have a key greater than the specified key.
     *
     * @param key the key to compare against
     * @return the number of elements removed
     */
    public abstract int removeGreater(long key);
    /**
     * Removes all elements from the collection that have a key less than the specified key.
     *
     * @param key the key to compare against
     * @return the number of elements removed
     */
    public abstract long removeLower(Long key);
    /**
     * Returns the collection as a HashMap.
     *
     * @return the collection as a HashMap
     */
    public abstract HashMap<Long, T> getCollection();

    /**
     * Returns the Flat object with the specified ID.
     * @param id the ID of the Flat object to retrieve
     * @return the Flat object with the specified ID, or null if the object is not found
     */
    public abstract Flat getById(int id);

    /**
     * Returns the maximum ID in the collection.
     * @return the maximum ID in the collection
     */
    public abstract Long getMaxId();
}
