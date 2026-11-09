
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Task1 {

    public static void run(Scanner scanner) {
        System.out.print("Введіть шлях до файлу: ");
        String fileName = scanner.nextLine().trim();

        String maxLine = null;
        int maxWords = -1;

        try (BufferedReader reader =
                     Files.newBufferedReader(Path.of(fileName))) {

            String line;

            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();

                int wordCount = trimmed.isEmpty()
                        ? 0
                        : trimmed.split("\\s+").length;

                if (wordCount > maxWords) {
                    maxWords = wordCount;
                    maxLine = line;
                }
            }

            if (maxLine == null) {
                System.out.println("Файл порожній.");
            } else {
                System.out.println(
                        "Рядок із найбільшою кількістю слів:");
                System.out.println(maxLine);
                System.out.println("Кількість слів: " + maxWords);
            }

        } catch (IOException e) {
            System.out.println("Помилка читання файлу: "
                    + e.getMessage());
        } catch (RuntimeException e) {
            System.out.println("Помилка: некоректний шлях до файлу.");
        }
    }
}
