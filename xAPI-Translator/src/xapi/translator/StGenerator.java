/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import xapi.translator.mapping.MoodleEvents;
import com.google.gson.Gson;
import com.rusticisoftware.tincan.*;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import org.joda.time.DateTime;
import xapi.translator.mapping.TVerb;

/**
 *
 * @author chloe
 */
public class StGenerator {
    
    //FilePath for export
    String exportPath;
    String verblistPath;
    static HashMap<String,TVerb> verbList = new HashMap<>(); 
    
    //ArrayList with created statements
    ArrayList<Statement> statementlist = new ArrayList<>();
    
    public StGenerator(String verblistPath, String exportPath) {
        this.exportPath = exportPath;
        this.verblistPath = verblistPath;
    }
    
    public void init() throws FileNotFoundException, IOException {
        BufferedReader reader = new BufferedReader(new FileReader(this.verblistPath));
        Gson gson = new Gson();
        String line;
        while((line = reader.readLine()) != null){
            TVerb verb = gson.fromJson(line, TVerb.class);
            verbList.put(verb.getName(), verb);
        }
    }
    
    public void generateStatements(ArrayList<MoodleEvents> eventlist) throws URISyntaxException {
        
        System.out.println("Start generate statements");
        
        for(MoodleEvents event : eventlist) {

            //actor
            Agent agent = new Agent();
            agent.setName(event.getUsername());
            
            //verb
            TVerb tverb = verbList.get(event.getAction());
            LanguageMap lmap = new LanguageMap();
            lmap.put("en", tverb.getDisplay().getEn());
            Verb verb = new Verb();
            verb.setId(event.getAction());
            verb.setId(tverb.getId());
            verb.setDisplay(lmap);
            //System.out.println(verbList.get(event.getAction()).getDisplay().get("en"));
            
            //object
            Activity activity = new Activity();
            activity.setId(event.getCourseid());
            
            //generating statement
            Statement st = new Statement();
            st.setActor(agent);
            st.setVerb(verb);
            st.setObject(activity);
            st.setTimestamp(new DateTime(event.getTimecreated() * 1000L));
            statementlist.add(st);
            //System.out.println(st.toJSON());
        } 
        System.out.println("Statements generated");
    }
    
    public void exportToJson() throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(exportPath));
        for(Statement st : statementlist) {
            writer.write(st.toJSON());
            writer.newLine();
        }
        System.out.println("Json File erstellt.");
    }
}
