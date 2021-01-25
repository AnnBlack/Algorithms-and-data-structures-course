package Lab_3.Report;

public class Mergesort extends Sort {
    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length);
    }
    private static void sort(Comparable[] a,
                             Comparable[] aux, int lo, int hi) {
        if (hi <= lo + 1) return;
        int m = lo + (hi - lo) / 2;
        sort(a, aux, lo, m);
        sort(a, aux, m, hi);
        merge(a, aux, lo, m, hi);
    }
    private static void merge(Comparable[] a,
                              Comparable[] aux, int l, int m, int r) {
        for (int k = l; k < r; k++) {
            copy(aux, a, k, k); // aux[k] = a[k]; <-- replaced
        }
        int i = l, j = m;
        for (int k = l; k < r; k++)
            if (i >= m) {
                copy(a, aux, k, j++); // a[k] = aux[j++];
            }
            else if (j >= r) {
                copy(a, aux, k, i++); // a[k] = aux[i++];
            }
            else if (less(aux[j], aux[i])) {
                copy(a, aux, k, j++); // a[k] = aux[j++];
            }
            else {
                copy(a, aux, k, i++); // a[k] = aux[i++];
            }
    }
}