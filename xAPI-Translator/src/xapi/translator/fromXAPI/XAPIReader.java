/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromXAPI;

import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import xapi.translator.maps.XAPIActorList;
import xapi.translator.maps.XAPIStatement;

/**
 *
 * @author chloe
 */
public class XAPIReader {
    private ArrayList<Map> rawList = new ArrayList();
    private ArrayList<XAPIStatement> statementList = new ArrayList();

    public void parseFile(String xapiLogPath) {
        Gson gsonreader = new Gson();
        BufferedReader reader;
        System.out.println("Start reading xAPI file");
        try {
            reader = new BufferedReader(new FileReader(xapiLogPath));
            String line;
            while((line = reader.readLine()) != null) {
                Map raw = gsonreader.fromJson(line, Map.class);
                Map statement = (Map) raw.get("statement");
                rawList.add(statement);
//                System.out.println(rawActor);
        }
        } catch (FileNotFoundException ex) {
            System.out.println("xAPI file cant't be found");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    public void extract(XAPIActorList actorList) {
        System.out.println("Start extracting xAPI file");
        for (Map statement : rawList) {
            XAPIStatement st = new XAPIStatement();
            st.setActor(actorList.getActor((Map) statement.get("actor")));
            System.out.println(st);
            statementList.add(st);
            Gson gson = new Gson();
            System.out.println(gson.toJson(st));
        }
        
        
    }
}
