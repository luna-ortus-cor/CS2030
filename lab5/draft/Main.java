import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Roster r = new Roster("main");
        String keyStudent = "";
        String keyCourse = "";
        String keyAssessment = "";
        String valGrade = "";
        for (int i = 0; i < n; i++) {
            keyStudent = sc.next();
            keyCourse = sc.next();
            keyAssessment = sc.next();
            valGrade = sc.next();
            r = r.add(keyStudent, keyCourse, keyAssessment, valGrade);
        }
        while (sc.hasNext()) {
            keyStudent = sc.next();
            keyCourse = sc.next();
            keyAssessment = sc.next();
            System.out.println(r.getGrade(keyStudent, keyCourse, keyAssessment));
        }
        sc.close();
    }
}
