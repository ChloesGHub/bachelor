/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.maps;

import java.util.Map;

/**
 *
 * @author chloe
 */
public class XAPIActor {
    private String name;
    private Map account;

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public Map getAccount() {
            return account;
    }

    public void setAccount(Map account) {
            this.account = account;
    }
}
