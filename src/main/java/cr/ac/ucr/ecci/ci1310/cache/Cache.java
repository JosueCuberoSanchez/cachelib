package cr.ac.ucr.ecci.ci1310.cache;

/**
 * Created by bjgd9 on 7/7/2017.
 */
public interface Cache <K , V> {


    String getName();
    V get(K var1);
    void put(K var1, V var2);
    void evidict();
    void clear();

}
