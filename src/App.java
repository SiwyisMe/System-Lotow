import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws Exception {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        FlightManager manager = new FlightManager();
        PaymentProcessor paymentProcessor = new PaymentProcessor();
        Scanner scanner = new Scanner(System.in);

        initializeFlights(manager, fmt);

        System.out.println("=== FLIGHT BOOKING SYSTEM ===");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    manager.displayAllFlights();
                    break;
                case 2:
                    bookFlightInteractive(manager, paymentProcessor, scanner);
                    break;
                case 3:
                    manager.displayBookingHistory();
                    break;
                case 4:
                    searchFlightsByDestination(manager, scanner);
                    break;
                case 5:
                    System.out.println("Total bookings: " + manager.getTotalBookings());
                    break;
                case 6:
                    running = false;
                    System.out.println("Thank you for using Flight Booking System!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void initializeFlights(FlightManager manager, DateTimeFormatter fmt) {
        Flight f1 = new DomesticFlight("DL123", "Krakow", LocalDateTime.parse("2025-10-10 14:30", fmt), 3);
        Flight f2 = new InternationalFlight("INT456", "London", LocalDateTime.parse("2025-11-01 09:00", fmt), 2, "Passport must be valid 6 months");
        Flight f3 = new CharterFlight("CH789", "Zakopane", LocalDateTime.parse("2025-12-20 07:15", fmt), 1, "SunCharter Ltd.");
        Flight f4 = new DomesticFlight("DL456", "Warsaw", LocalDateTime.parse("2025-09-15 10:00", fmt), 4);
        Flight f5 = new InternationalFlight("INT789", "Berlin", LocalDateTime.parse("2025-11-20 16:45", fmt), 3, "Schengen area");
        Flight f6 = new DomesticFlight("DL789", "Krakow", LocalDateTime.parse("2025-10-12 08:00", fmt), 2); // Dodatkowy lot do Krakowa dla testów wyszukiwania

        manager.addFlight(f1);
        manager.addFlight(f2);
        manager.addFlight(f3);
        manager.addFlight(f4);
        manager.addFlight(f5);
        manager.addFlight(f6);
    }

    private static void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Display all flights");
        System.out.println("2. Book a flight");
        System.out.println("3. View booking history"); 
        System.out.println("4. Search flights by destination"); 
        System.out.println("5. Show total bookings");
        System.out.println("6. Exit");
        System.out.print("Choose an option: ");
    }

    private static void bookFlightInteractive(FlightManager manager, PaymentProcessor paymentProcessor, Scanner scanner) {
        System.out.print("Enter flight number: ");
        String flightNumber = scanner.nextLine();
        
        Flight flight = manager.findFlightByNumber(flightNumber);
        if (flight == null) {
            System.out.println("Flight not found!");
            return;
        }

        System.out.print("Enter passenger name: ");
        String passengerName = scanner.nextLine();

        paymentProcessor.displayPaymentOptions(flight);
        
        System.out.print("Choose payment method: ");
        String paymentMethod = scanner.nextLine();
        
        if (!paymentProcessor.validatePaymentMethod(paymentMethod)) {
            return;
        }

        double price = paymentProcessor.calculateFlightPrice(flight);
        System.out.printf("Enter payment amount (minimum %.2f PLN): ", price);
        double amount = scanner.nextDouble();
        scanner.nextLine(); 

        if (paymentProcessor.processPayment(flight, paymentMethod, amount)) {
            manager.bookFlight(flightNumber, passengerName);
        } else {
            System.out.println("Booking cancelled due to payment failure");
        }
    }

    private static void searchFlightsByDestination(FlightManager manager, Scanner scanner) {
        System.out.print("Enter destination to search: ");
        String destination = scanner.nextLine();
        
        List<Flight> flights = manager.findFlightsByDestination(destination);
        if (flights.isEmpty()) {
            System.out.println("❌ No flights found to: " + destination);
        } else {
            System.out.println("\n=== FLIGHTS TO " + destination.toUpperCase() + " ===");
            for (int i = 0; i < flights.size(); i++) {
                Flight flight = flights.get(i);
                System.out.printf("%d. %s | Available seats: %d/%d\n", 
                    i + 1, flight, flight.seatsAvailable(), flight.getCapacity());
            }
            System.out.println("Found " + flights.size() + " flight(s) to " + destination);
        }
    }
}