package uk.co.brightec.alphaconferences.data;

import org.json.JSONObject;


public class Speaker implements Comparable<Speaker> {

    public final int speakerId;
    public final String twitterUsername;
    public final String websiteUrl;
    public final String biography;
    public final String position;
    public final String imageKey;
    
    private final String firstName, lastName, alias, sortableName;
    
    
    Speaker(JSONObject o) {
        this.speakerId = o.optInt("id");
        this.firstName = getString(o, "first_name");
        this.lastName = getString(o, "last_name");
        this.biography = getString(o, "biography");
        this.position = getString(o, "position");
        this.imageKey = getString(o, "image_key");
        this.twitterUsername = getString(o, "twitter_username");
        this.websiteUrl = getString(o, "website_url");
        this.alias = getString(o, "alias");
        this.sortableName = lastName+" "+firstName;
    }

    // TODO: move this somewhere generic
    private static final String getString(JSONObject j, String name) {
        return j.isNull(name) ? null : j.optString(name);
    }
    
    
    public String displayName() {
        return (alias != null) ? alias : firstName+" "+lastName;
    }

    public String indexLetter() {
        return (lastName != null && lastName.length() > 0) ? lastName.substring(0, 1).toUpperCase() : "?";
    }

    @Override
    public int compareTo(Speaker another) {
        return this.sortableName.compareTo(another.sortableName);
    }
    
}
