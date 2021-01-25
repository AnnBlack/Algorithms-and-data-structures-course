package Lab_6.Preparation;

import java.util.ArrayList;
import java.util.HashMap;

public class Hash {
    ArrayList<String> list = new ArrayList<String>();
    int a = 13;
    int M = 31;
    private static HashMap<Character, Integer> table = new HashMap();

    private Hash() {
        this.list.add("he");
        this.list.add("is");
        this.list.add("Art");
        this.list.add("has");
        this.list.add("Hat");
        this.list.add("this");
        fillTable();
    }

    private void fillTable() {
        table.put('_', 0);
        int i = 1;
        for(char ch = 'a'; ch <= 'z'; ch++) {
            //System.out.println(ch +" " +i);
            table.put(ch, i++);
        }
        for(char ch = 'A'; ch <= 'Z'; ch++) {
            table.put(ch, i++);
        }
    }

    // https://www.javatpoint.com/how-to-reverse-string-in-java
    private static String reverseString(String str){
        StringBuilder sb = new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }

    private int getKey(char ch) {
        return table.get(ch);
    }

    private int getHornersCode(String str) {
        int hash = 0;
        str = reverseString(str);
        for(int i = 0; i < str.length(); i++) {
            hash += Math.pow(53, i) * getKey(str.charAt(i));
        }
        return hash;
    }

    private int getHash(String str) {
        return a * getHornersCode(str) % M;
    }

    public void print() {
        for (String s : list) {
            System.out.println(s + " hash code:  " + getHash(s));
        }
    }

    public static void main(String[] args) {
        Hash test = new Hash();
        test.print();
    }
}
