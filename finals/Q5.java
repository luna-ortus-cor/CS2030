class Q5 {
    int processUrl(String url) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        return 1;
    }

    List<String> urls = List.of("abc.xyz", "cde.pqr", "xyz.abc");

    Supplier<Integer> supplier = () -> urls.stream().map(x -> processUrl(x)).reduce(0, (x, y) -> x + y);
    //supplier.get()
    Supplier<List<Integer>> supplier2 = () -> urls.stream().map(x -> processUrl(x)).toList();
    //supplier2.get()
    CompletableFuture<Integer> cf = urls.stream()
        .map(x -> CompletableFuture.supplyAsync(() -> processUrl(x)))
        .reduce(CompletableFuture.completedFuture(0), (x, y) -> x.thenCombine(y, (a, b) -> a + b));
    //cf.join()
}
