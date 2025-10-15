import java.time.LocalDateTime;

public class InternationalFlight extends Flight implements Describable {
    private static final double CUSTOMS_FEE = 50.0;
    private String passportRequired;

    public InternationalFlight(String number, String destination, LocalDateTime dateTime, int capacity, String passportRequired) {
        super(number, destination, dateTime, capacity);
        this.passportRequired = passportRequired;
    }

    public double getCustomsFee() {
        return CUSTOMS_FEE;
    }

    public String getPassportRequirement() {
        return passportRequired;
    }

    @Override
    public String toString() {
        return "[International] " + super.toString();
    }

    @Override
    public String describe() {
        return "International flight " + number + " to " + destination + " (passport: " + passportRequired + ")";
    }
}