import java.util.Optional;

class Fraction extends AbstractNum<Frac> {
    private Fraction(Num n, Num d) {
        super(Frac.of(n, d));
    }

    private Fraction(Optional<Frac> opt) {
        super(opt);
    }

    static Fraction of(int n, int d) {
        return AbstractNum.valid.test(n) && AbstractNum.valid.test(d) && 
            !Num.zero().equals(Num.of(d)) ?
            new Fraction(Num.of(n), Num.of(d)) :
            new Fraction(Optional.empty());
    } 

    Fraction add(Fraction other) {
        if (this.isValid() && other.isValid()) {
            Optional<Num> ad = this.opt.flatMap(x -> 
                    other.opt.map(y -> x.first().mul(y.second())));
            Optional<Num> bc = this.opt.flatMap(x -> 
                    other.opt.map(y -> x.second().mul(y.first())));
            Optional<Num> bd = this.opt.flatMap(x -> 
                    other.opt.map(y -> x.second().mul(y.second())));
            return new Fraction(Num.of(ad).add(Num.of(bc)),Num.of(bd));
        } else {
            return new Fraction(Optional.empty());
        }
    }

    Fraction mul(Fraction other) {
        if (this.isValid() && other.isValid()) {
            Optional<Num> ac = this.opt.flatMap(x ->
                    other.opt.map(y -> x.first().mul(y.first())));
            Optional<Num> bd = this.opt.flatMap(x ->
                    other.opt.map(y -> x.second().mul(y.second())));
            return new Fraction(Num.of(ac), Num.of(bd));
        } else {
            return new Fraction(Optional.empty());
        }
    }

    Fraction isValidSelf() {
        Optional<Integer> a = this.opt.flatMap(x -> x.second().opt).filter(AbstractNum.valid);
        Optional<Integer> b = this.opt.flatMap(x -> x.first().opt).filter(AbstractNum.valid);
        if (a.equals(Optional.empty()) || b.equals(Optional.empty())) {
            return new Fraction(Optional.empty());
        } else {
            return this;
        }
    }

    Fraction sub(Fraction other) {
        if (this.isValid() && other.isValid()) {
            Optional<Num> ad = this.opt.flatMap(x ->
                    other.opt.map(y -> x.first().mul(y.second())));
            Optional<Num> bc = this.opt.flatMap(x ->
                    other.opt.map(y -> x.second().mul(y.first())));
            Optional<Num> bd = this.opt.flatMap(x ->
                    other.opt.map(y -> x.second().mul(y.second())));
            return new Fraction(Num.of(ad).sub(Num.of(bc)), Num.of(bd)).isValidSelf();
        } else {
            return new Fraction(Optional.empty());
        }
    }
}
