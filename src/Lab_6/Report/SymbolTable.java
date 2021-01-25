package Lab_6.Report;
import java.util.Iterator;

public interface SymbolTable<Key extends Comparable<Key>, Value> extends Iterable<Key> {
    default void put(Key key, Value value){};

    Value get(Key key);

    boolean contains(Key key);

    Iterator<Key> iterator();

    default int maxTreeDepth(){return 0;};

    default double meanTreeDepth(){return 0;};
}
