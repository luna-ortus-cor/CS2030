import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

class Maybe<T> {
    private final T value;

    private Maybe(T value) {
        this.value = value;
    }

    static <U> Maybe<U> of(U value) {
        return new Maybe<U>(value);
    }

    static <T> Maybe<T> empty() {
        return new Maybe<T>(null);
    }

    private T get() {
        return this.value;
    }

    private boolean isEmpty() {
        return this.value == null;
    }

    private boolean isPresent() {
        return !this.isEmpty();
    }

    public void ifPresent(Consumer<? super T> action) {
        if (this.isPresent()) {
            action.accept(this.value);
        }
    }

    public void ifPresentOrElse(Consumer<? super T> action, Runnable emptyAction) {
        if (this.isPresent()) {
            action.accept(this.value);
        } else {
            emptyAction.run();
        }
    }

    public Maybe<T> or(Supplier<? extends Maybe<? extends T>> supplier) {
        if (this.isPresent()) {
            return this;
        } else {
            Maybe<? extends T> m = supplier.get();
            T value = m.value;
            return new Maybe<T>(value);
        }
    }
    
    public T orElse(T other) {
        return this.isPresent() ? this.value : other;
    }

    public T orElseGet(Supplier<? extends T> supplier) {
        return this.isPresent() ? this.value : supplier.get();
    }

    <U> Maybe<U> map(Function<? super T, ? extends U> mapper) {
        return this.isPresent() ? new Maybe<U>(mapper.apply(this.value)) : Maybe.<U>empty();
    }

    <U> Maybe<U> flatMap(Function<? super T, ? extends Maybe<? extends U>> mapper) {
        if (this.isPresent()) {
            Maybe<? extends U> m = mapper.apply(this.value);
            U value = m.value;
            return new Maybe<U>(value);
        } else {
            return Maybe.<U>empty();
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        return (obj instanceof Maybe<?> m ? 
                (m.isEmpty() && this.isEmpty() ? true : 
                 m.isPresent() && this.isPresent() && m.value.equals(this.value) 
                 ? true : false) : false);
    }

    public Maybe<T> filter(Predicate<? super T> predicate) {
        return this.isPresent() && predicate.test(this.value) ? this : empty();
    }

    @Override
    public String toString() {
        if (this.value == null) {
            return "Maybe.empty";
        } else {
            return "Maybe[" + this.value + "]";
        }
    }
}
