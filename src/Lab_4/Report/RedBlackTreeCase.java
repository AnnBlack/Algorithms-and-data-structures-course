package Lab_4.Report;

public class RedBlackTreeCase extends UseCase {
    public RedBlackTree RB = new RedBlackTree();

    public RedBlackTreeCase(InputCase inputCase, int size) {
        super(inputCase, size);
    }
    public String toString() {
        return "Tree creating, " + super.toString();
    }


    @Override
    public void sortAndCount() {
        // call suitable sort method
        maxTreeDepth = 0;
        meanTreeDepth = 0;
        for (Integer i : arrayForTests) {
            RB.put(i, i + 1);
        }
        maxTreeDepth = RB.maxTreeDepth();
        meanTreeDepth = RB.meanTreeDepth();
    }
}
