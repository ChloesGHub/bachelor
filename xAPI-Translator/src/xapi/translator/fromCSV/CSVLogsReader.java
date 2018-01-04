/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromCSV;

import java.io.File;
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
     * parsing userid.csv into user hashmap POJO
     * @param rawfile
     * @return 
     */
    public HashMap parseUserIDFile(File rawfile) {
        HashMap<String, String> usermap = new HashMap<>();
        try {
            CsvBeanReader useridReader = new CsvBeanReader(new FileReader(rawfile), CsvPreference.TAB_PREFERENCE); //CSVReader
            String[] useridheader = useridReader.getHeader(true); //Headerline
            MoodleUser user;
            while((user = useridReader.read(MoodleUser.class, useridheader, USERID_PROCESSOR)) != null ) {
                usermap.put(user.getId(), user.getUser());
            }
            return usermap;
        } catch (FileNotFoundException ex) {
            System.out.println("User ID file file not found -> parseUserIDFile() failed.");
        } catch (IOException ex) {
            System.out.println("IOException -> parseUserIDFile() failed.");
        }
        return usermap;
    }
    
    /**
     * parsing log.csv into eventlist POJO
     * @param rawfile
     * @return 
     */
    public ArrayList parseLogFile(File rawfile) {
        ArrayList<MoodleEvents> eventlist = new ArrayList<>();
        try {
            CsvBeanReader logReader = new CsvBeanReader(new FileReader(rawfile), CsvPreference.STANDARD_PREFERENCE); //CSVReader
            String[] logheader = logReader.getHeader(true); //Headerline
            MoodleEvents event;
            while((event = logReader.read(MoodleEvents.class, logheader, LOG_PROCESSOR)) != null ) {
                eventlist.add(event);
            }
            System.out.println("Parsing from csv files completed.");
        } catch (FileNotFoundException ex) {
            System.out.println("Moodle log file not found -> parseLogFile() failed.");
        } catch (IOException ex) {
            System.out.println("IOException -> parseLogFile() failed.");
        }
        return eventlist;
    }
}
