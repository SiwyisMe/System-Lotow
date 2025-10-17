import java.time.LocalDateTime;

public class FlightTest {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2025, 10, 10, 12, 0);
        Flight f = new DomesticFlight("T1", "TestCity", dt, 5);

        if (f.getBooked() != 0) {
            System.out.println("FAIL: expected booked=0, got " + f.getBooked());
            System.exit(1);
        }
        System.out.println("PASS: initial booked == 0");

        boolean ok = f.bookTicket();
        if (!ok || f.getBooked() != 1) {
            System.out.println("FAIL: booking did not increment booked to 1");
            System.exit(2);
        }
        System.out.println("PASS: booking increments booked to 1");

        System.out.println("Final flight: " + f);
    }
}