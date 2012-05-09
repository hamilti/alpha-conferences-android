package uk.co.brightec.alphaconferences.data;

import org.json.JSONObject;

import uk.co.brightec.util.JSON;


public class Room {

    public final int roomId, venueId;
    public final String name;


    Room(JSONObject o) {
        this.roomId = o.optInt("id");
        this.venueId = o.optInt("venue");
        this.name = JSON.getString(o, "name");
    }

}
