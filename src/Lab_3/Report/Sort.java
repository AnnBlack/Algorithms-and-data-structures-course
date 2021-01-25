package Lab_3.Report;

public abstract class Sort {
    public static int comparisonOperations;
    public static int exchangeOperations;
    public static int copyOperations;

    protected static void exch(Comparable[] a, int i, int j) {
        exchangeOperations++;
        copyOperations++;
        Comparable swap = a[i];
        //copyOperations++;
        //copy(a, a, i, j);
        a[i] = a[j];
        //copyOperations++;
        //copy(a, a, j, i);
        a[j] = swap; // a[j] = swap;
    }

    protected static boolean less(Comparable v, Comparable w) {
        comparisonOperations++;
        return (v.compareTo(w) < 0);
    }

    protected static boolean isSorted(Comparable a[], int left, int right) {
//        for(int i = 0; i < a.length - 1; i++) {
//            if(less(a[left], a[right])) return true;
//        }
//        return false;
//
        for(int i = left; i <= right; i++) {
            for(int j = i + 1; j <= right; j++) {
                if(less(a[j], a[i])) {
                    return false;
                }
                comparisonOperations = comparisonOperations + 1;
            }
        }
        return true;
    }

    public static boolean isPartitioned(Comparable a[], int left, int border, int right) {

        boolean leftIsSorted = isSorted(a, left, border - 1);
        boolean rightISSorted = isSorted(a, border, right);

        if(leftIsSorted && rightISSorted) {
            return true;
        } else {
            return false;
        }

    }

    public static void copy(Comparable[] aux, Comparable[] a, int k, int j) {
        copyOperations++;
        aux[k] = a[j];
    }

    public static int getComparisonOperations() { return comparisonOperations; }
    public static int getCopyOperations() { return copyOperations; }
    public static int getExchangeOperations() { return exchangeOperations; }
    public static void reset(){ comparisonOperations = 0; copyOperations = 0; exchangeOperations = 0; }
}