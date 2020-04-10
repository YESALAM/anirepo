package com.billdesk.gc;

import java.util.Random;

public class MultipleInsert {
	static String URL = "localhost";
	
public static void main(String []args) {
	String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            + "abcdefghijklmnopqrstuvxyz"; 
	int n=10;
	 String key = null;
	 int value=0;
	StringBuilder sb = new StringBuilder(n);
	for (int i = 0; i < n; i++) { 
		  
        // generate a random number between 
        // 0 to AlphaNumericString variable length 
        int index = (int)(AlphaNumericString.length()* Math.random()); 

        // add Character one by one in end of sb 
        sb.append(AlphaNumericString.charAt(index)); 
        key = sb.toString();
       
        Random rand = new Random(); 
        value = rand.nextInt(10000); 
    } 
	GraphiteClient client = new SimpleGraphiteClient(URL, 2003);
    client.sendMetric("chen0040.test.metric", 4711);
	 System.out.println(key);
	 System.out.println(value);
}
	
	
}
