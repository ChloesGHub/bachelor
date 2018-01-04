package xapi.translator.maps;

import java.util.Map;

/**
 * Container class for xAPI actor
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIActor {
    private String objectType;
    private String name;
    private Map account;

    public String getObjectType() {
        return this.objectType;
    }

    public void setObjectType(String objectType) {
        this.objectType = objectType;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Map getAccount() {
        return this.account;
    }

    public void setAccount(Map account) {
        this.account = account;
    }
}