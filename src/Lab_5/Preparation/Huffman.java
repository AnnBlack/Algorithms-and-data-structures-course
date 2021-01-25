package Lab_5.Preparation;

public class Huffman {

    // alphabet size of extended ASCII
    Integer frequency;
    Node root;
    private static final int R = 256;

    // Do not instantiate.
    private Huffman() { }

    private static class Node implements Comparable<Node> {
        // Huffman trie node
        private char ch;   // unused for internal nodes
        private int freq;  // unused for expand
        private final Node left, right;
        Node(char ch, int freq, Node left, Node right)
        {
            this.ch    = ch;
            this.freq  = freq;
            this.left  = left;
            this.right = right;
        }
        public boolean isLeaf() {
            return left == null && right == null;
        }
        public int compareTo(Node that) {
            return this.freq - that.freq;
        }
    }

    private static Node readTrie() {
        if (BinaryStdIn.readBoolean())
            return new Node(BinaryStdIn.readChar(), 0, null, null);
        return new Node('\0', 0, readTrie(), readTrie());
    }

    public static void expand(String s) {
        Node root = readTrie();
        for (int i = 0; i < s.length(); i++) {  // Expand ith codeword.
            Node x = root;
            while (!x.isLeaf())
                if (BinaryStdIn.readBoolean())
                    x = x.right;
                else x = x.left;
            BinaryStdOut.write(x.ch);
        }
        BinaryStdOut.close();
    }

    private static String[] buildCode(Node root)
    {  // Make a lookup table from trie.
        String[] st = new String[R];
        buildCode(st, root, "");
        return st;
    }
    private static void buildCode(String[] st, Node x, String s)
    {  // Make a lookup table from trie (recursive).
        if (x.isLeaf())
        {  st[x.ch] = s; return; }
        buildCode(st, x.left,  s + '0');
        buildCode(st, x.right, s + '1');
    }

    private static void writeTrie(Node x) {
        // Write bitstring-encoded trie.
        if (x.isLeaf())
        {
            BinaryStdOut.write(true);
            BinaryStdOut.write(x.ch);
            return;
        }
        BinaryStdOut.write(false);
        writeTrie(x.left);
        writeTrie(x.right);
    }

    public static void compress(String s) {
        char[] input = s.toCharArray();
        // Tabulate frequency counts.
        int[] freq = new int[R];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;
        // Build Huffman code trie.
        Node root = buildTrie(freq);
        // Build code table (recursive).
        String[] st = new String[R];
        buildCode(st, root, "");
        // Print trie for decoder (recursive).
        writeTrie(root);
        // Print number of chars.
        BinaryStdOut.write(input.length);
        // Use Huffman code to encode input.
        for (int i = 0; i < input.length; i++)
        {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++)
                if (code.charAt(j) == '1')
                    BinaryStdOut.write(true);
                else BinaryStdOut.write(false);
        }
        BinaryStdOut.close();
    }

    private static Node buildTrie(int[] freq) {
        // Initialize priority queue with singleton trees.
        MinPQ<Node> pq = new MinPQ<Node>();
        for (int i = 0; i < 128; i++)
            if (freq[i] > 0) {
                pq.insert(new Node((char) i, freq[i], null, null));
            }

        while (pq.size() > 1)
        {  // Merge two smallest trees.
            Node x = pq.delMin();
            Node y = pq.delMin();
            Node parent = new Node('\0', x.freq + y.freq, x, y);
            pq.insert(parent);
        }
        return pq.delMin();
    }

    public Huffman(String s) {
        int[] freq = new int[128];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
            System.out.println(s.charAt(i) + "  : " + freq[s.charAt(i)]);
        }
        root = buildTrie(freq);
    }

    public static void main(String[] args) {
        Huffman tree = new Huffman("she sells sea shells by the seashore");
    }

}