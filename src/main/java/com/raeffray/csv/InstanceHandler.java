package com.raeffray.csv;

/**
 * Handles the processing of a single line of a CSV file. The line is converted into a properly populated instance of
 * {@code T}.
 */
public interface InstanceHandler<T> {
    void processInstance(T instance);
    void endProcess();
}
