package Lab_4.Report;

import java.security.SecureRandom;
import java.util.Formatter;

public abstract class UseCase {
    public static final int kmax = 15;
    public static final int Nmax = (int) Math.round(Math.pow(2, kmax));
    public static final int M = 20;  // sample size

    // instance variables:
    private InputCase inputCase;
    private int size;
    private int iterations; // for averaging a sample
    public int maxTreeDepth;
    public double meanTreeDepth;
    protected Integer [] arrayForTests;

    abstract void sortAndCount();

    protected UseCase(InputCase input, int kmax) {
        // initialise instance variables
        this.size = kmax;
        this.inputCase = input;
        if (input == InputCase.BSTCASE){
            iterations = M;
        } else {
            iterations = 1;
        }
        switch(input){
            case REDBLACK:
            case BSTCASE:
                initRandom();
                break;
            default:
                ;
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
        sortAndCount();
        String results;
        results = String.format("%28s %15s", meanTreeDepth, maxTreeDepth);
        return results;
    }

    public void writeResults(String format) {
        System.out.print(size + "      "); // first part of suitable format
        format= "      |";
        int sub = (int) Math.ceil( Math.log10( (int) size));
        format= format.substring(sub);   // skip part consumed
        System.out.print (format);
        System.out.println(getResults(format));
    }

    public static void makeTable(String sortCase) {
        for (InputCase tableCase : InputCase.values()) {
            int N = 1;
            Formatter f = new Formatter();
            System.out.println(tableCase + " case:");
            System.out.println(f.format("%1s%28s%15s", "|---- N ----", "|---- #(meanTreeDepth) ----", "|---- #(maxTreeDepth) ----|"));
            String format = "suitable format";
            for (int k = 1; k <= kmax; k++) {
                N *= 2;
                UseCase usecase = null;
                if (sortCase.equals("BST")) {
                    usecase = new BSTCase(tableCase, N);
                } else if (sortCase.equals("Red-Black")) {
                    usecase = new RedBlackTreeCase(tableCase, N);
                }
                // check for all sort cases and instantiate them accordingly!
                if (usecase != null) {
                    usecase.writeResults(format);
                }
            }

            System.out.println("");
        }
    }

    public static void main(String arg [] ) {
        makeTable("BST");
        //makeTable("Red-Black");
    }
}