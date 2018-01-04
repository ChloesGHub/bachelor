/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.maps;

import java.util.HashMap;
import java.util.Map;
import xapi.translator.fromCSV.MoodleEvents;

/**
 * Superclass for xAPI-Actor
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIActorList extends XAPIActor {
    private HashMap<String, XAPIActor> actorMap;

    /**
     * constructor
     */
    public XAPIActorList() {
        this.actorMap = new HashMap<>();
    }
    
    /**
     * adding actor from moodle event to actorMap
     * @param event
     * @return 
     */
    public XAPIActor addActor(MoodleEvents event) {
        XAPIActor actor = new XAPIActor();
        actor.setObjectType("Agent");
        actor.setName(event.getUsername());
        this.actorMap.put(event.getUsername(), actor);
        return actor;
    }

    /**
     * get xAPIActor object from actorMap
     * @param event
     * @return 
     */
    public XAPIActor getActor(MoodleEvents event) {
        if (!this.actorMap.containsKey(event.getUsername())) {
            return this.addActor(event);
        }
        return this.actorMap.get(event.getUsername());
    }

    /**
     * get xAPIActor object from actorMap
     * @param user
     * @return 
     */
    public XAPIActor getActor(Map user) {
        String objectType = (String)user.get("objectType");
        String name = (String)user.get("name");
        XAPIActor actor = new XAPIActor();
        if (!this.actorMap.containsKey(name)) {
            actor.setObjectType(objectType);
            actor.setName(name);
            if (user.containsKey("account")) {
                actor.setAccount((Map)user.get("account"));
            } else {
                actor.setAccount(null);
            }
            this.actorMap.put(name, actor);
        } else {
            actor = this.actorMap.get(user.get("name"));
            if (user.containsKey("account") && actor.getAccount() == null) {
                actor.setAccount((Map)user.get("account"));
            }
        }
        return actor;
    }

    /**
     * check actorMap for userid
     * @param name
     * @return 
     */
    public boolean containActor(String name) {
        return this.actorMap.containsKey(name);
    }
}