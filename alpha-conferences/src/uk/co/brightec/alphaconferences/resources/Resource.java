package uk.co.brightec.alphaconferences.resources;


public class Resource {

    public static enum Type {
        SpeakerImageSmall
    }

    
    final String key;
    final Type type;
    
    public Resource(String key, Type type) {
        this.key = key;
        this.type = type;
    }
    

    public String url() {
        switch (type) {
        case SpeakerImageSmall:
            return "http://static.alpha.org/acs/conferences/speakers/"+key+"/200.jpg";
        default:
            return null;
        }
    }
    
    public String cacheFilename() {
        return "resource-"+key+"-"+type.ordinal();
    }

}
