package org.wesss.general_utils.collection;

import org.wesss.general_utils.exceptions.NotImplementedException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * Represents a collection where each key has exactly one value and vice versa.
 * When duplicate keys or values are added, any previous pairings are overwritten.
 * @param <K> the key type
 * @param <V> the value type
 */
public class OneToOne<K, V> {

    public OneToOne() {

    }

    public boolean isEmpty() {
        throw new NotImplementedException();
    }

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

    public V getValue(K key) {
        throw new NotImplementedException();
    }

    public K getKey(V value) {
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
