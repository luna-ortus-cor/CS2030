Log<Integer> sum(int n) {
    return n == 0 ? Log.<Integer>of(0, "hit base case!") :
        sum(n - 1).flatMap(x -> Log.<Integer>of(x + n, String.format("adding %d", n)));
}

Log<Integer> f(int n) {
    return n == 1 ? Log.<Integer>of(1, "1") :
        n % 2 == 0 ? Log.<Integer>of(n, String.format("%d / 2", n)).flatMap(x -> f(n / 2)) :
            Log.<Integer>of(n, String.format("3(%d) + 1", n)).flatMap(x -> f(3 * x + 1));
}
