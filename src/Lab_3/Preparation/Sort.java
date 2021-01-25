package Lab_3.Preparation;

public abstract class Sort {
    public static int comparisonOperations;
    public static int copyOperations;

    protected static void exch(Comparable[] a, int i, int j) {
        Comparable swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    protected static boolean less(Comparable v, Comparable w) {
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

    public static int getComparisonOperations() {
        return comparisonOperations;
    }

    public static int getCopyOperations() {
        return copyOperations;
    }
}