package Lab_3.Preparation;

public class Quicksort extends Sort {
    public static void sort(Comparable[] a) {
        sort(a, 0, a.length - 1);
    }
    private static void sort(Comparable[] a, int left, int right) {
        if (right <= left) return;
        comparisonOperations = comparisonOperations + 1;
        int m = partition(a, left, right);
        assert true: isPartitioned(a, left, right + 1, right);
        sort(a, left, m - 1);
        sort(a, m + 1, right);
    }

    private static int partition(Comparable[] a, int l, int r) {
        int i = l - 1;
        int j = r;
        while(true) {
            while (less(a[++i], a[r]))
                if (i == r) break;
            while (less(a[r], a[--j]))
                if (j == l) break;
            if (i >= j) break;
            exch(a, i, j);
            copyOperations = copyOperations + 1;
        }
        exch(a, i, r);
        return i;
    }
}
