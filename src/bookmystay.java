import java.util.HashMap;
import java.util.Map;

/**
<<<<<<< HEAD
<<<<<<< HEAD
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

<<<<<<< HEAD
    private static void displayRoomStatus(Room room, int count) {
        System.out.println("\nType: " + room.getRoomType());
        System.out.println("Price: $" + room.getPrice());
        System.out.println("Availability: " + count + " rooms left");
        room.displayFeatures();

=======
        // Displaying final state
        hotelInventory.displayInventory();
>>>>>>> uc3
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

        // Displaying final state
        hotelInventory.displayInventory();
    }
}
