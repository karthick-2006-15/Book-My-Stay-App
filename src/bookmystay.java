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