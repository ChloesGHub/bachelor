/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import xapi.translator.mapping.XAPIEvents;

/**
 *
 * @author chloe
 */
public class XAPIReader {
    private final String xapiLogPath;
    
    public XAPIReader(String xapiLogPath) {
        this.xapiLogPath = xapiLogPath;
    }
    
    public void parseFile() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.xapiLogPath));
        Gson gsonreader = new Gson();
        ArrayList<XAPIEvents> eventlist = new ArrayList();
        String line;
        while((line = reader.readLine()) != null) {
            XAPIEvents event = gsonreader.fromJson(line, XAPIEvents.class);
            eventlist.add(event);
        }
//        for(XAPIEvents event : eventlist) {
//            System.out.println("ID: " + event.getId() + " | ST: " + event.getStatement() + " | Timestamp: " + event.getTimestamp());
//        }
    }
}
