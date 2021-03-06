package xapi.translator.maps;

import java.util.Map;

/**
 * Container class for xAPI statements
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIStatement {
    private XAPIActor actor;
    private XAPIVerb verb;
    private Map object;
    private Map context;
    private String timestamp;

    public XAPIActor getActor() {
        return this.actor;
    }

    public void setActor(XAPIActor actor) {
        this.actor = actor;
    }

    public XAPIVerb getVerb() {
        return this.verb;
    }

    public void setVerb(XAPIVerb verb) {
        this.verb = verb;
    }

    public Map getObject() {
        return this.object;
    }

    public void setObject(Map object) {
        this.object = object;
    }

    public Map getContext() {
        return this.context;
    }

    public void setContext(Map context) {
        this.context = context;
    }

    public String getTimestamp() {
        return this.timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}