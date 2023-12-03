import java.util.List;
import java.util.stream.Collectors;

class Main {
    private static final double lo = -1.0;
    private static final double hi = 1.0;

    static double simulate(int seed, int n){
        List<List<Double>> l = Rand.of(seed).
            flatMap(x -> Rand.of(x).next().map(y -> List.of(
            (hi - lo) * x / (Integer.MAX_VALUE - 1) + lo, 
            (hi - lo) * y / (Integer.MAX_VALUE - 1) + lo))).
            stream().limit(2 * n).
            collect(Collectors.toList());
        int count = 0;
        for (int i = 0; i < l.size(); i++) {
            if (i % 2 == 0) {
                if (Math.sqrt(Math.pow(l.get(i).get(0), 2) + 
                            Math.pow(l.get(i).get(1), 2)) <= 1.0) {
                    count += 1;
                }
            }
        }
        return 4.0 * count / n;
    }
}
