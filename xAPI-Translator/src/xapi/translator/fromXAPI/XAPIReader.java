package xapi.translator.fromXAPI;

import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import org.joda.time.DateTime;
import xapi.translator.maps.XAPIActorList;
import xapi.translator.maps.XAPIStatement;
import xapi.translator.maps.XAPIStatementList;
import xapi.translator.maps.XAPIVerbList;

/**
 * Reader for xAPI log files
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIReader {
    private ArrayList<Map> rawList;
    
    public XAPIReader() {
        this.rawList = new ArrayList<>();
    }

    /**
     * parsing xAPI log file
     * @param rawfile 
     */
    public void parseFile(File rawfile) {
        Gson gsonreader = new Gson();
        BufferedReader reader;
        System.out.println("Start parsing xAPI file");
        try {
            reader = new BufferedReader(new FileReader(rawfile));
            String line;
            while((line = reader.readLine()) != null) {
                Map raw = gsonreader.fromJson(line, Map.class);
                Map statement = (Map) raw.get("statement");
                rawList.add(statement);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("xAPI file cant't be found");
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * extracting actor, verb, object, context and timestamp from a xAPI event
     * @param statementList
     * @param actorList
     * @param verbList 
     */
    public void extract(XAPIStatementList statementList, XAPIActorList actorList, XAPIVerbList verbList) {
        int counter = 0;
        for (Map statement : rawList) {
            XAPIStatement st = new XAPIStatement();
            st.setActor(actorList.getActor((Map) statement.get("actor")));
            st.setVerb(verbList.getByMap((Map) statement.get("verb")));
            st.setObject((Map) statement.get("object"));
            st.setContext ((Map) statement.get("context"));
            st.setTimestamp(DateTime.parse((String) statement.get("timestamp")).toString());
            statementList.addStatement(st);
            counter++;
        }
        System.out.printf("%d of %d xapi events extracted.", counter, rawList.size());
    }
    
    /**
     * check if xAPI events got parsed (rawlist empty?)
     * @return 
     */
    public boolean isEmpty() {
        return this.rawList.isEmpty();
    }
}
