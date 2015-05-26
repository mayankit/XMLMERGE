package com.wegilant.downloaders;

import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.logging.Logger;

import com.wegilant.gui.PCJFrame;

/**
 * @author MAYANK This class is created for downloading file from any URL
 * @version 1.0
 */
public class Downloader {

	private static final Logger LOGGER = Logger.getLogger(Downloader.class
			.getName());

	/**
	 * @param url
	 * @param fileName
	 * @return void
	 * @throws IOException
	 */
	public static void downloadFromUrl(String urlStr, String fileName) {

		LOGGER.info("Downloader::downloadFromUrl --> downloading from url " + urlStr);
		LOGGER.info("Downloader::downloading fole --> " + fileName);

		URL url = null;
		ReadableByteChannel byteChannel = null;
		FileOutputStream outputStream = null;
		try {
			url = new URL(urlStr);
			byteChannel = Channels.newChannel(url.openStream());
			outputStream = new FileOutputStream(fileName);
			outputStream.getChannel().transferFrom(byteChannel, 0,
					Long.MAX_VALUE);
			LOGGER.info("Downloader::downloadFromUrl --> downloading completed");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			LOGGER.warning(e.getMessage());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			LOGGER.warning(e.getMessage());
		} finally {
			try {
				outputStream.close();
				byteChannel.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
