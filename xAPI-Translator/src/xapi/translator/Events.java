/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import com.opencsv.bean.CsvBindByName;

/**
 *
 * @author chloe
 */
public class Events {
    
    @CsvBindByName
    private String eventname;
    
    @CsvBindByName
    private String component;
    
    @CsvBindByName
    private String action;
    
    @CsvBindByName
    private String target;
    
    @CsvBindByName
    private String objecttable;
    
    @CsvBindByName
    private String objectid;
    
    @CsvBindByName
    private String crud;
    
    @CsvBindByName
    private String edulevel;
    
    @CsvBindByName
    private String contextid;
    
    @CsvBindByName
    private String contextlevel;
    
    @CsvBindByName
    private String contextinstanceid;
    
    @CsvBindByName
    private String userid;
    
    @CsvBindByName
    private String courseid;
    
    @CsvBindByName
    private String relateduserid;
    
    @CsvBindByName
    private String anonymous;
    
    @CsvBindByName
    private String other;
    
    @CsvBindByName
    private String timecreated;
    
    @CsvBindByName
    private String origin;
    
    @CsvBindByName
    private String ip;
    
    @CsvBindByName
    private String realuserid;
    
    public void getInfo() {
        System.out.println(this.eventname + " " + this.userid + " " + this.ip);
    }
}
