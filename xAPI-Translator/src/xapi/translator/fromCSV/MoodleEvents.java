/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromCSV;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author chloe
 */
public class MoodleEvents {
    
    private String eventname;
    private String component;
    private String action;
    private String target;
    private String objecttable;
    private String objectid;
    private String crud;
    private String edulevel;
    private String contextid;
    private String contextlevel;
    private String contextinstanceid;
    private String userid;
    private String courseid;
    private String relateduserid;
    private String anonymous;
    private String other;
    private String timecreated;
    private String origin;
    private String ip;
    private String realuserid;
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

    public String getObjectid() {
        return objectid;
    }

    public void setObjectid(String objectid) {
        this.objectid = objectid;
    }

    public String getCrud() {
        return crud;
    }

    public void setCrud(String crud) {
        this.crud = crud;
    }

    public String getEdulevel() {
        return edulevel;
    }

    public void setEdulevel(String edulevel) {
        this.edulevel = edulevel;
    }

    public String getContextid() {
        return contextid;
    }

    public void setContextid(String contextid) {
        this.contextid = contextid;
    }

    public String getContextlevel() {
        return contextlevel;
    }

    public void setContextlevel(String contextlevel) {
        this.contextlevel = contextlevel;
    }

    public String getContextinstanceid() {
        return contextinstanceid;
    }

    public void setContextinstanceid(String contextinstanceid) {
        this.contextinstanceid = contextinstanceid;
    }
    
    public String getUserid() {
        return this.userid;
    }
    
    public void setUserid(String userid) {
        this.userid = userid;
    }
    
    public String getCourseid() {
        return this.courseid;
    }
    
    public void setCourseID(String courseid) {
        this.courseid = courseid;
    }

    public String getRelateduserid() {
        return relateduserid;
    }

    public void setRelateduserid(String relateduserid) {
        this.relateduserid = relateduserid;
    }

    public String getAnonymous() {
        return anonymous;
    }

    public void setAnonymous(String anonymous) {
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

    public String getRealuserid() {
        return realuserid;
    }

    public void setRealuserid(String realuserid) {
        this.realuserid = realuserid;
    }
    
    public String getUsername() {
        return this.username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public Map toExtension() {
        LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
        map.put("eventname", this.eventname);
        map.put("component", this.component);
        map.put("action", this.action);
        map.put("target", this.target);
        map.put("objecttable", this.objecttable);
        map.put("objectid", this.objectid);
        map.put("crud", this.crud);
        map.put("edulevel", this.edulevel);
        map.put("contextid", this.contextid);
        map.put("contextlevel", this.contextlevel);
        map.put("contextinstanceid", this.contextinstanceid);
        map.put("userid", this.userid);
        map.put("courseid", this.courseid);
        map.put("relateduserid", this.relateduserid);
        map.put("anonymous", this.anonymous);
        map.put("other", this.other);
        map.put("timecreated", this.timecreated);
        map.put("origin", this.origin);
        map.put("ip", this.ip);
        map.put("realuserid", this.realuserid);
        return map;
    }
}
