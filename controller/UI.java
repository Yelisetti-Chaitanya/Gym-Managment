package controller;

import java.util.Scanner;

public class UI {
    private static final Scanner scanner = new Scanner(System.in);

    public static void displayWelcome() {
        System.out.println("____________________________________________________________________________");
        System.out.println("                            WELCOME TO SYSTEM");
        System.out.println("____________________________________________________________________________");
        System.out.println("****************************************************************************");
        System.out.println("*********************  _______  *  _____  *****                           **");
        System.out.println("********************* |  _____| * |  ___| *****       **           **     **");
        System.out.println("********************* | |___    * | |     *****      ***___________***    **");
        System.out.println("********************* |  ___|   * | |     *****     ****-----------****   **");
        System.out.println("********************* | |       * | |___  *****      *** Stone Man ***    **");
        System.out.println("********************* |_|       * |_____| *****       **    GYM    **     **");
        System.out.println("*********************           *         *****                           **");
        System.out.println("                    ****  FITNESS CLUB  ************************************");
        System.out.println("     *     *   WHEN LIFE GIVES YOU PAIN.....GO TO THE GYM   *    *    *    *");
    }

    public static String displayLoginMenu() {
        System.out.println("\nDefault Username: admin");
        System.out.println("Default Password: anything\n");
        System.out.println("1. Login");
        System.out.println("2. Change Login Credentials");
        System.out.println("0. Quit Program");
        System.out.print("\nEnter Your Choice: ");
        
        String choice = scanner.nextLine();
        
        while (!choice.matches("[012]")) {
            System.out.print("Invalid input. Please enter 0, 1, or 2: ");
            choice = scanner.nextLine();
        }
        
        return choice;
    }

    public static String[] getLoginCredentials() {
        String username;
        while (true) {
            System.out.print("\nEnter Username (0 to go back): ");
            username = scanner.nextLine().trim();
            if (!username.isEmpty()) break;
            System.out.println("Username cannot be empty. Please try again.");
        }
        
        if (username.equals("0")) {
            return new String[]{"0", "0"};
        }
        
        String password;
        while (true) {
            System.out.print("Enter Password: ");
            password = scanner.nextLine().trim();
            if (!password.isEmpty()) break;
            System.out.println("Password cannot be empty. Please try again.");
        }
        
        return new String[]{username, password};
    }

    public static String[] getNewCredentials() {
        String username;
        while (true) {
            System.out.print("\nEnter New Username: ");
            username = scanner.nextLine().trim();
            if (!username.isEmpty()) break;
            System.out.println("Username cannot be empty. Please try again.");
        }
        
        String password;
        while (true) {
            System.out.print("Enter New Password: ");
            password = scanner.nextLine().trim();
            if (!password.isEmpty()) break;
            System.out.println("Password cannot be empty. Please try again.");
        }
        
        return new String[]{username, password};
    }
}
