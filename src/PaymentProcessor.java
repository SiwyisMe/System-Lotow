public class PaymentProcessor {
    private static final double DOMESTIC_FEE = 20.0;
    private static final double INTERNATIONAL_FEE = 50.0;
    private static final double CHARTER_FEE = 75.0;

    public boolean processPayment(Flight flight, String paymentMethod, double amount) {
        double requiredAmount = calculateFlightPrice(flight);
        
        if (amount < requiredAmount) {
            System.out.printf("Payment failed: Insufficient amount. Required: %.2f, Provided: %.2f\n", 
                    requiredAmount, amount);
            return false;
        }

        double change = amount - requiredAmount;
        
        System.out.printf("Payment successful! Flight: %s, Method: %s\n", 
                flight.getNumber(), paymentMethod);
        System.out.printf("Amount: %.2f, Change: %.2f\n", requiredAmount, change);
        
        return true;
    }

    public double calculateFlightPrice(Flight flight) {
        double basePrice = 100.0;
        
        if (flight instanceof DomesticFlight) {
            DomesticFlight domestic = (DomesticFlight) flight;
            return basePrice + DOMESTIC_FEE + (basePrice * domestic.getExtraFee());
        } else if (flight instanceof InternationalFlight) {
            InternationalFlight international = (InternationalFlight) flight;
            return basePrice + INTERNATIONAL_FEE + international.getCustomsFee();
        } else if (flight instanceof CharterFlight) {
            return basePrice + CHARTER_FEE;
        }
        
        return basePrice;
    }

    public void displayPaymentOptions(Flight flight) {
        double price = calculateFlightPrice(flight);
        System.out.printf("\n=== PAYMENT OPTIONS FOR FLIGHT %s ===\n", flight.getNumber());
        System.out.printf("Total price: %.2f PLN\n", price);
        System.out.println("Available payment methods:");
        System.out.println("1. Credit Card");
        System.out.println("2. Bank Transfer");
        System.out.println("3. PayPal");
        System.out.println("4. Cash");
    }
}