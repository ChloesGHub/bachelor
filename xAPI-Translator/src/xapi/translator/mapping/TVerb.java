/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.mapping;

/**
 *
 * @author chloe
 */
public class TVerb {
    String name;
    String id;
    Display display;
    
    public class Display {
        String en;

        public String getEn() {
            return en;
        }

        public void setEn(String en) {
            this.en = en;
        }
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Display getDisplay() {
        return display;
    }

    public void LanguageMap(Display display) {
        this.display = display;
    }
}
