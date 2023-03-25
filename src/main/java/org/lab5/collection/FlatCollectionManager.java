package org.lab5.collection;

import org.lab5.models.Flat;

import java.util.*;
import java.util.stream.Collectors;

public class FlatCollectionManager extends CollectionManager<Flat> {

    public FlatCollectionManager(Set<Flat> flats) {
        super();
        HashMap<Long, Flat> map = new HashMap<>();
        for (var flat : flats) {
            map.put(flat.getKey(), flat);
        }
        this.collection = map;
    }

    public FlatCollectionManager() {
        super();
    }

    @Override
    public boolean add(Object obj) {
        if (obj instanceof Flat flat) {
            collection.put(flat.getId(), flat);
            return true;
        }
        return false;
    }

    @Override
    public boolean remove(Object obj) {
        if (obj instanceof Flat flat) {
            if (collection.containsKey(flat.getId())) {
                collection.remove(flat.getId());
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean clear() {
        collection.clear();
        return true;
    }

    @Override
    public boolean contains(Object obj) {
        if (obj instanceof Flat flat) {
            return collection.containsKey(flat.getId());
        }
        return false;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public List<Flat> all() {
        return new ArrayList<>(collection.values());
    }

    public List<Flat> filterByNumberOfRooms(int numberOfRooms) {
        List<Flat> filtered = all().stream()
                .filter(f -> f.getNumberOfRooms() == numberOfRooms)
                .collect(Collectors.toList());
        return filtered;
    }

    public String getCollectionInfo() {
        return "Collection Info:\n" + collection.getClass().getSimpleName() +
                "  Type of elements: \n" +
                "  Size: " + collection.size() + "\n";
    }

    public void insert(Long key, Flat flat) {
        this.collection.put(key, flat);
    }

    public boolean containsKey(Long key) {
        return collection.containsKey(key);
    }

    public boolean isEmpty() {
        return collection.isEmpty();
    }

    public int removeGreater(long key) {
        int count = 0;
        Iterator<Map.Entry<Long, Flat>> iterator = collection.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<Long, Flat> entry = iterator.next();
            if (entry.getKey() > key) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }

    public long removeLower(Long key) {
        int count = 0;
        Iterator<Long> iterator = collection.keySet().iterator();
        while (iterator.hasNext()) {
            Long currentKey = iterator.next();
            if (currentKey < key) {
                iterator.remove();
                count++;
            }
        }
        return count;
    }

    public HashMap<Long, Flat> getCollection() {
        return collection;
    }

    public Flat getById(int id) {
        return collection.values().stream()
                .filter(flat -> flat.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Long getMaxId() {
        return collection.values().stream()
                .mapToLong(Flat::getId)
                .max()
                .orElse(0);
    }


}
