package controller;

import model.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class FileHandler {
    private static final String MEMBERS_FILE = "Members.txt";
    private static final String TRAINERS_FILE = "Trainers.txt";
    private static final String MACHINES_FILE = "Machines.txt";
    private static final String ADMIN_FILE = "AdminInfo.txt";
    private static final String PAYMENTS_FILE = "Payments.txt";
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE_TIME;

    public static void initializeFiles() throws IOException {
        createFileIfNotExists(MEMBERS_FILE);
        createFileIfNotExists(TRAINERS_FILE);
        createFileIfNotExists(MACHINES_FILE);
        createFileIfNotExists(ADMIN_FILE);
        createFileIfNotExists(PAYMENTS_FILE);
    }

    private static void createFileIfNotExists(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            file.createNewFile();
            System.out.println("\n\t\tNo Previous File Exists. So a New '" + filename + "' File is Created\n");
        }
    }

    public static Admin loadAdmin() throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(ADMIN_FILE))) {
            String line = reader.readLine();
            if (line != null) {
                String[] parts = line.split("#");
                if (parts.length >= 2) {
                    return new Admin(parts[0], parts[1]);
                }
            }
            return new Admin("admin", "anything");
        }
    }

    public static void saveAdmin(Admin admin) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(("C:/Users/manur/Downloads/ECLIPSE-JAVA/workspace/Gymmanagement/ADMIN_FILE")))) {
            writer.println(admin.getUsername() + "#" + admin.getPassword());
        }
    }

    public static Member[] loadMembers() throws IOException {
        Member[] members = new Member[200];
        
        File file = new File(MEMBERS_FILE);
        if (!file.exists() || file.length() == 0) {
            return members;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split("#");
                    if (parts.length >= 5) {
                        // Find first empty slot
                        for (int i = 0; i < members.length; i++) {
                            if (members[i] == null) {
                                members[i] = new Member(
                                    parts[0].trim(),  // id
                                    parts[1].trim(),  // name
                                    parts[2].trim(),  // phone
                                    parts[3].trim(),  // email
                                    parts[4].trim()   // membershipType
                                );
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Warning: Skipping invalid member data: " + line);
                }
            }
        }
        return members;
    }

    public static void saveMembers(Member[] members) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MEMBERS_FILE))) {
            for (Member member : members) {
                if (member != null) {
                    writer.println(String.format("%s#%s#%s#%s#%s",
                        member.getId().trim(),
                        member.getName().trim(),
                        member.getPhone().trim(),
                        member.getEmail().trim(),
                        member.getMembershipType().trim()
                    ));
                }
            }
        }
    }

    public static Machine[] loadMachines() throws IOException {
        Machine[] machines = new Machine[100];
        
        File file = new File(MACHINES_FILE);
        if (!file.exists() || file.length() == 0) {
            return machines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split("#");
                    if (parts.length >= 4) {
                        // Find first empty slot
                        for (int i = 0; i < machines.length; i++) {
                            if (machines[i] == null) {
                                Machine machine = new Machine(
                                    parts[0].trim(),  // id
                                    parts[1].trim(),  // name
                                    parts[2].trim()   // type
                                );
                                machine.setStatus(parts[3].trim());  // status
                                machines[i] = machine;
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Warning: Skipping invalid machine data: " + line);
                }
            }
        }
        return machines;
    }

    public static void saveMachines(Machine[] machines) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(MACHINES_FILE))) {
            for (Machine machine : machines) {
                if (machine != null) {
                    writer.println(String.format("%s#%s#%s#%s",
                        machine.getId().trim(),
                        machine.getName().trim(),
                        machine.getType().trim(),
                        machine.getStatus().trim()
                    ));
                }
            }
        }
    }

    public static List<Payment> loadPayments() throws IOException {
        List<Payment> payments = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(PAYMENTS_FILE))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("#");
                if (parts.length >= 5) {
                    Payment payment = new Payment(parts[0], Double.parseDouble(parts[1]), parts[2]);
                    payment.setDateTime(LocalDateTime.parse(parts[3], DATE_FORMATTER));
                    payment.setStatus(parts[4]);
                    payments.add(payment);
                }
            }
        }
        return payments;
    }

    public static void savePayment(Payment payment) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PAYMENTS_FILE, true))) {
            writer.println(String.format("%s#%f#%s#%s#%s",
                payment.getId(),
                payment.getAmount(),
                payment.getType(),
                payment.getDateTime().format(DATE_FORMATTER),
                payment.getStatus()));
        }
    }

    public static void savePayments(List<Payment> payments) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(PAYMENTS_FILE))) {
            for (Payment payment : payments) {
                writer.println(String.format("%s#%f#%s#%s#%s",
                    payment.getId(),
                    payment.getAmount(),
                    payment.getType(),
                    payment.getDateTime().format(DATE_FORMATTER),
                    payment.getStatus()));
            }
        }
    }

    public static Trainer[] loadTrainers() throws IOException {
        Trainer[] trainers = new Trainer[50];
        
        File file = new File(TRAINERS_FILE);
        if (!file.exists() || file.length() == 0) {
            return trainers;	
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                try {
                    String[] parts = line.split("#");
                    if (parts.length >= 6) {
                        // Find first empty slot
                        for (int i = 0; i < trainers.length; i++) {
                            if (trainers[i] == null) {
                                trainers[i] = new Trainer(
                                    parts[0].trim(),  // id
                                    parts[1].trim(),  // name
                                    parts[2].trim(),  // phone
                                    parts[3].trim(),  // email
                                    parts[4].trim(),  // specialization
                                    Double.parseDouble(parts[5].trim())  // hourlyRate
                                );
                                break;
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Warning: Skipping invalid trainer data: " + line);
                }
            }
        }
        return trainers;
    }

    public static void saveTrainers(Trainer[] trainers) throws IOException {
        try (PrintWriter writer = new PrintWriter(new FileWriter(TRAINERS_FILE))) {
            for (Trainer trainer : trainers) {
                if (trainer != null) {
                    writer.println(String.format("%s#%s#%s#%s#%s#%.2f",
                        trainer.getId().trim(),
                        trainer.getName().trim(),
                        trainer.getPhone().trim(),
                        trainer.getEmail().trim(),
                        trainer.getSpecialization().trim(),
                        trainer.getHourlyRate()
                    ));
                }
            }
        }
    }
}
