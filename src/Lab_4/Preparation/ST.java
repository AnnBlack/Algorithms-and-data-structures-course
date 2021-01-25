package Lab_4.Preparation;

public class ST<Key extends Comparable<Key>, Value> {
    private Value[] vals;
    private Key[] keys;
    private int N = 0;
    public ST(int maxN) {
        keys = (Key[]) new Object[maxN];
        vals = (Value[]) new Object[maxN];
    }
    private void put(Key key, Value val) {
        int i;
        for (i = 0; i < N; i++)
            if (key.equals(keys[i])) break;
        vals[i] = val;
        keys[i] = key;
        if (i == N) N++;
    }

    public Value get(Key key) {
        for (int i = 0; i < N; i++) if (keys[i].equals(key))
            return vals[i];
        return null;
    }
    public boolean contains(Key key) {
        return false;
    }
    public void remove(Key key) {

    }
}