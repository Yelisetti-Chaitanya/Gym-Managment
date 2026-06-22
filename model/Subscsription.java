package model;

import java.io.Serializable;
import java.time.LocalDate;

public class Subscsription implements Serializable {
    private String id;
    private String type;
    private LocalDate startDate;
    private LocalDate endDate;
    private double amount;

    public Subscsription(String id, String type, LocalDate startDate, LocalDate endDate, double amount) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Subscription ID cannot be null or empty.");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Subscription type cannot be null or empty.");
        }
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("Start date and end date cannot be null.");
        }
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("End date cannot be before start date.");
        }
        if (amount < 0) {
            throw new IllegalArgumentException("Amount cannot be negative.");
        }

        this.id = id;
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    @Override
    public String toString() {
        return String.format("%s (%s to %s) - $%.2f", type, startDate, endDate, amount);
    }

    public String getId() { return id; }
    public String getType() { return type; }
    public LocalDate getStartDate() { return startDate; }
    public LocalDate getEndDate() { return endDate; }
    public double getAmount() { return amount; }
}
