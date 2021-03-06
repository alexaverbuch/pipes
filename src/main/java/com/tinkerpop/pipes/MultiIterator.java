package com.tinkerpop.pipes;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class MultiIterator<T> implements Iterator<T> {

    private final Iterator<Iterator<T>> iterators;
    private Iterator<T> currentIterator = null;

    public MultiIterator(final Iterator<T>... iterators) {
        this(Arrays.asList(iterators));

    }

    public MultiIterator(final List<Iterator<T>> iterators) {
        this.iterators = iterators.iterator();
        if (this.iterators.hasNext())
            this.currentIterator = this.iterators.next();
    }

    public void remove() {
        throw new UnsupportedOperationException();
    }

    public T next() {
        while (true) {
            if (this.currentIterator.hasNext()) {
                return this.currentIterator.next();
            } else {
                if (this.iterators.hasNext()) {
                    this.currentIterator = this.iterators.next();
                } else {
                    throw new NoSuchElementException();
                }
            }
        }
    }

    public boolean hasNext() {
        while (true) {
            if (null != this.currentIterator && this.currentIterator.hasNext()) {
                return true;
            } else if (this.iterators.hasNext()) {
                this.currentIterator = iterators.next();
            } else {
                return false;
            }
        }
    }


}