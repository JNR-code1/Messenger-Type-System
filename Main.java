import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    static HashMap<String, User> users = new HashMap<>();
    static ArrayList<Conversation> conversations = new ArrayList<>();
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        // Sample users to start with
        users.put("alice", new User("alice"));
        users.put("bob", new User("bob"));
        users.put("carol", new User("carol"));

        User current = login();
        boolean running = true;

        while (running) {
            System.out.println("\nLogged in as: " + current.getUsername());
            System.out.println("1. Send message");
            System.out.println("2. View chat");
            System.out.println("3. Switch user");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    sendMessage(current);
                    break;
                case "2":
                    viewChat(current);
                    break;
                case "3":
                    current = login();
                    break;
                case "4":
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
        System.out.println("Goodbye!");
    }

    static User login() {
        while (true) {
            System.out.println("Available users: " + users.keySet());
            System.out.print("Login as: ");
            String name = scanner.nextLine().trim().toLowerCase();
            if (users.containsKey(name)) {
                return users.get(name);
            }
            System.out.println("User not found.");
        }
    }

    // Finds the conversation between two users, or creates it if none exists yet
    static Conversation findOrCreateConversation(String a, String b) {
        for (Conversation c : conversations) {
            if (c.isBetween(a, b)) {
                return c;
            }
        }
        Conversation created = new Conversation(a, b);
        conversations.add(created);
        return created;
    }

    static void sendMessage(User current) {
        System.out.print("Send to: ");
        String to = scanner.nextLine().trim().toLowerCase();

        if (!users.containsKey(to) || to.equals(current.getUsername())) {
            System.out.println("Invalid recipient.");
            return;
        }

        System.out.print("Message: ");
        String text = scanner.nextLine();

        Conversation convo = findOrCreateConversation(current.getUsername(), to);
        convo.addMessage(new Message(current.getUsername(), to, text));
        System.out.println("Sent!");
    }

    static void viewChat(User current) {
        System.out.print("View chat with: ");
        String other = scanner.nextLine().trim().toLowerCase();

        if (!users.containsKey(other) || other.equals(current.getUsername())) {
            System.out.println("Invalid user.");
            return;
        }

        System.out.println("\n--- Chat with " + other + " ---");
        findOrCreateConversation(current.getUsername(), other).showHistory();
    }
}