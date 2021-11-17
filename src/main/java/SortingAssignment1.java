import java.util.Random;
public class SortingAssignment1 {



    static int compareforMergeSort = 0;
    static int quicSortcompars = 0;

    public static void Merge(int numbers[], int i, int j, int k) {

        int mergedSize = k - i + 1;               // Size of merged partition
        int mergePos = 0;                          // Position to insert merged number
        int leftPos = 0;                           // Position of elements in left partition
        int rightPos = 0;                         // Position of elements in right partition
        int mergedNumbers[] = new int[mergedSize];  // Dynamically allocates temporary array
        // for merged numbers
        leftPos = i;                          // Initialize left partition position
        rightPos = j + 1;                     // Initialize right partition position

        // Add smallest element from left or right partition to merged numbers
        // here three comparisions

        while (leftPos <= j && rightPos <= k) {
            compareforMergeSort += 3;
            if (numbers[leftPos] <= numbers[rightPos]) {
                mergedNumbers[mergePos] = numbers[leftPos];
                ++leftPos;
            } else {
                mergedNumbers[mergePos] = numbers[rightPos];
                ++rightPos;

            }
            ++mergePos;
        }

        // If left partition is not empty, add remaining elements to merged numbers
        while (leftPos <= j) {
            compareforMergeSort++;
            mergedNumbers[mergePos] = numbers[leftPos];
            ++leftPos;
            ++mergePos;
        }

        // If right partition is not empty, add remaining elements to merged numbers
        while (rightPos <= k) {
            compareforMergeSort++;
            mergedNumbers[mergePos] = numbers[rightPos];
            ++rightPos;
            ++mergePos;
        }

        // Copy merge number back to numbers
        for (mergePos = 0; mergePos < mergedSize; ++mergePos) {
            compareforMergeSort++;
            numbers[i + mergePos] = mergedNumbers[mergePos];
        }
    }

    public static void MergeSort(int numbers[], int i, int k) {
        int j = 0;
        compareforMergeSort++;
        if (i < k) {
            j = (i + k) / 2;// Find the midpoint in the partition

            // Recursively sort left and right partitions
            MergeSort(numbers, i, j);
            MergeSort(numbers, j + 1, k);

            // Merge left and right partition in sorted order
            Merge(numbers, i, j, k);
        }
    }

    //------------------------------------------------------------------------------
//quick SORT;
//--------------------------------------------------------------------------------
    public static int Partition(int numbers[], int lowIndex, int highIndex) {
        // Pick middle element as pivot
        int midpoint = lowIndex + (highIndex - lowIndex) / 2;
        int pivot = numbers[midpoint];

        boolean done = false;
        while (!done) {
            quicSortcompars++;
            // Increment lowIndex while numbers[lowIndex] < pivot
            while (numbers[lowIndex] < pivot) {
                quicSortcompars++;
                lowIndex += 1;
            }

            // Decrement highIndex while pivot < numbers[highIndex]
            while (pivot < numbers[highIndex]) {
                quicSortcompars++;
                highIndex -= 1;
            }

            // If zero or one elements remain, then all numbers are
            // partitioned. Return highIndex.
            quicSortcompars++;
            if (lowIndex >= highIndex) {
                done = true;
            } else {
                // Swap numbers[lowIndex] and numbers[highIndex]
                int temp = numbers[lowIndex];
                numbers[lowIndex] = numbers[highIndex];
                numbers[highIndex] = temp;

                // Update lowIndex and highIndex
                lowIndex += 1;
                highIndex -= 1;
            }
        }

        return highIndex;
    }

    public static void Quicksort(int numbers[], int lowIndex, int highIndex) {
        // Base case: If the partition size is 1 or zero
        // elements, then the partition is already sorted
        quicSortcompars++;
        if (lowIndex >= highIndex) {
            return;
        }

        // Partition the data within the array. Value lowEndIndex
        // returned from partitioning is the index of the low
        // partition's last element.
        int lowEndIndex = Partition(numbers, lowIndex, highIndex);

        // Recursively sort low partition (lowIndex to lowEndIndex)
        // and high partition (lowEndIndex + 1 to highIndex)
        Quicksort(numbers, lowIndex, lowEndIndex);
        Quicksort(numbers, lowEndIndex + 1, highIndex);
    }

    //=-----------------------------------------------
//          Selection Sort
//------------------------------------------------------------------------------
    public static int SelectionSort(int numbers[], int numbersSize) {
        int i = 0;
        int j = 0;
        int indexSmallest = 0;
        int temp = 0;  // Temporary variable for swap
        int countComparision = 0;

        for (i = 0; i < numbersSize - 1; ++i) {

            // Find index of smallest remaining element
            indexSmallest = i;
            for (j = i + 1; j < numbersSize; ++j) {
                countComparision++;
                if (numbers[j] < numbers[indexSmallest]) {
                    indexSmallest = j;
                }
            }

            // Swap numbers[i] and numbers[indexSmallest]
            temp = numbers[i];
            numbers[i] = numbers[indexSmallest];
            numbers[indexSmallest] = temp;
        }
        return countComparision;
    }

    public static void main(String[] args) {

        Random rand = new Random();
        int numbers[] = new int[50];
        int numbers2[] = new int[50];
        int numbers3[] = new int[50];
        for (int i = 0; i < 50; i++) {
            int v = rand.nextInt(500);
            numbers[i] = v;
            numbers2[i] = v;
            numbers3[i] = v;
        }

        int NUMBERS_SIZE = 50;
        int i = 0;

        System.out.println("Selection Sort-----------\n");
        System.out.println("UNSORTED: ");
        for (i = 0; i < NUMBERS_SIZE; ++i) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println("");
        long startTime = System.nanoTime();
        int numberOfComparision = SelectionSort(numbers, NUMBERS_SIZE);

        long stopTime = System.nanoTime();
        long elapsedTime = stopTime - startTime;

        System.out.println("sorted");
        for (i = 0; i < NUMBERS_SIZE; ++i) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println("\n\nTime Stemps for selection Sort is: " + (double) elapsedTime / 1000000 + " milli Seconds");
        System.out.println("Number of Comparision of Selection Sort: " + numberOfComparision);


        System.out.println("\nMerge Sort-------");


        System.out.println("UNSORTED: ");
        for (i = 0; i < NUMBERS_SIZE; ++i) {
            System.out.print(numbers2[i] + " ");
        }
        startTime = System.nanoTime();
        MergeSort(numbers2, 0, NUMBERS_SIZE - 1);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;

        System.out.println("\nSORTED: ");
        for (i = 0; i < NUMBERS_SIZE; ++i) {
            System.out.print(numbers2[i] + " ");
        }
        System.out.println("\n\nTime Stemps for Merge Sort is: " + (double) elapsedTime / 1000000 + " milli Seconds");
        System.out.println("Number of Comparision of Merge Sort: " + compareforMergeSort);


        System.out.println("\nQuick Sort-------");

        System.out.println("UNSORTED: ");
        for (i = 0; i < NUMBERS_SIZE; ++i) {
            System.out.print(numbers3[i] + " ");
        }
        startTime = System.nanoTime();
        Quicksort(numbers3, 0, NUMBERS_SIZE - 1);
        stopTime = System.nanoTime();
        elapsedTime = stopTime - startTime;


        System.out.println("\nSORTED: ");
        for (i = 0; i < NUMBERS_SIZE; ++i) {
            System.out.print(numbers3[i] + " ");
        }
        System.out.println("\n\nTime Stemps for Quick Sort is: " + (double) elapsedTime / 1000000 + " milli Seconds");
        System.out.println("Number of Comparision of Quick Sort: " + quicSortcompars + "\n");

    }
}
