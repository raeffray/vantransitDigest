package com.raeffray.csv;

import com.raeffray.reflection.ReflectionData;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * {@link CSVRowHandler} that converts a CSV row (i.e. a {@code Map<String,String>}) into an instance of the specified
 * class using reflection.
 */
public class InstanceConverter<T> implements CSVRowHandler {

    private final Class<T> clazz;
    private final InstanceHandler<? super T> instanceHandler;

    public InstanceConverter(final Class<T> clazz, final InstanceHandler<? super T> instanceHandler) {
        this.clazz = clazz;
        this.instanceHandler = instanceHandler;
    }

    @Override
    public void processLine(Map<String, String> row) {
        try {
            T instance = ReflectionData.getInstance().buildInstance(clazz, row);
            instanceHandler.processInstance(instance);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
    
    @Override
    public void processLine(Map<String, String> row, String idToSearch) {
        try {
            T instance = ReflectionData.getInstance().buildInstance(clazz, row,idToSearch);
            instanceHandler.processInstance(instance);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

	@Override
	public void notifyEOF() {
		instanceHandler.endProcess();
	}
}
