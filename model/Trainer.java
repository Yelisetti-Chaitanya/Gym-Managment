package model;

import java.util.List;
import java.util.ArrayList;

public class Trainer extends Person {
    private String specialization;
    private double hourlyRate;
    private List<Schedule.Session> sessions;

    public Trainer(String id, String name, String phone, String email, String specialization, double hourlyRate) {
        super(id, name, phone, email);
        
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be null or empty.");
        }
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }

        this.specialization = specialization;
        this.hourlyRate = hourlyRate;
        this.sessions = new ArrayList<>();
    }

    @Override
    public void display() {
        System.out.println("Trainer ID: " + getId());
        System.out.println("Name: " + getName());
        System.out.println("Phone: " + getPhone());
        System.out.println("Email: " + getEmail());
        System.out.println("Specialization: " + specialization);
        System.out.println("Hourly Rate: $" + hourlyRate);
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        if (specialization == null || specialization.trim().isEmpty()) {
            throw new IllegalArgumentException("Specialization cannot be null or empty.");
        }
        this.specialization = specialization;
    }

    public double getHourlyRate() {
        return hourlyRate;
    }

    public void setHourlyRate(double hourlyRate) {
        if (hourlyRate < 0) {
            throw new IllegalArgumentException("Hourly rate cannot be negative.");
        }
        this.hourlyRate = hourlyRate;
    }

    public List<Schedule.Session> getSessions() {
        return new ArrayList<>(sessions);
    }

    public void addSession(Schedule.Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session cannot be null.");
        }
        sessions.add(session);
    }

    public void removeSession(Schedule.Session session) {
        if (session == null) {
            throw new IllegalArgumentException("Session to remove cannot be null.");
        }
        sessions.remove(session);
    }
}