package xapi.translator.maps;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Superclass for XAPIVerb
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIVerbList extends XAPIVerb {
    private HashMap<String, XAPIVerb> idMap;
    private HashMap<String, XAPIVerb> nameMap;

    /**
     * constructor
     */
    public XAPIVerbList() {
        this.idMap = new HashMap<>();
        this.nameMap = new HashMap<>();
    }
    
    /**
     * adding new verb to both verbmaps from xAPIVerb object
     * @param verb 
     */
    private void addVerb(XAPIVerb verb) {
        this.idMap.put(verb.getId(), verb);
        verb.getDisplay().forEach((k, v) -> {
            this.nameMap.put(v, verb);
        }
        );
    }

    /**
     * adding new verb to both verbmaps from map object 
     * @param verb 
     */
    private void addVerb(Map verb) {
        String id = (String)verb.get("id");
        Map display = (Map)verb.get("display");
        XAPIVerb newVerb = new XAPIVerb();
        newVerb.setId(id);
        newVerb.setDisplay(display);
        if (!this.idMap.containsKey(id)) {
            this.idMap.put(id, newVerb);
            display.forEach((k, v) -> {
                if (!this.nameMap.containsKey((String)v)) {
                    this.nameMap.put((String)v, newVerb);
                }
            }
            );
        } else {
            display.forEach((k, v) -> {
                this.idMap.get(id).addLanguage((String)k, (String)v);
            }
            );
        }
        display.forEach((k, v) -> {
            if (!this.nameMap.containsKey((String)v)) {
                this.nameMap.put((String)v, newVerb);
            }
        }
        );
    }

    /**
     * get XAPIVerb by verb id
     * @param id
     * @return 
     */
    public XAPIVerb getById(String id) {
        if (!this.idMap.containsKey(id)) {
            System.out.println("Verb not found: " + id);
            return null;
        }
        return this.idMap.get(id);
    }

    /**
     * get XAPIVerb by verb name
     * @param name
     * @return 
     */
    public XAPIVerb getByName(String name) {
        if (!this.nameMap.containsKey(name)) {
            System.out.println("Verb not found: " + name);
            return null;
        }
        return this.nameMap.get(name);
    }

    /**
     * get XAPIVerb from map object
     * @param verb
     * @return 
     */
    public XAPIVerb getByMap(Map verb) {
        String id = (String)verb.get("id");
        if (!this.idMap.containsKey(id)) {
            this.addVerb(verb);
        }
        return this.getById(id);
    }

    /**
     * initializing basic verb list for translating moodle events
     * @param rawfile 
     */
    public void init(File rawfile) {
        Gson gson = new Gson();
        try {
            String line;
            BufferedReader reader = new BufferedReader(new FileReader(rawfile));
            while ((line = reader.readLine()) != null) {
                XAPIVerb verb = (XAPIVerb)gson.fromJson(line, XAPIVerb.class);
                this.addVerb(verb);
            }
        }
        catch (FileNotFoundException ex) {
            System.out.println("Missing verblist");
            ex.printStackTrace();
        }
        catch (IOException ex) {
            System.out.println("Initializing verblist failed");
            ex.printStackTrace();
        }
    }
}