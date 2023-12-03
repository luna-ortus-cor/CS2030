import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class Str {
    private final String s;
    private final Supplier<String> sup;
    private final String traced;
    private final String tstr = "traced Str: ";
    private final String tmap = "traced map: ";
    private final String tfmap = "traced flatMap: ";

    private Str(String s) {
        this.s = s;
        this.sup = () -> "";
        this.traced = tstr  + s + "\n";
    }

    private Str(Supplier<String> sup) {
        this.s = "";
        this.sup = sup;
        this.traced = tstr  + sup.get() + "\n";
    }

    private Str(String s, String traced) {
        this.s = s;
        this.sup = () -> "";
        this.traced = traced;
    }

    static Str of(String s) {
        return new Str(s);
    }

    static Str of(Supplier<String> sup) {
        return new Str(sup);
    }

    void run(Consumer<String> action) {
        action.accept(this.s + this.sup.get());
    }

    void print() {
        System.out.println(this.s + this.sup.get());
    }

    Str map(Function<String, String> f) {
        String newS = f.apply(this.s + this.sup.get());
        return new Str(newS, this.traced + tmap  + newS + "\n");
    }

    Str flatMap(Function<String, Str> f) {
        Str newS = f.apply(this.s + this.sup.get());
        return new Str(newS.s, this.traced + tfmap  + newS.s + "\n");
    }

    Str join(String s) {
        return this.map(x -> x + s);
    }

    Str join(Str s) {
        return this.flatMap(x -> new Str(x).map(y -> y + s.s));
    }

    void trace() {
        System.out.print(this.traced);
        this.print();
    }

    void trace(Consumer<String> action) {
        action.accept(this.traced);
        this.print();
    }
}
