package uk.co.brightec.alphaconferences.resources;


public class Resource {

    public static enum Type {
        SpeakerImageSmall,
        VenueImageSmall,
        VenueFloorplan
    }

    
    final String key;
    final Type type;
    
    public Resource(String key, Type type) {
        this.key = key;
        this.type = type;
    }
    

    public String url() {
    	String baseUrl = "http://static.alpha.org/acs/conferences/";
    	
        switch (type) {
        case SpeakerImageSmall:
            return baseUrl + "speakers/"+key+"/200.jpg";
        case VenueImageSmall:
        	return baseUrl + "venues/"+key+"/200.jpg";
        case VenueFloorplan:
        	return baseUrl + "venues/"+key+".pdf";
        default:
            return null;
        }
    }
    
    public String cacheFilename() {
        return "resource-"+key+"-"+type.ordinal();
    }

}
