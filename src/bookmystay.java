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
}