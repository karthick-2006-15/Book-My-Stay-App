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
}