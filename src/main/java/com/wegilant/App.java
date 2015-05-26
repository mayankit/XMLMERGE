package com.wegilant;

import java.util.logging.Logger;

import com.wegilant.downloaders.Downloader;
import com.wegilant.gui.PCJFrame;
import com.wegilant.utils.LoadProperties;
import com.wegilant.xml.XmlReaderMerge;

/**
 * @author Mayank
 * This is the main class for starting the application.
 *
 */
public class App 
{
	//Loading various properties from properties File
	public static final String URL  = LoadProperties.getProperty("url");
	public static final String FILES_DIRECTORY = LoadProperties.getProperty("filesLocation");
	public static final String DOWNLOADED_FILE_NAME = LoadProperties.getProperty("downloadedFile");
	public static final String EXISTING_FILE_NAME = LoadProperties.getProperty("existingFile");
	public static final String MERGED_FILE_NAME = LoadProperties.getProperty("mergedFile");
	
	private static final Logger LOGGER = Logger.getLogger(App.class.getName());
	
    public static void main( String[] args )
    {
    	LOGGER.info("App: start Executing the app");
    	
    	String downloadedFile = FILES_DIRECTORY +"/"+DOWNLOADED_FILE_NAME;//full file path
    	String existingFile = FILES_DIRECTORY +"/"+EXISTING_FILE_NAME;//full file path
    	String mergedFile = FILES_DIRECTORY +"/"+MERGED_FILE_NAME;//full file path
    	
    	//check for configuration parameters
    	if(URL != null && FILES_DIRECTORY != null && DOWNLOADED_FILE_NAME !=null && EXISTING_FILE_NAME !=null && MERGED_FILE_NAME !=null){
    	
        //download xml file from url
    	LOGGER.info("App: Before Downloading");
    	Downloader.downloadFromUrl(URL,downloadedFile);
    	LOGGER.info("App: File Successfully Downloaded");
    	
    	//merge  xml files
    	XmlReaderMerge.mergeXml(existingFile, downloadedFile,mergedFile);
    	LOGGER.info("App: Merging completed successfully");
    	
    	//showing gui for producer consumer
    	LOGGER.info("App:Launcing GUI");
    	PCJFrame pcj = new PCJFrame();
	
    	}
    	else{
    		
    		System.out.println("One or more configuration is missing");
    	}
    }
}
