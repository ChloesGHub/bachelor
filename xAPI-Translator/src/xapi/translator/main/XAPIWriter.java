package xapi.translator.main;

import com.google.gson.Gson;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import java.io.Writer;
import java.util.ArrayList;
import xapi.translator.maps.XAPIStatement;
import xapi.translator.maps.XAPIStatementList;

public class XAPIWriter {
    public void exportToJson(String exportPath, XAPIStatementList stList) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(exportPath));
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
                writer.write(gson.toJson((Object)st));
                writer.newLine();
                ++stcounter;
            }
            catch (IOException ex) {
                System.err.println("writing event failed");
                ex.printStackTrace();
            }
        }
        System.out.printf("Json file created. (%d statements created)/n", stcounter);
    }
}