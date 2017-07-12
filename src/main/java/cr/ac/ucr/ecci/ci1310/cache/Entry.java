package cr.ac.ucr.ecci.ci1310.cache;

import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.GeneralCache;

import java.util.Date;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import static java.lang.System.exit;

/**
 * Created by bjgd9 on 11/7/2017.
 */
public class Entry<K,V> implements Comparable {
    private K key;
    private V value;
    private Date date;
    GeneralCache generalCache;

    public Entry(){
        //System.out.println ("Soy un entry");

    }

    public void setGeneralCache(GeneralCache generalCache) {
        this.generalCache = generalCache;
    }

    public GeneralCache getGeneralCache() {
        return generalCache;
    }

    public K getKey() {
        return key;
    }


    public void setKey(K key) {
        this.key = key;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public V getValue() {
        return value;

    }


    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }


    @Override
    public int compareTo(Object o) {
        if(this.date.getTime () == ((Entry)o).date.getTime ())
            return 0;
        else if (this.date.getTime () >((Entry)o).date.getTime ())
            return 1;
        else
            return -1;
    }


}

