import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.List;
import java.util.function.UnaryOperator;
import java.util.stream.Collectors;

class Main {
    static boolean isPrime(int n) {
        return n > 1 && IntStream.range(2, n)
            .noneMatch(x -> n % x == 0);
    }

    static IntStream twinPrimes(int n) {
        return IntStream.rangeClosed(2, n)
            .filter(x -> 
            (isPrime(x) && isPrime(x + 2) || 
            (isPrime(x) && isPrime(x - 2))));
    }

    static String reverse(String str) {
        return Stream.<String>of(str.split(""))
            .reduce("", (x,y) -> y + x);
    }

    static long countRepeats(List<Integer> list) {
        return IntStream.range(0, list.size() - 1)
            .filter(x -> list.get(x) == list.get(x + 1) && 
                    (x + 2 >= list.size() || list.get(x) != list.get(x + 2)))
            .count();
    }
    
    static int getNextState(int idx, List<Integer> li) {
        if (idx > 0 && idx < li.size() - 1) {
            return li.get(idx) == 1 ? 0 : li.get(idx - 1) ^ li.get(idx + 1);
        } else {
            return idx == 0 ? 
                (li.get(idx) == 1 ? 0 : li.get(idx + 1)) : 
                (li.get(idx) == 1 ? 0 : li.get(idx - 1));
        }
    }
        
    static UnaryOperator<List<Integer>> generateRule() {
        return x -> IntStream.range(0, x.size())
            .map(i -> getNextState(i, x))
            .boxed()
            .collect(Collectors.toList());
    }

    static Stream<String> gameOfLife(List<Integer> list, UnaryOperator<List<Integer>> rule, int n) {
        return Stream.<List<Integer>>iterate(list, rule)
            .limit(n)
            .map(x -> x.stream().map(y -> y == 1 ? "x" : ".")
            .reduce("", (a, b) -> a + b));
    }
}
