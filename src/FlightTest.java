import java.time.LocalDateTime;

public class FlightTest {
    public static void main(String[] args) {
        LocalDateTime dt = LocalDateTime.of(2025, 10, 10, 12, 0);
        Flight f = new DomesticFlight("T1", "TestCity", dt, 5);

        // Test: constructor should set booked = 0 (standard behavior)
        if (f.getBooked() != 0) {
            System.out.println("FAIL: expected booked=0, got " + f.getBooked());
            System.exit(1);
        }
        System.out.println("PASS: initial booked == 0");

        // Use protected setter to simulate a flight that already has 1 booking
        f.setBooked(1);
        if (f.getBooked() != 1) {
            System.out.println("FAIL: after setBooked expected 1, got " + f.getBooked());
            System.exit(2);
        }
        System.out.println("PASS: setBooked sets booked to 1");

        // Now booking should increase to 2
        boolean ok = f.bookTicket();
        if (!ok || f.getBooked() != 2) {
            System.out.println("FAIL: booking did not increment booked to 2 (ok=" + ok + ", booked=" + f.getBooked() + ")");
            System.exit(3);
        }
        System.out.println("PASS: booking increments booked to 2");

        // Test overbooking prevention
        System.out.println("\n--- Testing Overbooking Prevention ---");
        Flight fullFlight = new DomesticFlight("FULL", "TestCity", dt, 2);
        
        // Fill the flight
        fullFlight.bookTicket(); // 1/2
        fullFlight.bookTicket(); // 2/2 - full
        
        // Try to overbook
        boolean overbookAttempt = fullFlight.bookTicket();
        if (overbookAttempt) {
            System.out.println("FAIL: overbooking should be prevented");
            System.exit(4);
        }
        System.out.println("PASS: overbooking correctly prevented");
        
        if (fullFlight.getBooked() != 2) {
            System.out.println("FAIL: booked count should remain 2 after overbook attempt");
            System.exit(5);
        }
        System.out.println("PASS: booked count unchanged after overbook attempt");

        // Test seats available calculation
        if (fullFlight.seatsAvailable() != 0) {
            System.out.println("FAIL: seats available should be 0 for full flight");
            System.exit(6);
        }
        System.out.println("PASS: seats available correctly calculated as 0");

        // Print final status for manual inspection
        System.out.println("\nFinal flight: " + f);
        System.out.println("Full flight: " + fullFlight);
    }
}