package com.tinkerpop.pipes.filter;


/**
 * A ComparisonFilterPipe will allow or disallow objects that pass through it depending on some implemented comparison criteria.
 *
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public interface ComparisonFilterPipe<S, T> extends FilterPipe<S> {

    public enum Filter {
        EQUAL, NOT_EQUAL, GREATER_THAN, LESS_THAN, GREATER_THAN_EQUAL, LESS_THAN_EQUAL
    }

    public boolean compareObjects(T leftObject, T rightObject);

}
