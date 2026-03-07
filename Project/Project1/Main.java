//// Main class for the DNS cache simulation program.
// It reads the query file, runs the cache simulation,
// and prints the hit and miss statistics.


import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class Main {
 public static void simulate(ArrayCache cache, String fileName) {

       try {

            File file = new File(fileName);
            Scanner reader = new Scanner(file);

            while (reader.hasNextLine()) {

                String line = reader.nextLine();

                String[] parts = line.split(":");

                String url = parts[0];
                String ip = parts[1];

                String result = cache.get(url);

                if (result == null) {
                    cache.put(url, ip);
                }

                System.out.println(cache);
            }

            reader.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found.");



        }


    }



       public static void main(String[] args) {

        Scanner input = new Scanner(System.in);

        System.out.print("Please enter query file name: ");
        String fileName = input.nextLine();

        int size;

        do {
            System.out.print("Please enter a positive cache size: ");
            size = input.nextInt();
        } while (size <= 0);

        ArrayCache cache = new ArrayCache(size);

        simulate(cache, fileName);

        printStats(cache);

        input.close();
    }

          public static void printStats(ArrayCache cache) {


    int hits = cache.getNumHits();
    int misses = cache.getNumMisses();
    int total = hits + misses;

    double hitRate = hits * 100.0 / total;
    double missRate = misses * 100.0 / total;

    System.out.println("Hit Rate: " + hitRate + "%");
    System.out.println("Miss Rate: " + missRate + "%");
}



    }





 



    

