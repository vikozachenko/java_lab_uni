package zoo;

// Узагальнений вольєр для ссавців
public class MammalCage<T extends Mammal> extends Cage<T> {

    public MammalCage(int maxCapacity) {
        super(maxCapacity);
    }
}
