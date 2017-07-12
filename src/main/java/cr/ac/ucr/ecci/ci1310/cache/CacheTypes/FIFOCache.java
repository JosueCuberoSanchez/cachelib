package cr.ac.ucr.ecci.ci1310.cache.CacheTypes;

import java.util.ArrayDeque;
import java.util.Map;
import java.util.Queue;

/**
 * Created by bjgd9 on 7/7/2017.
 */
//public class FIFOCache <K extends Comparable<? super K > , V> extends GeneralCache<K , V> {
public class FIFOCache <K  , V> extends GeneralCache<K , V> {



    private Queue<K> auxiliarCache;

    public FIFOCache(){
        super();
        auxiliarCache = new ArrayDeque<> ();
    }


    K selectVictim() {
        return auxiliarCache.poll ();
    }

    @Override
    public void put(K var1, V var2) {
        super.put (var1,var2);
        auxiliarCache.add (var1);
    }

}
