package model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Payment implements Serializable {
    private String id;
    private double amount;
    private LocalDateTime dateTime;
    private String type;
    private String status;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public Payment(String id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
        this.dateTime = LocalDateTime.now();
        this.status = "Pending";
    }

    public void processPayment() {
        this.status = "Completed";
    }

    public String getId() { return id; }
    public double getAmount() { return amount; }
    public LocalDateTime getDateTime() { return dateTime; }
    public String getType() { return type; }
    public String getStatus() { return status; }

    public void setDateTime(LocalDateTime dateTime) { this.dateTime = dateTime; }
    public void setStatus(String status) { this.status = status; }

    public void display() {
        System.out.println("Payment ID: " + id);
        System.out.println("Amount: $" + String.format("%.2f", amount));
        System.out.println("Type: " + type);
        System.out.println("Date/Time: " + dateTime.format(DATE_FORMATTER));
        System.out.println("Status: " + status);
    }
}
