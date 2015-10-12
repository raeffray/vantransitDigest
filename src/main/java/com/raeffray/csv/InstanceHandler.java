package com.raeffray.csv;

/**
 * Created by mau on 11/10/15.
 */
public interface InstanceHandler<T> {
    void processInstance(T instance);
}
