package Lab_4.Report;

public class BSTCase extends UseCase {
    public BST bst = new BST();

    public BSTCase(InputCase inputCase, int size) {
        super(inputCase, size);
    }

    @Override
    public String toString() {
        return "Tree creating method, " + super.toString();
    }

    @Override
    public void sortAndCount() {
        // call suitable sort method
        maxTreeDepth = 0;
        meanTreeDepth = 0;
        for (Integer i : arrayForTests) {
            bst.put(i, i + 1);
        }
        maxTreeDepth = bst.maxTreeDepth();
        meanTreeDepth = bst.meanTreeDepth();
    }
}