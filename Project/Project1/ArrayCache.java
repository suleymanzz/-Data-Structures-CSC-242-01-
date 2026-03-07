// This class implements the cache using an array of CacheEntry objects.
// It keeps track of the number of entries, cache hits, and misses.
// It also manages adding and searching entries in the cache.


 public class ArrayCache {
 private CacheEntry[] cacheEntries;
 private int numEntries;
 private int numhits ;
 private int numMisses;



 public ArrayCache(int size) {
     cacheEntries = new CacheEntry[size];
     numEntries = 0;
     numhits = 0;
     numMisses = 0;
 }


public void put(String name, String value) {
    if (numEntries < cacheEntries.length) {
        cacheEntries[numEntries] = new CacheEntry(name, value);
        numEntries++;
    } else {
        
        cacheEntries[0] = new CacheEntry(name, value);
    }
}


public String get(String name) {
        for (int i = 0; i < numEntries; i++) {
            if (cacheEntries[i].getName().equals(name)) {
                numhits++;
                return cacheEntries[i].getValue();
            }
        }
        numMisses++;
        return null;
    }

  public int getNumHits() {
        return numhits;
    }


    public int getNumMisses() {
        return numMisses;

    }
 

    public void clear() {
        cacheEntries = new CacheEntry[cacheEntries.length];
        numEntries = 0;
        numhits = 0;
        numMisses = 0;
    }

    public boolean isFull() {
        return numEntries == cacheEntries.length;


    }


    public String toString() {

        String result = "";

        result += "Entries: " + numEntries + "\n";
        result += "Hits: " + numhits + "\n";
        result += "Misses: " + numMisses + "\n";

        for (int i = 0; i < numEntries; i++) {
            result += cacheEntries[i].toString() + "\n";
        }

        return result;
    }

    
    private void shiftEntries(int endIdx) {

        for (int i = endIdx; i > 0; i--) {
            cacheEntries[i] = cacheEntries[i - 1];
        }
    }
}












