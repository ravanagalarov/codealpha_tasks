import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    private ArrayList<Student> students = new ArrayList<>();

    private GradeCalculator gradeCalculator = new GradeCalculator();

    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added.");
    }

    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("Not found any students");
        } else {
            students.forEach(System.out::println);
        }
    }

    public void displaySummary() {
        if (students.isEmpty()) {
            System.out.println("Not found any students");
            return;
        }
        for (Student st : students) {
            String name = st.getName();
            List<Integer> grade = st.getGrades();
            double average = gradeCalculator.calculateAverage(grade);
            int max = gradeCalculator.findHighest(grade);
            int min = gradeCalculator.findLowest(grade);

            System.out.println(
                    "Student: " + name +
                            " | Average: " + average +
                            " | Highest: " + max +
                            " | Lowest: " + min
            );
        }
    }
}
