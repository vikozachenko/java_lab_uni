import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Task2 {

    public static void run(Scanner scanner) {
        List<Student> students = new ArrayList<>();

        while (true) {
            System.out.println("\n--- Збереження об'єктів ---");
            System.out.println("1. Додати студента");
            System.out.println("2. Показати список");
            System.out.println("3. Зберегти у файл");
            System.out.println("4. Завантажити з файлу");
            System.out.println("0. Вихід із завдання");
            System.out.print("Ваш вибір: ");

            String choice = scanner.nextLine().trim();

            try {
                switch (choice) {
                    case "1" -> {
                        System.out.print("Введіть ім'я: ");
                        String name = scanner.nextLine().trim();

                        if (name.isEmpty()) {
                            throw new IllegalArgumentException(
                                    "Ім'я не може бути порожнім.");
                        }

                        System.out.print("Введіть вік: ");
                        int age = Integer.parseInt(
                                scanner.nextLine().trim());

                        if (age < 1 || age > 120) {
                            throw new IllegalArgumentException(
                                    "Вік повинен бути від 1 до 120.");
                        }

                        students.add(new Student(name, age));
                        System.out.println("Студента додано.");
                    }

                    case "2" -> {
                        if (students.isEmpty()) {
                            System.out.println("Список порожній.");
                        } else {
                            students.forEach(System.out::println);
                        }
                    }

                    case "3" -> {
                        System.out.print("Введіть шлях до файлу: ");
                        String path = scanner.nextLine().trim();

                        FileService.saveStudents(students, path);
                        System.out.println("Дані успішно збережено.");
                    }

                    case "4" -> {
                        System.out.print("Введіть шлях до файлу: ");
                        String path = scanner.nextLine().trim();

                        students = FileService.loadStudents(path);
                        System.out.println("Дані успішно завантажено.");
                    }

                    case "0" -> {
                        return;
                    }

                    default -> System.out.println(
                            "Невідома команда. Введіть число від 0 до 4.");
                }

            } catch (NumberFormatException e) {
                System.out.println("Помилка: введіть вік цілим числом.");
            } catch (IllegalArgumentException e) {
                System.out.println("Помилка введення: " + e.getMessage());
            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Помилка роботи з файлом: "
                        + e.getMessage());
            }

        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }
}
