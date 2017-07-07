package cr.ac.ucr.ecci.ci1310.cache;

import java.util.Map;

/**
 * Created by bjgd9 on 7/7/2017.
 */
public class LeastRecentlyCache <K extends Comparable<? super K > , V> implements Cache<K , V> {

    K key;
    V value;
    private int size;
    private String name;
    private int maxItems;
    private Double objectLifespan;
    private Double cacheLifespan;
    private Map<K,V> map;


    public LeastRecentlyCache(int size,String name,int maxItems,Double objectLifespan,Double cacheLifespan){
        this.size = size;
        this.name = name;
        this.maxItems = maxItems;
        this.objectLifespan = objectLifespan;
        this.cacheLifespan = cacheLifespan;
    }

    public String getName() {
        return null;
    }

    public V get(K var1) {
        return null;
    }

    public void put(K var1, V var2) {

    }

    public void evidict() {

    }

    public void clear() {

    }
}
