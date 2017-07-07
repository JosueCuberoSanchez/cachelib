package cr.ac.ucr.ecci.ci1310.cache.CacheModule;

import cr.ac.ucr.ecci.ci1310.cache.CacheModule.Cache;
import javafx.util.Pair;

/**
 * Created by bjgd9 on 7/7/2017.
 */
public class LeastRecentlyCache implements Cache<String,Pair<Integer,String>> {
    @Override
    public String getName() {
        return null;
    }

    @Override
    public Pair<Integer, String> get(String var1) {
        return null;
    }

    @Override
    public void put(String var1, Pair<Integer, String> var2) {

    }

    @Override
    public void evict(String var1) {

    }

    @Override
    public String selectVictim() {
        return null;
    }

    @Override
    public void clear() {

    }
}
