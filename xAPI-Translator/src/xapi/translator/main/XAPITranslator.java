package xapi.translator.main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.util.ArrayList;
import xapi.translator.fromCSV.CSVLogsReader;
import xapi.translator.fromCSV.StGenerator;
import xapi.translator.fromXAPI.XAPIReader;
import xapi.translator.maps.XAPIActorList;

/**
 *
 * @author chloe
 */
public class XAPITranslator {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        String logcsvFilePath = "D:\\Bachelorarbeit\\Rohdaten\\log-commasep-withNULL.csv";
//        String useridFilePath = "D:\\Bachelorarbeit\\Rohdaten\\userids2.csv";
//        String exportPath = "D:\\Bachelorarbeit\\Rohdaten\\xapi-sample.json";
        String jsonPath = "D:\\Bachelorarbeit\\Rohdaten\\april2.json";
        
        XAPIActorList actorList = new XAPIActorList();
        
        //parsing moodlefiles
//        StGenerator stGenerator = new StGenerator();
//        ArrayList stList = stGenerator.generateStatements(logcsvFilePath, useridFilePath);
//        XAPIWriter writer = new XAPIWriter();
//        writer.exportToJson(exportPath, stList);
        
        //parsing xapi(json) files
        XAPIReader xapireader = new XAPIReader();
        xapireader.parseFile(jsonPath);
        xapireader.extract(actorList);
    }
}
