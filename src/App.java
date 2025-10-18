import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class App {
    public static void main(String[] args) throws Exception {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        Flight f1 = new DomesticFlight("DL123", "Krakow", LocalDateTime.parse("2025-10-10 14:30", fmt), 3);
        Flight f2 = new InternationalFlight("INT456", "London", LocalDateTime.parse("2025-11-01 09:00", fmt), 2, "Passport must be valid 6 months");

        Flight[] flights = {f1, f2};

        System.out.println("Initial flight status:");
        for (Flight f : flights) System.out.println(f);

        System.out.println("\nAttempting bookings...");
        attemptBooking(f1);
        attemptBooking(f1);
        attemptBooking(f2);

        System.out.println("\nFinal flight status:");
        for (Flight f : flights) System.out.println(f);
    }

    private static void attemptBooking(Flight f) {
        boolean ok = f.bookTicket();
        System.out.printf("Booking on %s %s: %s\n", f.getNumber(), f.getDestination(), ok ? "SUCCESS" : "FAILED");
    }
}