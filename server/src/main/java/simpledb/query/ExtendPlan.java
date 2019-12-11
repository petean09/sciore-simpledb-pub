/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simpledb.query;

import simpledb.record.Schema;
import java.util.Collection;

/**
 *
 * @author petean09
 */
public class ExtendPlan implements Plan {
    private Plan p;
    private Schema schema = new Schema();
    
    public ExtendPlan(Plan p, String newName, int type, int length) {
        this.p = p;
        for (String fldname: p.schema().fields())
            schema.add(fldname, p.schema());
            schema.addField(newName, type, length);
   }
    
    public Scan open() {
      Scan s = p.open();
      return new ProjectScan(s, schema.fields());
   }
   
   /**
    * Estimates the number of block accesses in the projection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#blocksAccessed()
    */
   public int blocksAccessed() {
      return p.blocksAccessed();
   }
   
   /**
    * Estimates the number of output records in the projection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#recordsOutput()
    */
   public int recordsOutput() {
      return p.recordsOutput();
   }
   
   /**
    * Estimates the number of distinct field values
    * in the projection,
    * which is the same as in the underlying query.
    * @see simpledb.query.Plan#distinctValues(java.lang.String)
    */
   public int distinctValues(String fldname) {
      return p.distinctValues(fldname);
   }
   
   /**
    * Returns the schema of the projection,
    * which is taken from the field list.
    * @see simpledb.query.Plan#schema()
    */
   public Schema schema() {
      return schema;
   }
}
