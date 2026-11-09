package zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Узагальнений клас вольєра для тварин
public class Cage<T extends Animal> {

    // Максимальна кількість тварин у вольєрі
    private final int maxCapacity;

    // Список тварин, які перебувають у вольєрі
    private final List<T> animals = new ArrayList<>();

    // Конструктор вольєра із заданою максимальною місткістю
    public Cage(int maxCapacity) {
        if (maxCapacity <= 0) {
            throw new IllegalArgumentException(
                    "Максимальна місткість має бути більшою за нуль"
            );
        }

        this.maxCapacity = maxCapacity;
    }

    // Повертає максимальну місткість вольєра
    public int getMaxCapacity() {
        return maxCapacity;
    }

    // Повертає кількість зайнятих місць
    public int getOccupiedCount() {
        return animals.size();
    }

    // Повертає незмінну копію списку тварин
    public List<T> getAnimals() {
        return List.copyOf(animals);
    }

    // Розміщує тварину у вольєрі
    public void addAnimal(T animal) {
        Objects.requireNonNull(animal, "Тварина не може бути null");

        // Перевіряємо, чи є вільні місця
        if (animals.size() >= maxCapacity) {
            throw new IllegalStateException(
                    "Вольєр заповнений"
            );
        }

        animals.add(animal);
    }

    // Вилучає тварину з вольєра
    public void removeAnimal(T animal) {
        Objects.requireNonNull(animal, "Тварина не може бути null");

        // Перевіряємо, чи є тварина у вольєрі
        if (!animals.remove(animal)) {
            throw new IllegalArgumentException(
                    "Тварини немає у вольєрі"
            );
        }
    }
}
