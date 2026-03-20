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
}