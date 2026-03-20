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

 * * @author Developer
 * @version 3.0
 */

class RoomInventory {
    // Encapsulated HashMap: Key = Room Type, Value = Available Count
    private Map<String, Integer> inventory;

    public RoomInventory() {
        this.inventory = new HashMap<>();
    }

    /**
     * Registers a room type with an initial count.
     */
    public void addRoomType(String roomType, int count) {
        inventory.put(roomType, count);
    }

    /**
     * Retrieves the current availability for a specific room type.
     */
    public int getAvailability(String roomType) {
        return inventory.getOrDefault(roomType, 0);
    }

    /**
     * Updates availability (e.g., after a booking or cancellation).
     */
    public void updateAvailability(String roomType, int change) {
        if (inventory.containsKey(roomType)) {
            int current = inventory.get(roomType);
            inventory.put(roomType, current + change);
        }
    }

    public void displayInventory() {
        System.out.println("--- Current Room Inventory ---");
        for (Map.Entry<String, Integer> entry : inventory.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + " available");
        }
    }
}

public class UseCase3InventorySetup {
    public static void main(String[] args) {
        RoomInventory hotelInventory = new RoomInventory();

        // Registering rooms into the centralized Map
        hotelInventory.addRoomType("Single Room", 10);
        hotelInventory.addRoomType("Double Room", 5);
        hotelInventory.addRoomType("Suite Room", 2);

        System.out.println("Hotel Booking System v3.0 - Inventory Initialized.");

        // Simulating a booking (reducing count by 1)
        System.out.println("\nBooking one Double Room...");
        hotelInventory.updateAvailability("Double Room", -1);


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
}