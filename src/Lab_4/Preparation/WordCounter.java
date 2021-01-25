package Lab_4.Preparation;

import java.util.HashSet;
import java.util.Iterator;

public class WordCounter {
    private BST<Integer, HashSet<String> > inverted;
    private BST<String, Integer> counts;

    /**
     * Create a WordCounter
     */
    public WordCounter()
    {
        counts = new BST<String, Integer>();
        updateInverted(); //instance variable
    }

    public void addWords(HashSet<String> input) {
        for(String word : input) {
            addWord(word);
        }
    }

    public void addWord(String word) {
        int defaultCount = 0;
        if(counts.contains(word)){
            defaultCount = counts.get(word);}
        // get(word) would return null if word is not in the counts HashMap
        // but we want to get returned 0 if word is not ... " ....
        // getOrDefault(word, 999) will return 999 if word is not ... " ....
        counts.put(word, defaultCount + 1);
        updateInverted();
    }

    public HashSet<String> getWords() {
        Iterator iterator = counts.iterator();
        HashSet<String> set = new HashSet<String>();
        while(iterator.hasNext()) {
            set.add(iterator.next().toString());
        }
        return set;
    }

    public HashSet<String> getWordsInverted() {
        Iterator iterator = inverted.iterator();
        HashSet<String> set = new HashSet<String>();
        while(iterator.hasNext()) {
            set.add(iterator.next().toString());
        }
        return set;
    }

    private void updateInverted()
    // allways to be called after changing counts!!
    // in order to keep inverted consistent!!!
    {
        inverted = new BST<Integer, HashSet<String>>();
        for (String word: getWords()){
            int counter = counts.get(word); // returns the number of occurencies of the word
            // i.e. the y-values in the histogram !
            HashSet<String> tmp = getWords();// returns the hashSet of words
            // in inverted for the y-value
            // or null if that integer is not in the keys of inverted
            if(tmp == null) tmp = new HashSet<String>();
            // inverted.getOrDefault(counter, new HashSet<String>());
            tmp.add(word);
            inverted.put(counter, tmp);
        }
    }

    public BST<Integer, HashSet<String>> calculateInverted() {
        BST<Integer, HashSet<String>> inverted = new BST<Integer, HashSet<String>>();
        for (String word: getWords()){
            int counter= counts.get(word); // returns the number of occurencies of the word
            // i.e. the y-values in the histogram !
            HashSet<String> tmp = getWords();// returns the hashSet of words
            // in inverted for the y-value
            // or null if that integer is not in the keys of inverted
            if(tmp == null) tmp = new HashSet<String>();
            // inverted.getOrDefault(counter, new HashSet<String>());
            tmp.add(word);
            inverted.put(counter, tmp);
        }
        return inverted;
    }

    public void print() {
        System.out.println(getWords());
        System.out.println(getWordsInverted());
        System.out.println("maxTreeDepth: " +  counts.maxTreeDepth());
        System.out.println("meanTreeDepth: " + counts.meanTreeDepth());

    }
}