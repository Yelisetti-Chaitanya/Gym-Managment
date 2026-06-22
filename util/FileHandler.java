package util;

import model.*;
import java.io.*;
import java.util.*;

public class FileHandler {
    private static final String DATA_DIR = "data";
    private static final String ADMIN_FILE = DATA_DIR + "/admin.dat";
    private static final String MEMBERS_FILE = DATA_DIR + "/members.dat";
    private static final String MACHINES_FILE = DATA_DIR + "/machines.dat";
    private static final String TRAINERS_FILE = DATA_DIR + "/trainers.dat";
    private static final String PAYMENTS_FILE = DATA_DIR + "/payments.dat";

    public static void initializeFiles() throws IOException {
        // Create data directory if it doesn't exist
        File dataDir = new File(DATA_DIR);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        // Create files if they don't exist
        createFileIfNotExists(ADMIN_FILE);
        createFileIfNotExists(MEMBERS_FILE);
        createFileIfNotExists(MACHINES_FILE);
        createFileIfNotExists(TRAINERS_FILE);
        createFileIfNotExists(PAYMENTS_FILE);
    }

    private static void createFileIfNotExists(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            file.createNewFile();
        }
    }

    public static Admin loadAdmin() throws IOException {
        File file = new File("C:/Users/manur/Downloads/ECLIPSE-JAVA/workspace/Gymmanagement/ADMIN_FILE");
        if (file.length() == 0) {
            // Return default admin if file is empty
            return new Admin("admin", "anything");
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (Admin) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException("Error loading admin data: " + e.getMessage());
        }
    }

    public static void saveAdmin(Admin admin) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("C:/Users/manur/Downloads/ECLIPSE-JAVA/workspace/Gymmanagement/ADMIN_FILE"))) {
            oos.writeObject(admin);
        }
    }

    public static Member[] loadMembers() throws IOException {
        Member[] members = new Member[200];
        File file = new File(MEMBERS_FILE);
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                members = (Member[]) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Error loading member data: " + e.getMessage());
            }
        }
        return members;
    }

    public static void saveMembers(Member[] members) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MEMBERS_FILE))) {
            oos.writeObject(members);
        }
    }

    public static Machine[] loadMachines() throws IOException {
        Machine[] machines = new Machine[100];
        File file = new File(MACHINES_FILE);
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                machines = (Machine[]) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Error loading machine data: " + e.getMessage());
            }
        }
        return machines;
    }

    public static void saveMachines(Machine[] machines) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MACHINES_FILE))) {
            oos.writeObject(machines);
        }
    }

    public static Trainer[] loadTrainers() throws IOException {
        Trainer[] trainers = new Trainer[50];
        File file = new File(TRAINERS_FILE);
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                trainers = (Trainer[]) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Error loading trainer data: " + e.getMessage());
            }
        }
        return trainers;
    }

    public static void saveTrainers(Trainer[] trainers) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(TRAINERS_FILE))) {
            oos.writeObject(trainers);
        }
    }

    @SuppressWarnings("unchecked")
    public static List<Payment> loadPayments() throws IOException {
        List<Payment> payments = new ArrayList<>();
        File file = new File(PAYMENTS_FILE);
        if (file.length() > 0) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                payments = (List<Payment>) ois.readObject();
            } catch (ClassNotFoundException e) {
                throw new IOException("Error loading payment data: " + e.getMessage());
            }
        }
        return payments;
    }

    public static void savePayment(Payment payment) throws IOException {
        List<Payment> payments = loadPayments();
        payments.add(payment);
        savePayments(payments);
    }

    public static void savePayments(List<Payment> payments) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(PAYMENTS_FILE))) {
            oos.writeObject(payments);
        }
    }
}
