import java.util.stream.Stream;
import java.util.Optional;
import java.util.List;

class Level {
    private final int mark;

    Level(int mark) {
        this.mark = mark;
    }

    int getMark() {
        return this.mark;
    }
}

class PA {
    private final List<Level> levels;

    PA(List<Level> levels) {
        this.levels = levels;
    }

    Stream<Integer> getMarks() {
        return levels.stream()
            .map(x -> x.getMark());
    }
}

class Student {
    private final List<Optional<PA>> pas;
    
    Student(List<Optional<PA>> pas) {
        this.pas = pas;
    }

    Stream<Optional<Integer>> getMarks() {
        return pas.stream()
            .map(x -> x.map(y -> y.getMarks()
                        .reduce(0, (a, b) -> a + b)));
    }
}

class Main {
    static int getTotalMarks(List<Student> students) {
        return students.stream()
            .flatMap(x -> x.getMarks()
                    .map(y -> y.orElse(0)))
            .reduce(0, (a, b) -> a + b);
    }
}
