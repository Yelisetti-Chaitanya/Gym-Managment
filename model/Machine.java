package model;

public class Machine implements GymEntity {
    private String id;
    private String name;
    private String type;
    private String status;

    public Machine(String id, String name, String type) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException("Machine ID cannot be null or empty.");
        }
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Machine name cannot be null or empty.");
        }
        if (type == null || type.trim().isEmpty()) {
            throw new IllegalArgumentException("Machine type cannot be null or empty.");
        }
        
        this.id = id;
        this.name = name;
        this.type = type;
        this.status = "Working";
    }

    
    public void display() {
        System.out.println("Machine ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Type: " + type);
        System.out.println("Status: " + status);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public String getType() { return type; }
    public String getStatus() { return status; }
    
    public void setStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            throw new IllegalArgumentException("Status cannot be null or empty.");
        }
        this.status = status;
    }
}
