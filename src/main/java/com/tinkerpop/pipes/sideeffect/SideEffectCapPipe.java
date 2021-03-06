package com.tinkerpop.pipes.sideeffect;

import com.tinkerpop.pipes.AbstractPipe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The SideEffectCapPipe will yield an E that is the side effect of the provided SideEffectPipe.
 * This is useful for when the side effect of a Pipe is desired in a computational stream.
 *
 * @author Marko A. Rodriguez (http://markorodriguez.com)
 */
public class SideEffectCapPipe<S, T> extends AbstractPipe<S, T> {

    private final SideEffectPipe<S, ?, T> pipeToCap;
    private boolean alive = true;

    public SideEffectCapPipe(SideEffectPipe<S, ?, T> pipeToCap) {
        this.pipeToCap = pipeToCap;
    }

    public void setStarts(Iterator<S> starts) {
        this.pipeToCap.setStarts(starts);
    }

    protected T processNextStart() {
        if (this.alive) {
            while (this.pipeToCap.hasNext()) {
                this.pipeToCap.next();
            }
            this.alive = false;
            return this.pipeToCap.getSideEffect();
        } else {
            throw new NoSuchElementException();
        }
    }
}
