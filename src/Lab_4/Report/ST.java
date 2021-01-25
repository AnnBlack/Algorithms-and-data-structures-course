package Lab_4.Report;

import java.util.Iterator;

interface ST<Key extends Comparable<Key>, Value> {
    public void put(Key key, Value val);
    public Value get(Key key);
    public boolean contains(Key key);
    public Iterator<Key> iterator();
    public int maxTreeDepth();
    public double meanTreeDepth();
}