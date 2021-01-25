package Lab_3.Report;

public class SelectionSortCase extends UseCase {
    /**
     * Constructor for objects of class SelectionSortCase
     */
    public SelectionSortCase(InputCase inputCase, int size) {
        super(inputCase, size);
    }

    @Override
    public String toString() {
        return "Sorting method, " + super.toString();
    }

    @Override
    public void sortAndCount() {
        // call suitable sort method
        Selection.reset();
        Selection.sort(arrayForTests);
        comp = Sort.getComparisonOperations(); // Sort.getComp();
        copy = Sort.getCopyOperations(); // Sort.getCopy();
    }
}
