import java.util.ArrayList;

public class Conversation {
    private String userA;
    private String userB;
    private ArrayList<Message> messages = new ArrayList<>();

    public Conversation(String userA, String userB) {
        this.userA = userA;
        this.userB = userB;
    }

    // True if this conversation is between the two given users (in any order)
    public boolean isBetween(String x, String y) {
        return (userA.equals(x) && userB.equals(y))
            || (userA.equals(y) && userB.equals(x));
    }

    public void addMessage(Message message) {
        messages.add(message);
    }

    public void showHistory() {
        if (messages.isEmpty()) {
            System.out.println("No messages yet.");
            return;
        }
        for (Message m : messages) {
            System.out.println(m);
        }
    }
}