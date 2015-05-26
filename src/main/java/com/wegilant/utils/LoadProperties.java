package com.wegilant.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Logger;

import com.wegilant.App;


/**
 * @author MAYANK
 * This is a singleton class which load properties
 * file only once
 * @version 1.0
 */
public class LoadProperties {
    
	private static final Logger LOGGER = Logger.getLogger(LoadProperties.class.getName());
	public static final String PROPERTIES_FILE = "config.properties";
	public static Properties prop = getInstance();
	

	private LoadProperties(){

	}
    
	private static Properties getInstance() {
		LOGGER.info("LoadProperties::getInstance()-->Initializing config.properties");
		if(prop == null){
			prop = new Properties();
			InputStream input = null;

			try{
				input = LoadProperties.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE);
				prop.load(input);
			}catch (IOException ex) {
				ex.printStackTrace();
			} finally {
				if (input != null) {
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		}

		return prop;
	}

	
	/**
	 * @param propertyName
	 * @return String
	 */
	public static String getProperty(String propertyName){

		return prop.getProperty(propertyName);

	}
}



