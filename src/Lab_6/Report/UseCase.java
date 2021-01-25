package Lab_6.Report;

import java.security.SecureRandom;
import java.util.Formatter;
import java.util.stream.IntStream;

public abstract class UseCase {
    public static final int kmax = 17;
    public static final int Nmax = (int) Math.round(Math.pow(2, kmax));
    public static final int M = 20;  // sample size

    // instance variables:
    private InputCase inputCase;
    private int size;
    private int iterations; // for averaging a sample
    protected int comp, copy; // results to be written by sub-class
    protected Comparable [] arrayForTests;

    abstract Double2 sortAndCount(int size);

    protected UseCase(InputCase input, int kmax) {
        // initialise instance variables
        this.size = kmax;
        this.inputCase = input;
        if (input == InputCase.AVERAGE){
            iterations = M;
        } else {
            iterations = 1;
        }
        switch(input){
            case SORTED:
                initAscending();
                break;
            case REVERSE:
                initDescending();
                break;
            case RANDOM:
            case AVERAGE:
                initRandom();
                break;

            default:
                ;
        }
    }
    public void initAscending() {
        this.arrayForTests = new Integer[size];
        for (int i = 1; i <= size; i++) {
            this.arrayForTests[i - 1] = i;
            // System.out.println(this.arrayForTests[i - 1]);
        }
    }
    public void initDescending() {
        this.arrayForTests = new Integer[size];
        for (int i = 0; i < size; i++){
            this.arrayForTests[i] = size - i;
            // System.out.println(this.arrayForTests[i]);
        }
    }
    public void initRandom() {
        this.arrayForTests = new Integer[size];
        for (int i = 0; i < size; i++){
            SecureRandom randomInt = new SecureRandom();
            randomInt.setSeed(M);
            this.arrayForTests[i] = randomInt.nextInt(size * 2);
            // System.out.println(this.arrayForTests[i]);
        }
    }

    @Override
    public String toString() {
        return inputCase + " case for size " + size + ":";
    }

    String getResults(String format) {
        long start = System.currentTimeMillis();

        Double2 res = new Double2();
        IntStream stream = IntStream.range(0, M);
        res = stream.parallel().boxed()
                //result = stream.sequential().boxed()
                .map(i-> new ConcurrentRandomBST(size))
                .map(ConcurrentRandomBST::getDepthValues)
                .reduce(res, Double2::add);
        res.divide(M);


        //Double2 res = sortAndCount(size);
        String results;
        long end = System.currentTimeMillis();
        results = String.format("%9s %16s %16s", res.getFirst(), res.getSecond(), end - start);
        return results;
    }

    public void writeResults(String format) {
        System.out.print(size + "      "); // first part of suitable format
        format = "      |";
        int sub = (int) Math.ceil( Math.log10( (int) size));
        format = format.substring(sub);   // skip part consumed
        System.out.print(format);
        System.out.println(getResults(format));
    }

    public static void makeTable(String sortCase) {
        for (InputCase tableCase: InputCase.values()){
            int N = 1;
            Formatter f = new Formatter();
            System.out.println(tableCase + " case:");
            System.out.println(f.format("%1s%10s%10s", "|---- N ----", "|---- #(cmp) ----", "|---- #(cpy) ----|, | --- Time --- | "));
            String format = "suitable format";
            for(int k = 1; k <= kmax; k++) {
                N *= 2;
                UseCase usecase = new InsertionSortCase(tableCase, N);

                // check for all sort cases and instantiate them accordingly!
                if(usecase != null){
                    usecase.writeResults(format);
                }
            }
        }
        System.out.println ("");
    }

    public static void main(String arg [] ) {
        makeTable("InsertionSortCase");
    }
}