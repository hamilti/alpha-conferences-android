package uk.co.brightec.alphaconferences.data;

import org.json.JSONObject;

import android.graphics.Color;

import uk.co.brightec.util.JSON;


public class Stream {

    public final int streamId;
    public final String name;
    public final Integer color;


    Stream(JSONObject o) {
        this.streamId = o.optInt("id");
        this.name = JSON.getString(o, "name");
        this.color = JSON.getColor(o, "colour");
    }

}
