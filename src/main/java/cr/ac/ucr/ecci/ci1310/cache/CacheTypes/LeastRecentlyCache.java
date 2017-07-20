package cr.ac.ucr.ecci.ci1310.cache.CacheTypes;

import cr.ac.ucr.ecci.ci1310.cache.Entry;


import java.util.Date;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Created by bjgd9 on 7/7/2017.
 */
public class LeastRecentlyCache <K extends Comparable<? super K > , V> extends GeneralCache<K , V> {

    private PriorityQueue<Entry<K,V>> auxiliarCache;

    public  LeastRecentlyCache(){
        super();
        auxiliarCache= new PriorityQueue<> ();
    }
    public LeastRecentlyCache(String name,int maxItems,Double objectLifespan,Double cacheLifespan){
        super.setName(name);
        super.setMaxSize(maxItems);
    }

    @Override
    public V get(K var1) { //pedir el dato
        Entry entryValue;
        V value=null;
        entryValue = (Entry) super.getCache ().get (var1);//lo obtine

        if(entryValue!=null) {
            //update time in entry

            auxiliarCache.remove (entryValue);
            Date date = new Date ();
            entryValue.setDate (date);
            super.getCache ().put (var1, entryValue);
              value=(V)entryValue.getValue ();
        }
        return value;
      }

    @Override
    public void put(K var1, V var2) {
        super.put (var1,var2);
        Entry entry =super.getCache ().get (var1);
        auxiliarCache.add (entry);
    }

    K selectVictim() {
        return auxiliarCache.poll ().getKey ();
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
