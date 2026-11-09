
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

public class Task3 {

    // Потік для шифрування символів
    static class EncryptWriter extends FilterWriter {

        private final int keyCode;

        public EncryptWriter(Writer out, char key) {
            super(out);
            this.keyCode = key;
        }

        @Override
        public void write(int c) throws IOException {
            out.write(c + keyCode);
        }

        @Override
        public void write(char[] chars, int offset, int length)
                throws IOException {
            for (int i = offset; i < offset + length; i++) {
                write(chars[i]);
            }
        }

        @Override
        public void write(String str, int offset, int length)
                throws IOException {
            for (int i = offset; i < offset + length; i++) {
                write(str.charAt(i));
            }
        }
    }

    // Потік для дешифрування символів
    static class DecryptReader extends FilterReader {

        private final int keyCode;

        public DecryptReader(Reader in, char key) {
            super(in);
            this.keyCode = key;
        }

        @Override
        public int read() throws IOException {
            int c = in.read();

            if (c == -1) {
                return -1;
            }

            return c - keyCode;
        }

        @Override
        public int read(char[] chars, int offset, int length)
                throws IOException {
            int count = in.read(chars, offset, length);

            if (count == -1) {
                return -1;
            }

            for (int i = offset; i < offset + count; i++) {
                chars[i] = (char) (chars[i] - keyCode);
            }

            return count;
        }
    }

    public static void run(Scanner scanner) {
        try {
            System.out.print("Введіть шлях до початкового файлу: ");
            Path source = Path.of(scanner.nextLine().trim());

            System.out.print("Введіть шлях для зашифрованого файлу: ");
            Path encrypted = Path.of(scanner.nextLine().trim());

            System.out.print("Введіть шлях для розшифрованого файлу: ");
            Path decrypted = Path.of(scanner.nextLine().trim());

            System.out.print("Введіть один символ ключа: ");
            String keyInput = scanner.nextLine();

            if (keyInput.length() != 1) {
                throw new IllegalArgumentException(
                        "Ключ повинен складатися з одного символу.");
            }

            char key = keyInput.charAt(0);

            // Шифрування
            try (Reader reader = Files.newBufferedReader(
                    source, StandardCharsets.UTF_8);
                 Writer writer = new EncryptWriter(
                         Files.newBufferedWriter(
                                 encrypted, StandardCharsets.UTF_8), key)) {

                char[] buffer = new char[1024];
                int count;

                while ((count = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, count);
                }
            }

            // Дешифрування
            try (Reader reader = new DecryptReader(
                    Files.newBufferedReader(
                            encrypted, StandardCharsets.UTF_8), key);
                 Writer writer = Files.newBufferedWriter(
                         decrypted, StandardCharsets.UTF_8)) {

                char[] buffer = new char[1024];
                int count;

                while ((count = reader.read(buffer)) != -1) {
                    writer.write(buffer, 0, count);
                }
            }

            System.out.println("Шифрування завершено.");
            System.out.println("Зашифрований файл: " + encrypted);
            System.out.println("Розшифрований файл: " + decrypted);

        } catch (IOException e) {
            System.out.println("Помилка роботи з файлами: "
                    + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("Помилка введення: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        run(scanner);
    }
}
