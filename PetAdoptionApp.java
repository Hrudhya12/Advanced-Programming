import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PetAdoptionApp {
    private static final Scanner scanner = new Scanner(System.in);

    private static final List<User> users = new ArrayList<>();
    private static final List<Pet> pets = new ArrayList<>();
    private static User loggedIn = null;

    public static void main(String[] args) {
        staffAccount();
        seedPets();

        while (true) {
            if (loggedIn == null) {
                welcomeMenu();
            } else {
                if (loggedIn.isStaff()) {
                    staffDashboard();
                } else {
                    mainMenu();
                }
            }
        }
    }

    private static void staffAccount() {
        users.add(new User("Staff", "0000000000", "staff@petadoption.com",
                "staff", "s123", true));
    }

    private static void seedPets() {
        pets.add(new Dog("Bruno", 3, "Male", "Playful",
                "Labrador", "Brown", "Vaccinated",
                "Fetching balls", 150.0, 20.0, "2025-11-01", "Loves belly rubs"));
        pets.add(new Cat("Simba", 2, "Female", "Shy",
                "Persian", "Golden", "Healthy",
                "Climbing shelves", 120.0, 5.0, "2025-10-15", "Enjoys chasing things"));
        pets.add(new Bird("Chippy", 1, "Male", "Cheerful",
                "Parakeet", "Green", "Healthy",
                "Making noise", 50.0, 0.2, "2025-12-01", "Whistles tunes"));
        pets.add(new Fish("Nemo", 1, "Male", "Calm",
                "Clownfish", "Orange/White", "Healthy",
                "Swimming", 30.0, 0.1, "2025-09-20", "Recognizes feeding time"));
        pets.add(new Rabbit("Snowball", 2, "Female", "Gentle",
                "Angora", "White", "Vaccinated",
                "Nibbling carrots", 80.0, 2.5, "2025-11-10", "Very fluffy"));
        pets.add(new Hamster("Cookie", 1, "Male", "Energetic",
                "Syrian", "Golden Brown", "Healthy",
                "Running in wheel", 40.0, 0.3, "2025-12-05", "Sleeps during the day"));
    }

    private static void welcomeMenu() {
        System.out.println("\n--- Welcome to the Pet Adoption Center ---");
        System.out.println("1. Login");
        System.out.println("2. Create account");
        System.out.println("3. View available pets");
        System.out.println("4. Exit");

        int choice = readInt("Enter your choice: ");
        if (choice == 1) {
            login();
        } else if (choice == 2) {
            register();
        } else if (choice == 3) {
            previewPets();
            System.out.println("\nPlease login or create an account to continue.");
        } else if (choice == 4) {
            System.out.println("Thank you for visiting, See you!");
            System.exit(0);
        }
    }

    private static void register() {
        System.out.println("\n--- Create Account ---");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        String username;
        while (true) {
            System.out.print("Create username: ");
            username = scanner.nextLine();
            if (findUser(username) != null) {
                System.out.println("Username already exists. Try a different one.");
            } else {
                break;
            }
        }

        System.out.print("Create password: ");
        String password = scanner.nextLine();

        users.add(new User(name, phone, email, username, password, false));
        System.out.println("Account created. Please login to continue.");
    }

    private static void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        User u = findUser(username);
        if (u != null && u.checkPassword(password)) {
            loggedIn = u;
            if (u.isStaff()) {
                System.out.println("Login successful. You are logged in as Staff.");
            } else {
                System.out.println("Login successful.");
            }
        } else {
            System.out.println("Invalid credentials.");
        }
    }

    private static User findUser(String username) {
        for (User u : users) {
            if (u.getUsername().equalsIgnoreCase(username)) return u;
        }
        return null;
    }

    private static void previewPets() {
        System.out.println("\n--- Available Pets ---");
        boolean any = false;
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            if (!p.isAdopted()) {
                System.out.println((i + 1) + ". " + p.getListLine());
                any = true;
            }
        }
        if (!any) System.out.println("No pets available right now.");
    }

    private static void staffDashboard() {
        System.out.println("\n--- Staff Dashboard ---");
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            String status;
            if (p.isAdopted()) {
                status = "Adopted by " + p.getAdopterUsername() + " on " + p.getAdoptionDate();
            } else {
                status = "Available";
            }
            System.out.println((i + 1) + ". " + p.name + " (" + p.breed + ", " + p.gender + ") - " + status);
        }

        int choice = readInt("\n1. View pet details\n2. Logout\nEnter your choice: ");
        if (choice == 1) {
            showAvailablePetsWithSubmenu();
        } else if (choice == 2) {
            loggedIn = null;
            System.out.println("Logged out.");
        }
    }

    private static void mainMenu() {
        System.out.println("\n--- Pet Adoption Main Menu ---");
        System.out.println("1. View available pets");
        System.out.println("2. Adopt a pet");
        System.out.println("3. View adopted pets");
        System.out.println("4. Search pet by name");
        System.out.println("5. Logout");

        int choice = readInt("Enter your choice: ");
        if (choice == 1) {
            showAvailablePetsWithSubmenu();
        } else if (choice == 2) {
            adoptFlowMenu();
        } else if (choice == 3) {
            showMyAdoptedPets();
        } else if (choice == 4) {
            searchByNameFlow();
        } else if (choice == 5) {
            loggedIn = null;
            System.out.println("Logged out.");
        }
    }

    private static void showAvailablePetsWithSubmenu() {
        int countShown = listAvailablePets();
        if (countShown == 0) return;

        while (true) {
            System.out.println("\n--- Available Pets Menu ---");
            System.out.println("1. View details of a pet");
            System.out.println("2. Go back");
            System.out.println("3. Main menu");

            int choice = readInt("Enter your choice: ");
            if (choice == 1) {
                int idx = askPetIndex();
                if (idx != -1 && !pets.get(idx).isAdopted()) {
                    System.out.println("\n" + pets.get(idx).getFullDetails());
                    int sub = readInt("\nDo you want to:\n1. Adopt this pet\n2. Go back\n3. Main menu\nEnter choice: ");
                    if (sub == 1) {
                        adoptPet(pets.get(idx), "AVAILABLE_LIST");
                        return;
                    } else if (sub == 2) {
                        continue;
                    } else if (sub == 3) {
                        return;
                    } else {
                        System.out.println("Invalid choice.");
                    }
                } else {
                    System.out.println("Invalid pet number or pet not available.");
                }
            } else if (choice == 2) {
                return;
            } else if (choice == 3) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static int listAvailablePets() {
        System.out.println("\n--- Available Pets ---");
        int shown = 0;
        for (int i = 0; i < pets.size(); i++) {
            Pet p = pets.get(i);
            if (!p.isAdopted()) {
                System.out.println((i + 1) + ". " + p.getListLine());
                shown++;
            }
        }
        if (shown == 0) System.out.println("No pets available right now.");
        return shown;
    }

    private static int askPetIndex() {
        int n = readInt("Enter pet number: ");
        int idx = n - 1;
        if (idx < 0 || idx >= pets.size()) {
            System.out.println("Invalid pet number.");
            return -1;
        }
        return idx;
    }

    private static void adoptFlowMenu() {
        while (true) {
            System.out.println("\n--- Adopt Menu ---");
            System.out.println("1. View pets");
            System.out.println("2. Adopt directly");
            System.out.println("3. Go back");
            System.out.println("4. Main menu");

            int sub = readInt("Enter your choice: ");
            if (sub == 1) {
                listAvailablePets();
                while (true) {
                    System.out.println("\n--- Adopt Menu: Available Pets ---");
                    System.out.println("1. More details of a pet");
                    System.out.println("2. Adopt one");
                    System.out.println("3. Go back");
                    System.out.println("4. Main menu");

                    int choice = readInt("Enter your choice: ");
                    if (choice == 1) {
                        int idx = askPetIndex();
                        if (idx != -1) {
                            System.out.println("\n" + pets.get(idx).getFullDetails());
                        }
                    } else if (choice == 2) {
                        handleAdoptionFromIndexFrom("ADOPT_MENU");
                    } else if (choice == 3) {
                        break;
                    } else if (choice == 4) {
                        return;
                    } else {
                        System.out.println("Invalid choice. Try again.");
                    }
                }
            } else if (sub == 2) {
                handleAdoptionFromIndexFrom("ADOPT_MENU");
            } else if (sub == 3) {
                return;
            } else if (sub == 4) {
                return;
            } else {
                System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void handleAdoptionFromIndexFrom(String prevPage) {
        int countShown = listAvailablePets();
        if (countShown == 0) return;

        while (true) {
            System.out.println("\nChoose a pet to adopt or:");
            System.out.println("0. Go back");
            System.out.println("9. Main menu");
            int idxChoice = readInt("Enter pet number (or 0/9): ");
            if (idxChoice == 0) return;
            if (idxChoice == 9) return;
            int idx = idxChoice - 1;
            if (idx >= 0 && idx < pets.size() && !pets.get(idx).isAdopted()) {
                adoptPet(pets.get(idx), prevPage);
                return;
            } else {
                System.out.println("Invalid pet number or pet not available.");
            }
        }
    }

    private static void adoptPet(Pet p, String prevPage) {
        p.adopt(loggedIn.getUsername());
        System.out.println("You adopted " + p.name + "! Our staff will contact you within 48 hours.");
        System.out.println("For doubts or cancellations, email customer@petadoption.com.");

        while (true) {
            System.out.println("\nWhat would you like to do next?");
            System.out.println("1. Adopt another pet");
            System.out.println("2. Go back");
            System.out.println("3. Main menu");
            int next = readInt("Enter your choice: ");
            if (next == 1) {
                showAvailablePetsWithSubmenu();
                return;
            } else if (next == 2) {
                if ("AVAILABLE_LIST".equals(prevPage)) {
                    showAvailablePetsWithSubmenu();
                } else if ("ADOPT_MENU".equals(prevPage)) {
                    adoptFlowMenu();
                } else if ("STAFF_DASHBOARD".equals(prevPage)) {
                    staffDashboard();
                } else if ("SEARCH_RESULT".equals(prevPage)) {
                    searchByNameFlow();
                } else {
                    return;
                }
                return;
            } else if (next == 3) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void showMyAdoptedPets() {
        System.out.println("\n--- Your Adopted Pets ---");
        int count = 0;
        for (Pet p : pets) {
            if (p.isAdopted() && loggedIn.getUsername().equalsIgnoreCase(p.getAdopterUsername())) {
                System.out.println("- " + p.name + " (Adopted on: " + p.getAdoptionDate() + ")");
                count++;
            }
        }
        if (count == 0) {
            System.out.println("You haven't adopted any pets yet.");
        }

        while (true) {
            System.out.println("\n1. Go back");
            System.out.println("2. Adopt more");
            System.out.println("3. Main menu");
            int sub = readInt("Enter your choice: ");
            if (sub == 1) {
                return;
            } else if (sub == 2) {
                adoptFlowMenu();
                return;
            } else if (sub == 3) {
                return;
            } else {
                System.out.println("Invalid choice.");
            }
        }
    }

    private static void searchByNameFlow() {
        System.out.print("\nEnter pet name to search: ");
        String name = scanner.nextLine();
        Pet found = null;
        for (Pet p : pets) {
            if (p.name.toLowerCase().contains(name.toLowerCase())) {
                found = p;
                break;
            }
        }
        if (found == null) {
            System.out.println("No pet found with that name.");
            return;
        }

        if (!found.isAdopted()) {
            System.out.println("Pet is available: " + found.getListLine());
            int sub = readInt("\n--- Search Result ---\n1. View details\n2. Adopt\n3. Go back\n4. Main menu\nEnter your choice: ");
            if (sub == 1) {
                System.out.println("\n" + found.getFullDetails());
                int after = readInt("\nDo you want to:\n1. Adopt this pet\n2. Go back\n3. Main menu\nEnter choice: ");
                if (after == 1) {
                    adoptPet(found, "SEARCH_RESULT");
                }
            } else if (sub == 2) {
                adoptPet(found, "SEARCH_RESULT");
            }
        } else {
            if (found.getAdopterUsername().equalsIgnoreCase(loggedIn.getUsername())) {
                System.out.println("Hope it's happy at your home. You adopted " + found.name +
                        " on " + found.getAdoptionDate() + ".");
            } else {
                System.out.println(found.name + " is happy in someone else's house.");
            }
            int sub = readInt("1. Go back\n2. View available pets\n3. Main menu\nEnter your choice: ");
            if (sub == 2) {
                showAvailablePetsWithSubmenu();
            }
        }
    }

    private static int readInt(String prompt) {
        while (true) {
            try {
                System.out.print(prompt);
                String s = scanner.nextLine();
                return Integer.parseInt(s);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid choice.");
            }
        }
    }
}