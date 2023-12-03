import java.util.function.Function;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.Optional;
import java.util.function.BinaryOperator;

class DnC<T, R> {
    private final T t;
    private final Predicate<T> pred;
    private final Function<T, R> func;
    private final Function<T, Pair<T, T>> trans;

    DnC(T t, Predicate<T> pred, Function<T, R> func, Function<T, Pair<T, T>> trans) {
        this.t = t;
        this.pred = pred;
        this.func = func;
        this.trans = trans;
    }

    static <T, R> DnC<T, R> of(T t, Predicate<T> pred, Function<T, R> func) {
        return new DnC<T, R>(t, pred, func, x -> Pair.<T, T>of(x,x));
    }
    
    static <T, R> DnC<T, R> of(T t, Predicate<T> pred, Function<T, R> func, Function<T, Pair<T, T>> trans) {
        return new DnC<T, R> (t, pred, func, trans);
    }

    DnC<T, R> left() {
        return new DnC<T, R>(this.trans.apply(this.t).first(), this.pred, this.func, this.trans);
    }

    DnC<T, R> right() {
        T newT = this.trans.apply(this.t).second();
        return !this.pred.test(newT) ? this : new DnC<T, R>(newT, this.pred, this.func, this.trans);
    }

    void peek(Consumer<T> action) {
        action.accept(this.t);
    }

    Optional<R> solve() {
        if (this.pred.test(this.t)) {
            return Optional.<R>of(this.func.apply(this.t));
        } else {
            return Optional.<R>empty();
        }
    }

    Optional<R> solve(BinaryOperator<R> bop) {
        return this.pred.test(this.t) ? Optional.<R>of(this.func.apply(this.t)) :
                this.left().solve(bop).flatMap(x -> this.right().solve(bop).map(
                            y -> bop.apply(x, y)));
    }
}

