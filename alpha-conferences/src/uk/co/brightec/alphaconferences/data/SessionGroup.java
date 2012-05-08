package uk.co.brightec.alphaconferences.data;

import org.json.JSONObject;

import uk.co.brightec.util.JSON;


public class SessionGroup {

    public final int sessionGroupId, dayId;
    public final String name;


    SessionGroup(JSONObject o) {
        this.sessionGroupId = o.optInt("id");
        this.dayId = o.optInt("day");
        this.name = JSON.getString(o, "name");
    }

}
