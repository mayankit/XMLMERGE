package com.wegilant.queue;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import com.wegilant.App;
import com.wegilant.gui.PCJFrame;

public class Producer implements Runnable {

    private final BlockingQueue<String> sharedQueue;
    private PCJFrame pcjFrame;

    public Producer(BlockingQueue<String> sharedQueue, PCJFrame pcjFrame) {
        this.sharedQueue = sharedQueue;
        this.pcjFrame = pcjFrame;
    }

    @Override
    public void run() {
    	BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(App.FILES_DIRECTORY+"/"+App.MERGED_FILE_NAME));
			String line = br.readLine();
			int i =0;
			while (line != null) {
	            try {
	            	i++;
	                pcjFrame.getProducerLabel().setText("Producer: "+ line);
	                Thread.sleep(100);
	                sharedQueue.put("Producer: "+line);
	                Logger.getLogger(Producer.class.getName()).info("Producer: MessageCount"+i);
	            } catch (InterruptedException ex) {
	                Logger.getLogger(Producer.class.getName()).log(Level.SEVERE, null, ex);
	            }
	            line = br.readLine();
	        }
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(br !=null){
			try {
				br.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
		}
    	
        
    }

}


