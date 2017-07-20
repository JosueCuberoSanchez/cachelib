package cr.ac.ucr.ecci.ci1310.cache;

import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.FIFOCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.LIFOCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.LeastRecentlyCache;
import cr.ac.ucr.ecci.ci1310.cache.CacheTypes.RandomCache;

import javax.naming.Context;
import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Josue Cubero on 06/07/2017.
 */
public class Main {


    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException, SQLException
        {



            //Variables de Experimentos

            BufferedReader br = null;
            FileReader fr = null;
            int missNumber=0;
            int hitNumeber=0;
            int sizeExperiment=400; //cambiar
            int numberRequest=200;  //cambiar
            int cacheSize=50;

            Double totalTimeRest=0.0;
            Double timewithOutCache=0.0;
            Double timeWithCache=0.0;


            FIFOCache<String,String> cache= new FIFOCache<> ();
            //LIFOCache<String,String> cache= new LIFOCache<> ();
            //LeastRecentlyCache<String,String> cache= new LeastRecentlyCache<> ();
            //RandomCache<String,String> cache= new RandomCache<> ();

            cache.setMaxSize (cacheSize);

            String currentData="";
            br = new BufferedReader(new FileReader("filename.txt"));
            Vector<String> vecDataId= new Vector<> (sizeExperiment);
           // Vector<String> vecDataName= new Vector<> (sizeExperiment);

            int i=0;
            //cargar los datos a estructura rápida;
            while ((currentData= br.readLine()) != null&& i<sizeExperiment) {
                String data[]=currentData.split (" ");
                vecDataId.add (i,data[0]);
             //   vecDataName.add (i,data[1]);
            }


            //cliente OJDBC
            int  j=0;
            Class.forName ("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection (
                    "jdbc:mysql://localhost:3306/wiki", "root", "mysql");
            Statement stmt = con.createStatement ();
            String likeWord = "man";

            //Ejecución de Experimento
            while (j<numberRequest){

                String valRequest=  vecDataId.get ((int) (Math.random()*sizeExperiment));
                //String valRequest=  vecDataName.get ((int) (Math.random()*sizeExperiment));
                String valueReturn="";
                if(cache.get (valRequest)==null){//como no esta, se debe hacer request a la base de datos
                    long date1= new Date ().getTime ();
                    String query = "select * from wiki.page where page.page_id =\""+valRequest+"\"";
                    //String query = "select * from wiki.page where page.page_title =\""+valRequest+"\"";
                    ResultSet rs = stmt.executeQuery (query);
                    rs.next ();
                    valueReturn = rs.getInt (1) + " " + rs.getString (3);
                    cache.put (valRequest,valueReturn);
                    long date2= new Date ().getTime ();
                    timewithOutCache+= date2-date1;
                    //String query = "select * from wiki.page  where page_title like \"%" + "man" + "%\"";
                    missNumber++;
                }else {
                    long date1= new Date ().getTime ();
                    String result = cache.get (valRequest);
                    long date2= new Date ().getTime ();
                    timeWithCache+= date2-date1;
                    hitNumeber++;
                }
                j++;
            }

            //resultados
            System.out.println ("numero de miss "+ missNumber +" y en promedio dura "+ timewithOutCache/missNumber);
            System.out.println ("numero de hits "+hitNumeber+ " y en promedio dura "+timeWithCache/hitNumeber);
        }
}