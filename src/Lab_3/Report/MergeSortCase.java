package Lab_3.Report;

public class MergeSortCase extends UseCase {
    /**
     * Constructor for objects of class MergeSortCase
     */
    public MergeSortCase(InputCase inputCase, int size) {
        super(inputCase, size);
    }

    @Override
    public String toString() {
        return "Sorting method, " + super.toString();
    }

    @Override
    public void sortAndCount() {
        // call suitable sort method
        Mergesort.reset();
        Mergesort.sort(arrayForTests);
        comp = Sort.getComparisonOperations(); // Sort.getComp();
        copy = Sort.getCopyOperations(); // Sort.getCopy();
    }
}
