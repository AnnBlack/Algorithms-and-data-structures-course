package Lab_6.Report;

import java.util.Iterator;
import java.util.Stack;

//public class BST<Key extends Comparable<Key>,Value> implements SymbolTable<Key,Value>
//{
//    // insert your code here
//
//    // this code is for compilabililty only
//
//    public Value get(Key key){return null;};
//
//    public boolean contains(Key key){return false;};
//
//    public Iterator<Key> iterator(){return null;};
//}

public class BST<Key extends Comparable<Key>, Value> implements SymbolTable<Key, Value> {
    private Node root;
    private class Node {
        public Key key;
        public Value val;
        public Node left, right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    public BST() {
    }

    public int size() {
        return size(root);
    }
    private int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    public Value get(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return x.val;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return null;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) return new Node(key, val, 1);
        int cmp = key.compareTo(x.key);
        if (cmp == 0) x.val = val;
        else if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public boolean contains(Key key) {
        Node x = root;
        while (x != null ){
            int cmp = key.compareTo(x.key);
            if (cmp == 0) return  x.val != null;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
        return false;
    }

    public void remove(Key key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) x = null ;
            else if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
        }
    }

    public int maxTreeDepth() {
        return findHeight(root);
    }

    private int findMax(int a, int b){
        return Math.max(a, b);
    }

    private int findHeight(Node root) {
        if(root == null) return 0;
        return findMax(findHeight(root.left) + 1, findHeight(root.right));
    }

    public double meanTreeDepth() {
        int depth = maxTreeDepth();
        int sum = 0;
        for (int i = 0; i < depth; i++) {
            sum += i * (depth - i);
        }
        return sum / depth;
    }

    public Iterator<Key> iterator() {
        return new BSTIterator();
    }
    private class BSTIterator implements Iterator<Key> {
        private Stack<Node> stack = new Stack<Node>();

        private void pushLeft(Node x) {
            while (x != null) {
                stack.push(x);
                x = x.left;
            }
        }
        BSTIterator() {
            pushLeft(root);
        }
        public boolean hasNext() {
            return !stack.isEmpty();
        }
        public Key next() {
            Node x = stack.pop();
            pushLeft(x.right);
            return x.key;
        }
    }

    public Key min() {
        return min(root).key;
    }

    private Node min(Node x) {
        if (x.left == null) return x;
        return min(x.left);
    }

    public Key floor(Key key) {
        Node x = floor(root, key); if (x == null) return null; return x.key;
    }

    private Node floor(Node x, Key key) {
        if (x == null) return null;
        int cmp = key.compareTo(x.key);
        if (cmp == 0) return x;
        if (cmp < 0) return floor(x.left, key); Node t = floor(x.right, key);
        if (t != null) return t;
        else return x;
    }
}