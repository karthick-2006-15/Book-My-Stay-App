import java.util.*;

/**
 * UseCase11ConcurrentBookingSimulation demonstrates thread safety.
 * It ensures that shared inventory is updated correctly by multiple threads.
 * * @author Developer
 * @version 11.0
 */

// --- Thread-Safe Inventory Service ---
class ConcurrentRoomInventory {
    private Map<String, Integer> inventory = new HashMap<>();

    public void addRoomType(String type, int count) {
        inventory.put(type, count);
    }

    /**
     * The 'synchronized' keyword ensures that only one thread can
     * execute this method at a time, preventing race conditions.
     */
    public synchronized boolean bookRoom(String guestName, String type) {
        int available = inventory.getOrDefault(type, 0);

        if (available > 0) {
            // Simulate a slight delay to highlight potential race conditions
            // if synchronization were missing.
            try { Thread.sleep(10); } catch (InterruptedException e) {}

            inventory.put(type, available - 1);
            System.out.println("[SUCCESS] " + guestName + " booked a " + type + ". Remaining: " + (available - 1));
            return true;
        } else {
            System.out.println("[FAILED] " + guestName + " could not book " + type + ". Sold out!");
            return false;
        }
    }

    public int getFinalCount(String type) {
        return inventory.getOrDefault(type, 0);
    }
}

// --- Booking Task (Simulates a Guest) ---
class BookingTask implements Runnable {
    private ConcurrentRoomInventory inventory;
    private String guestName;
    private String roomType;

    public BookingTask(ConcurrentRoomInventory inventory, String guestName, String roomType) {
        this.inventory = inventory;
        this.guestName = guestName;
        this.roomType = roomType;
    }

    @Override
    public void run() {
        inventory.bookRoom(guestName, roomType);
    }
}

public class UseCase11ConcurrentBookingSimulation {
    public static void main(String[] args) throws InterruptedException {
        ConcurrentRoomInventory hotelInventory = new ConcurrentRoomInventory();

        // Only 2 Luxury Suites available
        hotelInventory.addRoomType("Luxury Suite", 2);

        System.out.println("Book My Stay App v11.0 - Concurrent Simulation");
        System.out.println("Initial Luxury Suites: 2");
        System.out.println("Simulating 5 simultaneous booking requests...\n");

        // Creating 5 guest threads competing for 2 rooms
        Thread t1 = new Thread(new BookingTask(hotelInventory, "Alice", "Luxury Suite"));
        Thread t2 = new Thread(new BookingTask(hotelInventory, "Bob", "Luxury Suite"));
        Thread t3 = new Thread(new BookingTask(hotelInventory, "Charlie", "Luxury Suite"));
        Thread t4 = new Thread(new BookingTask(hotelInventory, "David", "Luxury Suite"));
        Thread t5 = new Thread(new BookingTask(hotelInventory, "Eve", "Luxury Suite"));

        // Start all threads simultaneously
        t1.start(); t2.start(); t3.start(); t4.start(); t5.start();

        // Wait for all threads to finish
        t1.join(); t2.join(); t3.join(); t4.join(); t5.join();

        System.out.println("\nSimulation Finished.");
        System.out.println("Final Inventory for Luxury Suite: " + hotelInventory.getFinalCount("Luxury Suite"));
    }
}