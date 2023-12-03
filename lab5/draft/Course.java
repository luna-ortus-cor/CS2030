class Course extends KeyableMap<Assessment> {    
    Course(String key) {
        super(key);
    }

    Course(String key, ImmutableMap<String, Assessment> iMap) {
        super(key, iMap);
    }

    Course(KeyableMap<Assessment> kMap) {
        super(kMap);
    }

    Course put(Assessment a) {
        return new Course(super.put(a));
    }
}

