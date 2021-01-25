package Lab_6.Preparation;

import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Sum {
    public static void main(String[] args) {
        int N = 5;
        int sumOfElements = IntStream.range(0, N - 1)
                .peek(System.out::println)
                .sum();
        System.out.println("method 1 (from 0 to N - 1): " + sumOfElements);

        System.out.println();

        int randomNumbers = Stream.generate( () -> (new Random())
                .nextInt(N - 1) )
                .peek(System.out::println)
                .limit(N - 1)
                .reduce(0, Integer::sum);
        System.out.println("method 2 (with random integers): " + randomNumbers);

        System.out.println();

        IntStream stream = IntStream.generate(() -> (int)(Math.random() * 10));
        int randomSum = stream
                .peek(System.out::println)
                .limit(N - 1)
                .reduce(0, Integer::sum);
        System.out.println("method 3 (with random integers): " + randomSum);
    }
}

/*
    static ArrayList<Integer> list;

        public Sum(Integer N) {
            this.list = new ArrayList<Integer>();
            this.list.forEach(System.out::println);
            }

        public Integer toSumFirstExample() {
            return list.stream().reduce(0, Integer::sum);
        }


        public Integer toSumSecondExample() {
            return numbers.reduce(0, (a, b) -> a + b);
        }

        public Integer toSumThirdExample() {
            return numbers.collect(Collectors.summingInt(Integer::intValue));
        }

        public Integer toSumFourthExample() {
            return numbers.mapToInt(Integer::intValue).sum();
        }

        public Integer toSumFifthExample () {
            return numbers
                    .mapToInt(Integer::intValue)
                    .sum();
        }


 */
