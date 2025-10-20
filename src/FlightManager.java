import java.util.ArrayList;
import java.util.List;

public class FlightManager {
    private List<Flight> flights;
    private List<String> bookingHistory;

    public FlightManager() {
        this.flights = new ArrayList<>();
        this.bookingHistory = new ArrayList<>();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
        System.out.println("Added flight: " + flight.getNumber() + " to " + flight.getDestination());
    }

    public boolean bookFlight(String flightNumber, String passengerName) {
        for (Flight flight : flights) {
            if (flight.getNumber().equals(flightNumber)) {
                boolean success = flight.bookTicket();
                if (success) {
                    String bookingInfo = String.format("Booking confirmed: %s on flight %s to %s", 
                            passengerName, flightNumber, flight.getDestination());
                    bookingHistory.add(bookingInfo);
                    System.out.println(bookingInfo);
                    return true;
                } else {
                    System.out.println("Booking failed: No available seats on flight " + flightNumber);
                    return false;
                }
            }
        }
        System.out.println("Flight " + flightNumber + " not found");
        return false;
    }

    public void displayAllFlights() {
        System.out.println("\n=== ALL AVAILABLE FLIGHTS ===");
        if (flights.isEmpty()) {
            System.out.println("No flights available");
            return;
        }
        
        for (int i = 0; i < flights.size(); i++) {
            Flight flight = flights.get(i);
            System.out.printf("%d. %s | Seats available: %d/%d\n", 
                    i + 1, flight, flight.getBooked(), flight.getCapacity());
        }
    }

    public Flight findFlightByNumber(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getNumber().equals(flightNumber)) {
                return flight;
            }
        }
        return null;
    }
}