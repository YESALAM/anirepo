package com.billdesk.gc;


import lombok.Getter;
import lombok.Setter;

import java.util.Map;


@Getter
@Setter
public class GraphiteMetricNode {
   private int leaf;
   private Map<String,String> context;
   private String text;
   private int expandable;
   private String id;
   private int allowChildren;
}
