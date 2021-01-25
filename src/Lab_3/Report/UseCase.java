package Lab_3.Report;

import java.security.SecureRandom;
import java.util.Formatter;

public abstract class UseCase {
    public static final int kmax = 14;
    public static final int Nmax = (int) Math.round(Math.pow(2, kmax));
    public static final int M = 20;  // sample size

    // instance variables:
    private InputCase inputCase;
    private int size;
    private int iterations; // for averaging a sample
    protected int comp, copy; // results to be written by sub-class
    protected Comparable [] arrayForTests;

    abstract void sortAndCount();

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
        sortAndCount();
        String results;
        results = String.format("%9s %16s", comp, copy);
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
            System.out.println(f.format("%1s%10s%10s", "|---- N ----", "|---- #(cmp) ----", "|---- #(cpy) ----|"));
            String format = "suitable format";
            for(int k = 1; k <= kmax; k++) {
                N *= 2;
                UseCase usecase = null;
                if (sortCase.equals("QuickSortCase")){
                    usecase = new QuickSortCase(tableCase, N);
                } else if (sortCase.equals("SelectionSortCase")){
                    usecase = new SelectionSortCase(tableCase, N);
                } else if(sortCase.equals("MergeSortCase")){
                    usecase = new MergeSortCase(tableCase, N);
                } else if (sortCase.equals("InsertionSortCase")) {
                    usecase = new InsertionSortCase(tableCase, N);
                }
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