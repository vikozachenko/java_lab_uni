
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class FileService {

    public static void saveStudents(
            List<Student> students, String fileName)
            throws IOException {

        Path path = Path.of(fileName);

        try (ObjectOutputStream out = new ObjectOutputStream(
                new FileOutputStream(path.toFile()))) {

            out.writeObject(new ArrayList<>(students));
        }
    }

    public static List<Student> loadStudents(String fileName)
            throws IOException, ClassNotFoundException {

        Path path = Path.of(fileName);

        try (ObjectInputStream in = new ObjectInputStream(
                new FileInputStream(path.toFile()))) {

            Object data = in.readObject();

            if (!(data instanceof List<?> list)
                    || !list.stream().allMatch(Student.class::isInstance)) {
                throw new IOException(
                        "Файл не містить коректного списку студентів.");
            }

            List<Student> students = new ArrayList<>();

            for (Object item : list) {
                students.add((Student) item);
            }

            return students;
        }
    }
}
