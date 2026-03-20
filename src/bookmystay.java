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
}