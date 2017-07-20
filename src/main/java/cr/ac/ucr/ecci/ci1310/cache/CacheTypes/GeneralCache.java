package cr.ac.ucr.ecci.ci1310.cache.CacheTypes;

import cr.ac.ucr.ecci.ci1310.cache.Entry;


import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by bjgd9 on 11/7/2017.
 */
public abstract class GeneralCache<K,V> implements Cache<K,V> ,Runnable{

    private String name;
    private int currentSize=0;
    private int maxSize=100;
    private int maxTimeByItem=30;
    private int maxTimeToClear=600;

    private Map<K,Entry<K,V>> cache;
    private PriorityQueue<Entry<K,V>> checkTimeQueue;


    private void checkItems( int time , int toltalTime){
        System.out.println ("Chequeando");
        System.out.println ("numero de elementos es "+ this.cache.size ());
        if(  toltalTime > maxTimeToClear){
            this.clear ();
            maxTimeToClear=0;
        }
        if(time>maxTimeByItem){
            Boolean deleting=true;
            Date date = new Date ();
            long actualTime=date.getTime ();
            while (checkTimeQueue.peek ()!= null && deleting){
                long theTime = (actualTime - checkTimeQueue.peek ().getDate ().getTime ())/1000;
               if( theTime > maxTimeByItem){
                   Entry entry = checkTimeQueue.poll ();
                   this.evict ((K) entry.getKey ());
               } else{
                   deleting=false;
               }
            }
        }
    }


    @Override
    public void run() {
        int avg= maxTimeByItem/2;
        int acumulateToltalTime=0;//para el tiempo grande de la cache
        int acumulateTime=0;//para el tiempo por objeto de la cache
        while(true){
            try {
                TimeUnit.SECONDS.sleep(3);
                acumulateTime+=avg;
                acumulateToltalTime+=avg;
                checkItems(acumulateTime,acumulateToltalTime);

            } catch (InterruptedException e) {

            }


        }
    }

    GeneralCache(){
        cache= new HashMap<K, Entry<K, V>> ();
        checkTimeQueue=new PriorityQueue<> ();
    }

    public synchronized V get(K var1) {
        Entry entry = this.cache.get (var1);
        V value=null;
        if(entry!= null) {
            value= (V) entry.getValue ();
            checkTimeQueue.remove (entry);
            entry.setDate (new Date ());
            checkTimeQueue.add (entry);
        }
        return value;
    }

    public synchronized void put(K var1,V var2) {
        Entry entry =new Entry ();
        Date  date = new Date ();
        entry.setKey (var1);
        entry.setValue (var2);
        entry.setDate (date);

        if( currentSize<maxSize) {
            this.cache.put (var1,entry);

            currentSize++;
        }else{
            K key = selectVictim();
            evict (key);
            this.cache.put(var1,entry);
        }
        this.checkTimeQueue.add (entry);
    }

    public synchronized void evict( K var1) {
        cache.remove (var1);
    }

    public synchronized void clear() {
        this.cache.clear ();
        currentSize=0;
    }

    abstract K selectVictim();

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCurrentSize() {
        return this.currentSize;
    }

    public void setCurrentSize(int currentSize) {
        this.currentSize = currentSize;
    }

    public int getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(int maxSize) {
        this.maxSize = maxSize;
    }

    public Map<K,Entry<K,V> > getCache() {return cache;}

    public void setCache(Map<K, Entry<K,V>> cache) {
        this.cache = cache;
    }


}
