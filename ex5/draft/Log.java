import java.util.Optional;
import java.util.function.Function;

class Log<T> {
    private final T t;
    private final String log;

    Log(T t) {
        this.t = t;
        this.log = "";
    }

    Log(T t, String log) {
        this.t = t;
        this.log = log;
    }

    static <T> Log<T> of(T t) {
        return Log.<T>of(t, "");
    }

    static <T> Log<T> of(T t, String log) {
        return Optional.<T>ofNullable(t)
            .filter(x -> !(x instanceof Log<?>))
            .flatMap(x -> Optional.<String>ofNullable(log)
                    .map(y -> new Log<T>(x, y)))
            .orElseThrow(() -> new IllegalArgumentException("Invalid arguments"));
    }

    <U> Log<U> map(Function<? super T, ? extends U> mapper) {
        return Log.<U>of(mapper.apply(this.t), this.log);
    }

    <U> Log<U> flatMap(Function<? super T, ? extends Log<? extends U>> mapper) {
        Log<? extends U> t = mapper.apply(this.t);
        U u = t.t;
        String l = this.log + "\n" + t.log;
        return Log.<U>of(u, l);
    }

    boolean equals(Log<?> other) {
        return this.t.equals(other.t) && this.log.trim().equals(other.log.trim());
    }

    @Override
    public String toString() {
        return this.log == "" ? 
            String.format("Log[%s]", this.t) :
            String.format("Log[%s]\n%s", this.t, this.log);
    }
}
