
import java.util.HashMap;
import java.util.Map;
import java.util.ArrayList;
import java.util.List;

/**
<<<<<<< HEAD
<<<<<<< HEAD
<<<<<<< HEAD
 * UseCase3InventorySetup introduces centralized state management.
 * It replaces individual variables with a HashMap for better scalability.
=======
 * UseCase4RoomSearch demonstrates read-only search logic.
 * It filters inventory to show only available rooms without mutating state.
>>>>>>> uc4
 * * @author Developer
 * @version 4.0
 */

// --- Domain Model (From UC2) ---
abstract class Room {
    private String type;
    private double price;

    public Room(String type, double price) {
        this.type = type;
        this.price = price;
    }

    public String getType() { return type; }
    public double getPrice() { return price; }
}

class SingleRoom extends Room { public SingleRoom() { super("Single", 100.0); } }
class DoubleRoom extends Room { public DoubleRoom() { super("Double", 180.0); } }
class SuiteRoom extends Room { public SuiteRoom() { super("Suite", 350.0); } }

// --- Inventory Management (From UC3) ---
class RoomInventory {
    private Map<String, Integer> counts = new HashMap<>();

    public void setAvailability(String type, int count) { counts.put(type, count); }
    public int getCount(String type) { return counts.getOrDefault(type, 0); }
    public Map<String, Integer> getAllInventory() { return counts; }
}

// --- NEW: Search Service (UC4) ---
class SearchService {
    private RoomInventory inventory;
    private List<Room> roomTemplates;

    public SearchService(RoomInventory inventory) {
        this.inventory = inventory;
        this.roomTemplates = new ArrayList<>();
        // Initialize templates to get metadata like price
        roomTemplates.add(new SingleRoom());
        roomTemplates.add(new DoubleRoom());
        roomTemplates.add(new SuiteRoom());
    }

    /**
     * Performs a read-only search for available rooms.
     */

    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);

 * UseCase2RoomInitialization demonstrates object modeling using inheritance.
 * This version establishes the domain entities for the booking system.

 * UseCase3InventorySetup introduces centralized state management.
 * It replaces individual variables with a HashMap for better scalability.


import java.util.LinkedList;
import java.util.Queue;

/**
 * UseCase5BookingRequestQueue demonstrates fair request handling.
 * It uses a Queue to ensure First-Come-First-Served (FCFS) processing.
>>>>>>> uc5
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



    private static void displayRoomStatus(Room room, int count) {
        System.out.println("\nType: " + room.getRoomType());
        System.out.println("Price: $" + room.getPrice());
        System.out.println("Availability: " + count + " rooms left");
        room.displayFeatures();


        // Displaying final state
        hotelInventory.displayInventory();

    }

    public void searchAvailableRooms() {
        System.out.println("\n--- Available Rooms Search Results ---");
        boolean found = false;


        for (Room room : roomTemplates) {
            int availableCount = inventory.getCount(room.getType());

            // Validation Logic: Only show if count > 0
            if (availableCount > 0) {
                System.out.println("Room: " + room.getType() +
                        " | Price: $" + room.getPrice() +
                        " | Available: " + availableCount);
                found = true;
            }
        }

        if (!found) {
            System.out.println("Sorry, no rooms are currently available.");
        }
    }
}

public class UseCase4RoomSearch {
    public static void main(String[] args) {
        // Setup System
        RoomInventory hotelInventory = new RoomInventory();
        hotelInventory.setAvailability("Single", 5);
        hotelInventory.setAvailability("Double", 0); // Sold out
        hotelInventory.setAvailability("Suite", 2);

        SearchService searchService = new SearchService(hotelInventory);

        System.out.println("Book My Stay App v4.0");

        // Execute Search
        searchService.searchAvailableRooms();

        System.out.println("\nSearch complete. System state remains unchanged.");
    }
=======
        System.out.println("\nStatus: Requests captured. Awaiting allocation processing.");
    }

}