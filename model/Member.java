package model;

import java.util.ArrayList;
import java.util.List;

public class Member extends Person implements GymEntity {
    private String membershipType;
    private Subscsription subscription;
    private List<Payment> payments;
    private List<AttendanceRecord> attendanceRecords;
    private boolean active;

    public Member(String id, String name, String phone, String email, String membershipType) {
        super(id, name, phone, email);
        if (membershipType == null || membershipType.trim().isEmpty()) {
            throw new IllegalArgumentException("Membership type cannot be null or empty.");
        }
        this.membershipType = membershipType;
        this.payments = new ArrayList<>();
        this.attendanceRecords = new ArrayList<>();
        this.active = true;
    }

    @Override
    public void display() {
        System.out.println("Member ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Phone: " + getPhone());
        System.out.println("Email: " + getEmail());
        System.out.println("Membership Type: " + membershipType);
        System.out.println("Status: " + (active ? "Active" : "Inactive"));
    }

    public void setSubscription(Subscsription subscription) {
        if (subscription == null) {
            throw new IllegalArgumentException("Subscription cannot be null.");
        }
        this.subscription = subscription;
    }

    public void addPayment(Payment payment) {
        if (payment == null) {
            throw new IllegalArgumentException("Payment cannot be null.");
        }
        payments.add(payment);
    }

    public void addAttendance(AttendanceRecord record) {
        if (record == null) {
            throw new IllegalArgumentException("Attendance record cannot be null.");
        }
        attendanceRecords.add(record);
    }

    public void updateDetails(String name, String phone, String email) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        if (phone == null || phone.trim().isEmpty()) {
            throw new IllegalArgumentException("Phone cannot be null or empty.");
        }
        if (email == null || email.trim().isEmpty()) {
            throw new IllegalArgumentException("Email cannot be null or empty.");
        }
        setName(name);
        setPhone(phone);
        setEmail(email);
    }

    public String getMembershipType() {
        return membershipType;
    }

    public Subscsription getSubscription() {
        return subscription;
    }

    public List<Payment> getPayments() {
        return payments;
    }

    public List<AttendanceRecord> getAttendanceRecords() {
        return attendanceRecords;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
