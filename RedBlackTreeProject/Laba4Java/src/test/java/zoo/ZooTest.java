package zoo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ZooTest {

    @Test
    void shouldAddCagesToZoo() {
        Zoo zoo = new Zoo();

        zoo.addCage(new LionCage(3));
        zoo.addCage(new BirdCage<Eagle>(2));
        zoo.addCage(new UngulateCage(4));

        assertEquals(0, zoo.getCountOfAnimals());
    }

    @Test
    void shouldCountAnimalsInAllCages() {
        Zoo zoo = new Zoo();

        LionCage lionCage = new LionCage(3);
        lionCage.addAnimal(new Lion("Сімба"));
        lionCage.addAnimal(new Lion("Муфаса"));

        BirdCage<Eagle> birdCage = new BirdCage<>(2);
        birdCage.addAnimal(new Eagle("Орлик"));

        UngulateCage ungulateCage = new UngulateCage(4);
        ungulateCage.addAnimal(new Zebra("Марті"));
        ungulateCage.addAnimal(new Giraffe("Мелман"));

        zoo.addCage(lionCage);
        zoo.addCage(birdCage);
        zoo.addCage(ungulateCage);

        assertEquals(5, zoo.getCountOfAnimals());
    }

    @Test
    void shouldReturnZeroWhenZooHasNoCages() {
        Zoo zoo = new Zoo();

        assertEquals(0, zoo.getCountOfAnimals());
    }

    @Test
    void shouldRejectNullCage() {
        Zoo zoo = new Zoo();

        assertThrows(
                NullPointerException.class,
                () -> zoo.addCage(null)
        );
    }
}
