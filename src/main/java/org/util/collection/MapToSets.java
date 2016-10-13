package org.util.collection;

import java.util.*;

public class MapToSets<K, V> {

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
    public ArrayList values() {
        ArrayList<V> values = new ArrayList<>();
        for (K key : keySet()) {
            values.addAll(keyToValues.get(key));
        }
        return values;
    }

    public Set<V> get(K key) {
        return new HashSet<>(keyToValues.get(key));
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
