package org.util.collection;

import java.util.*;

public class MapToSets<K, V> {

    /*
     * valueToKey != null
     * for each (value --> key) pair in valueToKey
     * 		keyToValue.get(key) contains value
     *
     * keyToValue != null
     * for each (key --> values) pair in keyToValue.keyset()
     * 		key >= 0
     * 		values != null
     * 		values.isEmpty
     * 		for each value being stored within values
     * 			value != null
     * 			value also exists in priorityToValue
     * 			value also exists in valueToKey, valueToPriority, priorityToValue
     * 			value does not exist anywhere else in keyToValue
     */
    private HashMap<K, HashSet<V>> keyToValues;
    private HashMap<V, K> valueToKey;

    public MapToSets() {
        keyToValues = new HashMap<>();
        valueToKey = new HashMap<>();
    }

    public boolean isEmpty() {
        return keyToValues.isEmpty();
    }

    public boolean containsKey(K key) {
        return keyToValues.containsKey(key);
    }

    public boolean containsValue(V value) {
        return valueToKey.containsKey(value);
    }

    public Set<K> keySet() {
        return keyToValues.keySet();
    }

    /**
     * Duplicates allowed
     */
    public ArrayList<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (K key : keySet()) {
            values.addAll(keyToValues.get(key));
        }
        return values;
    }

    public Set<V> get(K key) {
        Set<V> resultSet = keyToValues.get(key);
        if (resultSet == null) {
            return null;
        } else {
            return new HashSet<>(resultSet);
        }
    }

    public K getKey(V value) {
        return valueToKey.get(value);
    }

    public void put(K key, V value) {
        if (!keyToValues.containsKey(key))
            keyToValues.put(key, new HashSet<>());
        keyToValues.get(key).add(value);

        valueToKey.put(value, key);
    }

    public boolean remove(K key, V value) {
        valueToKey.remove(value);
        boolean wasRemoved = keyToValues.get(key).remove(value);
        if (keyToValues.get(key).isEmpty()) {
            keyToValues.remove(key);
        }

        return wasRemoved;
    }

    public void clear() {
        keyToValues.clear();
        valueToKey.clear();
    }
}
