package com.avinty.hr.dto;

public abstract class Dto<T> {
    private T id;

    public Dto() {
    }

    public T getId() {
        return this.id;
    }

    public void setId(final T id) {
        this.id = id;
    }
}
