package xapi.translator.maps;

import java.util.HashMap;
import java.util.Map;

/**
 * Container class for xAPI verb
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIVerb {
    private String id;
    private HashMap<String, String> display;

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public HashMap<String, String> getDisplay() {
        return this.display;
    }

    public void setDisplay(HashMap<String, String> display) {
        this.display = display;
    }

    public void setDisplay(Map display) {
        this.display = new HashMap<>();
        display.forEach((k, v) -> {
            this.display.put((String)k, (String)v);
        }
        );
    }

    public void addLanguage(String key, String value) {
        this.display.putIfAbsent(key, value);
    }
}