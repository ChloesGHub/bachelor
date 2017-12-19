/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.maps;

import com.google.gson.Gson;

/**
 *
 * @author chloe
 */
public class XAPIStatement {
    private XAPIActor actor;
    private XAPIObject object;

    public XAPIActor getActor() {
        return actor;
    }

    public void setActor(XAPIActor actor) {
        this.actor = actor;
    }

    public XAPIObject getObject() {
        return object;
    }

    public void setObject(XAPIObject object) {
        this.object = object;
    }
}
