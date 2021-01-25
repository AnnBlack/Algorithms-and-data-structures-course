package Lab_4.Report;

/**
 * Class Record defines simple records consisting of an integer and a string.
 * Sorting shall be done with respect to the integer.
 * Test of stability of divers sorting algorithms.
 *
 * @author Purtova Anastasiia
 *
 */
public class Record implements Comparable<Record> {
    private Integer id;
    private String name;

    /**
     * Constructor for objects of class Record
     */
    public Record(int i, String str) {
        id = i;
        name = str;
    }

    @Override
    public String toString() {
        return id + ": " + name;
    }

    public int getId() {
        return id;
    }

    public int compareTo(Record rec) {
        return name.compareTo(rec.name); //alternative sorting w.r.t. the string
        //return id.compareTo(rec.id);
    }
}
