package Lab_5.Report;

public class Huffman {
    private static String binaryCodeString;
    Node root;
    String encodedTree;
    static String[] str;

    private Huffman(String s) {
        int[] freq = new int[128];
        for (int i = 0; i < s.length(); i++) {
            freq[s.charAt(i)]++;
            // System.out.println(s.charAt(i) + "  : " + freq[s.charAt(i)]);
        }
        root = buildTrie(freq);
        encodedTree = new String("");
        binaryCodeString = new String("");
        str = new String[128];
    }

    private static class Node implements Comparable<Node> {
        // Huffman trie node
        private char ch;   // unused for internal nodes
        private int freq;  // unused for expand
        private final Node left, right;
        private int R = 256;
        Node(char ch, int freq, Node left, Node right) {
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

    public static Node buildTrie(int[] freq) {
        // Initialize priority queue with singleton trees.
        MinPQ<Node> pq = new MinPQ<Node>();
        for (char i = 0; i < 128; i++)
            if (freq[i] > 0) {
                pq.insert(new Node(i, freq[i], null, null));
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

    private static String[] buildCodewordTable(Node root)
    {
        String[] st = new String[128];
        buildCode(st, root, "");
        return st;
    }

    private static void buildCode(String[] st, Node x, String s)
    {
        if (x.isLeaf()) {
            System.out.println(x.ch + " : " + s);
            binaryCodeString = binaryCodeString + s;
            st[x.ch] = s;
            return;
        }
        buildCode(st, x.left,  s + '0');
        buildCode(st, x.right, s + '1');
    }

    public void printCode(Node root) {
        binaryCodeString = new String("");
        str = buildCodewordTable(root);
    }

    public void printNumberOfBits(Node root) {
        int numberOfBits = 0;
        for(String s : str){
            if(s != null) {
                for(char c : s.toCharArray()){
                    numberOfBits++;
                }
            }
        }
        System.out.println("numberOfBits: " + numberOfBits);
    }

    public void printDecoded(Node n) {
        if(n != null && n.left != null && n.right != null){
            encodedTree = encodedTree + "*";
        }
        if(n != null ){
            if (n.left != null) {
                encodedTree = encodedTree + n.left.ch;
                //System.out.println(n.left.ch);
                printDecoded(n.left);
            }
            if (n.right != null) {
                encodedTree = encodedTree + n.right.ch;
                //System.out.println(n.right.ch);
                printDecoded(n.right);
            }
        }
    }

    public static void main(String[] args) {
        Huffman tree = new Huffman("she_sells_sea_shells_by_the_seashore");
        Huffman tree1 = new Huffman("selly_sells_her_shorts_by_the_seattle_store");
        Huffman tree2 = new Huffman("abracadabra!");

        tree.printDecoded(tree.root);
        System.out.println(tree.encodedTree);
        tree.printCode(tree.root);
        System.out.println("bitstream: " + binaryCodeString);
        tree1.printNumberOfBits(tree.root);

        System.out.println();

        tree1.printDecoded(tree1.root);
        System.out.println(tree1.encodedTree);
        tree1.printCode(tree1.root);
        System.out.println("bitstream: " + binaryCodeString);
        tree1.printNumberOfBits(tree1.root);

        System.out.println();

        System.out.println("abracadabra!");
        tree2.printDecoded(tree2.root);
        System.out.println(tree2.encodedTree);
        tree2.printCode(tree2.root);
        System.out.println("bitstream: " + binaryCodeString);
        tree2.printNumberOfBits(tree2.root);
    }

}

    /*
    public static void printFromPreOrder(Node n, String dashes) {
        if (n.ch != ' ') {
            System.out.println(dashes + n.freq + ":" + n.ch);
        }
        else {
            System.out.println(dashes + n.freq);
        }
        if (n.left != null) {
            printFromPreOrder(n.left, dashes + "-");
        }
        if (n.right != null) {
            printFromPreOrder(n.right, dashes + "-");
        }
    }

        public static void writeTrie(Node x)
    {  // Write bitstring-encoded trie.
        if (x.isLeaf())
        {
            binaryCodeString = binaryCodeString + str[x.ch];
            System.out.println(str[x.ch]);
            return;
        }
        writeTrie(x.left);
        writeTrie(x.right);
    }

    public static void compress(String s)
    {
        // Read input.
        char[] input = s.toCharArray();
        // Tabulate frequency counts.
        int[] freq = new int[128];
        for (int i = 0; i < input.length; i++)
            freq[input[i]]++;
        // Build Huffman code trie.
        Node root = buildTrie(freq);
        // Build code table (recursive).
        String[] st = new String[128];
        buildCode(st, root, "");
        // Print trie for decoder (recursive).
        writeTrie(root);
        // Print number of chars.
        System.out.println(input.length);

        // Use Huffman code to encode input.
        /*for (int i = 0; i < input.length; i++)
        {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++)
                if (code.charAt(j) == '1')
                    System.out.println(true);
                else System.out.println(false);
        }
    }
    */

