package Lab_6;

import Lab_6.Report.ConcurrentRandomBST;
import Lab_6.Report.Double2;

import java.util.stream.IntStream;

public class UseCaseSampling {
    private final int M = 20;
    private final int kMin = 17;
    private final int kMax = 21;

    private  void printHeader() {
        System.out.println("Binary Search Tree");
        System.out.println("  Size  || Max Depth || Mean Depth ||     Time");
        System.out.println("--------++-----------++------------++----------");
    }

    private  void printRow(int size, double maxDepth, double meanDepth, long time) {
        System.out.printf("%8d||%10.2f ||%11.2f || %8d\n",
                size, maxDepth, meanDepth, time);
    }

    private  void makeTable() {
        printHeader();
        for (int i = kMin; i <= kMax; i++) {
            int iterationSize = Double.valueOf(Math.pow(2, i)).intValue();

            long start = System.currentTimeMillis();
            // measure time for UseCaseSampling
            Double2 result = sampleUseCase8(iterationSize);
            // end UseCaseSampling
            long end = System.currentTimeMillis();

            printRow(iterationSize, result.getFirst(), result.getSecond(), end-start);
        }
    }

    /**
     * sampleUseCase8 provides a Java 8 stream implementation
     * for parallel execution of M Random BST samples for calculating
     * averages of maxTreeDepth and of meanTreeDepth
     *
     * @param size of the BST
     * @return result consisting of the two averages calculated via @code{Double2}
     */
    public Double2 sampleUseCase8(int size)
    {
        Double2 result = new Double2();
        IntStream stream= IntStream.range(0, M);
        result = stream.boxed()
                // result = stream.sequential().boxed()
                .map(i-> new ConcurrentRandomBST(size))
                .map(ConcurrentRandomBST::getDepthValues)
                .reduce(result, Double2::add);
        result.divide(M);
        return result;
    }

    public static void main(String[] args) {
        (new UseCaseSampling()).makeTable();
    }
}