package com.wegilant.queue;
import java.util.concurrent.BlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.wegilant.gui.PCJFrame;

 

public class Consumer implements Runnable{

    private final BlockingQueue<String> sharedQueue;
    private PCJFrame pcjFrame;
    
    public Consumer (BlockingQueue<String> sharedQueue, PCJFrame pcjFrame) {
        this.sharedQueue = sharedQueue;
        this.pcjFrame = pcjFrame;
    }
  
    @Override
    public void run() {
    	int i=0;
        while(true){
            try {
            	  i++;
            	 pcjFrame.getConsumerLabel().setText("Consumed: "+ reverseString(sharedQueue.take()));
            	 Thread.sleep(100);
            	 Logger.getLogger(Consumer.class.getName()).info("Consumer : Message Count"+i);
            } catch (InterruptedException ex) {
                Logger.getLogger(Consumer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private String reverseString(String original){
    	String reverse = "";
    	int length = original.length();
    	
    	for ( int i = length - 1 ; i >= 0 ; i-- )
            reverse = reverse + original.charAt(i);
    	
    	return reverse;
    }
  
  
}
