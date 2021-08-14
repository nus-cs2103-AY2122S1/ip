import java.util.Scanner;

class Node {
    String text;
    Node next;

    public Node(String text) {
        this.text = text;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node n) {
        this.next = n;
    }

    @Override
    public String toString() {
        return text;
    }
}

public class Duke {
    private static int taskCount = 0;
    private static Node dummyHead = new Node("");
    private static Node lastNode = dummyHead;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String logo = "┏━━┓╋╋╋┏━━┓╋╋╋┏┓\n" +
                      "┃┏┓┃╋╋╋┃┏┓┃╋╋┏┛┗┓\n" +
                      "┃┗┛┗┳━┳┫┗┛┗┳━┻┓┏┛\n" +
                      "┃┏━┓┃┏╋┫┏━┓┃┏┓┃┃\n" +
                      "┃┗━┛┃┃┃┃┗━┛┃┗┛┃┗┓\n" +
                      "┗━━━┻┛┗┻━━━┻━━┻━┛";

        System.out.println("Hello from\n" + logo);
        lineSeparator();
        System.out.println("I'm BriBot");
        System.out.println("What can I do for you?");
        lineSeparator();
        while (true) {
            String userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                sayGoodBye();
                break;
            } else if (userInput.equals("list")) {
                printList();
            } else {
                addInput(userInput);
            }

        }
    }

    private static void addInput(String userInput) {
        lineSeparator();
        System.out.println("added: " + userInput);
        lineSeparator();
        taskCount++;
        lastNode.setNext(new Node(userInput));
        lastNode = lastNode.getNext();
    }

    private static void printList() {
        Node pointer = dummyHead;
        lineSeparator();
        for (int i = 0; i < taskCount; i++) {
            pointer = pointer.getNext();
            System.out.println((i+1) + ". " + pointer.toString());
        }
        lineSeparator();
    }

    private static void sayGoodBye() {
        lineSeparator();
        System.out.println("Bye. Hope to see you soon!");
        lineSeparator();
    }

    private static void lineSeparator() {
        System.out.println("____________________________________________________________");
    }
}
