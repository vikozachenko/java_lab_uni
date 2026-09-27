import java.util.Scanner;

public class Main {
    // Перевіряємо, чи є число простим
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }

        for (int i = 2; i * i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }

        return true;
    }

    // Рахуємо кількість нулів у двійковому представленні числа
    public static int countZerosInBinary(int number) {
        int zeroCount = 0;

        while (number > 0) {
            if (number % 2 == 0) {
                zeroCount++;
            }
            number = number / 2;
        }
        return zeroCount;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Введіть натуральне число n: ");
        int n = scanner.nextInt();

        if (n < 2) {
            System.out.println("Простих чисел у заданому діапазоні немає.");
            return;
        }

        int result = -1;
        int maxZeros = -1;

        for (int number = 2; number <= n; number++) {

            if (isPrime(number)) {
                int zeros = countZerosInBinary(number);

                if (zeros > maxZeros) {
                    maxZeros = zeros;
                    result = number;
                }
            }
        }

        System.out.println("Число: " + result);
        System.out.println("Двійкова форма: " + Integer.toBinaryString(result));
        System.out.println("Кількість нулів: " + maxZeros);

        scanner.close();
    }
}