package cr.ac.ucr.ecci.ci1310.cache.CacheModule;

import java.util.Map;

/**
 * Created by bjgd9 on 7/7/2017.
 */
public interface  Cache<K, V> {

    /** Defaults values */
    String cachesName="myCache";
    int cacheSize=10;//default size
    int currentElements=0;// current elements in the Cache (Map)
    int timeToClearCache=-1;// -1 represents infinite
    int timetoClearChacheObject=3600;// one hour

    String getName();

    V get (K var1);

    void put(K var1, V var2);

    void  evict(K var1);

    K selectVictim();

    void clear();

}

/*
    Map<K,V> cache;
    String cachesName="myCache";
    int cacheSize=10;//default size
    int currentElements=0;// current elements in the Cache (Map)
    int timeToClearCache=-1;// -1 represents infinite
    int timetoClearChacheObject=3600;// one hour
*/

