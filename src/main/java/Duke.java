import java.util.Scanner;

class Node {
    private final Task task;
    private Node next;

    public Node(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return this.task;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node n) {
        next = n;
    }

    public void markTask() {
        task.markAsDone();
    }


    @Override
    public String toString() {
        return task.toString();
    }


}

public class Duke {
    private static int taskCount = 0;
    private static Node dummyHead = new Node(new Task(""));
    private static Node lastNode = dummyHead;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";

        String logo = "┏━━┓╋╋╋┏━━┓╋╋╋┏┓\n" +
                      "┃┏┓┃╋╋╋┃┏┓┃╋╋┏┛┗┓\n" +
                      "┃┗┛┗┳━┳┫┗┛┗┳━┻┓┏┛\n" +
                      "┃┏━┓┃┏╋┫┏━┓┃┏┓┃┃\n" +
                      "┃┗━┛┃┃┃┃┗━┛┃┗┛┃┗┓\n" +
                      "┗━━━┻┛┗┻━━━┻━━┻━┛";

        System.out.println("Greetings from\n" + logo);
        lineSeparator();
        System.out.println("What can I do for you?");
        lineSeparator();

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            String[] words = userInput.split(" ", 2);
            switch (words[0]) {
                case "bye":
                    sayGoodBye();
                    break;
                case "list":
                    printList();
                    break;
                case "done":
                    markNode(Integer.parseInt(words[1]));
                    break;
                case "todo":
                    addTodo(userInput);
                    break;
                case "deadline":
                    String restDeadline = words[1];
                    String[] separatedDeadline = restDeadline.split(" /by ");
                    addDeadline(separatedDeadline[0], separatedDeadline[1]);
                    break;
                case "event":
                    String restEvent = words[1];
                    String[] separatedEvent = restEvent.split(" /at ");
                    addEvent(separatedEvent[0], separatedEvent[1]);
                    break;
                default:
                    System.out.println("Not a command...");
            }
        }
    }

    private static void markNode(int taskNumber) {
        Node pointer = dummyHead;
        lineSeparator();
        for (int i = 0; i < taskNumber; i++) {
            pointer = pointer.getNext();
        }
        pointer.markTask();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + pointer);
        lineSeparator();
    }

    private static void printAddedMessage(Node node) {
        lineSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println(node.getTask().toString());
        System.out.println("Now you have " + taskCount + " tasks in the list.");
        lineSeparator();
    }

    private static void addTodo(String description) {
        taskCount++;
        Node nextNode = new Node(new Todo(description));
        lastNode.setNext(nextNode);
        lastNode = nextNode;
        printAddedMessage(nextNode);
    }

    private static void addDeadline(String description, String by) {
        taskCount++;
        Node nextNode = new Node(new Deadline(description, by));
        lastNode.setNext(nextNode);
        lastNode = nextNode;
        printAddedMessage(nextNode);
    }

    private static void addEvent(String description, String timing) {
        taskCount++;
        Node nextNode = new Node(new Event(description, timing));
        lastNode.setNext(nextNode);
        lastNode = nextNode;
        printAddedMessage(nextNode);
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
