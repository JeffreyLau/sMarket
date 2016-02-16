package org.splay.base;

/**
 * Created by jeffrey on 16-2-14.
 */
public enum BaseLoadState {
    NONE(0), LOADING(1),
    EMPTY(2), ERROR(3),
    SUCCESS(4);

    public int getState() {
        return state;
    }
    private final int state;

    BaseLoadState(int state) {
        this.state = state;
    }
}
