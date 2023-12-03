class Student extends KeyableMap<Course> {
    Student(String key) {
        super(key);
    }
    
    Student(String key, ImmutableMap<String, Course> iMap) {
        super(key, iMap);
    }

    Student(KeyableMap<Course> kMap) {
        super(kMap);
    }
    
    Student put(Course c) {
        return new Student(super.put(c));
    }
}
