import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

class Str {
    private final Supplier<String> s;
    //private final Supplier<String> sup;
    private final Function<Consumer<String>, String> traced;
    private final String tstr = "traced Str: ";
    private final String tmap = "traced map: ";
    private final String tfmap = "traced flatMap: ";

    private Str(Supplier<String> s, Function<Consumer<String>, String> f) {
        this.s = s;
        //this.s = () -> s;
        this.traced = f;
    }

    private Str(Supplier<String> s) {
        //this.s = "";
        this.s = s;
        this.traced = x->{String v=s.get(); x.accept(tstr+v); return v;};
    }
    /*
    private Str(String s, String traced) {
        this.s = s;
        this.sup = () -> "";
        this.traced = traced;
    }
    */
    static Str of(String s) {
        return new Str(()->s);
    }

    static Str of(Supplier<String> sup) {
        return new Str(sup);
    }

    void run(Consumer<String> action) {
        action.accept(this.s.get());
    }

    void print() {
        //(this.s + this.sup.get());
        this.run(x->System.out.println(x));
    }

    Str map(Function<String, String> f) {
        //String newS = f.apply(this.s + this.sup.get());
        return new Str(()->f.apply(this.s.get()),
                x->{String v=f.apply(this.traced.apply(x)); x.accept(tmap+v); return v;});
        //, this.traced + tmap  + newS + "\n");
    }

    Str flatMap(Function<String, Str> f) {
        //Str newS = f.apply(this.s + this.sup.get());
        return new Str(()->f.apply(this.s.get()).s.get(),
                x->{String v=f.apply(this.traced.apply(x)).traced.apply(x); x.accept(tfmap+v); return v;});
        //, this.traced + tfmap  + newS.s + "\n");
    }

    Str join(String s) {
        return this.map(x -> x + s);
    }

    Str join(Str s) {
        return this.flatMap(x -> s.map(y -> x + y));
    }

    void trace() {
        //(this.traced);
        //this.print();
        this.trace(x -> System.out.println(x));
    }

    void trace(Consumer<String> action) {
        action.accept(this.traced.apply(action));
        //this.print();
    }
}
