/**
 * UseCase2RoomInitialization demonstrates object modeling using inheritance.
 * This version establishes the domain entities for the booking system.
 * * @author Developer
 * @version 2.0
 */

// Abstract Class: Defines the template for all rooms
abstract class Room {
    private String roomType;
    private double price;

    public Room(String roomType, double price) {
        this.roomType = roomType;
        this.price = price;
    }

    public String getRoomType() { return roomType; }
    public double getPrice() { return price; }

    // Abstract method to be implemented by subclasses
    public abstract void displayFeatures();
}

// Concrete Class: Single Room
class SingleRoom extends Room {
    public SingleRoom() { super("Single Room", 100.0); }
    @Override
    public void displayFeatures() {
        System.out.println("Features: 1 Single Bed, High-speed Wi-Fi");
    }
}

// Concrete Class: Double Room
class DoubleRoom extends Room {
    public DoubleRoom() { super("Double Room", 180.0); }
    @Override
    public void displayFeatures() {
        System.out.println("Features: 1 Queen Bed, Mini-bar, City View");
    }
}

// Concrete Class: Suite Room
class SuiteRoom extends Room {
    public SuiteRoom() { super("Suite Room", 350.0); }
    @Override
    public void displayFeatures() {
        System.out.println("Features: King Bed, Living Area, Complimentary Breakfast");
    }
}

public class UseCase2RoomInitialization {
    public static void main(String[] args) {
        // Step 1: Initialize Room Objects (Polymorphism)
        Room single = new SingleRoom();
        Room doubleRm = new DoubleRoom();
        Room suite = new SuiteRoom();

        // Step 2: Static Availability (Hardcoded variables)
        int singleAvailable = 5;
        int doubleAvailable = 3;
        int suiteAvailable = 2;

        System.out.println("=== Hotel Room Inventory (v2.0) ===");

        // Displaying Details
        displayRoomStatus(single, singleAvailable);
        displayRoomStatus(doubleRm, doubleAvailable);
        displayRoomStatus(suite, suiteAvailable);
    }

    private static void displayRoomStatus(Room room, int count) {
        System.out.println("\nType: " + room.getRoomType());
        System.out.println("Price: $" + room.getPrice());
        System.out.println("Availability: " + count + " rooms left");
        room.displayFeatures();
    }
}