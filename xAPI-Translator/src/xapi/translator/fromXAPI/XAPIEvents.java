package xapi.translator.fromXAPI;

import com.google.gson.JsonPrimitive;

/**
 * POJO class for reading xAPI log files
 * @author Chloe Lao <chloe@jia-online.de>
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
    JsonPrimitive updated_at;

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
}
