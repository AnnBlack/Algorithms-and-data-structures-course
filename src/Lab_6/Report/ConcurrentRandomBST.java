package Lab_6.Report;

import java.util.concurrent.ThreadLocalRandom;

public class ConcurrentRandomBST
        extends BST<Integer, Integer>
        implements SymbolTable<Integer, Integer>
{
    int size;

    public ConcurrentRandomBST(int size) {
        this.size = size;
        fillTree();
    }

    private void fillTree() {
        for (int i = 0; i < size; i++) {
            // use a concurrent random number generator for parallel execution
            int nextKey = ThreadLocalRandom.current().nextInt();
            put(nextKey, null);
        }
    }

    public Double2 getDepthValues() {
        return new Double2(maxTreeDepth(), meanTreeDepth());
    }
}
