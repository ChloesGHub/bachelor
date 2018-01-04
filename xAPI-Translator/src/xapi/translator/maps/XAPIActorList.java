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
 *
 * @author chloe
 */
public class XAPIActorList extends XAPIActor {
    private HashMap<String, XAPIActor> actorMap = new HashMap();

    public XAPIActor addActor(MoodleEvents event) {
        XAPIActor actor = new XAPIActor();
        actor.setObjectType("Agent");
        actor.setName(event.getUsername());
        return actor;
    }

    public XAPIActor getActor(MoodleEvents event) {
        if (!this.actorMap.containsKey(event.getUsername())) {
            return this.addActor(event);
        }
        return this.actorMap.get(event.getUsername());
    }

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

    public boolean containActor(String name) {
        return this.actorMap.containsKey(name);
    }
}