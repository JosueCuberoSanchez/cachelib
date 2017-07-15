package cr.ac.ucr.ecci.ci1310.cache;

import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.FIFOCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.LIFOCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.LeastRecentlyCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.RandomCache;

import javax.naming.Context;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by Josue Cubero on 06/07/2017.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException {

        /*

            try {
                Class.forName ("com.mysql.jdbc.Driver");
                Connection con = DriverManager.getConnection (
                        "jdbc:mysql://localhost:3306/wiki", "root", "mysql");
                Statement stmt = con.createStatement ();
                String likeWord = "man";
                String query = "select * from wiki.page  where page_title like \"%" + likeWord + "%\"";
                ResultSet rs = stmt.executeQuery (query);


                try (Writer writer = new BufferedWriter (new OutputStreamWriter (
                        new FileOutputStream ("filename.txt"), "utf-8"))) {
                    while (rs.next ()) {
                        String result = rs.getInt (1) + " " + rs.getString (3);
                        writer.write (result + "\n");
                    }
                    con.close ();

                } catch (Exception e) {
                    e.printStackTrace ();
                }


            } catch (Exception e) {
                System.out.println (e);

            }
*/

        BufferedReader br = null;
        FileReader fr = null;

/*
        Reader reader = new BufferedReader ("filename.txt",0);

*/



    FIFOCache<String,Integer> fi= new FIFOCache<> ();
        Thread thread = new Thread (fi);
        thread.start ();
        fi.put ("1",1);
        TimeUnit.SECONDS.sleep(1);

        fi.put ("2",2);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("3",3);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("4",4);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("5",5);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("6",6);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("7",7);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("8",8);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("9",9);
        TimeUnit.SECONDS.sleep(1);
        fi.put ("10",10);
        TimeUnit.SECONDS.sleep(1);
        //fi.put ("11",11);


        long date1= new Date ().getTime ();
        TimeUnit.SECONDS.sleep(1);

        long date2= new Date ().getTime ();
        long time = date2-date1;
        System.out.println ("El tiempo fue "+time);
        thread.join ();


            boolean caseSearchId = true;
            String queryWord = "";





    }
}