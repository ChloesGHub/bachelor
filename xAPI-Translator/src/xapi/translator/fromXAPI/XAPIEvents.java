/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xapi.translator.fromXAPI;

import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

/**
 *
 * @author chloe
 */
public class XAPIEvents {
    JsonPrimitive _id;
    JsonPrimitive lrs;
    JsonPrimitive lrs_id;
    JsonPrimitive client_id;
    JsonPrimitive statement;
    JsonPrimitive active;
    JsonPrimitive voided;
    JsonPrimitive timestamp;

    public JsonPrimitive getId() {
        return _id;
    }

    public void setId(JsonPrimitive _id) {
        this._id = _id;
    }

    public JsonPrimitive getLrs() {
        return lrs;
    }

    public void setLrs(JsonPrimitive lrs) {
        this.lrs = lrs;
    }

    public JsonPrimitive getLrs_id() {
        return lrs_id;
    }

    public void setLrs_id(JsonPrimitive lrs_id) {
        this.lrs_id = lrs_id;
    }

    public JsonPrimitive getClient_id() {
        return client_id;
    }

    public void setClient_id(JsonPrimitive client_id) {
        this.client_id = client_id;
    }

    public JsonPrimitive getStatement() {
        return statement;
    }

    public void setStatement(JsonPrimitive statement) {
        this.statement = statement;
    }

    public JsonPrimitive getActive() {
        return active;
    }

    public void setActive(JsonPrimitive active) {
        this.active = active;
    }

    public JsonPrimitive getVoided() {
        return voided;
    }

    public void setVoided(JsonPrimitive voided) {
        this.voided = voided;
    }

    public JsonPrimitive getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(JsonPrimitive timestamp) {
        this.timestamp = timestamp;
    }

    public JsonPrimitive getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(JsonPrimitive updated_at) {
        this.updated_at = updated_at;
    }
    JsonPrimitive updated_at;
    
    
}
