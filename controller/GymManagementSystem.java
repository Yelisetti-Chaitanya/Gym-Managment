package controller;

import model.*;
import java.io.*;
import java.util.Scanner;
import java.util.List;

public class GymManagementSystem {
    private static Admin admin;
    private static Member[] members;
    private static Machine[] machines;
    private static Trainer[] trainers;
    private static List<Payment> payments;
    private static final Scanner scanner = new Scanner(System.in);
    private static final int MAX_MEMBERS = 200;
    private static final int MAX_MACHINES = 100;
    private static final int MAX_TRAINERS = 50;

    public static void main(String[] args) {
        try {
            initialize();
            UI.displayWelcome();
            
            if (args.length > 0) {
                runCommandLineMode(args);
            } else {
                runInteractiveMode();
            }
        } catch (IOException e) {
            System.out.println("Error: " + e.getMessage());
            System.exit(1);
        } finally {
            scanner.close();
        }
    }

    private static void initialize() throws IOException {
        FileHandler.initializeFiles();
        admin = FileHandler.loadAdmin();
        members = FileHandler.loadMembers();
        machines = FileHandler.loadMachines();
        trainers = new Trainer[MAX_TRAINERS];  // Initialize trainers array
        trainers = FileHandler.loadTrainers();
        payments = FileHandler.loadPayments();
        
        // Count existing members
        for (Member member : members) {
            if (member != null) {
                // memberCount++;
            }
        }
        
        // Count existing machines
        for (Machine machine : machines) {
            if (machine != null) {
                // machineCount++;
            }
        }
        
        // Count existing payments
        for (Payment payment : payments) {
            if (payment != null) {
                // paymentCount++;
            }
        }
    }

    private static void runInteractiveMode() throws IOException {
        while (true) {
            try {
                System.out.println("\nDefault Username: " + admin.getUsername());
                System.out.println("Default Password: " + admin.getPassword() + "\n");
                System.out.println("1. Login");
                System.out.println("2. Change Login Credentials");
                System.out.println("0. Quit Program");
                System.out.print("\nEnter Your Choice: ");
                
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        System.out.print("Enter Username (0 to go back): ");
                        String username = scanner.nextLine().trim();
                        if (username.equals("0")) continue;
                        
                        System.out.print("Enter Password: ");
                        String password = scanner.nextLine().trim();
                        
                        if (login(username, password)) {
                            adminMenu();
                        }
                        break;
                        
                    case "2":
                        System.out.print("Enter New Username (0 to go back): ");
                        String newUsername = scanner.nextLine().trim();
                        if (newUsername.equals("0")) continue;
                        
                        System.out.print("Enter New Password: ");
                        String newPassword = scanner.nextLine().trim();
                        
                        changeLoginCredentials(newUsername, newPassword);
                        break;
                        
                    case "0":
                        System.out.println("Thank you for using the Gym Management System!");
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static boolean login(String username, String password) {
        if (admin.getUsername().equals(username) && admin.getPassword().equals(password)) {
            System.out.println("\nLogin successful!");
            return true;
        } else {
            System.out.println("\nInvalid credentials.");
            return false;
        }
    }

    private static void changeLoginCredentials(String newUsername, String newPassword) throws IOException {
        if (newUsername.isEmpty() || newPassword.isEmpty()) {
            System.out.println("Error: Username and password cannot be empty!");
            return;
        }
        
        admin = new Admin(newUsername, newPassword);
        FileHandler.saveAdmin(admin);
        System.out.println("Login credentials updated successfully!");
    }

    private static void adminMenu() throws IOException {
        while (true) {
            try {
                System.out.println("\n=== Admin Menu ===");
                System.out.println("1. Manage Members");
                System.out.println("2. Manage Trainers");
                System.out.println("3. Manage Machines");
                System.out.println("4. Manage Payments");
                System.out.println("5. View Reports");
                System.out.println("0. Logout");
                System.out.print("\nEnter your choice: ");
                
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        manageMembersMenu();
                        break;
                        
                    case "2":
                        manageTrainersMenu();
                        break;
                        
                    case "3":
                        manageMachinesMenu();
                        break;
                        
                    case "4":
                        managePaymentsMenu();
                        break;
                        
                    case "5":
                        viewReportsMenu();
                        break;
                        
                    case "0":
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static void manageMembersMenu() throws IOException {
        while (true) {
            try {
                System.out.println("\n=== Manage Members ===");
                System.out.println("1. Add Member");
                System.out.println("2. View Members");
                System.out.println("3. Update Member");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");
                
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        System.out.print("Enter Member ID (0 to go back): ");
                        String id = scanner.nextLine().trim();
                        if (id.equals("0")) continue;
                        
                        if (id.isEmpty()) {
                            System.out.println("Error: Member ID cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Error: Name cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Phone: ");
                        String phone = scanner.nextLine().trim();
                        if (phone.isEmpty()) {
                            System.out.println("Error: Phone cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine().trim();
                        if (email.isEmpty()) {
                            System.out.println("Error: Email cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Membership Type (Standard/Premium): ");
                        String membershipType = scanner.nextLine().trim();
                        if (!membershipType.equalsIgnoreCase("Standard") && !membershipType.equalsIgnoreCase("Premium")) {
                            System.out.println("Error: Invalid membership type! Must be 'Standard' or 'Premium'");
                            continue;
                        }
                        
                        addNewMember(id, name, phone, email, membershipType);
                        break;
                        
                    case "2":
                        viewAllMembers();
                        break;
                        
                    case "3":
                        System.out.print("Enter Member ID to update (0 to go back): ");
                        String updateId = scanner.nextLine().trim();
                        if (updateId.equals("0")) continue;
                        
                        // Find the member
                        Member memberToUpdate = null;
                        int memberIndex = -1;
                        for (int i = 0; i < members.length; i++) {
                            if (members[i] != null && members[i].getId().equals(updateId)) {
                                memberToUpdate = members[i];
                                memberIndex = i;
                                break;
                            }
                        }
                        
                        if (memberToUpdate == null) {
                            System.out.println("Error: Member with ID " + updateId + " not found!");
                            continue;
                        }
                        
                        // Show current details
                        System.out.println("\nCurrent Member Details:");
                        memberToUpdate.display();
                        
                        // Get new details
                        System.out.print("\nEnter New Name (press Enter to keep current): ");
                        String newName = scanner.nextLine().trim();
                        if (newName.isEmpty()) {
                            newName = memberToUpdate.getName();
                        }
                        
                        System.out.print("Enter New Phone (press Enter to keep current): ");
                        String newPhone = scanner.nextLine().trim();
                        if (newPhone.isEmpty()) {
                            newPhone = memberToUpdate.getPhone();
                        }
                        
                        System.out.print("Enter New Email (press Enter to keep current): ");
                        String newEmail = scanner.nextLine().trim();
                        if (newEmail.isEmpty()) {
                            newEmail = memberToUpdate.getEmail();
                        }
                        
                        System.out.print("Enter New Membership Type (Standard/Premium) (press Enter to keep current): ");
                        String newType = scanner.nextLine().trim();
                        if (newType.isEmpty()) {
                            newType = memberToUpdate.getMembershipType();
                        } else if (!newType.equalsIgnoreCase("Standard") && !newType.equalsIgnoreCase("Premium")) {
                            System.out.println("Error: Invalid membership type! Must be 'Standard' or 'Premium'");
                            continue;
                        }
                        
                        // Update member
                        try {
                            members[memberIndex] = new Member(updateId, newName, newPhone, newEmail, newType);
                            FileHandler.saveMembers(members);
                            System.out.println("Member updated successfully!");
                        } catch (IllegalArgumentException e) {
                            System.out.println("Error: " + e.getMessage());
                        }
                        break;
                        
                    case "0":
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static void addNewMember(String id, String name, String phone, String email, String membershipType) throws IOException {
        // Check for duplicate ID
        for (Member member : members) {
            if (member != null && member.getId().equals(id)) {
                System.out.println("Error: Member with ID " + id + " already exists!");
                return;
            }
        }
        
        // Find first empty slot and check capacity
        boolean added = false;
        for (int i = 0; i < MAX_MEMBERS; i++) {
            if (members[i] == null) {
                try {
                    members[i] = new Member(id, name, phone, email, membershipType);
                    FileHandler.saveMembers(members);
                    System.out.println("Member added successfully!");
                    added = true;
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }
            }
        }
        
        if (!added) {
            System.out.println("Error: Maximum member capacity (" + MAX_MEMBERS + ") reached!");
        }
    }

    private static void viewAllMembers() {
        System.out.println("\n=== All Members ===");
        boolean found = false;
        for (Member member : members) {
            if (member != null) {
                member.display();
                System.out.println("------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No members found.");
        }
    }

    private static void manageMachinesMenu() throws IOException {
        while (true) {
            try {
                System.out.println("\n=== Manage Machines ===");
                System.out.println("1. Add Machine");
                System.out.println("2. View Machines");
                System.out.println("3. Update Machine Status");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");
                
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        System.out.print("Enter Machine ID (0 to go back): ");
                        String id = scanner.nextLine().trim();
                        if (id.equals("0")) continue;
                        
                        if (id.isEmpty()) {
                            System.out.println("Error: Machine ID cannot be empty!");
                            continue;
                        }
                        
                        // Check for duplicate ID
                        boolean isDuplicate = false;
                        for (Machine machine : machines) {
                            if (machine != null && machine.getId().equals(id)) {
                                isDuplicate = true;
                                break;
                            }
                        }
                        
                        if (isDuplicate) {
                            System.out.println("Error: Machine with ID " + id + " already exists!");
                            continue;
                        }
                        
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Error: Name cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Type (Cardio/Strength/Flexibility): ");
                        String type = scanner.nextLine().trim();
                        if (!type.equalsIgnoreCase("Cardio") && !type.equalsIgnoreCase("Strength") && !type.equalsIgnoreCase("Flexibility")) {
                            System.out.println("Error: Invalid machine type! Must be 'Cardio', 'Strength', or 'Flexibility'");
                            continue;
                        }
                        
                        addNewMachine(id, name, type);
                        break;
                        
                    case "2":
                        viewAllMachines();
                        break;
                        
                    case "3":
                        System.out.print("Enter Machine ID to update (0 to go back): ");
                        String machineId = scanner.nextLine().trim();
                        if (machineId.equals("0")) continue;
                        
                        Machine machineToUpdate = null;
                        int machineIndex = -1;
                        for (int i = 0; i < machines.length; i++) {
                            if (machines[i] != null && machines[i].getId().equals(machineId)) {
                                machineToUpdate = machines[i];
                                machineIndex = i;
                                break;
                            }
                        }
                        
                        if (machineToUpdate == null) {
                            System.out.println("Error: Machine with ID " + machineId + " not found!");
                            continue;
                        }
                        
                        System.out.println("\nCurrent Machine Details:");
                        machineToUpdate.display();
                        
                        System.out.print("\nEnter New Status (Working/Under Maintenance): ");
                        String newStatus = scanner.nextLine().trim();
                        if (!newStatus.equalsIgnoreCase("Working") && !newStatus.equalsIgnoreCase("Under Maintenance")) {
                            System.out.println("Error: Invalid status! Must be 'Working' or 'Under Maintenance'");
                            continue;
                        }
                        
                        machines[machineIndex].setStatus(newStatus);
                        FileHandler.saveMachines(machines);
                        System.out.println("Machine status updated successfully!");
                        break;
                        
                    case "0":
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static void addNewMachine(String id, String name, String type) throws IOException {
        // Check for duplicate ID
        for (Machine machine : machines) {
            if (machine != null && machine.getId().equals(id)) {
                System.out.println("Error: Machine with ID " + id + " already exists!");
                return;
            }
        }
        
        // Find first empty slot and check capacity
        boolean added = false;
        for (int i = 0; i < machines.length; i++) {
            if (machines[i] == null) {
                machines[i] = new Machine(id, name, type);
                FileHandler.saveMachines(machines);
                System.out.println("Machine added successfully!");
                added = true;
                break;
            }
        }
        
        if (!added) {
            System.out.println("Error: Maximum machine capacity (" + MAX_MACHINES + ") reached!");
        }
    }

    private static void viewAllMachines() {
        System.out.println("\n=== All Machines ===");
        boolean found = false;
        for (Machine machine : machines) {
            if (machine != null) {
                machine.display();
                System.out.println("------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No machines found.");
        }
    }

    private static void manageTrainersMenu() throws IOException {
        while (true) {
            try {
                System.out.println("\n=== Manage Trainers ===");
                System.out.println("1. Add Trainer");
                System.out.println("2. View Trainers");
                System.out.println("3. Update Trainer");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");
                
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        System.out.print("Enter Trainer ID (0 to go back): ");
                        String id = scanner.nextLine().trim();
                        if (id.equals("0")) continue;
                        
                        if (id.isEmpty()) {
                            System.out.println("Error: Trainer ID cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Name: ");
                        String name = scanner.nextLine().trim();
                        if (name.isEmpty()) {
                            System.out.println("Error: Name cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Phone: ");
                        String phone = scanner.nextLine().trim();
                        if (phone.isEmpty()) {
                            System.out.println("Error: Phone cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine().trim();
                        if (email.isEmpty()) {
                            System.out.println("Error: Email cannot be empty!");
                            continue;
                        }
                        
                        System.out.print("Enter Hourly Rate: $");
                        double hourlyRate;
                        try {
                            hourlyRate = Double.parseDouble(scanner.nextLine().trim());
                            if (hourlyRate <= 0) {
                                System.out.println("Error: Hourly rate must be positive!");
                                continue;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Error: Invalid hourly rate format!");
                            continue;
                        }
                        
                        System.out.print("Enter Specialization (Strength/Cardio/Yoga): ");
                        String specialization = scanner.nextLine().trim();
                        if (!specialization.equalsIgnoreCase("Strength") && 
                            !specialization.equalsIgnoreCase("Cardio") && 
                            !specialization.equalsIgnoreCase("Yoga")) {
                            System.out.println("Error: Invalid specialization! Must be 'Strength', 'Cardio', or 'Yoga'");
                            continue;
                        }
                        
                        addNewTrainer(id, name, phone, email, specialization, hourlyRate);
                        break;
                        
                    case "2":
                        viewAllTrainers();
                        break;
                        
                    case "3":
                        System.out.print("Enter Trainer ID to update (0 to go back): ");
                        String updateId = scanner.nextLine().trim();
                        if (updateId.equals("0")) continue;
                        
                        // Find the trainer
                        Trainer trainerToUpdate = null;
                        for (int i = 0; i < trainers.length; i++) {
                            if (trainers[i] != null && trainers[i].getId().equals(updateId)) {
                                trainerToUpdate = trainers[i];
                                break;
                            }
                        }
                        
                        if (trainerToUpdate == null) {
                            System.out.println("Error: Trainer with ID " + updateId + " not found!");
                            continue;
                        }
                        
                        // Show current details
                        System.out.println("\nCurrent Trainer Details:");
                        trainerToUpdate.display();
                        
                        // Get new details
                        System.out.print("\nEnter New Name (press Enter to keep current): ");
                        String newName = scanner.nextLine().trim();
                        if (newName.isEmpty()) {
                            newName = trainerToUpdate.getName();
                        }
                        
                        System.out.print("Enter New Phone (press Enter to keep current): ");
                        String newPhone = scanner.nextLine().trim();
                        if (newPhone.isEmpty()) {
                            newPhone = trainerToUpdate.getPhone();
                        }
                        
                        System.out.print("Enter New Email (press Enter to keep current): ");
                        String newEmail = scanner.nextLine().trim();
                        if (newEmail.isEmpty()) {
                            newEmail = trainerToUpdate.getEmail();
                        }
                        
                        System.out.print("Enter New Hourly Rate (press Enter to keep current): $");
                        String newHourlyRate = scanner.nextLine().trim();
                        double newHourlyRateValue;
                        if (newHourlyRate.isEmpty()) {
                            newHourlyRateValue = trainerToUpdate.getHourlyRate();
                        } else {
                            try {
                                newHourlyRateValue = Double.parseDouble(newHourlyRate);
                                if (newHourlyRateValue <= 0) {
                                    System.out.println("Error: Hourly rate must be positive!");
                                    continue;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("Error: Invalid hourly rate format!");
                                continue;
                            }
                        }
                        
                        System.out.print("Enter New Specialization (Strength/Cardio/Yoga) (press Enter to keep current): ");
                        String newSpec = scanner.nextLine().trim();
                        if (newSpec.isEmpty()) {
                            newSpec = trainerToUpdate.getSpecialization();
                        } else if (!newSpec.equalsIgnoreCase("Strength") && 
                                 !newSpec.equalsIgnoreCase("Cardio") && 
                                 !newSpec.equalsIgnoreCase("Yoga")) {
                            System.out.println("Error: Invalid specialization! Must be 'Strength', 'Cardio', or 'Yoga'");
                            continue;
                        }
                        
                        // Update trainer
                        updateTrainer(updateId, newName, newPhone, newEmail, newSpec, newHourlyRateValue);
                        break;
                        
                    case "0":
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static void addNewTrainer(String id, String name, String phone, String email, String specialization, double hourlyRate) throws IOException {
        // Check for duplicate ID
        for (Trainer trainer : trainers) {
            if (trainer != null && trainer.getId().equals(id)) {
                System.out.println("Error: Trainer with ID " + id + " already exists!");
                return;
            }
        }
        
        // Find first empty slot and check capacity
        boolean added = false;
        for (int i = 0; i < MAX_TRAINERS; i++) {
            if (trainers[i] == null) {
                try {
                    trainers[i] = new Trainer(id, name, phone, email, specialization, hourlyRate);
                    FileHandler.saveTrainers(trainers);
                    System.out.println("Trainer added successfully!");
                    added = true;
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                    return;
                }
            }
        }
        
        if (!added) {
            System.out.println("Error: Maximum trainer capacity (" + MAX_TRAINERS + ") reached!");
        }
    }

    private static void viewAllTrainers() {
        System.out.println("\n=== All Trainers ===");
        boolean found = false;
        for (Trainer trainer : trainers) {
            if (trainer != null) {
                trainer.display();
                System.out.println("------------------------");
                found = true;
            }
        }
        if (!found) {
            System.out.println("No trainers found.");
        }
    }

    private static void updateTrainer(String id, String name, String phone, String email, String specialization, double hourlyRate) throws IOException {
        for (int i = 0; i < trainers.length; i++) {
            if (trainers[i] != null && trainers[i].getId().equals(id)) {
                try {
                    trainers[i] = new Trainer(id, name, phone, email, specialization, hourlyRate);
                    FileHandler.saveTrainers(trainers);
                    System.out.println("Trainer updated successfully!");
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                return;
            }
        }
        
        System.out.println("Error: Trainer with ID " + id + " not found!");
    }

    private static void managePaymentsMenu() throws IOException {
        while (true) {
            try {
                System.out.println("\n=== Manage Payments ===");
                System.out.println("1. Process New Payment");
                System.out.println("2. View Payment History");
                System.out.println("3. Update Payment Status");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");
                
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        processNewPayment();
                        break;
                        
                    case "2":
                        viewPaymentHistory();
                        break;
                        
                    case "3":
                        updatePaymentStatus();
                        break;
                        
                    case "0":
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static void processNewPayment() throws IOException {
        System.out.print("Enter Member ID (0 to go back): ");
        String memberId = scanner.nextLine().trim();
        if (memberId.equals("0")) return;

        // Verify member exists
        boolean memberFound = false;
        for (Member member : members) {
            if (member != null && member.getId().equals(memberId)) {
                memberFound = true;
                break;
            }
        }

        if (!memberFound) {
            System.out.println("Error: Member with ID " + memberId + " not found!");
            return;
        }

        System.out.print("Enter Payment Amount: $");
        double amount;
        try {
            amount = Double.parseDouble(scanner.nextLine().trim());
            if (amount <= 0) {
                System.out.println("Error: Payment amount must be positive!");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Error: Invalid amount format!");
            return;
        }

        System.out.println("\nPayment Type:");
        System.out.println("1. Membership Fee");
        System.out.println("2. Training Session");
        System.out.println("3. Other");
        System.out.print("Enter choice: ");
        
        String typeChoice = scanner.nextLine().trim();
        String paymentType;
        switch (typeChoice) {
            case "1":
                paymentType = "Membership Fee";
                break;
            case "2":
                paymentType = "Training Session";
                break;
            case "3":
                System.out.print("Enter payment type: ");
                paymentType = scanner.nextLine().trim();
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        // Generate payment ID: P + timestamp
        String paymentId = "P" + System.currentTimeMillis();
        Payment payment = new Payment(paymentId, amount, paymentType);
        
        System.out.println("\nPayment Details:");
        payment.display();
        
        System.out.print("\nConfirm payment (yes/no)? ");
        String confirm = scanner.nextLine().trim().toLowerCase();
        
        if (confirm.equals("yes")) {
            payment.processPayment();
            FileHandler.savePayment(payment);
            System.out.println("Payment processed successfully!");
        } else {
            System.out.println("Payment cancelled.");
        }
    }

    private static void viewPaymentHistory() throws IOException {
        List<Payment> payments = FileHandler.loadPayments();
        
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
            return;
        }

        System.out.println("\n=== Payment History ===");
        for (Payment payment : payments) {
            payment.display();
            System.out.println("------------------------");
        }
    }

    private static void updatePaymentStatus() throws IOException {
        System.out.print("Enter Payment ID (0 to go back): ");
        String paymentId = scanner.nextLine().trim();
        if (paymentId.equals("0")) return;

        List<Payment> payments = FileHandler.loadPayments();
        Payment paymentToUpdate = null;
        int paymentIndex = -1;

        for (int i = 0; i < payments.size(); i++) {
            if (payments.get(i).getId().equals(paymentId)) {
                paymentToUpdate = payments.get(i);
                paymentIndex = i;
                break;
            }
        }

        if (paymentToUpdate == null) {
            System.out.println("Error: Payment with ID " + paymentId + " not found!");
            return;
        }

        System.out.println("\nCurrent Payment Details:");
        paymentToUpdate.display();

        System.out.println("\nSelect new status:");
        System.out.println("1. Pending");
        System.out.println("2. Completed");
        System.out.println("3. Cancelled");
        System.out.print("Enter choice: ");

        String statusChoice = scanner.nextLine().trim();
        String newStatus;
        switch (statusChoice) {
            case "1":
                newStatus = "Pending";
                break;
            case "2":
                newStatus = "Completed";
                break;
            case "3":
                newStatus = "Cancelled";
                break;
            default:
                System.out.println("Invalid choice!");
                return;
        }

        payments.get(paymentIndex).setStatus(newStatus);
        FileHandler.savePayments(payments);
        System.out.println("Payment status updated successfully!");
    }

    private static void viewReportsMenu() throws IOException {
        while (true) {
            try {
                System.out.println("\n=== View Reports ===");
                System.out.println("1. Member Statistics");
                System.out.println("2. Machine Status Report");
                System.out.println("3. Payment Report");
                System.out.println("0. Back to Main Menu");
                System.out.print("\nEnter your choice: ");
                
                String choice = scanner.nextLine().trim();
                switch (choice) {
                    case "1":
                        System.out.println("\n=== Member Statistics ===");
                        int totalMembers = 0;
                        int premiumMembers = 0;
                        int standardMembers = 0;
                        
                        for (Member member : members) {
                            if (member != null) {
                                totalMembers++;
                                if (member.getMembershipType().equalsIgnoreCase("Premium")) {
                                    premiumMembers++;
                                } else {
                                    standardMembers++;
                                }
                            }
                        }
                        
                        System.out.println("Total Members: " + totalMembers);
                        System.out.println("Premium Members: " + premiumMembers);
                        System.out.println("Standard Members: " + standardMembers);
                        break;
                        
                    case "2":
                        System.out.println("\n=== Machine Status Report ===");
                        int totalMachines = 0;
                        int workingMachines = 0;
                        int maintenanceMachines = 0;
                        
                        for (Machine machine : machines) {
                            if (machine != null) {
                                totalMachines++;
                                if (machine.getStatus().equalsIgnoreCase("Working")) {
                                    workingMachines++;
                                } else {
                                    maintenanceMachines++;
                                }
                            }
                        }
                        
                        System.out.println("Total Machines: " + totalMachines);
                        System.out.println("Working Machines: " + workingMachines);
                        System.out.println("Machines Under Maintenance: " + maintenanceMachines);
                        break;
                        
                    case "3":
                        System.out.println("\n=== Payment Report ===");
                        int totalPayments = 0;
                        double totalAmount = 0;
                        
                        for (Payment payment : payments) {
                            if (payment != null) {
                                totalPayments++;
                                totalAmount += payment.getAmount();
                            }
                        }
                        
                        System.out.println("Total Payments: " + totalPayments);
                        System.out.println("Total Amount: $" + totalAmount);
                        break;
                        
                    case "0":
                        return;
                        
                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            } catch (Exception e) {
                System.out.println("An error occurred: " + e.getMessage());
                scanner.nextLine(); // Clear the input buffer
            }
        }
    }

    private static void runCommandLineMode(String[] args) throws IOException {
        String command = args[0].toLowerCase();
        switch (command) {
            case "login":
                if (args.length != 3) {
                    System.out.println("Usage: java -cp bin com.gym.controller.GymManagementSystem login <username> <password>");
                    break;
                }
                login(args[1], args[2]);
                break;

            case "change":
                if (args.length != 3) {
                    System.out.println("Usage: java -cp bin com.gym.controller.GymManagementSystem change <newUsername> <newPassword>");
                    break;
                }
                if (args[1].isEmpty() || args[2].isEmpty()) {
                    System.out.println("Error: Username and password cannot be empty!");
                    break;
                }
                changeLoginCredentials(args[1], args[2]);
                break;

            case "addmember":
                if (args.length != 6) {
                    System.out.println("Usage: java -cp bin com.gym.controller.GymManagementSystem addmember <id> <name> <phone> <email> <membershipType>");
                    break;
                }
                if (args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty() || args[4].isEmpty() || args[5].isEmpty()) {
                    System.out.println("Error: All fields are required!");
                    break;
                }
                if (!args[5].equalsIgnoreCase("Standard") && !args[5].equalsIgnoreCase("Premium")) {
                    System.out.println("Error: Invalid membership type! Must be 'Standard' or 'Premium'");
                    break;
                }
                addNewMember(args[1], args[2], args[3], args[4], args[5]);
                break;

            case "updatemember":
                if (args.length != 6) {
                    System.out.println("Usage: java -cp bin com.gym.controller.GymManagementSystem updatemember <id> <name> <phone> <email> <membershipType>");
                    break;
                }
                updateMember(args[1], args[2], args[3], args[4], args[5]);
                break;

            case "viewmembers":
                viewAllMembers();
                break;

            case "addmachine":
                if (args.length != 4) {
                    System.out.println("Usage: java -cp bin com.gym.controller.GymManagementSystem addmachine <id> <name> <type>");
                    break;
                }
                if (args[1].isEmpty() || args[2].isEmpty() || args[3].isEmpty()) {
                    System.out.println("Error: All fields are required!");
                    break;
                }
                if (!args[3].equalsIgnoreCase("Cardio") && !args[3].equalsIgnoreCase("Strength") && !args[3].equalsIgnoreCase("Flexibility")) {
                    System.out.println("Error: Invalid machine type! Must be 'Cardio', 'Strength', or 'Flexibility'");
                    break;
                }
                
                // Check for duplicate ID
                boolean isDuplicate = false;
                for (Machine machine : machines) {
                    if (machine != null && machine.getId().equals(args[1])) {
                        isDuplicate = true;
                        break;
                    }
                }
                
                if (isDuplicate) {
                    System.out.println("Error: Machine with ID " + args[1] + " already exists!");
                    break;
                }
                
                // Find first empty slot
                boolean added = false;
                for (int i = 0; i < machines.length; i++) {
                    if (machines[i] == null) {
                        machines[i] = new Machine(args[1], args[2], args[3]);
                        FileHandler.saveMachines(machines);
                        System.out.println("Machine added successfully!");
                        added = true;
                        break;
                    }
                }
                
                if (!added) {
                    System.out.println("Error: Maximum machine capacity reached!");
                }
                break;

            case "updatemachine":
                if (args.length != 3) {
                    System.out.println("Usage: java -cp bin com.gym.controller.GymManagementSystem updatemachine <id> <status>");
                    break;
                }
                
                String machineId = args[1];
                String newStatus = args[2];
                
                if (machineId.isEmpty() || newStatus.isEmpty()) {
                    System.out.println("Error: All fields are required!");
                    break;
                }
                
                if (!newStatus.equalsIgnoreCase("Working") && !newStatus.equalsIgnoreCase("Under Maintenance")) {
                    System.out.println("Error: Invalid status! Must be 'Working' or 'Under Maintenance'");
                    break;
                }
                
                // Find the machine
                Machine machineToUpdate = null;
                int machineIndex = -1;
                for (int i = 0; i < machines.length; i++) {
                    if (machines[i] != null && machines[i].getId().equals(machineId)) {
                        machineToUpdate = machines[i];
                        machineIndex = i;
                        break;
                    }
                }
                
                if (machineToUpdate == null) {
                    System.out.println("Error: Machine with ID " + machineId + " not found!");
                    break;
                }
                
                machines[machineIndex].setStatus(newStatus);
                FileHandler.saveMachines(machines);
                System.out.println("Machine status updated successfully!");
                break;

            case "viewmachines":
                viewAllMachines();
                break;

            case "viewreport":
                if (args.length != 2 || (!args[1].equalsIgnoreCase("members") && !args[1].equalsIgnoreCase("machines") && !args[1].equalsIgnoreCase("payments"))) {
                    System.out.println("Usage: java -cp bin com.gym.controller.GymManagementSystem viewreport <type>");
                    System.out.println("Type must be either 'members', 'machines', or 'payments'");
                    System.exit(1);
                }
                
                if (args[1].equalsIgnoreCase("members")) {
                    System.out.println("\n=== Member Statistics ===");
                    int totalMembers = 0;
                    int premiumMembers = 0;
                    int standardMembers = 0;
                    
                    for (Member member : members) {
                        if (member != null) {
                            totalMembers++;
                            if (member.getMembershipType().equalsIgnoreCase("Premium")) {
                                premiumMembers++;
                            } else {
                                standardMembers++;
                            }
                        }
                    }
                    
                    System.out.println("Total Members: " + totalMembers);
                    System.out.println("Premium Members: " + premiumMembers);
                    System.out.println("Standard Members: " + standardMembers);
                } else if (args[1].equalsIgnoreCase("machines")) {
                    System.out.println("\n=== Machine Status Report ===");
                    int totalMachines = 0;
                    int workingMachines = 0;
                    int maintenanceMachines = 0;
                    
                    for (Machine machine : machines) {
                        if (machine != null) {
                            totalMachines++;
                            if (machine.getStatus().equalsIgnoreCase("Working")) {
                                workingMachines++;
                            } else {
                                maintenanceMachines++;
                            }
                        }
                    }
                    
                    System.out.println("Total Machines: " + totalMachines);
                    System.out.println("Working Machines: " + workingMachines);
                    System.out.println("Machines Under Maintenance: " + maintenanceMachines);
                } else {
                    System.out.println("\n=== Payment Report ===");
                    int totalPayments = 0;
                    double totalAmount = 0;
                    
                    for (Payment payment : payments) {
                        if (payment != null) {
                            totalPayments++;
                            totalAmount += payment.getAmount();
                        }
                    }
                    
                    System.out.println("Total Payments: " + totalPayments);
                    System.out.println("Total Amount: $" + totalAmount);
                }
                break;

            default:
                System.out.println("Usage:");
                System.out.println("1. Login: java -cp bin com.gym.controller.GymManagementSystem login <username> <password>");
                System.out.println("2. Change Credentials: java -cp bin com.gym.controller.GymManagementSystem change <newUsername> <newPassword>");
                System.out.println("3. Add Member: java -cp bin com.gym.controller.GymManagementSystem addmember <id> <name> <phone> <email> <membershipType>");
                System.out.println("4. Update Member: java -cp bin com.gym.controller.GymManagementSystem updatemember <id> <name> <phone> <email> <membershipType>");
                System.out.println("5. View Members: java -cp bin com.gym.controller.GymManagementSystem viewmembers");
                System.out.println("6. Add Machine: java -cp bin com.gym.controller.GymManagementSystem addmachine <id> <name> <type>");
                System.out.println("7. Update Machine: java -cp bin com.gym.controller.GymManagementSystem updatemachine <id> <status>");
                System.out.println("8. View Machines: java -cp bin com.gym.controller.GymManagementSystem viewmachines");
                System.out.println("9. View Report: java -cp bin com.gym.controller.GymManagementSystem viewreport <type>");
                System.exit(1);
        }
    }

    private static void updateMember(String id, String name, String phone, String email, String membershipType) throws IOException {
        // Find the member
        Member memberToUpdate = null;
        int memberIndex = -1;
        for (int i = 0; i < members.length; i++) {
            if (members[i] != null && members[i].getId().equals(id)) {
                memberToUpdate = members[i];
                memberIndex = i;
                break;
            }
        }
        
        if (memberToUpdate == null) {
            System.out.println("Error: Member with ID " + id + " not found!");
            return;
        }
        
        String newName = name.isEmpty() ? memberToUpdate.getName() : name;
        String newPhone = phone.isEmpty() ? memberToUpdate.getPhone() : phone;
        String newEmail = email.isEmpty() ? memberToUpdate.getEmail() : email;
        String newType = membershipType.isEmpty() ? memberToUpdate.getMembershipType() : membershipType;
        
        if (!newType.equalsIgnoreCase("Standard") && !newType.equalsIgnoreCase("Premium")) {
            System.out.println("Error: Invalid membership type! Must be 'Standard' or 'Premium'");
            return;
        }
        
        try {
            members[memberIndex] = new Member(id, newName, newPhone, newEmail, newType);
            FileHandler.saveMembers(members);
            System.out.println("Member updated successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
