package Lab_4.Preparation;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;

/**
 * The test class WordCounterTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class WordCounterTest
{
    /**
     * Default constructor for test class WordCounterTest
     */
    public WordCounterTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @Before
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @After
    public void tearDown()
    {
    }

    @Test
    public void testAddWord()
    {
        WordCounter wordCoun1 = new WordCounter();
        wordCoun1.addWord("a");
        wordCoun1.addWord("a");
        wordCoun1.addWord("b");
        wordCoun1.addWord("c");
        wordCoun1.print();
        HashSet<String> value = new HashSet<String>();
        value.add("a");
        value.add("b");
        value.add("c");
        HashSet<String> set = wordCoun1.getWords();
        assertEquals(set, value);
    }

    @Test
    public void testExampleWord()
    {
        WordCounter wordCoun1 = new WordCounter();
        wordCoun1.addWord("one");
        wordCoun1.addWord("more");
        wordCoun1.addWord("nice");
        wordCoun1.addWord("simple");
        wordCoun1.addWord("example");
        wordCoun1.addWord("of");
        wordCoun1.addWord("a");
        wordCoun1.addWord("binary");
        wordCoun1.addWord("search");
        wordCoun1.addWord("tree");
        wordCoun1.print();
        HashSet<String> value = new HashSet<String>();
        value.add("one");
        value.add("more");
        value.add("nice");
        value.add("simple");
        value.add("example");
        value.add("of");
        value.add("a");
        value.add("binary");
        value.add("search");
        value.add("tree");
        HashSet<String> set = wordCoun1.getWords();
        assertEquals(set, value);
    }

    @Test
    public void testCalculateInverted()
    {
        WordCounter wordCoun1 = new WordCounter();
        wordCoun1.addWord("a");
        wordCoun1.addWord("a");
        wordCoun1.addWord("a");
        wordCoun1.addWord("b");
        wordCoun1.addWord("c");
        HashSet<String> aSet = new HashSet<String>();
        assertEquals(true, aSet.add("a"));
        HashSet<String> bcSet = new HashSet<String>();
        assertEquals(true, bcSet.add("b"));
        assertEquals(true, bcSet.add("c"));
        BST map = new BST<Integer, HashSet<String>>();
        map.put(1, aSet);
        BST test = wordCoun1.calculateInverted();
        Iterator iterator = test.iterator();
        HashSet<String> set = new HashSet<String>();
        while(iterator.hasNext()) {
            set.add(iterator.next().toString());
        }
        System.out.println(set);
//        assertEquals(map, hashMap1);
    }
}

