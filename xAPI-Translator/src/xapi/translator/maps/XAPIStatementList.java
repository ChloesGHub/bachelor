package xapi.translator.maps;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Superclass for xAPIStatement
 * @author Chloe Lao <chloe@jia-online.de>
 */
public class XAPIStatementList extends XAPIStatement {
    private ArrayList<XAPIStatement> statementList;

    /**
     * constructor
     */
    public XAPIStatementList() {
        this.statementList = new ArrayList<>();
    }
    /**
     * get xAPI statement list
     * @return 
     */
    public ArrayList<XAPIStatement> getStatementList() {
        return this.statementList;
    }

    /**
     * add xAPI statement to list
     * @param st 
     */
    public void addStatement(XAPIStatement st) {
        this.statementList.add(st);
    }
    
    /**
     * get size of statement list
     * @return 
     */
    public int size() {
        return this.statementList.size();
    }
    
    /**
     * sorting method by datetime
     */
    public void sortByDate() {
        Collections.sort(this.statementList, new Comparator<XAPIStatement>() {
            @Override
            public int compare(XAPIStatement o1, XAPIStatement o2) {
                return o1.getTimestamp().compareTo(o2.getTimestamp());
            }
        });
    }
}

