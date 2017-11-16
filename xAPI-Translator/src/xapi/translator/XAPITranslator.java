/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

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
        String logcsvFilePath = "D:\\Bachelorarbeit\\Rohdaten\\log2.csv";
        String useridFilePath = "D:\\Bachelorarbeit\\Rohdaten\\userids2.csv";
        String exportPath = "D:\\Bachelorarbeit\\Rohdaten\\xapi-sample.json";
        
        CSVLogsReader logreader = new CSVLogsReader(logcsvFilePath, useridFilePath);
        //logreader.parseFile();
        StGenerator stGenerator = new StGenerator(exportPath);
        stGenerator.generateStatements(logreader.parseFile());
        stGenerator.exportToJson();
    }
    
}
