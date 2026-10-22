package zoo;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CageTest {

    @Test
    void shouldCreateCageWithCorrectCapacity() {
        Cage<Lion> cage = new Cage<>(3);

        assertEquals(3, cage.getMaxCapacity());
        assertEquals(0, cage.getOccupiedCount());
    }

    @Test
    void shouldAddAnimalsToCage() {
        Cage<Lion> cage = new Cage<>(3);

        cage.addAnimal(new Lion("Сімба"));
        cage.addAnimal(new Lion("Муфаса"));

        assertEquals(2, cage.getOccupiedCount());
    }

    @Test
    void shouldThrowExceptionWhenCageIsFull() {
        Cage<Lion> cage = new Cage<>(1);

        cage.addAnimal(new Lion("Сімба"));

        assertThrows(
                IllegalStateException.class,
                () -> cage.addAnimal(new Lion("Муфаса"))
        );
    }

    @Test
    void shouldRemoveAnimalFromCage() {
        Cage<Lion> cage = new Cage<>(3);
        Lion lion = new Lion("Сімба");

        cage.addAnimal(lion);
        cage.removeAnimal(lion);

        assertEquals(0, cage.getOccupiedCount());
    }

    @Test
    void shouldThrowExceptionWhenAnimalIsNotInCage() {
        Cage<Lion> cage = new Cage<>(3);

        assertThrows(
                IllegalArgumentException.class,
                () -> cage.removeAnimal(new Lion("Сімба"))
        );
    }

    @Test
    void shouldRejectZeroOrNegativeCapacity() {
        assertThrows(
                IllegalArgumentException.class,
                () -> new Cage<Lion>(0)
        );

        assertThrows(
                IllegalArgumentException.class,
                () -> new Cage<Lion>(-1)
        );
    }
}
