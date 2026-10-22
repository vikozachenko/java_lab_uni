package zoo;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// Клас, який представляє зоопарк
public class Zoo {

    // Список вольєрів різних типів
    private final List<Cage<?>> cages = new ArrayList<>();

    // Повертає загальну кількість тварин у всіх вольєрах
    public int getCountOfAnimals() {
        int count = 0;

        for (Cage<?> cage : cages) {
            count += cage.getOccupiedCount();
        }

        return count;
    }

    // Додає вольєр до зоопарку
    public void addCage(Cage<?> cage) {
        Objects.requireNonNull(cage, "Вольєр не може бути null");
        cages.add(cage);
    }
}
