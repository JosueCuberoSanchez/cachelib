package cr.ac.ucr.ecci.ci1310.cache.CacheTypes;

import cr.ac.ucr.ecci.ci1310.cache.Entry;

import java.util.Map;
import java.util.Stack;

/**
 * Created by bjgd9 on 7/7/2017.
 */
public class LIFOCache <K extends Comparable<? super K > , V> extends GeneralCache<K,V> {


    private Double objectLifespan;
    private Double cacheLifespan;
    private Stack<K> auxiliarCache;

    public LIFOCache(){
        super();
        auxiliarCache= new Stack<K>();
    }

    public LIFOCache(String name,int maxItems,Double objectLifespan,Double cacheLifespan){
        super();
        super.setName (name);
        super.setMaxSize (maxItems);
    }


    @Override
    public void put(K var1, V var2) {
        super.put (var1,var2);
        auxiliarCache.add (var1);
    }

    K selectVictim() {
        return auxiliarCache.pop ();
    }

    @Override
    public void evict( K val){
        Entry entry = this.getCache ().get (val);
        auxiliarCache.remove (entry);
        super.evict (val);
    }

    @Override
    public  void  clear(){
        super.clear ();
        auxiliarCache.clear ();

    }
}
