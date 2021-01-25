package Lab_2.Report;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArithmeticTermTest {

    @Test
    void evaluate() {
        ArithmeticTerm arithmeticTerm1 = new ArithmeticTerm("5.1 9 8.88 + 4 sqrt 6 / ^ 7 - *");
        ArithmeticTerm arithmeticTerm2 = new ArithmeticTerm("8.8 2.2 / 0.2 *");
        ArithmeticTerm arithmeticTerm3 = new ArithmeticTerm("3 4.0 / 5.0 + ");
        ArithmeticTerm arithmeticTerm4 = new ArithmeticTerm("8.0 4.0 / 10.0 + 6.0 / 2.0 ^ sqrt");
        ArithmeticTerm arithmeticTerm5 = new ArithmeticTerm("4.3 0.1 * 0.4 - ");
        ArithmeticTerm arithmeticTerm6 = new ArithmeticTerm("1.0 2.0 3.0 + 4.0 5.0 * * + ");
        ArithmeticTerm arithmeticTerm7 = new ArithmeticTerm("4.0 3.141592653589793 * 3.0 / sin 2.718281828459045 2.0 sqrt 1.0 - 0.0 8.0 - / ^ 6.0 3.141592653589793 * sqrt / * ");
        ArithmeticTerm arithmeticTerm8 = new ArithmeticTerm("4.0 3.141592653589793 * 3.0 / sin 2.718281828459045 -1.0 2.0 sqrt 1.0 - * 8.0 / ^ * 6.0 3.141592653589793 * sqrt / ");
        ArithmeticTerm arithmeticTerm9 = new ArithmeticTerm("4.0 sqrt 2.0 3.0 ^ * 2.0 / ");


        assertEquals(-22.363986874743546, arithmeticTerm1.evaluate(), 1);
        assertEquals(0.8, arithmeticTerm2.evaluate(), 1);
        assertEquals(5.75, arithmeticTerm3.evaluate(), 1);
        assertEquals(2.0, arithmeticTerm4.evaluate(), 1);
        assertEquals(0.02999999999999997, arithmeticTerm5.evaluate());
        assertEquals(101.0, arithmeticTerm6.evaluate());
        assertEquals(-0.18940600202368948, arithmeticTerm7.evaluate());
        assertEquals(-0.18940600202368948, arithmeticTerm8.evaluate());
        assertEquals(8.0, arithmeticTerm9.evaluate());
    }

    @Test
    void convert() {
        ArithmeticTerm arithmeticTerm5 = new ArithmeticTerm("( ( 4.3 * 1e-1 ) - .4 )");
        ArithmeticTerm arithmeticTerm6 = new ArithmeticTerm("( sqrt ( ( ( ( 8.0 / 4.0 ) + 10.0 ) / 6.0 ) ^ 2.0 ) ) ");
        ArithmeticTerm arithmeticTerm7 = new ArithmeticTerm("( 1 ( ( 2 3 + ) ( 4 5 * ) * ) + )");
        ArithmeticTerm arithmeticTerm8 = new ArithmeticTerm("( ( ( sin ( ( 4 * pi ) / 3 ) ) * ( exp ( ( -1 * ( ( sqrt 2 ) - 1 ) ) / 8 ) ) ) / ( sqrt ( 6 * pi ) ) )");
        ArithmeticTerm arithmeticTerm9 = new ArithmeticTerm("( sin ( ( 4 * pi ) / 3 ) ) * ( exp ( ( ( sqrt ( 2 ) - 1 ) / ( 0 - 8 ) ) ) / ( sqrt ( ( 6 * pi ) ) ) )");
        ArithmeticTerm arithmeticTerm10 = new ArithmeticTerm("( ( ( sqrt 4 ) * ( 2 ^ 3 ) ) / 2 ) ");

        assertEquals("4.3 0.1 * 0.4 - ", arithmeticTerm5.convert());
        assertEquals("8.0 4.0 / 10.0 + 6.0 / 2.0 ^ sqrt ", arithmeticTerm6.convert());
        assertEquals("1.0 2.0 3.0 + 4.0 5.0 * * + ", arithmeticTerm7.convert());
        assertEquals("4.0 3.141592653589793 * 3.0 / sin 2.718281828459045 -1.0 2.0 sqrt 1.0 - * 8.0 / ^ * 6.0 3.141592653589793 * sqrt / ", arithmeticTerm8.convert());
        assertEquals("4.0 3.141592653589793 * 3.0 / sin 2.718281828459045 2.0 sqrt 1.0 - 0.0 8.0 - / ^ 6.0 3.141592653589793 * sqrt / * ", arithmeticTerm9.convert());
        assertEquals("4.0 sqrt 2.0 3.0 ^ * 2.0 / ", arithmeticTerm10.convert());
    }
}