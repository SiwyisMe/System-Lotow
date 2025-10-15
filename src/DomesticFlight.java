import java.time.LocalDateTime;

public class DomesticFlight extends Flight implements Describable {
    private static final double DOMESTIC_TAX = 0.05;

    public DomesticFlight(String number, String destination, LocalDateTime dateTime, int capacity) {
        super(number, destination, dateTime, capacity);
    }

    public double getExtraFee() {
        return DOMESTIC_TAX;
    }

    @Override
    public String toString() {
        return "[Domestic] " + super.toString();
    }

    @Override
    public String describe() {
        return "Domestic flight " + number + " to " + destination;
    }
}