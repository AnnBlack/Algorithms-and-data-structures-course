package Lab_6.Report;

public class Double2 {
    private double first,second;

    Double2(double first, double second) {
        this.first = first;
        this.second = second;
    }

    public Double2() {
        this(0.,0.);
    }

    public double getFirst() {
        return first;
    }

    public double getSecond() {
        return second;
    }

    public static Double2 add(Double2 arg1, Double2 arg2) {
        Double2 result = new Double2();
        result.first = arg1.first + arg2.first;
        result.second = arg1.second + arg2.second;
        return result;
    }

    public void divide(double denominator) {
        first = first / denominator;
        second = second / denominator;
    }
}
