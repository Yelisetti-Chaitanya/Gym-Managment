package model;

import java.io.Serializable;

public abstract class Person implements Serializable {
    private String id;
    private String name;
    private String phone;
    private String email;
    private boolean active;

    public Person(String id, String name, String phone, String email) {
        if (id == null || name == null || phone == null || email == null) {
            throw new IllegalArgumentException("All fields must be non-null");
        }
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.active = true;
    }

    public abstract void display();

    public void updateDetails(String name, String phone, String email) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null");
        }
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public String getId() { 
        return id; 
    }

    public String getName() { 
        return name; 
    }

    public void setName(String name) { 
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name; 
    }

    public String getPhone() { 
        return phone; 
    }

    public void setPhone(String phone) { 
        if (phone == null) {
            throw new IllegalArgumentException("Phone cannot be null");
        }
        this.phone = phone; 
    }

    public String getEmail() { 
        return email; 
    }

    public void setEmail(String email) { 
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        this.email = email; 
    }

    public boolean isActive() { 
        return active; 
    }

    public void setActive(boolean active) { 
        this.active = active; 
    }
}
