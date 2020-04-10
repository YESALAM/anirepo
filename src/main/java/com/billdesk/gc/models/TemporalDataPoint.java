package com.billdesk.gc.models;


import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


@Getter
@Setter
public class TemporalDataPoint {
   private long time;
   private double value;
   private String name = "";


   @Override public String toString() {
      return "TemporalDataPoint{" +
              "time=" + time +
              ", value=" + value +
              ", name='" + name + '\'' +
              '}';
   }


   public String formatTime() {
      DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
      return dateFormat.format(new Date(time * 1000L));
   }


public void setValue(double value2) {
	// TODO Auto-generated method stub
	this.value = value2;
	
}


public void setTime(long timestamp) {
	// TODO Auto-generated method stub
	this.time = timestamp;
	
}


public String getName() {
	return name;
}


public void setName(String name) {
	this.name = name;
}


public long getTime() {
	return time;
}


public double getValue() {
	return value;
}


}
