

package xapi.translator.maps;

import java.util.ArrayList;






public class XAPIStatementList
  extends XAPIStatement
{
  public XAPIStatementList() {}
  
  private ArrayList<XAPIStatement> statementList = new ArrayList();
  
  public ArrayList<XAPIStatement> getStatementList() {
    return statementList;
  }
  
  public void setStatementList(ArrayList<XAPIStatement> statementList) {
    this.statementList = statementList;
  }
  
  public void addStatement(XAPIStatement st) {
    statementList.add(st);
  }
  
  public int size() {
    return statementList.size();
  }
}

