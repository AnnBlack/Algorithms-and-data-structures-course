package Lab_2.Report;

import java.util.Stack;

public class Tree
{
    private BiNode root;
    private Stack stackForInvert;
    private String infix;
    private Stack stack2;
    private ArithmeticTerm term;

    private class BiNode {
        public String item;
        public BiNode left, right;
        BiNode(String item) {
            this(item, null, null);
        }
        BiNode(String item, BiNode left, BiNode right) {
            this.item = item;
            this.right = right;
            this.left = left;
        }
        public String getItem() {
            return item;
        }
    }

    public Tree(BiNode root) {
        this.root = root;
        this.stackForInvert = new Stack();
        this.stack2 = new Stack();
        this.infix = "";
    }
    public Tree(String postfix) {
        this.root = construct(postfix);
        this.stack2 = new Stack();
        this.infix = "";
        this.term = new ArithmeticTerm(postfix);
    }

    private BiNode construct(String postfix) {
        Stack<BiNode> stack = new Stack<>();
        String[] tokens = postfix.split(" ");
        try {
            for(String token : tokens) {
                if(token.equals("+") || token.equals("-")|| token.equals("*") || token.equals("/") ||  token.equals("^")) {
                    BiNode right = stack.pop();
                    BiNode left = stack.pop();
                    BiNode node = new BiNode(token, left, right);
                    stack.push(node);
                } else if(token.equals("sqrt")) {
                    BiNode left = new BiNode("sqrt");
                    BiNode right = stack.pop();
                    BiNode node = new BiNode("sqrt", left, right);
                    stack.push(node);
                } else if(token.equals("sin")) {
                    BiNode left = new BiNode("sin");
                    BiNode right = stack.pop();
                    BiNode node = new BiNode("sin", left, right);
                    stack.push(node);
                } else {
                    stack.push(new BiNode(token));
                }
            }
        }
        catch (Exception e) {
            System.out.println("It seems there was a typo error in the provided string");
        }
        return stack.pop();
    }

    private void inorderTraversal(BiNode node) {
        if(node.left != null) {
            inorderTraversal(node.left);
        }
        if(node.right != null) {
            inorderTraversal(node.right);
        }
        stackForInvert.push(node.item);
    }

    public void inorderTraversal() {
        this.inorderTraversal(this.root);
    }

    private static boolean isOperand(char x) {
        return x >= '0' && x <= '9';
    }

    private void infixGenerator(BiNode node) {
        if (node == null) return;
        if (node.left != null && node.right != null && node.item != null) {
            if (node.item.equals("+") || node.item.equals("-") || node.item.equals("*") || node.item.equals("/") || node.item.equals("^") || node.item.equals("sqrt") || node.item.equals("sin")) {
                stack2.push("(");
            }
            infixGenerator(node.left);
            // if node.item is NUMBER -> OPERAND in our case
            if(isOperand(node.left.getItem().charAt(0))) {
                stack2.push(node.left.getItem());
            }
            stack2.push(node.item);
            infixGenerator(node.right);
            if(isOperand(node.right.getItem().charAt(0))) {
                stack2.push(node.right.getItem());
            }
            if (node.item.equals("+") || node.item.equals("-") || node.item.equals("*") || node.item.equals("/") || node.item.equals("^") || node.item.equals("sqrt") || node.item.equals("sin")) {
                stack2.push(")");
            }
        }
    }

    public void infixGenerator() {
        this.infixGenerator(this.root);
        for (Object st: stack2) {
            infix += st.toString() + " ";
        }
        System.out.println(infix);
    }

    public static void main2(String[] args) {
        // TEST 1: (5 * (4 + 3))

        // Remove the comments below:

//        BiNode three = new BiNode ("3");
//        BiNode four = new BiNode ("4");
//        BiNode five = new BiNode ("5");
//        BiNode plus = new BiNode ("+", four, three);
//        BiNode root = new BiNode ("*", five, plus);
//        Tree tree = new Tree(root);
//        tree.infixGenerator();
//        System.out.println("expected: " + "(5 * (4 + 3))");
//        ArithmeticTerm term = new ArithmeticTerm(tree.infix);
//        System.out.println(term.evaluate());

        // TEST 2  ( ( ( sqrt 4 ) * ( 2 ^ 3 ) ) / 2 )

        // Remove the comments below:

//        BiNode two = new BiNode("2");
//        BiNode three = new BiNode ("3");
//        BiNode four = new BiNode ("4");
//        BiNode sqrt = new BiNode("sqrt");
//        BiNode power = new BiNode ("^", two, three);
//        BiNode square = new BiNode ("sqrt", sqrt, four);
//        BiNode multiply = new BiNode ("*", square, power);
//        BiNode root = new BiNode("/", multiply, two);
//        Tree tree = new Tree(root);
//        tree.infixGenerator();
//        System.out.println("expected: " + "( ( ( sqrt 4 ) * ( 2 ^ 3 ) ) / 2 )");
//        ArithmeticTerm term = new ArithmeticTerm(tree.infix);
//        term.convert();
//        System.out.println(term.evaluate());
    }

    public static void main(String[] args) {
        Tree tree1 = new Tree("5.1 9 8.88 + 4 sqrt 6 / ^ 7 - *");
        tree1.infixGenerator();
        System.out.println(tree1.term.evaluate());

        Tree tree2 = new Tree("4 sqrt 2 15 8 + 7 - * / 2 ^");
        tree2.infixGenerator();
        System.out.println(tree2.term.evaluate());

        Tree tree3 = new Tree("4.0 3.141592653589793 * 3.0 / sin 2.718281828459045 -1.0 2.0 sqrt 1.0 - * 8.0 / ^ * 6.0 3.141592653589793 * sqrt / ");
        tree3.infixGenerator();
        System.out.println(tree3.term.evaluate());

        Tree tree4 = new Tree("4.3 0.1 * 0.4 - ");
        tree4.infixGenerator();
        System.out.println(tree4.term.evaluate());

        Tree tree5 = new Tree("1.0 2.0 3.0 + 4.0 5.0 * * + ");
        tree5.infixGenerator();
        System.out.println(tree5.term.evaluate());

        Tree tree6 = new Tree("8.0 4.0 / 10.0 + 6.0 / 2.0 ^ sqrt");
        tree6.infixGenerator();
        System.out.println(tree6.term.evaluate());
    }
}


