/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.mapping;

import com.rusticisoftware.tincan.Statement;

/**
 *
 * @author chloe
 */
public class TObject {
    MoodleEvents moodleEvent;
    Statement statement;
    
    public TObject(MoodleEvents moodleEvent, Statement statement) {
        this.moodleEvent = moodleEvent;
        this.statement = statement;
    }
    
    public Statement addObject() {
        return statement;
    }
}
