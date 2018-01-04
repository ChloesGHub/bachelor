package xapi.translator.main;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import xapi.translator.maps.XAPIStatement;
import xapi.translator.maps.XAPIStatementList;

/**
 * Writer class for xAPI output file
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIWriter {
    private BufferedWriter writer;
    
    /**
     * export method for generating json file
     * @param exportPath
     * @param stList 
     */
    public void exportToJson(String exportPath, XAPIStatementList stList) {
        try {
            this.writer = new BufferedWriter(new FileWriter(exportPath));
        }
        catch (IOException ex) {
            System.err.println("Starting export failed");
            ex.printStackTrace();
        }
        System.out.println("Generating Json File started");
        Gson gson = new Gson();
        int stcounter = 0;
        for (XAPIStatement st : stList.getStatementList()) {
            try {
                this.writer.write(gson.toJson((Object)st));
                this.writer.newLine();
                ++stcounter;
            }
            catch (IOException ex) {
                System.err.println("XAPIWriter: writing event failed");
                ex.printStackTrace();
            }
        }
        System.out.printf("Json file created. (%d statements created)\n", stcounter);
    }
}