import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue demonstrates fair request handling.
 * It uses a Queue to ensure First-Come-First-Served (FCFS) processing.
 * * @author Developer
 * @version 5.0
 */

// Represents a Guest's intent to book
class Reservation {
    private String guestName;
    private String roomType;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }

    @Override
    public String toString() {
        return "Reservation [Guest: " + guestName + ", Room: " + roomType + "]";
    }
}

// Manages the incoming stream of requests
class BookingRequestQueue {
    private Queue<Reservation> requestQueue;

    public BookingRequestQueue() {
        // LinkedList is a common implementation of the Queue interface in Java
        this.requestQueue = new LinkedList<>();
    }

    /**
     * Adds a new booking request to the end of the line.
     */
    public void enqueueRequest(Reservation reservation) {
        requestQueue.add(reservation);
        System.out.println("Enqueued: " + reservation.getGuestName() + " for a " + reservation.getRoomType());
    }

    /**
     * Displays all pending requests in the order they arrived.
     */
    public void displayQueue() {
        System.out.println("\n--- Current Booking Request Queue (FIFO) ---");
        if (requestQueue.isEmpty()) {
            System.out.println("Queue is empty.");
        } else {
            for (Reservation res : requestQueue) {
                System.out.println(res);
            }
        }
    }

    /**
     * Provides access to the queue for the next processing stage.
     */
    public Queue<Reservation> getQueue() {
        return requestQueue;
    }
}

public class UseCase5BookingRequestQueue {
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();

        System.out.println("Book My Stay App v5.0 - Request Intake System");
        System.out.println("----------------------------------------------");

        // Simulating guests submitting requests in a specific order
        bookingQueue.enqueueRequest(new Reservation("Alice", "Suite"));
        bookingQueue.enqueueRequest(new Reservation("Bob", "Single"));
        bookingQueue.enqueueRequest(new Reservation("Charlie", "Double"));

        // Showing that order is preserved
        bookingQueue.displayQueue();

        System.out.println("\nStatus: Requests captured. Awaiting allocation processing.");
    }
}