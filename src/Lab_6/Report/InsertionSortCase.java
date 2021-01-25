package Lab_6.Report;

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
    public Double2 sortAndCount(int size) {
        Insertion.reset();
        Insertion.sort(arrayForTests);

        Double2 result = new Double2();
        IntStream stream = IntStream.range(0, M);
                result = stream.parallel().boxed()
                //result = stream.sequential().boxed()
                .map(i-> new ConcurrentRandomBST(size))
                .map(ConcurrentRandomBST::getDepthValues)
                .reduce(result, Double2::add);
        result.divide(M);
        return result;
        //comp = Sort.getComparisonOperations(); // Sort.getComp();
        //copy = Sort.getCopyOperations(); // Sort.getCopy();
    }

//    public Double2 sampleUseCase8(int size)
//    {
//        Double2 result = new Double2();
//        IntStream stream= IntStream.range(0, M);
//        result = stream.boxed()
//                // result = stream.sequential().boxed()
//                .map(i-> new ConcurrentRandomBST(size))
//                .map(ConcurrentRandomBST::getDepthValues)
//                .reduce(result, Double2::add);
//        result.divide(M);
//        return result;
//    }
}
