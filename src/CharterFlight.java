import java.time.LocalDateTime;

public class CharterFlight extends Flight implements Describable {
    private String operator;

    public CharterFlight(String number, String destination, LocalDateTime dateTime, int capacity, String operator) {
        super(number, destination, dateTime, capacity);
        this.operator = operator;
    }

    public String getOperator() {
        return operator;
    }

    @Override
    public String toString() {
        return "[Charter] (" + operator + ") " + super.toString();
    }

    @Override
    public String describe() {
        return "Charter flight " + number + " to " + destination + " operated by " + operator;
    }
}