package Lab_4.Report;

import java.util.Iterator;
import java.util.Stack;

public class RedBlackTree<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    int max = 0;
    int sumDepths = 0;
    int numNodes = 0;
    private Node root;
    private class Node {
        Key key;
        Value val;
        Node left, right;
        boolean color;
        int N;


        Node(Key key, Value val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.N = N;
        }
    }
    public RedBlackTree() {
    }

        public int size() {
            return size(root);
        }
        private int size(Node x) {
            if (x == null) return 0;
            else return x.N;
        }

        private boolean isRed(Node x) {
            if (x == null) return false;
            return (x.color == RED);
        }

        public void put(Key key, Value val) {
            root = put(root, key, val);
            root.color = BLACK;
        }

        private Node put(Node h, Key key, Value val) {
            if (h == null)
                return new Node(key, val, 1, RED);
            int cmp = key.compareTo(h.key);
            if (cmp < 0) h.left = put(h.left, key, val);
            else if (cmp > 0) h.right = put(h.right, key, val);
            else h.val = val;
            if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
            if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
            if (isRed(h.left) && isRed(h.right)) flipColors(h);
            h.N = size(h.left) + size(h.right) + 1;
            return h;
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

        private void flipColors(Node h) {
            h.color = RED;
            h.left.color = BLACK;
            h.right.color = BLACK;
        }

        private Node rotateLeft(Node h) {
            Node x = h.right;
            h.right = x.left;
            x.left = h;
            x.color = h.color;
            h.color = RED;
            x.N = h.N;
            h.N = 1 + size(h.left) + size(h.right);
            return x;
        }
        private Node rotateRight(Node h) {
            Node x = h.left;
            h.left = x.right;
            x.right = h;
            x.color = h.color;
            h.color = RED;
            x.N = h.N;
            h.N = 1 + size(h.left) + size(h.right);
            return x;
        }

        public Iterator<Key> iterator() {
            return new RedBlackBSTIterator();
        }
        private class RedBlackBSTIterator implements Iterator<Key> {
            private Stack<Node> stack = new Stack<Node>();

            private void pushLeft(Node x) {
                while (x != null) {
                    stack.push(x);
                    x = x.left;
                }
            }
            RedBlackBSTIterator() {
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

        private Node insert(Node h, Key key, Value val) {
            if (h == null)
                return new Node(key, val, 1, RED);
            if (isRed(h.left))
                if (isRed(h.left.left))
                    h = splitFourNode(h);
            int cmp = key.compareTo(h.key);
            if (cmp == 0) h.val = val;
            else if (cmp < 0)
                h.left = insert(h.left, key, val);
            else
                h.right = insert(h.right, key, val);
            if (isRed(h.right))
                h = leanLeft(h);
            return h;
        }
        private Node splitFourNode(Node h) {
            Node x = rotateRight(h);
            x.left.color = BLACK;
            return x;
        }
        private Node leanLeft(Node h) {
            Node x = rotateLeft(h);
            x.color = x.left.color;
            x.left.color = RED;
            return x;
        }

        public void remove (Key key) {
            Node x = root;
            while (x != null)
            {
                int cmp = key.compareTo(x.key);
                if (cmp == 0) x = null ;
                else if (cmp < 0) x = x.left;
                else if (cmp > 0) x = x.right;
            }

        }

        private void traverse(Node node, int depth) {
            sumDepths += depth - 1;
            numNodes++;
            if(depth > max) {
                max = depth;
            }
            if(node.left != null) traverse(node.left, depth + 1);
            if(node.right != null) traverse(node.right, depth + 1);
        }
        public int maxTreeDepth() {
            return maxTreeDepth(root);
        }
        public int numOfNodes() {
            return numNodes;
        }
        public double meanTreeDepth() {
            max = 0;
            sumDepths = 0;
            numNodes = 0;
            traverse(root, max + 1);
            return (double)sumDepths / numNodes;
        }

        private int maxTreeDepth(Node node) {
            if(node == null) {
                return 0;
            }
            else {
                int lDepth = maxTreeDepth(node.left);
                int rDepth = maxTreeDepth(node.right);

                if (lDepth > rDepth)
                    return (lDepth + 1);
                else
                    return (rDepth + 1);
            }
        }
    }


