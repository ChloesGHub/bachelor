/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.maps;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author chloe
 */
public class XAPIActorList extends XAPIActor{
    private HashMap<String, XAPIActor> actorMap;
    
    public XAPIActorList() {
        this.actorMap = new HashMap();
    }
    
    public XAPIActor getActor(String name) {
        return actorMap.get(name);
    }
    
    public XAPIActor getActor(Map user) {
        String name = (String) user.get("name");
        XAPIActor actor = new XAPIActor();
        if (!actorMap.containsKey(name)) {
            actor.setName(name);
            if (user.containsKey("account")) {
                actor.setAccount((Map) user.get("account"));
            } else {
                actor.setAccount(null);
            }
            this.actorMap.put(name, actor);
        } else {
            actor = actorMap.get(user.get("name"));
            if (user.containsKey("account") && (actor.getAccount() == null)) {
                actor.setAccount((Map) user.get("account"));
            }
        }
        return actor;
    }
}
