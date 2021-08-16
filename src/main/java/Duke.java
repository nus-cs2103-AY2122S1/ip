import javax.naming.NoInitialContextException;
import java.util.ArrayList;
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
//    private static int taskCount = 0;
//    private static Node dummyHead = new Node(new Task(""));
//    private static Node lastNode = dummyHead;
    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) throws DukeException {
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
            try {
                switch (words[0]) {
                    case "bye":
                        sayGoodBye();
                        break;
                    case "list":
                        printList();
                        break;
                    case "done":
                        if (words.length < 2) {
                            throw new DukeException("☹ OOPS!!! Please specify which task you wish to complete.");
                        }
                        int index = Integer.parseInt(words[1]) - 1;
                        if (index < 0 || index >= tasks.size()) {
                            throw new DukeException("☹ OOPS!!! You just gave an invalid task to be completed.");
                        }
                        markNode(index);
                        break;
                    case "todo":
                        if (words.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        addTodo(words[1]);
                        break;
                    case "deadline":
                        if (words.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                        }
                        String restDeadline = words[1];
                        String[] separatedDeadline = restDeadline.split(" /by ");
                        if (separatedDeadline.length < 2) {
                            throw new DukeException("☹ OOPS!!! Please ensure that the '/by' keyword is used and "
                                                  + "that a description and due date is given.");
                        }
                        addDeadline(separatedDeadline[0], separatedDeadline[1]);
                        break;
                    case "event":
                        if (words.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                        }
                        String restEvent = words[1];
                        String[] separatedEvent = restEvent.split(" /at ");
                        if (separatedEvent.length < 2) {
                            throw new DukeException("☹ OOPS!!! Please ensure that the '/at' keyword is used and "
                                    + "that a description and a timing is given.");
                        }
                        addEvent(separatedEvent[0], separatedEvent[1]);
                        break;
                    default:
                        throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                lineSeparator();
                System.out.println(e.getMessage());
                lineSeparator();
            }
        }
    }

    private static void markNode(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        lineSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        lineSeparator();
//        Node pointer = dummyHead;
//        lineSeparator();
//        for (int i = 0; i < taskNumber; i++) {
//            pointer = pointer.getNext();
//        }
//        pointer.markTask();
//        System.out.println("Nice! I've marked this task as done:");
//        System.out.println("  " + pointer);
//        lineSeparator();
    }

    private static void printAddedMessage(Task task) {
        lineSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        lineSeparator();
    }

    private static void addTodo(String description) {
        Task task = new Todo(description);
        tasks.add(task);
        printAddedMessage(task);
    }

    private static void addDeadline(String description, String by) {
        Task task = new Deadline(description, by);
        tasks.add(task);
        printAddedMessage(task);
    }

    private static void addEvent(String description, String timing) {
        Task task = new Event(description, timing);
        tasks.add(task);
        printAddedMessage(task);
    }

    private static void printList() {
        lineSeparator();
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
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
