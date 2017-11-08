/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator;

import com.opencsv.bean.CsvToBeanBuilder;
import com.opencsv.bean.CsvToBeanFilter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author chloe
 */
public class Csvreader {
    
    public void readFile(String inputPath) throws IOException {

//        CSVReader reader = new CSVReader(new FileReader(inputPath), '\t');
//        String [] nextLine;
//        while ((nextLine = reader.readNext()) != null) {
//            // nextLine[] is an array of values from the line
//            if (nextLine.length == 1) {
//                continue;
//            }
//            System.out.println(nextLine[0] + nextLine[1]);
//        }
        
        List<Events> eventlist = new CsvToBeanBuilder(new FileReader(inputPath))
        .withSeparator('\t').withFilter(new BlankRowsFilter()).withType(Events.class).build().parse();
        
//        System.out.println(eventlist.get(3).eventname);
        int i = 0;
        Iterator<Events> it = eventlist.iterator();
        while(it.hasNext() && (i<20)) {
            it.next().getInfo();
            i++;
        }
    }
    
    /*
    * OpenCSV Filter for skipping blank rows
    */
    private class BlankRowsFilter implements CsvToBeanFilter {
        @Override
        public boolean allowLine(String[] line) {
            int colNum = line.length;
            return (colNum != 1);
        }
    }
}
