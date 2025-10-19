import java.time.LocalDateTime;

public abstract class Flight implements Bookable {
    protected String number;
    protected String destination;
    protected LocalDateTime dateTime;
    protected int capacity;
    protected int booked;

    public Flight(String number, String destination, LocalDateTime dateTime, int capacity) {
        this.number = number;
        this.destination = destination;
        this.dateTime = dateTime;
        this.capacity = capacity;
        this.booked = 0;
    }

    public String getNumber() { return number; }
    public String getDestination() { return destination; }
    public LocalDateTime getDateTime() { return dateTime; }
    public int getCapacity() { return capacity; }
    public int getBooked() { return booked; }

        public void setNumber(String number) {
        this.number = number;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

 
    protected void setBooked(int booked) {
    this.booked = booked;
    }

    public int seatsAvailable() {
        return capacity - booked;
    }

    @Override
    public boolean bookTicket() {
        if (seatsAvailable() <= 0) return false;
        booked++;
        return true;
    }

    @Override
    public String toString() {
        return String.format("Flight %s -> %s at %s | %d/%d booked",
                number, destination, dateTime.toString(), booked, capacity);
    }
}