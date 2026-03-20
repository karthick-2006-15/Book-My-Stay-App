<<<<<<< HEAD






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
/**
 * UseCase9Validation demonstrates robust error handling.
 * It uses Custom Exceptions to prevent invalid system states.
 * * @author Developer
 * @version 9.0
 */

// Custom Exception for Domain-Specific Errors
class BookingException extends Exception {
    public BookingException(String message) {
        super(message);

    }
}

class BookingValidator {
    /**
<<<<<<< HEAD
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


import java.util.*;

/**
 * UseCase6RoomAllocationService handles the final booking logic.
 * It ensures inventory consistency and prevents double-booking using a Set.
 * * @author Developer
 * @version 6.0
 */

// --- Reusing Reservation from UC5 ---
class Reservation {
    private String guestName;
    private String roomType;
    private String assignedRoomId;

    public Reservation(String guestName, String roomType) {
        this.guestName = guestName;
        this.roomType = roomType;
    }

    public String getGuestName() { return guestName; }
    public String getRoomType() { return roomType; }
    public void setAssignedRoomId(String id) { this.assignedRoomId = id; }
    public String getAssignedRoomId() { return assignedRoomId; }

    @Override
    public String toString() {
        return "Confirmed: " + guestName + " | Room: " + roomType + " | ID: " + assignedRoomId;
    }
}

// --- Allocation Service (The Core Logic) ---
class BookingService {
    private Map<String, Integer> inventory;
    private Set<String> allocatedRoomIds; // Prevents double-booking

    public BookingService(Map<String, Integer> inventory) {
        this.inventory = inventory;
        this.allocatedRoomIds = new HashSet<>();
    }

    public void processQueue(Queue<Reservation> queue) {
        System.out.println("\n--- Processing Booking Allocations ---");

        while (!queue.isEmpty()) {
            Reservation request = queue.poll(); // FIFO: Get the first person in line
            String type = request.getRoomType();

            // 1. Check Inventory
            int available = inventory.getOrDefault(type, 0);

            if (available > 0) {
                // 2. Generate Unique ID (e.g., SUITE-101)
                String roomId = type.toUpperCase().replace(" ", "") + "-" + (100 + available);

                // 3. Uniqueness Enforcement using Set
                if (!allocatedRoomIds.contains(roomId)) {
                    allocatedRoomIds.add(roomId);
                    request.setAssignedRoomId(roomId);

                    // 4. Update Inventory immediately
                    inventory.put(type, available - 1);

                    System.out.println("SUCCESS: " + request);
                }
            } else {
                System.out.println("FAILED: No availability for " + request.getGuestName() + " (" + type + ")");
            }
        }
    }
}

public class UseCase6RoomAllocationService {
    public static void main(String[] args) {
        // Setup initial state
        Map<String, Integer> hotelInventory = new HashMap<>();
        hotelInventory.put("Suite", 1);
        hotelInventory.put("Single", 5);

        Queue<Reservation> requestQueue = new LinkedList<>();
        requestQueue.add(new Reservation("Alice", "Suite"));
        requestQueue.add(new Reservation("Bob", "Suite")); // This should fail (only 1 Suite)
        requestQueue.add(new Reservation("Charlie", "Single"));

        // Initialize Booking Service
        BookingService service = new BookingService(hotelInventory);

        System.out.println("Book My Stay App v6.0 - Allocation Engine");
        System.out.println("Initial Suite Count: " + hotelInventory.get("Suite"));

        // Process all requests
        service.processQueue(requestQueue);

        System.out.println("\nFinal Suite Count: " + hotelInventory.get("Suite"));
    }


import java.util.*;

/**
 * UseCase7AddOnServiceSelection demonstrates system extensibility.
 * It maps multiple services to a single reservation ID using a Map of Lists.
 * * @author Developer
 * @version 7.0
 */

// --- Service Model ---
class AddOnService {
    private String name;
    private double price;

    public AddOnService(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() { return name; }
    public double getPrice() { return price; }

    @Override
    public String toString() {
        return name + " ($" + price + ")";
    }
}

// --- Service Manager (One-to-Many Mapping) ---
class AddOnServiceManager {
    // Key: Reservation ID, Value: List of selected services
    private Map<String, List<AddOnService>> selections;

    public AddOnServiceManager() {
        this.selections = new HashMap<>();
    }

    /**
     * Adds a service to a specific reservation ID.
     */
    public void addServiceToReservation(String reservationId, AddOnService service) {
        // computeIfAbsent creates a new ArrayList if the key doesn't exist yet
        selections.computeIfAbsent(reservationId, k -> new ArrayList<>()).add(service);
        System.out.println("Added " + service.getName() + " to Reservation: " + reservationId);
    }

    /**
     * Calculates the total cost of all add-ons for a reservation.
     */
    public double calculateTotalAddOnCost(String reservationId) {
        List<AddOnService> services = selections.getOrDefault(reservationId, Collections.emptyList());
        return services.stream().mapToDouble(AddOnService::getPrice).sum();
    }

    public void displayAddOns(String reservationId) {
        List<AddOnService> services = selections.getOrDefault(reservationId, Collections.emptyList());
        if (services.isEmpty()) {
            System.out.println("No add-ons for " + reservationId);
        } else {
            System.out.println("Add-ons for " + reservationId + ": " + services);
        }
    }
}

public class UseCase7AddOnServiceSelection {
    public static void main(String[] args) {
        AddOnServiceManager serviceManager = new AddOnServiceManager();

        // Pre-defined Services
        AddOnService breakfast = new AddOnService("Buffet Breakfast", 25.0);
        AddOnService spa = new AddOnService("Spa Treatment", 80.0);
        AddOnService wifi = new AddOnService("Premium Wi-Fi", 15.0);

        // Simulation: Existing Reservation IDs from Use Case 6
        String resId1 = "SUITE-101";
        String resId2 = "SINGLE-105";

        System.out.println("Book My Stay App v7.0 - Add-On Services");
        System.out.println("----------------------------------------");

        // Guest 1 selects multiple services
        serviceManager.addServiceToReservation(resId1, breakfast);
        serviceManager.addServiceToReservation(resId1, spa);

        // Guest 2 selects one service
        serviceManager.addServiceToReservation(resId2, wifi);

        // Display results and costs
        System.out.println("\n--- Final Billing Summary (Add-ons Only) ---");

        serviceManager.displayAddOns(resId1);
        System.out.println("Total Extra Cost for " + resId1 + ": $" + serviceManager.calculateTotalAddOnCost(resId1));

        serviceManager.displayAddOns(resId2);
        System.out.println("Total Extra Cost for " + resId2 + ": $" + serviceManager.calculateTotalAddOnCost(resId2));
    }


import java.util.*;

/**
 * UseCase8BookingHistoryReport demonstrates historical tracking and reporting.
 * It uses a List to maintain a chronological audit trail of all transactions.
 * * @author Developer
 * @version 8.0
 */

// --- Enhanced Reservation Model ---
class Reservation {
    private String guestName;
    private String roomType;
    private String roomId;
    private double totalCost;

    public Reservation(String guestName, String roomType, String roomId, double totalCost) {
        this.guestName = guestName;
        this.roomType = roomType;
        this.roomId = roomId;
        this.totalCost = totalCost;
    }

    public double getTotalCost() { return totalCost; }

    @Override
    public String toString() {
        return String.format("ID: %-12s | Guest: %-10s | Room: %-10s | Total: $%.2f",
                roomId, guestName, roomType, totalCost);
    }
}

// --- Booking History & Reporting Service ---
class BookingReportService {
    // List preserves the order of confirmation (Chronological)
    private List<Reservation> history;

    public BookingReportService() {
        this.history = new ArrayList<>();
    }

    /**
     * Records a confirmed reservation into the history log.
     */
    public void recordBooking(Reservation res) {
        history.add(res);
    }

    /**
     * Generates a summary report of all activity.
     */
    public void generateAdminReport() {
        System.out.println("\n======= ADMINISTRATIVE BOOKING REPORT =======");
        if (history.isEmpty()) {
            System.out.println("No records found.");
            return;
        }

        double totalRevenue = 0;
        for (Reservation res : history) {
            System.out.println(res);
            totalRevenue += res.getTotalCost();
        }

        System.out.println("---------------------------------------------");
        System.out.println("Total Bookings: " + history.size());
        System.out.printf("Total Revenue:  $%.2f\n", totalRevenue);
        System.out.println("=============================================");
    }
}

public class UseCase8BookingHistoryReport {
    public static void main(String[] args) {
        BookingReportService reportService = new BookingReportService();

        System.out.println("Book My Stay App v8.0 - Reporting System");
        System.out.println("Recording confirmed bookings...");

        // Simulating the recording of bookings confirmed in previous stages
        // (Room Price + Add-on Costs aggregated)
        reportService.recordBooking(new Reservation("Alice", "Suite", "SUITE-101", 455.00));
        reportService.recordBooking(new Reservation("Charlie", "Single", "SINGLE-105", 115.00));
        reportService.recordBooking(new Reservation("Eve", "Double", "DOUBLE-102", 180.00));

        // Admin requests the report
        reportService.generateAdminReport();
    }

     * Validates if a booking can proceed based on input and inventory.
     * Throws an exception if any rule is violated (Fail-Fast).
     */
    public static void validateRequest(String roomType, int currentCount, java.util.Set<String> validTypes)
            throws BookingException {

        // 1. Validate Input: Does the room type even exist?
        if (!validTypes.contains(roomType)) {
            throw new BookingException("Invalid Room Type: '" + roomType + "' is not offered by this hotel.");
        }

        // 2. Validate State: Is there enough inventory?
        if (currentCount <= 0) {
            throw new BookingException("Sold Out: No '" + roomType + "' units available for booking.");
        }
    }
}

public class UseCase9Validation {
    public static void main(String[] args) {
        // Setup initial state
        java.util.Map<String, Integer> inventory = new java.util.HashMap<>();
        inventory.put("Suite", 1);

        java.util.Set<String> validRoomTypes = new java.util.HashSet<>();
        validRoomTypes.add("Suite");
        validRoomTypes.add("Single");

        System.out.println("Book My Stay App v9.0 - Validation & Error Handling");
        System.out.println("---------------------------------------------------");

        // Test Scenario 1: Invalid Room Type
        processTestBooking("Penthouse", inventory, validRoomTypes);

        // Test Scenario 2: Valid Room Type, but Sold Out
        processTestBooking("Single", inventory, validRoomTypes);

        // Test Scenario 3: Valid Request
        processTestBooking("Suite", inventory, validRoomTypes);
    }

    private static void processTestBooking(String type, java.util.Map<String, Integer> inv, java.util.Set<String> validTypes) {
        try {
            System.out.println("Attempting to book: " + type);
            int currentCount = inv.getOrDefault(type, 0);

            // The Gatekeeper: Validation
            BookingValidator.validateRequest(type, currentCount, validTypes);

            // If we reach here, validation passed
            inv.put(type, currentCount - 1);
            System.out.println("SUCCESS: Room allocated successfully.");

        } catch (BookingException e) {
            // Graceful failure handling
            System.err.println("ERROR: " + e.getMessage());
        } finally {
            System.out.println("Status: Validation check complete.\n");
        }
    }


import java.util.*;

/**
 * UseCase10BookingCancellation handles the reversal of system state.
 * It uses a Stack to manage room ID rollbacks and updates the inventory.
 * * @author Developer
 * @version 10.0
 */

class CancellationService {
    private Map<String, Integer> inventory;
    private Set<String> allocatedRoomIds;
    private Stack<String> cancellationHistory; // Tracks IDs for potential undo/audit

    public CancellationService(Map<String, Integer> inventory, Set<String> allocatedRoomIds) {
        this.inventory = inventory;
        this.allocatedRoomIds = allocatedRoomIds;
        this.cancellationHistory = new Stack<>();
    }

    /**
     * Reverses a booking by restoring inventory and releasing the Room ID.
     */
    public void cancelBooking(String roomId, String roomType) throws Exception {
        System.out.println("Initiating cancellation for: " + roomId);

        // 1. Validation: Does this room ID actually exist in our allocated set?
        if (!allocatedRoomIds.contains(roomId)) {
            throw new Exception("Cancellation Failed: Room ID " + roomId + " not found or already cancelled.");
        }

        // 2. State Reversal: Remove from Allocated Set
        allocatedRoomIds.remove(roomId);

        // 3. Inventory Restoration: Increment the count
        int currentCount = inventory.getOrDefault(roomType, 0);
        inventory.put(roomType, currentCount + 1);

        // 4. LIFO Tracking: Push to cancellation stack
        cancellationHistory.push(roomId);

        System.out.println("SUCCESS: " + roomId + " released. " + roomType + " inventory restored.");
    }

    public void displayStatus(String roomType) {
        System.out.println("Current " + roomType + " Inventory: " + inventory.get(roomType));
    }
}

public class UseCase10BookingCancellation {
    public static void main(String[] args) {
        // Initializing state from previous use cases
        Map<String, Integer> hotelInventory = new HashMap<>();
        hotelInventory.put("Suite", 0); // Assume all suites were booked

        Set<String> activeAllocations = new HashSet<>();
        activeAllocations.add("SUITE-101");
        activeAllocations.add("SUITE-102");

        CancellationService service = new CancellationService(hotelInventory, activeAllocations);

        System.out.println("Book My Stay App v10.0 - Cancellation & Rollback");
        System.out.println("-------------------------------------------------");
        service.displayStatus("Suite");

        try {
            // Valid Cancellation
            service.cancelBooking("SUITE-101", "Suite");
            service.displayStatus("Suite");

            // Attempting to cancel the same ID again (Should Fail)
            System.out.println("\nAttempting duplicate cancellation...");
            service.cancelBooking("SUITE-101", "Suite");

        } catch (Exception e) {
            System.err.println("ERROR: " + e.getMessage());
        }

        System.out.println("\nSystem state remains consistent after rollback.");
    }


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


import java.io.*;
import java.util.*;

/**
 * UseCase12DataPersistenceRecovery demonstrates durable system design.
 * It ensures that inventory and booking history survive application restarts.
 * * @author Developer
 * @version 12.0
 */

// --- Serializable Data Model ---
class HotelState implements Serializable {
    private static final long serialVersionUID = 1L;
    public Map<String, Integer> inventory;
    public List<String> bookingHistory;

    public HotelState(Map<String, Integer> inventory, List<String> bookingHistory) {
        this.inventory = inventory;
        this.bookingHistory = bookingHistory;
    }
}

class PersistenceService {
    private static final String FILE_NAME = "hotel_data.ser";

    /**
     * Serializes the current state to a physical file.
     */
    public void saveState(Map<String, Integer> inventory, List<String> history) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            HotelState state = new HotelState(inventory, history);
            oos.writeObject(state);
            System.out.println("SYSTEM: State persisted successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.err.println("ERROR: Could not save state - " + e.getMessage());
        }
    }

    /**
     * Deserializes data from the file back into memory.
     */
    public HotelState loadState() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("SYSTEM: No previous state found. Starting fresh.");
            return null;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            System.out.println("SYSTEM: Restoring state from " + FILE_NAME + "...");
            return (HotelState) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("ERROR: Recovery failed - " + e.getMessage());
            return null;
        }
    }
}

public class UseCase12DataPersistenceRecovery {
    public static void main(String[] args) {
        PersistenceService persistence = new PersistenceService();

        // 1. ATTEMPT RECOVERY
        HotelState recoveredState = persistence.loadState();

        Map<String, Integer> currentInventory;
        List<String> currentHistory;

        if (recoveredState != null) {
            currentInventory = recoveredState.inventory;
            currentHistory = recoveredState.bookingHistory;
            System.out.println("RECOVERY SUCCESSFUL. History count: " + currentHistory.size());
        } else {
            // Initial setup if no file exists
            currentInventory = new HashMap<>();
            currentInventory.put("Suite", 5);
            currentHistory = new ArrayList<>();
        }

        // 2. SIMULATE ACTIVITY
        System.out.println("Processing new booking...");
        String newBooking = "Booking #" + (currentHistory.size() + 1) + ": Guest Alice - Suite";
        currentHistory.add(newBooking);
        currentInventory.put("Suite", currentInventory.get("Suite") - 1);

        // 3. PERSIST BEFORE SHUTDOWN
        System.out.println("Shutting down system...");
        persistence.saveState(currentInventory, currentHistory);

        System.out.println("\nRun the program again to see the history count increase!");
    }

}