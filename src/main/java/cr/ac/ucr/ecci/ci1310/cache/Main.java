package cr.ac.ucr.ecci.ci1310.cache;

import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.FIFOCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.LIFOCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.LeastRecentlyCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.RandomCache;

import javax.naming.Context;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Josue Cubero on 06/07/2017.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, SQLException {


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

        try {
            Class.forName ("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection (
                    "jdbc:mysql://localhost:3306/wiki", "root", "mysql");
            Statement stmt = con.createStatement ();
            String likeWord = "20";
            //String query = "select * from wiki.page  where page_title like \"%" + likeWord + "%\"";
            String query = "select * from wiki.page where page.page_id =\""+likeWord+"\"";
            ResultSet rs = stmt.executeQuery (query);

                while (rs.next ()) {
                    String result = rs.getInt (1) + " " + rs.getString (3);
                    System.out.println (result);
                }
                con.close ();
            } catch (Exception e) {
                e.printStackTrace ();
            }




        FIFOCache<String,String> cache= new FIFOCache<> ();
        //LIFOCache<String,Integer> cache= new LIFOCache<> ();
        //LeastRecentlyCache<String,Integer> cache= new LeastRecentlyCache<> ();
       // RandomCache<String,Integer> cache= new RandomCache<> ();
        BufferedReader br = null;
        FileReader fr = null;
        int missNumber=0;
        int hitNumeber=0;
        int sizeExperiment=100;
        int numberRequest=75;
        Double totalTimeRest=0.0;
        Double timewithOutCache=0.0;
        Double timeWithCache=0.0;

        String currentData="";
        br = new BufferedReader(new FileReader("filename.txt"));
        Vector<String> vecDataId= new Vector<> (sizeExperiment);
        Vector<String> vecDataName= new Vector<> (sizeExperiment);

        int i=0;
        //cargar los datos a estructura rápida;
        while ((currentData= br.readLine()) != null&& i<sizeExperiment) {
            String data[]=currentData.split (" ");
            vecDataId.add (i,data[0]);
            vecDataName.add (i,data[1]);
        }


        int  j=0;
        Class.forName ("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection (
                "jdbc:mysql://localhost:3306/wiki", "root", "mysql");
        Statement stmt = con.createStatement ();
        String likeWord = "man";





        while (j<numberRequest){

          String valRequest=  vecDataId.get ((int) (Math.random()*sizeExperiment));
          //String valRequest=  vecDataName.get ((int) (Math.random()*sizeExperiment));
            String valueReturn="";
            if(cache.get (valRequest)==null){//como no esta, se debe hacer request a la base de datos

                String query = "select * from wiki.page where page.page_id =\""+valRequest+"\"";

                //String query = "select * from wiki.page  where page_title like \"%" + "man" + "%\"";
                ResultSet rs = stmt.executeQuery (query);
                if(rs.next ()) {
                    valueReturn = rs.getInt (1) + " " + rs.getString (3);
                }
                cache.put (valRequest,valueReturn);
                System.out.println (valueReturn);
                missNumber++;
            }else {
                System.out.println (valRequest = cache.get (valRequest));
                hitNumeber++;
            }




            j++;
        }


        System.out.println ("numero de miss"+ missNumber);
        System.out.println ("numero de hits"+hitNumeber);




/*
        Reader reader = new BufferedReader ("filename.txt",0);

*/


/*
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


*/


    }
}