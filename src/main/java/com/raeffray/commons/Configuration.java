package com.raeffray.commons;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;

public class Configuration {

	public static PropertiesConfiguration getConfigurationForClass(Class<?> clazz){
		try {
			return new PropertiesConfiguration(clazz.getName()+".properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static PropertiesConfiguration getConfiguration(){
		try {
			return new PropertiesConfiguration("system.properties");
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}
}
