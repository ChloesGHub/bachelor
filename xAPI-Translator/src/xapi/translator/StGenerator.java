/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import com.rusticisoftware.tincan.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 *
 * @author chloe
 */
public class StGenerator {
    
    //FilePath for export
    String exportPath;
    
    //ArrayList with created statements
    ArrayList<Statement> statementlist = new ArrayList<>();
    
    public StGenerator(String exportPath) {
        this.exportPath = exportPath;
    }
    
    public void generateStatements(ArrayList<Events> eventlist) throws URISyntaxException {
        
        for(Events event : eventlist) {
            //generating statement
            Statement st = new Statement();
            Agent agent = new Agent();
            agent.setName(event.getUserid());
            Verb verb = new Verb();
            verb.setId(event.getAction());
            Activity activity = new Activity();
            activity.setId(event.getCourseid());
            st.setActor(agent);
            st.setVerb(verb);
            st.setObject(activity);
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
