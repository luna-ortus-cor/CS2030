import java.util.Random;
import java.util.stream.Stream;
import java.util.function.Function;

class Rand<T> {
    private final int seed;
    private final Function<Integer, T> f;

    Rand(int seed, Function<Integer, T> f) {
        this.seed = seed;
        this.f = f;
    }

    static Rand<Integer> of(int seed) {
        return new Rand<Integer>(seed, x -> x);
    }

    T get() {
        return f.apply(this.seed);
    }

    Rand<T> next() {
        return new Rand<T>(new Random(this.seed).nextInt(Integer.MAX_VALUE), this.f);
    }

    public String toString() {
        return "Rand";
    }

    Stream<T> stream() {
        return Stream.iterate(this.seed, x -> new Random(x).nextInt(Integer.MAX_VALUE))
            .map(x -> f.apply(x));
    }

    static <R> Stream<R> randRange(int seed, Function<Integer, R> f) {
        return Rand.<Integer>of(seed).stream().map(x -> f.apply(x));
    }

    <R> Rand<R> map(Function<T, R> f) {
        return new Rand<R>(this.seed, f.compose(this.f));
    }

    <R> Rand<R> flatMap(Function<T, Rand<R>> f) {
        return new Rand<R>(this.seed, 
                x -> {
                    Rand<R> r = f.apply(this.f.apply(x)); // int>T>RandR
                    return r.f.apply(r.seed); //int>RandR
                });
    }
}
