
package zoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SpecializedCageTest {

    @Test
    void lionCageShouldAcceptOnlyLions() {
        LionCage cage = new LionCage(2);

        cage.addAnimal(new Lion("Сімба"));
        cage.addAnimal(new Lion("Муфаса"));

        assertEquals(2, cage.getOccupiedCount());
        assertEquals(2, cage.getMaxCapacity());
    }

    @Test
    void ungulateCageShouldAcceptZebrasAndGiraffes() {
        UngulateCage cage = new UngulateCage(3);

        cage.addAnimal(new Zebra("Марті"));
        cage.addAnimal(new Giraffe("Мелман"));

        assertEquals(2, cage.getOccupiedCount());
    }

    @Test
    void birdCageShouldAcceptEagles() {
        BirdCage<Eagle> cage = new BirdCage<>(2);

        cage.addAnimal(new Eagle("Орлик"));

        assertEquals(1, cage.getOccupiedCount());
    }

    @Test
    void specializedCagesShouldRespectCapacity() {
        UngulateCage cage = new UngulateCage(1);

        cage.addAnimal(new Zebra("Марті"));

        assertThrows(
                IllegalStateException.class,
                () -> cage.addAnimal(new Giraffe("Мелман"))
        );
    }
}
