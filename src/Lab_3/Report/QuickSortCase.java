package Lab_3.Report;

public class QuickSortCase extends UseCase {
    /**
     * Constructor for objects of class QuickSortCase
     */
    public QuickSortCase(InputCase inputCase, int size) {
        super(inputCase, size);
    }

    @Override
    public String toString() {
        return "Sorting method, " + super.toString();
    }

    @Override
    public void sortAndCount() {
        // call suitable sort method
        Quicksort.reset();
        Quicksort.sort(arrayForTests);
        comp = Sort.getComparisonOperations(); // Sort.getComp();
        copy = Sort.getCopyOperations(); // Sort.getCopy();
    }
}

