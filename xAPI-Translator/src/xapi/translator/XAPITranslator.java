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
     * @throws java.net.URISyntaxException
     */
    public static void main(String[] args) throws IOException, URISyntaxException {
        String logcsvFilePath = "D:\\Bachelorarbeit\\Rohdaten\\log-commasep-withNULL.csv";
        String useridFilePath = "D:\\Bachelorarbeit\\Rohdaten\\userids2.csv";
        String verblistPath = "D:\\Bachelorarbeit\\Rohdaten\\verbs.json";
        String exportPath = "D:\\Bachelorarbeit\\Rohdaten\\xapi-sample.json";
        
        CSVLogsReader logreader = new CSVLogsReader(logcsvFilePath, useridFilePath);
        //logreader.parseFile();
        StGenerator stGenerator = new StGenerator(verblistPath, exportPath);
        stGenerator.init();
        stGenerator.generateStatements(logreader.parseFile());
        stGenerator.exportToJson();
    }
    
}
