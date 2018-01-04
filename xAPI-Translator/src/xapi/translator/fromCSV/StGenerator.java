package xapi.translator.fromCSV;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.joda.time.DateTime;
import xapi.translator.maps.XAPIActorList;
import xapi.translator.maps.XAPIStatement;
import xapi.translator.maps.XAPIStatementList;
import xapi.translator.maps.XAPIVerbList;

/**
 * Class for handling Moodle events
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class StGenerator extends EventMapping {
    private ArrayList<MoodleEvents> eventlist;
    private HashMap<String,String> usermap;
    
    /**
     * constructor
     */
    public StGenerator() {
        this.eventlist = new ArrayList<>();
        this.usermap = new HashMap<>();
    }
    
    /**
     * parsing moodle event log file
     * @param logFile 
     */
    public void parseLogFile(File logFile) {
        CSVLogsReader logreader = new CSVLogsReader();
        eventlist.addAll(logreader.parseLogFile(logFile));
        System.out.println("Current raw events: " + eventlist.size());
    }
    
    /**
     * parsing user id file from moodle
     * @param userIDFile 
     */
    public void parseUIDFile(File userIDFile) {
        CSVLogsReader logreader = new CSVLogsReader();
        usermap.putAll(logreader.parseUserIDFile(userIDFile));
        System.out.println("Current UserID entrys: " + usermap.size());
    }
    
    /**
     * generate xAPI statements from moodle logs
     * @param statementList
     * @param actorList
     * @param verbList 
     */
    public void extract(XAPIStatementList statementList, XAPIActorList actorList, XAPIVerbList verbList) {
        System.out.println("Start generate statements");
        int counter = 0;
        for(MoodleEvents event : eventlist) {
            if (event.getUserid().equals("0") || !usermap.containsKey(event.getUserid())) {
                continue; //skip if userid for xapi-unique actor cannot be created
            } else {
                event.setUsername(usermap.get(event.getUserid()));
            }
            XAPIStatement st = new XAPIStatement();
            st.setActor(actorList.getActor(event));
            st.setVerb(verbList.getByName(event.getAction()));
            EventMapping eventmap = new EventMapping();
            if ((st = eventmap.generateContent(event, st)) == null) {
                continue;
            }
            st.setContext(getContext(event));
            st.setTimestamp(getTimestamp(event));
            statementList.addStatement(st);
            counter++;
        }
        System.out.printf("Statements generated - %d of %d events translated %n", counter, eventlist.size());
    }
    
    /**
     * setting context extension (origin moodle-log data)
     * @param event
     * @param st
     * @return 
     */
    private Map getContext(MoodleEvents event) {
        HashMap context = new HashMap();
        HashMap extension = new HashMap();
        extension.put("moodle_standard_log", event.toExtension());
        context.put("extensions", extension);
        return context;
    }
    
    /**
     * convert moodle timestamp to DateTime
     * @param event
     * @param st
     * @return 
     */
    private String getTimestamp(MoodleEvents event) {
        DateTime timestamp = new DateTime(Integer.parseInt(event.getTimecreated()) * 1000L);
        return timestamp.toString();
    }
}