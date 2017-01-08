package org.wesss.general_utils.collection;

import org.wesss.general_utils.exceptions.NotImplementedException;

import java.util.ArrayList;
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

    public ManyToMany() {

    }

    public boolean isEmpty() {
        throw new NotImplementedException();
    }

    /**
     * @return the number of key-value pairings present
     */
    public int size() {
        throw new NotImplementedException();
    }

    public boolean containsKey(K key) {
        throw new NotImplementedException();
    }

    public boolean containsValue(V value) {
        throw new NotImplementedException();
    }

    public Set<K> keySet() {
        throw new NotImplementedException();
    }

    public Set<V> valueSet() {
        throw new NotImplementedException();
    }

    public Set<V> getValues(K key) {
        throw new NotImplementedException();
    }

    public Set<K> getKeys(V value) {
        throw new NotImplementedException();
    }

    public void put(K key, V value) {
        throw new NotImplementedException();
    }

    public boolean remove(K key, V value) {
        throw new NotImplementedException();
    }

    public boolean removeKey(K key) {
        throw new NotImplementedException();
    }

    public boolean removeValue(V value) {
        throw new NotImplementedException();
    }

    public void clear() {
        throw new NotImplementedException();
    }
}
