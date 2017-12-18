/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromCSV;

import com.rusticisoftware.tincan.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;

/**
 *
 * @author chloe
 */
public class StGenerator extends EventMapping {
    private Map<String, Integer> testmap = new HashMap<>(); //only for testing
    
    /**
     * generate xAPI statements from moodle logs
     * @param logcsvFilePath
     * @param useridFilePath
     * @return 
     */
    public ArrayList generateStatements(String logcsvFilePath, String useridFilePath) {
        //reading moodle csv files (log + userid)
        System.out.println("Start reading moodle log files");
        ArrayList<MoodleEvents> eventlist = new ArrayList();
        try {
            CSVLogsReader logreader = new CSVLogsReader(logcsvFilePath, useridFilePath);
            eventlist = logreader.parseFile();
        } catch (IOException ex) {
            System.err.println("Init  StGenerator failed");
            ex.printStackTrace();
        }
        
        //generating xAPI statements from moodle log
        System.out.println("Start generate statements");
        ArrayList<Statement> statementlist = new ArrayList<>();
        
        int failcounter = 0; //only for testing
        for(MoodleEvents event : eventlist) {
            if (event.getUserid().equals("0")) {
                continue;
            }
            Statement st = new Statement();
            EventMapping eventmap = new EventMapping();
            if ((st = eventmap.generateContent(event, st)) == null) {
                failcounter++; //only for testing
                test(event); //only for testing
                continue;
            }
            setActor(event, st);
            setContext(event, st);
            setTimestamp(event, st);
            statementlist.add(st);
//            System.out.println(st.toJSON());
        } 
        
        System.out.printf("Statements generated - %d failed - %d event types%n", failcounter, testmap.size());
//        testmap.forEach((k,v)->System.out.println("Missing event : " + k + " Count : " + v));
        
        return statementlist;
    }
    
    private void test(MoodleEvents event) {
        if (!testmap.containsKey(event.getEventname())) {
            testmap.put(event.getEventname(), 1);
        } else {
            testmap.replace(event.getEventname(), (testmap.get(event.getEventname()) + 1));
        }
    }
    
    /**
     * setting actor to xAPI statement
     * @param event
     * @param st
     * @return 
     */
    private Statement setActor(MoodleEvents event, Statement st) {
        Agent agent = new Agent();
        agent.setName(event.getUsername());
        st.setActor(agent);
        return st;
    }
    
    /**
     * setting context extension (origin moodle-log data)
     * @param event
     * @param st
     * @return 
     */
    private Statement setContext(MoodleEvents event, Statement st) {
        Context context = new Context();
        Extensions extension = new Extensions();
        try {
            extension.put("moodle_standard_log", event);
            context.setExtensions(extension);
            st.setContext(context);
        } catch (URISyntaxException ex) {
            System.err.printf("Generating context failed: Event %s from user %s", event.getEventname(), event.getUserid());
            ex.printStackTrace();
        }
        return st;
    }
    
    /**
     * convert moodle timestamp to DateTime
     * @param event
     * @param st
     * @return 
     */
    private Statement setTimestamp( MoodleEvents event, Statement st) {
        st.setTimestamp(new DateTime(Integer.parseInt(event.getTimecreated()) * 1000L));
        return st;
    }
}
