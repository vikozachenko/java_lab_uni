import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            while (true) {
                System.out.println("\n========== LAB 5 JAVA ==========");
                System.out.println("1. Пошук рядка з найбільшою кількістю слів");
                System.out.println("2. Збереження та завантаження об'єктів");
                System.out.println("3. Шифрування та дешифрування");
                System.out.println("4. Підрахунок HTML-тегів");
                System.out.println("0. Вихід");
                System.out.print("Оберіть завдання: ");

                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1" -> Task1.run(scanner);
                    case "2" -> Task2.run(scanner);
                    case "3" -> Task3.run(scanner);
                    case "4" -> Task4.run(scanner);
                    case "0" -> {
                        System.out.println("Програму завершено.");
                        return;
                    }
                    default -> System.out.println(
                            "Помилка: введіть число від 0 до 4.");
                }
            }
        }
    }
}
