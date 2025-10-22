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

        // Initialize with sample flights
        initializeFlights(manager, fmt);

        System.out.println("=== FLIGHT BOOKING SYSTEM ===");
        
        boolean running = true;
        while (running) {
            displayMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    manager.displayAllFlights();
                    break;
                case 2:
                    bookFlightInteractive(manager, paymentProcessor, scanner);
                    break;
                case 3:
                    System.out.println("Total bookings: " + manager.getTotalBookings());
                    break;
                case 4:
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

        manager.addFlight(f1);
        manager.addFlight(f2);
        manager.addFlight(f3);
    }

    private static void displayMenu() {
        System.out.println("\n=== MAIN MENU ===");
        System.out.println("1. Display all flights");
        System.out.println("2. Book a flight");
        System.out.println("3. Show total bookings");
        System.out.println("4. Exit");
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

        // Show payment options
        paymentProcessor.displayPaymentOptions(flight);
        
        System.out.print("Choose payment method: ");
        String paymentMethod = scanner.nextLine();

        double price = paymentProcessor.calculateFlightPrice(flight);
        System.out.printf("Enter payment amount (minimum %.2f PLN): ", price);
        double amount = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        // Process payment first
        if (paymentProcessor.processPayment(flight, paymentMethod, amount)) {
            // Then book the flight
            manager.bookFlight(flightNumber, passengerName);
        } else {
            System.out.println("Booking cancelled due to payment failure");
        }
    }
}