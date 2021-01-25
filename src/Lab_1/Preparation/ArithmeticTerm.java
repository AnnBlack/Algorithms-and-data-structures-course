package Lab_1.Preparation;
import java.util.Stack;

public class ArithmeticTerm {
    private String arithmeticExpression;
    public String reversedString = "";
    public String toString(String stringToPrint) {
        return stringToPrint;
    }

    void reverse() {
        //  tokenizes the String with the arithmetic term
        //  uses Stack<String> to store the tokens of the arithmetic expression
        //  writes it in the opposite order to the string instance variable (reversedString).
        Stack<String> storedTokens = new Stack<String>();
        String[] splitedGivenString = arithmeticExpression.split("");
        Stack<String> middleArrayToReverse = new Stack<>();

        if(splitedGivenString.length > 0) {
            for (String s : splitedGivenString) {
                storedTokens.push(s);
            }
            for(int i = storedTokens.size() - 1; i >= 0; i--) {
                middleArrayToReverse.push(storedTokens.get(i));
            }
            middleArrayToReverse.forEach(item -> reversedString += item);
        }
        else {
            System.out.println("No expression was provided");
        }
    }

    void reverseOpt2() {
        Stack<String> storedTokens = new Stack<String>();
        String[] splitedGivenString = arithmeticExpression.split("");
        if(splitedGivenString.length > 0) {
            for (String s : splitedGivenString) {
                storedTokens.push(s);
            }
            while(!storedTokens.isEmpty()) {
                String s = storedTokens.pop();
                reversedString  += s;
            }
        } else {
            System.out.println("No expression was provided");
        }
    }

    static String reverseOpt3(String str) {
        if (str.equals("")) {
            return "";
        } else {
            return reverseOpt3(str.substring(1)) + str.charAt(0);
        }
    }


    public ArithmeticTerm(String str) {
        arithmeticExpression = str;
    }
    public static void main(String[] args) {
        ArithmeticTerm firstArithmeticTerm = new ArithmeticTerm("5.1 9 8.88 + 4 sqrt 6 / ^ 7 - *");
        System.out.println("Print the first string: " + firstArithmeticTerm.toString(firstArithmeticTerm.arithmeticExpression));
        firstArithmeticTerm.reverseOpt2();
        System.out.println("First reversedString: " + firstArithmeticTerm.reversedString);

        ArithmeticTerm secondArithmeticTerm = new ArithmeticTerm("( 5.1 * ( ( ( 9 + 8.88 ) ^ ( ( sqrt 4 ) / 6 ) ) - 7 ) )");
        System.out.println("Print the second string: " + secondArithmeticTerm.toString(secondArithmeticTerm.arithmeticExpression));
        secondArithmeticTerm.reverse();
        System.out.println("Second reversedString: " + secondArithmeticTerm.reversedString);

        // easier way with recursion (not as a preparation solution)
        String reversedTestString = reverseOpt3("Hello");
        System.out.println("Test reversed string: " + reversedTestString);
    }
}
