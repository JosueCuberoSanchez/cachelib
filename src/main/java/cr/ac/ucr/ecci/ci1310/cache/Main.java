package cr.ac.ucr.ecci.ci1310.cache;

import java.util.Scanner;

/**
 * Created by Josue Cubero on 06/07/2017.
 */
public class Main {




    public static void main(String[] args){


        //FIFOCache<String,Integer> fi= new FIFOCache<String, Integer> ();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input the desired cache name");
        String name = scanner.next();
        System.out.println("Input the desired cache size");
        int size = scanner.nextInt();
        System.out.println("Input the desired limit of objects");
        int numElem = scanner.nextInt();
        System.out.println("Input the desired object lifespan");
        Double objectLifespan = scanner.nextDouble();
        System.out.println("Input the desired cache lifespan");
        Double cacheLifespan = scanner.nextDouble();
    }
}