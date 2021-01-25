package Lab_4.Report;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Class Records:
 * Test of stability of divers sorting algorithms
 *
 * @author Purtova Anastasiia
 * @version Nov. 22, 2020
 */
public class Records {
    // size for stable sort tests in main program:
    private final static int N = 6;
    // instance variables - replace the example below with your own
    private final int size;
    private Record[] studentRecords;
    private List<Record> list;
    private Boolean isSorted;
    private Boolean isPartitioned;

    /**
     * Constructor for objects of class Records
     */
    public Records(int size) {
        // initialise instance variables
        this.size = size;
        this.studentRecords = new Record[size];
        this.isSorted = false;
        this.isPartitioned = false;
        init();
    }

    private void init() {
        //String[] names = {"Anna", "Olga", "Petr", "Jack", "Rachel", "Napoleon"};
        String[] names = {"A", "B", "C", "D", "E", "F"};
        for (int i = 0; i < size; i++) {
            this.studentRecords[i] = new Record(i, names[i]);
        }
        this.list = Arrays.asList(this.studentRecords);
    }

    @Override
    public String toString() {
        return this.list.toString();
    }

    public void shuffle() {
        Collections.shuffle(this.list, new Random(System.currentTimeMillis()));
    }

    public boolean isSorted() {
        for(int i = 0; i < this.size - 1; i++) {
            this.isSorted = Sort.isSorted(this.studentRecords, i, i + 1);

        }
        System.out.println(Sort.getComparisonOperations());
        return isSorted;
    }

    public boolean isPartitioned(int border) {
        for(int i = 0; i < this.size - 1; i++ ) {
            this.isPartitioned = Sort.isPartitioned(this.studentRecords, i, border,i + 1);
        }
        return this.isPartitioned;
    }

    public static void main(String[] a) {
        Records studentRecords = new Records(6);
    }
}
