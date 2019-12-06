/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledb.query;

import java.util.*;

/**
 *
 * @author petean09
 */
public class ExtendScan implements Scan {
    private Scan s;
    private Collection<String> fieldlist;
    
//    public ExtendScan(Scan s, String here, int df ) {
//        this.s = s;
//        this.fieldlist = fieldlist;
//    }
    
    public void beforeFirst() {
      s.beforeFirst();
    }
   
    public boolean next() {
      return s.next();
    }
   
    public void close() {
      s.close();
    }
   
    public Constant getVal(String fldname) {
      if (hasField(fldname))
         return s.getVal(fldname);
      else
         throw new RuntimeException("field " + fldname + " not found.");
    }
   
    public int getInt(String fldname) {
      if (hasField(fldname))
         return s.getInt(fldname);
      else
         throw new RuntimeException("field " + fldname + " not found.");
    }
   
    public String getString(String fldname) {
      if (hasField(fldname))
         return s.getString(fldname);
      else
         throw new RuntimeException("field " + fldname + " not found.");
    }
    
    public boolean hasField(String fldname) {
      return fieldlist.contains(fldname);
    }
    
}
