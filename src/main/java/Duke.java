import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

public class Duke {
    private final List<Task> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Task.resetMaxId();
        Duke duke = new Duke();

        duke.printInitialGreeting();
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            duke.response(input);
            input = sc.nextLine();
        }
        duke.close();
    }

    public void response(String input) {
        String[] words = input.split(" ");
        String command = words[0];
        try {
            switch (command) {
                case "list":
                    printList();
                    return;
                case "done":
                    int taskNumber = Integer.parseInt(words[1]);
                    markAsDone(taskNumber);
                    return;
                case "delete":
                    int taskNumberToBeDeleted = Integer.parseInt(words[1]);
                    deleteTask(taskNumberToBeDeleted);
                    return;
                case "todo":
                    if (words.length == 1) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    Task todo = new Todo(combine(words, 1, words.length));
                    addToList(todo);
                    return;
                case "deadline":
                    String rest = combine(words, 1, words.length);
                    String[] newList = rest.split(" /by ");
                    if (newList.length != 2) {
                        throw new DukeException("Incorrect command was given for deadline. Try this: deadline name_here /by date_here");
                    }
                    Task deadline = new Deadline(newList[0], newList[1]);
                    addToList(deadline);
                    return;
                case "event":
                    String restOfWords = combine(words, 1, words.length);
                    String[] eventList = restOfWords.split(" /at ");
                    if (eventList.length != 2) {
                        throw new DukeException("Incorrect command was given for event. Try this: deadline name_here /at date_here");
                    }
                    Task event = new Event(eventList[0], eventList[1]);
                    addToList(event);
                    return;
                default:
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
            }

        } catch(DukeException e) {
            System.out.println("OOPS!!! " + e.getMessage());
        } catch(java.lang.NumberFormatException e) {
            System.out.println("OOPS!!! " + e.getLocalizedMessage() + " was input instead of an integer.");
        }
    }

    public void printInitialGreeting() {
        System.out.println("Hello I'm Duke\n" +
                "What can I do for you?");
    }

    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printList() {
        System.out.println("Here are the tasks in your list:");
        for (Task t: list) {
            System.out.println(t);
        }
    }

    private void addToList(Task input) {
        this.list.add(input);
        System.out.println("Got it. I've added this task:\n  " + input.details());
        printListNumber();
    }

    private void printListNumber() {
        System.out.println("You now have " + this.list.size() + " tasks in the list.");
    }

    private void markAsDone(int id) throws DukeException {
        try {
            Task currentTask = this.list.get(id - 1);
            currentTask.markAsCompleted();
            System.out.println("Nice! I've marked this task as done:\n "
                    + currentTask.details());
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }

    }

    private void deleteTask(int id) throws DukeException {
        try {
            Task deletedTask = this.list.remove(id - 1);
            System.out.println("Noted. I've removed this task: \n "
                    + deletedTask.details()
            );
            printListNumber();
        } catch (java.lang.IndexOutOfBoundsException e) {
            throw new DukeException("That task does not exist.");
        }
    }


    private String combine(String[] splitList, int start, int end) {
        StringBuilder result = new StringBuilder();
        for (int i = start; i < end; i++) {
            result.append(splitList[i]);
            result.append(" ");
        }
        return result.substring(0,result.length() - 1).toString();
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
