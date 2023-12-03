import java.util.stream.Stream;
import java.util.stream.IntStream;
import java.util.List;

class Q2 {
    <E> ImList<E> reverse(List<? extends E> list) {
        return list.stream()
            .map(x -> new ImList<E>().add(x))
            .reduce(new ImList<E>(), (x,y) -> y.addAll(x));
    }

    <E> List<Pair<E, E>> pairing(List<? extends E> list) {
        return IntStream.range(0, list.size() / 2)
            .mapToObj(x -> new Pair<E, E>(list.get(x * 2), list.get(x * 2 + 1)))
            .toList();
    }

    <E> List<E> merging(List<? extends Pair<? extends E, ? extends E>> list) {
        return list.stream()
            .flatMap(x -> Stream.of(x.first(), x.second()))
            .toList();
    }
}
