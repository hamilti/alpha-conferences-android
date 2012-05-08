package uk.co.brightec.alphaconferences.data;

import org.json.JSONObject;

import uk.co.brightec.util.JSON;


public class FAQ {

    public final int faqId;
    public final String question, answer;


    FAQ(JSONObject o) {
        this.faqId = o.optInt("id");
        this.question = JSON.getString(o, "question");
        this.answer = JSON.getString(o, "answer");
    }

}
