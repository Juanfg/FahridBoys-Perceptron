package Perceptron;

public class Tweet {
	private final long id;
    private final int category;
    private final String result;
    private final boolean prospect;
    private final String latitud;
    private final String longitud;

    public Tweet(long id, int category, String result, boolean prospect, String lat, String lon) {
        this.id = id;
        this.category = category;
        this.result = result;
        this.prospect = prospect;
        this.latitud = lat;
        this.longitud = lon;
        
    }

    public long getId() {
        return id;
    }
    
    public double getCategory() {
    	return category;
    }
    
    public String getResult() {
    	return result;
    }

    public boolean getProspect() {
        return prospect;
    }
    
    public String getLat() {
        return latitud;
    }
    
    public String getlon() {
        return longitud;
    }
}
