import java.util.Scanner;

class Node {
    private Task task;
    private Node next;

    public Node(Task task) {
        this.task = task;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node n) {
        this.next = n;
    }

    public Task getTask() {
        return task;
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
        String userInput = "";
        while (userInput != "bye") {
            userInput = sc.nextLine();
            String[] words = userInput.split(" ", 2);
            switch(words[0]) {
                case "bye":
                    sayGoodBye();
                    break;
                case "list":
                    printList();
                    break;
                case "done":
                    markNode(Integer.parseInt(words[1]));
                    break;
                default:
                    addInput(userInput);
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

    private static void addInput(String userInput) {
        lineSeparator();
        System.out.println("added: " + userInput);
        lineSeparator();
        taskCount++;
        lastNode.setNext(new Node(new Task(userInput)));
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
