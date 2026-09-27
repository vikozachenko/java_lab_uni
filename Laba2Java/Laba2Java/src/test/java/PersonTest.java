import nl.jqno.equalsverifier.EqualsVerifier;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class PersonTest {

    @Test
    void testEquals() {
        Person person1 = new Person("Козаченко", "Вікторія", 19);
        Person person2 = new Person("Козаченко", "Вікторія", 19);

        assertEquals(person1, person2);
    }

    @Test
    void testNotEquals() {
        Person person1 = new Person("Козаченко", "Вікторія", 19);
        Person person2 = new Person("Шевченко", "Вікторія", 19);

        assertNotEquals(person1, person2);
    }

    @Test
    void testEqualsVerifier() {
        EqualsVerifier
                .forClass(Person.class)
                .verify();
    }
}