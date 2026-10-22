package zoo;

// Узагальнений вольєр для птахів
public class BirdCage<T extends Bird> extends Cage<T> {

    public BirdCage(int maxCapacity) {
        super(maxCapacity);
    }
}
