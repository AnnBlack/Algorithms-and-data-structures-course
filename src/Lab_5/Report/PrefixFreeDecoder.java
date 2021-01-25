package Lab_5.Report;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

public class PrefixFreeDecoder
{
    private Node root = new Node(null, 0);
    private String encodedBits = "";

    private class Node
    {
        private Scanner scan;
        char ch;
        Node left, right, father;
        int bit;
        Node(Node father, int bit){
            this.father = father;
            this.bit = bit;
            scan = new Scanner(System.in);
            System.out.println("type the next char");
            String myString = scan.next();
            ch = myString.charAt(0);
            if(ch == '*'){
                left = new Node(this, 0);
                right = new Node(this, 1);
            }
        }
        boolean isInternal(){ return false; }
    }

    private void encode(Node focusNode,char targetChar)
    {
        if(focusNode != null)
        {
            if(focusNode.ch == targetChar){
                Node tempNode = focusNode;
                Stack<Integer> bitsReversed = new Stack<Integer>();
                while(tempNode.father != null){
                    bitsReversed.push(tempNode.bit);
                    tempNode = tempNode.father;
                }
                while(!bitsReversed.empty()){
                    String popped = bitsReversed.pop().toString();
                    encodedBits += popped;
                }
            }
            encode(focusNode.left, targetChar);
            encode(focusNode.right, targetChar);
        }
    }

    private static void postOrderTraversal(Node focusNode)
    {
        if(focusNode != null)
        {
            postOrderTraversal(focusNode.left);
            postOrderTraversal(focusNode.right);
            System.out.print(focusNode.ch + " | ");
        }

    }

    public void postOrderTraversal()
    {
        postOrderTraversal(root);
    }


    public ArrayList<String> encode(String string){
        ArrayList<String> bitsList = new ArrayList<String>();
        for(char c : string.toCharArray()){
            encode(root, c);
            bitsList.add(encodedBits);
            encodedBits = "";
        }
        return bitsList;
    }

    public char decode(String bits){
        Node focusNode = root;
        for(char c : bits.toCharArray()){
            if(c == '0'){
                focusNode = focusNode.left;
            } else if(c == '1') {
                focusNode = focusNode.right;
            }
        }
        return focusNode.ch;
    }

    public String decode(ArrayList<String> bitsList){
        String result = "";
        for(String s : bitsList){
            result += decode(s);
        }
        return result;
    }

    public static int numberOfBits(ArrayList<String> bitsList){
        int numberOfBits = 0;
        for(String s : bitsList){
            for(char c : s.toCharArray()){
                numberOfBits++;
            }
        }
        return numberOfBits;
    }

    public static void main(String[]args){
        PrefixFreeDecoder pfd = new PrefixFreeDecoder();
        // postOrderTraversal(pfd.root);
        System.out.println("\nString 1: ");
        String phrase = "she_sells_sea_shells_by_the_sea_shore";
        ArrayList<String> encodedBits = pfd.encode(phrase);
        System.out.println("Encoded Bits:");
        for(String s : encodedBits){
            System.out.print(s + " ");
        }
        System.out.print("\n\n");
        System.out.println("Decoded Bits:");
        String decodedString = pfd.decode(encodedBits);
        System.out.println(decodedString);

        System.out.println("\nString 2: ");
        String phrase2 = "selly_sells_her_shorts_by_the_seattle_store";
        ArrayList<String> encodedBits2 = pfd.encode(phrase2);
        System.out.println("Encoded Bits:");
        for(String s : encodedBits2){
            System.out.print(s + " ");
        }
        System.out.print("\n\n");
        System.out.println("Decoded Bits:");
        String decodedString2 = pfd.decode(encodedBits2);
        System.out.println(decodedString2);

        System.out.println("\nNumber of bits in the encoded String: " + numberOfBits(encodedBits2));
    }



}

