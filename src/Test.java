import java.time.Instant;

/**
 * This class tests different time for our quicksort algorithm. It investigates the effect
 * of recursion limits in increments of 2s (2..4..6..) until 300. This is repeated across
 * different arrays sizes, from 20,000 to almost 10 million.
 * @author Bernie Miao
 */

public class Test {

    /**
     * The main method, all the array size increments are hardcoded, forgive me.
     * @param args Main
     */
    public static void main(String[] args) {
        final int [] TEST_CASES1 = {20000,40000,60000,80000,100000};
        final int [] TEST_CASES2 = {200000,400000,500000,600000,700000,800000,1000000};
        final int [] TEST_CASES3 = {2000000,3000000,4000000,5000000,6000000};
        final int [] TEST_CASES4 = {8000000,90000000,10000000};

        final int [] ALL_TEST_CASES = {20000,40000,60000,80000,100000,200000,
                400000,500000,600000,700000,800000,1000000,2000000,
                3000000,4000000,5000000,6000000,8000000,90000000,10000000};

        //these two cases are added because my PC cannot reach 10 million
        final int [] Remaining_TEST_CASES = {550000,670000};


        for (int a = 0; a < Remaining_TEST_CASES.length; a++) {
            testRecursionLimits( Remaining_TEST_CASES[a]);
        }

    } //end of main

    /**
     * This method creates three random template arrays, then tests
     * the recursion limits in increments of 2. Then calculates an average time which is printed.
     * @param arraySize Size of the array we wish to test
     */
    public static void testRecursionLimits(int arraySize) {
        long startTime, endTime, averageTime;
        long [] time = new long[3];

        System.out.println("\n\nTesting size of " + arraySize + "--------------");
        //TODO: Verify that this is the correct syntax
        Integer[][] templateArrays = new Integer[arraySize][3];
        Integer[][] copyArrays = new Integer[arraySize][3];

        //creating three new random arrays
        for (int i=0;i<3;i++) {
            templateArrays[i] = createRandomArray(arraySize);
        }

        //for loop that tests every recursive limit increment:
        // 2...4...6... 300
        for (int i = 2; i <= 300; i += 2) {
            FHsort.setRecursionLimit(i);

            //first, we clone the unsorted, template array
            for (int m = 0; m < 3; m++) {
                copyArrays[m] = templateArrays[m].clone();
            }

            //this is the timing bit, we have three times
            //time[0], time [1], and time [2]

            for (int x = 0; x < 3; x++) {
//                startTime = System.nanoTime();
                startTime = Instant.now().toEpochMilli();
                FHsort.quickSort(copyArrays[x]);
                endTime = Instant.now().toEpochMilli();
//                time[x] = System.nanoTime() - startTime;
                time[x] = endTime - startTime;
            }

//            averageTime = average(time, 3)/1000000;
            averageTime = average(time, 3);


            //print that time -- or better yet, return it!
//            System.out.println("Recursion Lim: "+i+" Time: "
//                    + " size: " + arraySize + ", "
//                    + TimeConverter.convertTimeToString(averageTime)
//                    + " = " + averageTime + "ns");

            System.out.println(i+", " + averageTime);

        }
    }

    /**
     * Finds the average for an array of longs
     * @param a An array
     * @param n Size of the array
     * @return The average long out of that array
     */
    private static long average(long a[], int n)
    {
        int sum = 0;
        for (int i = 0; i < n; i++)
            sum += a[i];
        return sum / n;
    }

    /**
     * Fills the array with random integers
     * @param size What size is your array of random numbers?
     * @return An array with random numbers
     */
    public static Integer[] createRandomArray(int size) {
        Integer[] arr = new Integer[size];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = (int)(size * Math.random()); // storing random integers in an array
        }
        return arr;
    }


}

