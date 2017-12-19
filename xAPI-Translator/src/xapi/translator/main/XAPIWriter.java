/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.main;

import com.rusticisoftware.tincan.Statement;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author chloe
 */
public class XAPIWriter {
    
    
    public void exportToJson(String exportPath, ArrayList<Statement> stList) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(exportPath));
        } catch (IOException ex) {
            System.err.println("Starting export failed");
            ex.printStackTrace();
        }
        System.out.println("Generating Json File started");
        for(Statement st : stList) {
            try {
                writer.write(st.toJSON());
                writer.newLine();
            } catch (IOException ex) {
                System.err.println("writing event failed");
                ex.printStackTrace();
            }
        }
        System.out.println("Json File erstellt.");
    }
}
