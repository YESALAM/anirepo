package com.billdesk.gc.models;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class GraphiteSeries {
   private String target;
   private List<List<Double>> datapoints;
public String getTarget() {
	// TODO Auto-generated method stub
	return target;
}
public void setTarget(String target) {
	this.target = target;
}

public void setDatapoints(List<List<Double>> datapoints) {
	this.datapoints = datapoints;
}
public List<List<Double>> getDatapoints() {
	// TODO Auto-generated method stub
	return datapoints;
}
}
