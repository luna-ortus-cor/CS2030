import java.util.Optional;
import java.util.stream.Stream;

class Num extends AbstractNum<Integer> {
    private Num(Optional<Integer> opt) {
        super(opt);
    }

    static Num zero() {
        return new Num(AbstractNum.<Integer>zero().opt);
    }

    static Num one() {
        return Num.zero().succ();
    }

    static Num of(int i) {
        return new Num(AbstractNum.valid.test(i) ? 
                new AbstractNum<Integer>(i).opt : new AbstractNum<Integer>(Optional.empty()).opt); 
    }

    static Num of(Optional<Num> n) {
        Optional<Integer> opt = n.flatMap(x -> x.opt);
        return new Num(opt);
    }

    Num succ() {
        return new Num(super.opt.map(s));
    }

    Num neg() {
        return new Num(super.opt.map(n));
    }
    
    Num add(Num other) {
        return this.isValid() && other.isValid() ?
            Stream.<Num>iterate(Num.zero(), x -> !x.equals(other), x -> x.succ())
                .reduce(this, (x, y) -> x.succ()) :   
            new Num(Optional.empty());
    }

    Num mul(Num other) {
        return this.isValid() && other.isValid() ?
            Stream.<Num>iterate(Num.zero(), x -> !x.equals(other), x -> x.succ())
                .reduce(Num.zero(), (x, y) -> x.add(this)) :
            new Num(Optional.empty());
    }

    Num isValidSelf() {
        return new Num(super.opt.filter(AbstractNum.valid));
    }

    Num sub(Num other) {
        return this.isValid() && other.isValid() ?
            Stream.<Num>iterate(Num.zero(), x -> !x.equals(this), x -> x.succ())
                .reduce(other.neg(), (x, y) -> x.succ()).isValidSelf() :
            new Num(Optional.empty());
    }
}
