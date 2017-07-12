package cr.ac.ucr.ecci.ci1310.cache.CacheTypes;

import java.util.*;


/**
 * Created by bjgd9 on 7/7/2017.
 */
public class RandomCache <K extends Comparable<? super K > , V> extends GeneralCache<K , V> {

    private List<K> auxiliarCache;


public RandomCache(){
    super();
    auxiliarCache=new ArrayList<> ();
}

    public RandomCache(String name,int maxItems,Double objectLifespan,Double cacheLifespan){
        super.setName (name);
        super.setMaxSize (maxItems);
    }

    @Override
    public void put(K var1, V var2) {
        super.put (var1,var2);
        auxiliarCache.add (var1);
    }

    K selectVictim() {
        int i = (int) (Math.random()*super.getCurrentSize ());
        return auxiliarCache.get (i);
    }


}
