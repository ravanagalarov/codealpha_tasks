import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static StudentManager manager = new StudentManager();

    public static void main(String[] args) {
        while (true) {
            showMenu();
            int choice = readInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    manager.displayAllStudents();
                    break;
                case 3:
                    manager.displaySummary();
                    break;
                case 0:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice, try again.");
            }
        }
    }

    private static void showMenu() {
        System.out.println("\n===== Student Grade Manager =====");
        System.out.println("1. Add Student");
        System.out.println("2. Display All Students");
        System.out.println("3. Display Summary");
        System.out.println("0. Exit");
        System.out.print("Choose option: ");
    }

    private static void addStudent() {
        System.out.print("Enter student name: ");
        String name = sc.nextLine();

        List<Integer> grades = readGrades();

        manager.addStudent(new Student(name, grades));
    }

    private static List<Integer> readGrades() {
        List<Integer> grades = new ArrayList<>();
        System.out.println("Enter grades (-1 to stop):");
        while (true) {
            int grade = readInt();
            if (grade == -1) break;
            grades.add(grade);
        }
        return grades;
    }

    private static int readInt() {
        while (!sc.hasNextInt()) {
            System.out.println("Invalid input, please enter an integer:");
            sc.next();
        }
        int val = sc.nextInt();
        sc.nextLine();
        return val;
    }
}