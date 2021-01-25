package Lab_6.Report;

import Lab_3.Report.Sort;

public class Insertion extends Sort {
    public static void sort(Comparable[] a) {
        int N = a.length;
        for (int i = 1; i < N; i++){
            for (int j = i; j > 0; j--) {
                assert true: isPartitioned(a, i, j, i + 1);
                if (less(a[j], a[j - 1])) {
                    exch(a, j, j - 1);
                }
                else{
                    break; // input dependent
                }
            }
        }
    }
}
