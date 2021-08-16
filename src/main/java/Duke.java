import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

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
                        int doneIndex = Integer.parseInt(words[1]) - 1;
                        if (doneIndex < 0 || doneIndex >= tasks.size()) {
                            throw new DukeException("☹ OOPS!!! You just gave an invalid task to be completed.");
                        }
                        markTask(doneIndex);
                        break;
                    case "delete":
                        if (words.length < 2) {
                            throw new DukeException("☹ OOPS!!! Please specify which task you wish to delete.");
                        }
                        int delIndex = Integer.parseInt(words[1]) - 1;
                        if (delIndex < 0 || delIndex >= tasks.size()) {
                            throw new DukeException("☹ OOPS!!! You just gave an invalid task to be deleted.");
                        }
                        deleteTask(delIndex);
                        break;
                    case "todo":
                        if (words.length < 2) {
                            throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        addTask(words[1], "", TaskType.TODO);
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
                        addTask(separatedDeadline[0], separatedDeadline[1], TaskType.DEADLINE);
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
                        addTask(separatedEvent[0], separatedEvent[1], TaskType.EVENT);
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

    private static void deleteTask(int delIndex) {
        Task task = tasks.get(delIndex);
        tasks.remove(delIndex);
        lineSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        lineSeparator();
    }

    private static void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        lineSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        lineSeparator();
    }

    private static void printAddedMessage(Task task) {
        lineSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        lineSeparator();
    }

    private static void addTask(String description, String timing, TaskType taskType) {
        Task task;
        switch (taskType) {
            case TODO:
                task = new Todo(description);
                tasks.add(task);
                printAddedMessage(task);
                break;
            case DEADLINE:
                task = new Deadline(description, timing);
                tasks.add(task);
                printAddedMessage(task);
                break;
            case EVENT:
                task = new Event(description, timing);
                tasks.add(task);
                printAddedMessage(task);
                break;
        }
    }

    private static void printList() {
        lineSeparator();
        System.out.println("Here are the tasks in your list:");
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
