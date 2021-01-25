package Lab_3.Report;

import java.util.stream.IntStream;

public class InsertionSortCase extends UseCase {
    /**
     * Constructor for objects of class InsertionSortCase
     */
    public InsertionSortCase(InputCase inputCase, int size) {
        super(inputCase, size);
    }

    @Override
    public String toString() {
        return "Sorting method, " + super.toString();
    }

    @Override
    public void sortAndCount() {
        // call suitable sort method
        Insertion.reset();
        Insertion.sort(arrayForTests);
        comp = Sort.getComparisonOperations(); // Sort.getComp();
        copy = Sort.getCopyOperations(); // Sort.getCopy();
    }

    public void sortAndCountParallel() {
        Insertion.reset();
        Insertion.sort(arrayForTests);

        IntStream stream = IntStream.range(0, M);
        comp = Sort.getComparisonOperations(); // Sort.getComp();
        copy = Sort.getCopyOperations(); // Sort.getCopy();
    }
    IntStream stream = IntStream.range(0, M);
}
