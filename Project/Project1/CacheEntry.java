// This class represents one cache entry.
// It stores the URL and its corresponding IP address.
// This class is used by ArrayCache to keep the cache data.

    public class CacheEntry { 

    private String name;
    private String value;

    public CacheEntry(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public String toString() {
        return "Name: " + name + " Value: " + value ;
    }
}



