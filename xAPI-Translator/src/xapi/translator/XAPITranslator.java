/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import java.io.IOException;

/**
 *
 * @author chloe
 */
public class XAPITranslator {

    /**
     * @param args the command line arguments
     * @throws java.io.IOException
     */
    public static void main(String[] args) throws IOException {
        String filePath = "D:\\Bachelorarbeit\\Rohdaten\\log.csv";
        
        Csvreader reader = new Csvreader();
        reader.readFile(filePath);
    }
    
}
