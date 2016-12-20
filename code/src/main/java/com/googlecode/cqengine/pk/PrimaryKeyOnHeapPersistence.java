package com.googlecode.cqengine.pk;

import com.googlecode.cqengine.attribute.SimpleAttribute;
import com.googlecode.cqengine.persistence.onheap.OnHeapPersistence;
import com.googlecode.cqengine.persistence.support.ObjectStore;

/**
 * ObjectStore with direct access to pk map
 */
public class PrimaryKeyOnHeapPersistence<O, A extends Comparable<A>> extends OnHeapPersistence<O, A> {

    private final int initialCapacity;
    private final float loadFactor;
    private final int concurrencyLevel;

    public PrimaryKeyOnHeapPersistence() {
        this(null, 16, 0.75F, 16);
    }

    public PrimaryKeyOnHeapPersistence(SimpleAttribute<O, A> primaryKeyAttribute) {
        this(primaryKeyAttribute, 16, 0.75F, 16);
    }

    public PrimaryKeyOnHeapPersistence(SimpleAttribute<O, A> primaryKeyAttribute, int initialCapacity, float loadFactor, int concurrencyLevel) {
        super(primaryKeyAttribute, initialCapacity, loadFactor, concurrencyLevel);
        this.initialCapacity = initialCapacity;
        this.loadFactor = loadFactor;
        this.concurrencyLevel = concurrencyLevel;
    }

    @Override
    public ObjectStore<O> createObjectStore() {
        return new PrimaryKeyOnHeapObjectStore<O, A>(initialCapacity, loadFactor, concurrencyLevel, getPrimaryKeyAttribute());
    }
}