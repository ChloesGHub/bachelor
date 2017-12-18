/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromCSV;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.prefs.CsvPreference;

/**
 *
 * @author chloe
 */
public class CSVLogsReader {
    
    //Filepath from log csv
    private final String logcsvFilePath;
    
    //Filepath from userids csv
    private final String useridsFilePath;
    
    private static final CellProcessor[] LOG_PROCESSOR = new CellProcessor[] {
        new NotNull(), //eventname
        new NotNull(), //component
        new NotNull(), //action
        null, //target
        null, //objecttable
        null, //objectid
        null, //crud
        null, //edulevel
        null, //contextid
        null, //contextlevel
        null, //contextinstanceid
        null, //userid
        null, //courseid
        null, //relateduserid
        null, //anonymous
        null, //other
        null, //timecreated
        null, //origin
        null, //ip
        null, //realuserid
    };
    
    private static final CellProcessor[] USERID_PROCESSOR = new CellProcessor[] {
        new NotNull(), //userid
        new NotNull() //username
    };
    
    /**
     * Constructor for parser
     * @param logcsvFilePath
     * @param useridsFilePath 
     */
    public CSVLogsReader(String logcsvFilePath, String useridsFilePath) {
        this.logcsvFilePath = logcsvFilePath;
        this.useridsFilePath = useridsFilePath;
    }
    
    public ArrayList parseFile() throws FileNotFoundException, IOException {
        //parsing userid.csv into user hashmap POJO
        CsvBeanReader useridReader = new CsvBeanReader(new FileReader(useridsFilePath), CsvPreference.TAB_PREFERENCE); //CSVReader
        String[] useridheader = useridReader.getHeader(true); //Headerline
        HashMap<String, String> userlist;
        userlist = new HashMap<>();
        MoodleUser user;
        while((user = useridReader.read(MoodleUser.class, useridheader, USERID_PROCESSOR)) != null ) {
            userlist.put(user.getId(), user.getUser());
        }
        
        //parsing log.csv into eventlist POJO
        CsvBeanReader logReader = new CsvBeanReader(new FileReader(logcsvFilePath), CsvPreference.STANDARD_PREFERENCE); //CSVReader
        String[] logheader = logReader.getHeader(true); //Headerline
        ArrayList<MoodleEvents> eventlist = new ArrayList<>();
        MoodleEvents event;
        while((event = logReader.read(MoodleEvents.class, logheader, LOG_PROCESSOR)) != null ) {
            String userid = event.getUserid();
            if(userlist.containsKey(userid)) {
                event.setUsername(userlist.get(userid));
            }
            eventlist.add(event);
            //System.out.println(event);
        }
        System.out.println("Parsing from csv files completed.");
        return eventlist;
    }
}
