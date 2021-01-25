package Lab_2.Report;

import java.util.Stack;

public class ArithmeticTerm {
    private Stack<Double> stackOfDoubles;
    private Stack<String> stackOfOperators;
    private String str;

    public Double evaluate() {
        String[] splitedGivenString = str.split(" ");
        try {
            for (String s : splitedGivenString) {
                if(s.equals("+")) {
                    Double lastToken = stackOfDoubles.pop();
                    Double prevToken = stackOfDoubles.pop();
                    Double tokensSum = prevToken + lastToken;
                    stackOfDoubles.push(tokensSum);
                }
                else if(s.equals("-")) {
                    Double lastToken = stackOfDoubles.pop();
                    Double prevToken = stackOfDoubles.pop();
                    Double tokensMinus = prevToken - lastToken;
                    stackOfDoubles.push(tokensMinus);
                }
                else if(s.equals("*")) {
                    Double lastToken = stackOfDoubles.pop();
                    Double prevToken = stackOfDoubles.pop();
                    Double tokensMult = prevToken * lastToken;
                    stackOfDoubles.push(tokensMult);
                }
                else if(s.equals("/")) {
                    Double lastToken = stackOfDoubles.pop();
                    Double prevToken = stackOfDoubles.pop();
                    Double tokensDivision = prevToken / lastToken;
                    stackOfDoubles.push(tokensDivision);
                }
                else if(s.equals("^")) {
                    Double lastToken = stackOfDoubles.pop();
                    Double prevToken = stackOfDoubles.pop();
                    Double tokensExponent = Math.pow(prevToken, lastToken);
                    stackOfDoubles.push(tokensExponent);
                }
                else if(s.equals("sqrt")) {
                    stackOfDoubles.push(Math.sqrt(stackOfDoubles.pop()));
                }
                else if(s.equals("sin")) {
                    stackOfDoubles.push(Math.sin(stackOfDoubles.pop()));
                }
                else if(s.equals("e")) {
                    stackOfDoubles.push(Math.E);
                }
                else if(s.equals("exp")) {
                    stackOfDoubles.push(Math.E);
                }
                else {
                    stackOfDoubles.push(Double.valueOf(s));
                }
            }
        } catch (Exception e) {
            System.out.println("It seems there was a typo error in the provided string");
        }
        if(stackOfDoubles.size() > 0) return stackOfDoubles.peek();
        else return 0.0;
    }

    public String convert() {
        String[] splitedGivenString = str.split(" ");
        Stack<String> middleTestString = new Stack<String>();
        StringBuilder test = new StringBuilder();
        try {
            for (String s : splitedGivenString) {
                if(s.equals(" ")) {}
                if(s.equals("(") ) {}
                else if(s.equals("+") || s.equals("-") ||s.equals("*") || s.equals("/") || s.equals("^") || s.equals("sqrt") || s.equals("sin")) {
                    stackOfOperators.push(s);
                }
                else if(s.equals("e")) {
                    middleTestString.push(Double.valueOf(Math.E).toString());
                }
                else if(s.equals("exp")) {
                    stackOfOperators.push("^");
                    middleTestString.push(Double.valueOf(Math.E).toString());
                }
                else if(s.equals("pi")) {
                    middleTestString.push(Double.valueOf(Math.PI).toString());
                }
                else if(s.equals(")")) {
                    middleTestString.push(stackOfOperators.pop());
                }
                else {
                    middleTestString.push(Double.valueOf(s).toString());
                }
            }
        } catch (Exception e) {
            System.out.println("It seems there was a typo error in the provided string for converting");
        }
        for(String s : middleTestString) {
            test.append(s).append(" ");
        }
        String result = test.toString();
        str = result;
        return result;
    }

    static private String reverse(String str) {
        if (str.equals("")) {
            return "";
        } else {
            return reverse(str.substring(1)) + str.charAt(0);
        }
    }

    public ArithmeticTerm(String givenString) {
        stackOfDoubles = new Stack<Double>();
        stackOfOperators = new Stack<String>();
        str = givenString;
    }

    public static void main(String[] args) {
        ArithmeticTerm arithmeticTerm2 = new ArithmeticTerm("( sin ( ( 4 * pi ) / 3 ) ) * ( e ( ^ ( - ( sqrt ( 2 ) - 1 ) / 8 ) ) ) / ( sqrt ( ( 6 * pi ) ) )");
        System.out.println(arithmeticTerm2.convert());
    }
}
