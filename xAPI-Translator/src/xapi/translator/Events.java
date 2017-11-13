/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

/**
 *
 * @author chloe
 */
public class Events {
    
    private String eventname;
    private String component;
    private String action;
    private String target;
    private String objecttable;
    private int objectid;
    private String crud;
    private int edulevel;
    private int contextid;
    private int contextlevel;
    private int contextinstanceid;
    private int userid;
    private int courseid;
    private int relateduserid;
    private int anonymous;
    private String other;
    private String timecreated;
    private String origin;
    private String ip;
    private int realuserid;
    //from userid csv
    private String username;
    
    public void setEventname(String eventname) {
        this.eventname = eventname;
    }
    
    public String getEventname() {
        return this.eventname;
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public void setAction(String action) {
        this.action = action;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getObjecttable() {
        return objecttable;
    }

    public void setObjecttable(String objecttable) {
        this.objecttable = objecttable;
    }

    public int getObjectid() {
        return objectid;
    }

    public void setObjectid(int objectid) {
        this.objectid = objectid;
    }

    public String getCrud() {
        return crud;
    }

    public void setCrud(String crud) {
        this.crud = crud;
    }

    public int getEdulevel() {
        return edulevel;
    }

    public void setEdulevel(int edulevel) {
        this.edulevel = edulevel;
    }

    public int getContextid() {
        return contextid;
    }

    public void setContextid(int contextid) {
        this.contextid = contextid;
    }

    public int getContextlevel() {
        return contextlevel;
    }

    public void setContextlevel(int contextlevel) {
        this.contextlevel = contextlevel;
    }

    public int getContextinstanceid() {
        return contextinstanceid;
    }

    public void setContextinstanceid(int contextinstanceid) {
        this.contextinstanceid = contextinstanceid;
    }
    
    public int getUserid() {
        return this.userid;
    }
    
    public void setUserid(int userid) {
        this.userid = userid;
    }
    
    /**
     * convert NULL Values of userids to 0
     * @param userid 
     */
    public void setUserid(String userid) {
        if ("NULL".equals(userid)) {
            this.userid = 0;
        }
        else {
            this.userid = Integer.parseInt(userid);
        }
    }
    
    public int getCourseid() {
        return this.courseid;
    }
    
    public void setCourseID(int courseid) {
        this.courseid = courseid;
    }

    public int getRelateduserid() {
        return relateduserid;
    }

    public void setRelateduserid(int relateduserid) {
        this.relateduserid = relateduserid;
    }

    public int getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(int anonymous) {
        this.anonymous = anonymous;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public String getTimecreated() {
        return timecreated;
    }

    public void setTimecreated(String timecreated) {
        this.timecreated = timecreated;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getRealuserid() {
        return realuserid;
    }

    public void setRealuserid(int realuserid) {
        this.realuserid = realuserid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
}
