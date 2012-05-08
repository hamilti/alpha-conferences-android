package uk.co.brightec.alphaconferences.data;

import org.json.JSONObject;

import uk.co.brightec.util.JSON;


public class SpecialOffer {

    public final int specialOfferId;
    public final String title, html;


    SpecialOffer(JSONObject o) {
        this.specialOfferId = o.optInt("id");
        this.title = JSON.getString(o, "title");
        this.html = JSON.getString(o, "description");
    }

}
