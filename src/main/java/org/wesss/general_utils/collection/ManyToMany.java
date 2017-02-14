package org.wesss.general_utils.collection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a collection where each key has several associated values
 * and each value can have many keys
 * @param <K> the key type
 * @param <V> the value type
 */
public class ManyToMany<K, V> {

    // TODO hamcrest matchers for these utility collections?

    /*
     * For every (K, V) pair present:
     *      keyToValue.get(K) must contain V
     *      valueToKey.get(V) must contain K
     *
     * No empty sets are contained within the maps
     */
    private HashMap<K, HashSet<V>> keyToValues;
    private HashMap<V, HashSet<K>> valueToKeys;

    public ManyToMany() {
        keyToValues = new HashMap<>();
        valueToKeys = new HashMap<>();
    }

    public boolean isEmpty() {
        return keyToValues.isEmpty();
    }

    /**
     * @return the number of key-value pairings present
     */
    public int size() {
        int sum = 0;
        for (Set<V> values : keyToValues.values()) {
            sum += values.size();
        }
        return sum;
    }

    public boolean containsKey(K key) {
        return keyToValues.containsKey(key);
    }

    public boolean containsValue(V value) {
        return valueToKeys.containsKey(value);
    }

    public Set<K> keySet() {
        return keyToValues.keySet();
    }

    public Set<V> valueSet() {
        return valueToKeys.keySet();
    }

    public Set<V> getValues(K key) {
        Set<V> resultSet = keyToValues.get(key);
        if (resultSet == null) {
            return new HashSet<>();
        } else {
            return new HashSet<>(resultSet);
        }
    }

    public Set<K> getKeys(V value) {
        Set<K> resultSet = valueToKeys.get(value);
        if (resultSet == null) {
            return new HashSet<>();
        } else {
            return new HashSet<>(resultSet);
        }
    }

    public void put(K key, V value) {
        if (!keyToValues.containsKey(key))
            keyToValues.put(key, new HashSet<>());
        keyToValues.get(key).add(value);

        if (!valueToKeys.containsKey(value))
            valueToKeys.put(value, new HashSet<>());
        valueToKeys.get(value).add(key);
    }

    public boolean remove(K key, V value) {
        Set<V> storedValues = keyToValues.get(key);
        if (storedValues == null || !storedValues.contains(value)) {
            return false;
        }

        valueToKeys.get(value).remove(key);
        if (valueToKeys.get(value).isEmpty()) {
            valueToKeys.remove(value);
        }
        keyToValues.get(key).remove(value);
        if (keyToValues.get(key).isEmpty()) {
            keyToValues.remove(key);
        }

        return true;
    }

    public boolean removeKey(K key) {
        if (!keyToValues.containsKey(key)) {
            return false;
        }

        Set<V> values = getValues(key);
        for (V value : values) {
            remove(key, value);
        }
        return true;
    }

    public boolean removeValue(V value) {
        if (!valueToKeys.containsKey(value)) {
            return false;
        }

        Set<K> keys = getKeys(value);
        for (K key : keys) {
            remove(key, value);
        }
        return true;
    }

    public void clear() {
        keyToValues.clear();
        valueToKeys.clear();
    }
}
