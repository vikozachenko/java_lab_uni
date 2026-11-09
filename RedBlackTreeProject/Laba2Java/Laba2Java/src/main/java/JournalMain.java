import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class JournalMain {

    private static final Scanner scanner = new Scanner(System.in);

    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static void main(String[] args) {

        CuratorJournal journal = new CuratorJournal();

        while (true) {

            System.out.println("\n==============================");
            System.out.println("     ЖУРНАЛ КУРАТОРА");
            System.out.println("==============================");
            System.out.println("1 - Додати запис");
            System.out.println("2 - Показати всі записи");
            System.out.println("0 - Вийти");
            System.out.print("\nВаш вибір: ");

            String choice = scanner.nextLine();

            switch (choice) {

                case "1":
                    addStudent(journal);
                    break;

                case "2":
                    journal.showAllStudents();
                    break;

                case "0":
                    System.out.println("Програму завершено.");
                    return;

                default:
                    System.out.println("Помилка! Оберіть 1, 2 або 0.");
            }
        }
    }

    private static void addStudent(CuratorJournal journal) {

        System.out.println("\n=== ДОДАВАННЯ ЗАПИСУ ===");

        String surname = readName("Введіть прізвище студента: ");
        String name = readName("Введіть ім'я студента: ");
        LocalDate birthDate = readDate();
        String phone = readPhone();
        String street = readNonEmpty("Введіть назву вулиці: ");
        String house = readNonEmpty("Введіть номер будинку: ");
        String apartment = readNonEmpty("Введіть номер квартири: ");

        Student student = new Student(
                surname,
                name,
                birthDate,
                phone,
                street,
                house,
                apartment
        );

        journal.addStudent(student);

        System.out.println("\nЗапис успішно додано!");
    }

    private static String readName(String message) {

        while (true) {

            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty() && value.matches("[А-Яа-яІіЇїЄєҐґA-Za-z' -]+")) {
                return value;
            }

            System.out.println("Помилка! Введіть коректне ім'я або прізвище.");
        }
    }

    private static LocalDate readDate() {

        while (true) {

            System.out.print(
                    "Введіть дату народження (dd.MM.yyyy): "
            );

            String value = scanner.nextLine().trim();

            try {
                return LocalDate.parse(value, DATE_FORMATTER);
            } catch (DateTimeParseException e) {
                System.out.println(
                        "Помилка! Некоректна дата. Приклад: 15.05.2007"
                );
            }
        }
    }

    private static String readPhone() {

        while (true) {

            System.out.print(
                    "Введіть телефон (+380XXXXXXXXX): "
            );

            String value = scanner.nextLine().trim();

            if (value.matches("\\+380\\d{9}")) {
                return value;
            }

            System.out.println(
                    "Помилка! Формат телефону: +380XXXXXXXXX"
            );
        }
    }

    private static String readNonEmpty(String message) {

        while (true) {

            System.out.print(message);
            String value = scanner.nextLine().trim();

            if (!value.isEmpty()) {
                return value;
            }

            System.out.println("Помилка! Значення не може бути порожнім.");
        }
    }
}