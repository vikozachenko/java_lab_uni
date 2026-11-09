
package org.example;

import java.util.*;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    public static void main(String[] args) {

        RedBlackTree tree = new RedBlackTree();

        boolean running = true;

        System.out.println("======================================");
        System.out.println("       RED-BLACK TREE PROJECT");
        System.out.println("======================================");

        while (running) {

            System.out.println("\n========== ГОЛОВНЕ МЕНЮ ==========");
            System.out.println("1. Додати елемент");
            System.out.println("2. Видалити елемент");
            System.out.println("3. Вивести дерево");
            System.out.println("4. Обхід дерева (Inorder)");
            System.out.println("5. Заповнити випадковими числами");
            System.out.println("6. Заповнити впорядкованими числами");
            System.out.println("7. Ввести масив вручну");
            System.out.println("8. Очистити дерево");
            System.out.println("9. Показати кількість елементів");
            System.out.println("10. Перевірити властивості дерева");
            System.out.println("0. Вихід");
            System.out.println("==================================");

            int choice = readInt("Ваш вибір: ");

            switch (choice) {

                case 1:
                    int value = readInt("Введіть число для додавання: ");

                    if (tree.insert(value)) {
                        System.out.println("Елемент успішно додано.");
                    } else {
                        System.out.println("Такий елемент вже існує.");
                    }

                    break;

                case 2:
                    int deleteValue = readInt(
                            "Введіть число для видалення: "
                    );

                    if (tree.delete(deleteValue)) {
                        System.out.println("Елемент успішно видалено.");
                    } else {
                        System.out.println("Елемент не знайдено.");
                    }

                    break;

                case 3:
                    tree.printTree();
                    break;

                case 4:
                    tree.inorderTraversal();
                    break;

                case 5:
                    fillRandom(tree);
                    break;

                case 6:
                    fillSorted(tree);
                    break;

                case 7:
                    fillManually(tree);
                    break;

                case 8:
                    tree.clear();
                    System.out.println("Дерево успішно очищено.");
                    break;

                case 9:
                    System.out.println(
                            "Кількість елементів: " + tree.getSize()
                    );
                    break;

                case 10:
                    tree.validateTree();
                    break;

                case 0:
                    running = false;
                    System.out.println("Завершення роботи програми.");
                    break;

                default:
                    System.out.println(
                            "Невірний пункт меню. Спробуйте ще раз."
                    );
            }
        }

        scanner.close();
    }

    // Читання цілого числа з перевіркою
    private static int readInt(String message) {

        while (true) {

            System.out.print(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input.trim());
            } catch (NumberFormatException e) {
                System.out.println("Помилка! Введіть ціле число.");
            }
        }
    }

    // Заповнення дерева випадковими числами
    private static void fillRandom(RedBlackTree tree) {

        int count = readInt(
                "Скільки випадкових чисел згенерувати? "
        );

        if (count < 1 || count > 100) {
            System.out.println(
                    "Кількість повинна бути від 1 до 100."
            );
            return;
        }

        Set<Integer> numbers = new LinkedHashSet<>();

        while (numbers.size() < count) {
            numbers.add(random.nextInt(100) + 1);
        }

        tree.clear();

        System.out.println("\nЗгенерований масив:");
        System.out.println(numbers);

        System.out.println("\nПорядок додавання елементів:");

        for (int number : numbers) {
            tree.insert(number);
            System.out.print(number + " ");
        }

        System.out.println("\n\nРезультат:");

        tree.printTree();
    }

    // Заповнення дерева впорядкованими числами
    private static void fillSorted(RedBlackTree tree) {

        int count = readInt(
                "Скільки чисел згенерувати? "
        );

        if (count < 1 || count > 100) {
            System.out.println(
                    "Кількість повинна бути від 1 до 100."
            );
            return;
        }

        Set<Integer> uniqueNumbers = new LinkedHashSet<>();

        while (uniqueNumbers.size() < count) {
            uniqueNumbers.add(random.nextInt(100) + 1);
        }

        List<Integer> numbers = new ArrayList<>(uniqueNumbers);

        Collections.sort(numbers);

        tree.clear();

        System.out.println("\nВідсортований масив:");
        System.out.println(numbers);

        System.out.println("\nПорядок додавання елементів:");

        for (int number : numbers) {
            tree.insert(number);
            System.out.print(number + " ");
        }

        System.out.println("\n\nРезультат:");

        tree.printTree();
    }

    // Ручне введення масиву
    private static void fillManually(RedBlackTree tree) {

        System.out.println(
                "Введіть числа через пробіл або кому:"
        );

        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Масив порожній.");
            return;
        }

        try {

            String[] elements = input.split("[,\\s]+");

            List<Integer> numbers = new ArrayList<>();

            for (String element : elements) {
                numbers.add(Integer.parseInt(element));
            }

            tree.clear();

            System.out.println("\nВведений масив:");
            System.out.println(numbers);

            System.out.println("\nПорядок додавання:");

            for (int number : numbers) {

                if (tree.insert(number)) {
                    System.out.print(number + " ");
                } else {
                    System.out.print(number + " (дублікат пропущено) ");
                }
            }

            System.out.println("\n\nРезультат:");

            tree.printTree();

        } catch (NumberFormatException e) {

            System.out.println(
                    "Помилка! Введіть лише цілі числа."
            );
        }
    }
}
