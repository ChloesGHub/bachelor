/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

/**
 *
 * @author chloe
 */
public class CSVLogsReader {
    
    //Filepath from log csv
    private final String logcsvFilePath;
    
    //Filepath from userids csv
    private final String useridsFilePath;
    
    /**
     * Constructor for parser
     * @param logcsvFilePath
     * @param useridsFilePath 
     */
    public CSVLogsReader(String logcsvFilePath, String useridsFilePath) {
        this.logcsvFilePath = logcsvFilePath;
        this.useridsFilePath = useridsFilePath;
    }
    
    public void parseFile() throws FileNotFoundException, IOException {
        Reader in = new FileReader(this.logcsvFilePath);
        Iterable<CSVRecord> records = CSVFormat.TDF.withFirstRecordAsHeader().withIgnoreEmptyLines().parse(in);
        List<Events> eventlist = new ArrayList<>();
        for (CSVRecord record : records) {
            Events event = new Events();
            event.setEventname(record.get("eventname"));
            event.setUserid(record.get("userid"));
            eventlist.add(event);
        }
        
        for (Events event : eventlist) {
            System.out.println(event.getEventname() + "  " + event.getUserid());
        }
    }
}
