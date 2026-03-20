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
}