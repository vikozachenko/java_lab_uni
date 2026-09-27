import java.util.ArrayList;
import java.util.List;

public class CuratorJournal {

    private final List<Student> students = new ArrayList<>();

    public void addStudent(Student student) {
        students.add(student);
    }

    public void showAllStudents() {

        if (students.isEmpty()) {
            System.out.println("Журнал порожній.");
            return;
        }

        System.out.println("\n=== УСІ ЗАПИСИ ЖУРНАЛУ ===");

        for (int i = 0; i < students.size(); i++) {
            System.out.println("\nЗапис №" + (i + 1));
            System.out.println(students.get(i));
        }
    }
}