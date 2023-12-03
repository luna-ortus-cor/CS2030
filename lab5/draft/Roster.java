import java.util.Optional;

class Roster extends KeyableMap<Student> {
    Roster(String key) {
        super(key);
    }

    Roster(String key, ImmutableMap<String, Student> iMap) {
        super(key, iMap);
    }

    Roster(KeyableMap<Student> kMap) {
        super(kMap);
    }

    Roster put(Student s) {
        return new Roster(super.put(s));
    }

    String getGrade(String keyStudent, String keyCourse, String keyAssessment) {
        return super.get(keyStudent)
                    .flatMap(x -> x.get(keyCourse))
                    .flatMap(x -> x.get(keyAssessment))
                    .map(x -> x.getGrade())
                    .orElse(String.format("No such record: %s %s %s",
                                keyStudent, keyCourse, keyAssessment));
    }

    Roster add(String keyStudent, String keyCourse, String keyAssessment, String valGrade) {
        return this.put(super.get(keyStudent)
                                .orElse(new Student(keyStudent))
                    .put(super.get(keyStudent)
                                .flatMap(x -> x.get(keyCourse))
                                .orElse(new Course(keyCourse))
                        .put(new Assessment(keyAssessment, valGrade))));
    }
}
