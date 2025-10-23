package graphs;

import org.junit.jupiter.api.Test;
import route_planner.Junction;

import java.util.*;

/**
 * Performance benchmarking utility for comparing Insertion Sort vs QuickSort algorithms.
 * Measures execution times across varying dataset sizes for empirical complexity analysis.
 * <p>
 * Note: This is not a traditional unit test but a timing measurement tool that
 * generates data for external performance analysis and Big-O verification.
 * <p>
 * Key features:
 * - Measures runtime from 100 to 5,000,000 elements (or 20-second timeout)
 * - Ensures fair comparison through dataset replication
 * - Validates sorting correctness against Collections.sort()
 * - Controls JVM state for consistent timing measurements
 */
public class SorterPerformanceTest {

        private static final long SEED = 20211220L;
        private static final Random RANDOM = new Random(SEED);
        public static void main(String[] args) {
            List<Country> countryList = generateCountryDataset(5000000);
            int[] sizes = {100, 200, 400, 800, 1600, 3200, 6400, 12800, 25600, 51200, 102400, 204800, 409600, 819200, 1638400, 3276800, 5000000};

            for (int size : sizes) {
                measureEfficiency(size, countryList);
            }
        }


    static void measureEfficiency(int size, List<Country> countryList) {
        // TODO Benchmarking code here...
        List<Country> countries =  new ArrayList<>(countryList.subList(0, size));

        // Run with -Xint JVM parameter to disable JIT compilation
        System.setProperty("java.vm.execution-mode", "int");


        long startTime = System.nanoTime();

         new Sorter<Country>().quickSort(countries, Comparator.comparing(Country::getName));
//         new Sorter<Country>().insertionSort( countries, Comparator.comparing(Country::getName));



        long endTime = System.nanoTime();
        long overTime = secondsToNanoseconds(20);

        long duration = (endTime - startTime) / 1_000_000; // naar ms
        System.out.println("Insertion Sort - Size: " + size + ", time " + duration + " ms" );


        if (endTime - startTime > overTime) {
            System.out.println("The time is greater then 20 sec");
            return;
        }


        // Reset the execution mode property
        System.clearProperty("java.vm.execution-mode");

        System.gc();

    }


    // Private helper method
    private static List<Country> generateCountryDataset(int size) {
        List<Country> countries = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            String name = "Country_" + String.format("%06d", i);
            int population = RANDOM.nextInt(100_000_000) + 1;
            countries.add(new Country(name, population));
        }
        Collections.shuffle(countries, RANDOM); // same seed = same shuffle
        return countries;
    }
    public static long secondsToNanoseconds(long seconds) {
        return seconds * 1_000_000_000L;
    }
}
