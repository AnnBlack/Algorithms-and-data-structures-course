import java.util.Stack;

public class ArithmeticTerm {
    private final Stack<Double> stackOfDoubles;

    public Double evaluate(String str) {
        String[] splittedGivenString = str.split(" ");
        try {
                for (String s : splittedGivenString) {
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
                        Double lastToken = stackOfDoubles.pop();
                        Double tokensSqrt = Math.sqrt(lastToken);
                        stackOfDoubles.push(tokensSqrt);
                    }
                    else if(s.equals("%")) {
                        Double lastToken = stackOfDoubles.pop();
                        Double prevToken = stackOfDoubles.pop();
                        Double tokensModulo = prevToken % lastToken;
                        stackOfDoubles.push(tokensModulo);
                    }
                    else if(!Double.valueOf(s).isNaN()) {
                        double value = Double.valueOf(s);
                        stackOfDoubles.push(value);
                    }
                }
        } catch (Exception e) {
            System.out.println("Sorry, there was a typo error in the provided string");
        }
            if(stackOfDoubles.size() > 0) return stackOfDoubles.peek();
            else return 0.0;
    };

    public String convert(String str) {
        String[] splittedGivenString = str.split(" ");
        Stack<String> stackOfOperators = new Stack<String>();
        Stack<String> middleTestString = new Stack<String>();
        StringBuilder test = new StringBuilder("");
        StringBuilder emptyString = new StringBuilder("");
        try {
            for (String s : splittedGivenString) {
                if(s.equals("(")) {
                    emptyString.append("");
                }
                else if(s.equals("+")) {
                    stackOfOperators.push(s);
                }
                else if(s.equals("-")) {
                    stackOfOperators.push(s);
                }
                else if(s.equals("*")) {
                    stackOfOperators.push(s);
                }
                else if(s.equals("/")) {
                    stackOfOperators.push(s);
                }
                else if(s.equals("^")) {
                    stackOfOperators.push(s);
                }
                else if(s.equals("sqrt")) {
                    stackOfOperators.push(s);
                }
                else if(s.equals(")")) {
                    middleTestString.push(stackOfOperators.pop());
                }
                else if(!Double.valueOf(s).isNaN()) {
                    middleTestString.push(Double.valueOf(s).toString());
                }
            }
        } catch (Exception e) {
            System.out.println("Sorry, there was a typo error in the provided string for converting");
        }
        for(String s : middleTestString) {
            test.append(s).append(" ");
        }
        return test.toString();
    }

    public ArithmeticTerm() {
        stackOfDoubles = new Stack<Double>();
    }

    public static void main(String[] args) {
        ArithmeticTerm arithmeticTerm = new ArithmeticTerm();
        System.out.println("Evaluating 1: " + arithmeticTerm.evaluate("5.1 9 8.88 + 4 sqrt 6 / ^ 7 - *"));
        System.out.println("Evaluating 2: " + arithmeticTerm.evaluate("8.8 2.2 / 0.2 *"));
        System.out.println("Evaluating 3: " + arithmeticTerm.evaluate("2 3 1 * + 9 -"));
        System.out.println("Evaluating 4: " + arithmeticTerm.evaluate("3 4.0 / 5.0 + "));
        System.out.println("Evaluating 5: " + arithmeticTerm.evaluate("8 4.0 / 10 + 6 / 2 ^ sqrt 2"));

        System.out.println("Converting 1: " + arithmeticTerm.convert("( 5.1 * ( ( ( 9 + 8.88 ) ^ ( ( sqrt 4 ) / 6 ) ) - 7 ) )"));
        System.out.println("Converting 2: " + arithmeticTerm.convert("( ( 8.8 / 2.2 ) * 0.2 )"));
        System.out.println("Converting 3: " + arithmeticTerm.convert("( ( 2 + ( 3 * 1 ) ) - 9 )"));
        System.out.println("Converting 4: " + arithmeticTerm.convert("( ( 3 / 4.0 ) + 5 )"));
        System.out.println("Converting 5: " + arithmeticTerm.convert("( ( ( ( ( 8 / 4 ) + 10 ) / 6 ) ^ 2 ) sqrt ) 2"));
        System.out.println("Converting 6: " + arithmeticTerm.convert("( ( 4.3 * 1e-1 ) - .4 )"));
        System.out.println("Calculation converted 1: " + arithmeticTerm.evaluate(arithmeticTerm.convert("( 5.1 * ( ( ( 9 + 8.88 ) ^ ( ( sqrt 4 ) / 6 ) ) - 7 ) )")));
        System.out.println("Calculation converted 2: " + arithmeticTerm.evaluate(arithmeticTerm.convert("( ( 8.8 / 2.2 ) * 0.2 )")));
        System.out.println("Calculation converted 3: " + arithmeticTerm.evaluate(arithmeticTerm.convert("( ( 2 + ( 3 * 1 ) ) - 9 )")));
        System.out.println("Calculation converted 4: " + arithmeticTerm.evaluate(arithmeticTerm.convert("( ( 3 / 4.0 ) + 5 )")));
        System.out.println("Calculation converted 5: " + arithmeticTerm.evaluate(arithmeticTerm.convert("( ( ( ( ( 8 / 4 ) + 10 ) / 6 ) ^ 2 ) sqrt ) 2")));
        System.out.println("Calculation converted 6: " + arithmeticTerm.evaluate(arithmeticTerm.convert("( ( 4.3 * 1e-1 ) - .4 )")));
    }
}
