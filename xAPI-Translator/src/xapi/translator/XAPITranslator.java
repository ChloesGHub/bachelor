/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import com.rusticisoftware.tincan.*;
import java.io.IOException;
import java.net.URISyntaxException;

/**
 *
 * @author chloe
 */
public class XAPITranslator {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        String logcsvFilePath = "D:\\Bachelorarbeit\\Rohdaten\\log.csv";
        String useridFilePath = "D:\\Bachelorarbeit\\Rohdaten\\userids2.csv";
        
        CSVLogsReader logreader = new CSVLogsReader(logcsvFilePath, useridFilePath);
        logreader.parseFile();
        
        
//        //setting test log event
//        Events testevent = new Events();
//        testevent.setUserid("testuser");
//        testevent.setCourseID("testcourse");
//        testevent.setAction("testaction");
//        
//        //setting test xAPI statement
//        Agent agent = new Agent();
//        agent.setName(testevent.getUserid());
//        Verb verb = new Verb();
//        verb.setId(testevent.getAction());
//        Activity activity = new Activity();
//        activity.setId(testevent.getCourseid());
//        
//        Statement st = new Statement();
//        st.setActor(agent);
//        st.setVerb(verb);
//        st.setObject(activity);
//        
//        System.out.print(st.toJSON());
    }
    
}
