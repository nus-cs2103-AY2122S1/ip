import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private final static DukeList list = new DukeList();

    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT,
        INVALID
    }

    private static void display(String content) {
        System.out.println(
                "____________________________________________________________\n"
                + content
                + "\n____________________________________________________________\n"
        );
    }

    private static void displayList() {
        display(list.toString());
    }

    private static void addTask(String input, TaskType type) throws InvalidArgumentsException, InvalidTaskException {
        int descriptionEnd;
        String description;
        String dateTime;
        Task task;
        switch (type) {
            case TODO:
                try {
                    // filter out todoXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "todo"
                    if (input.charAt(4) != ' ') {
                        throw new InvalidTaskException();
                    }
                    // StringIndexOutOfBoundsException thrown here if input = "todo "
                    description = input.substring(5);
                    // Checks if description is all whitespace
                    if (description.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentsException();
                }
                task = new ToDo(description);
                break;
            case DEADLINE:
                // DOES NOT CATCH INVALID INPUTS SUCH AS "deadline ${String} /by /by"
                try {
                    // filter out deadlineXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "deadline"
                    if (input.charAt(8) != ' ') {
                        throw new InvalidTaskException();
                    }
                    descriptionEnd = input.indexOf(" /by ");
                    // StringIndexOutOfBoundsException thrown here if input = "deadline /by "
                    // or if " /by " is not present in input
                    description = input.substring(9, descriptionEnd);
                    // Checks if description is all whitespace
                    if (description.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                    // StringIndexOutOfBoundsException thrown here if input = "$String /by "
                    dateTime = input.substring(descriptionEnd + 5);
                    // Checks if dateTime is all whitespace
                    if (dateTime.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentsException();
                }
                task = new Deadline(description, dateTime);
                break;
            case EVENT:
                // DOES NOT CATCH INVALID INPUTS SUCH AS "event ${String} /at /at"
                try {
                    // filter out eventXXXX
                    // StringIndexOutOfBoundsException thrown here if input = "event"
                    if (input.charAt(5) != ' ') {
                        throw new InvalidTaskException();
                    }
                    descriptionEnd = input.indexOf(" /at ");
                    // StringIndexOutOfBoundsException thrown here if input = "event /at"
                    // or if " /at " is not present in input
                    description = input.substring(6, descriptionEnd);
                    // Checks if description is all whitespace
                    if (description.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                    // StringIndexOutOfBoundsException thrown here if input = "$String /at "
                    dateTime = input.substring(descriptionEnd + 5);
                    // Checks if dateTime is all whitespace
                    if (dateTime.trim().isEmpty()) {
                        throw new InvalidArgumentsException();
                    }
                } catch (StringIndexOutOfBoundsException e) {
                    throw new InvalidArgumentsException();
                }
                task = new Event(description, dateTime);
                break;
            default:
                throw new InvalidTaskException();
        }
        list.add(task);
        display("Got it. I've added this task:\n "
                + task.toString() + "\n"
                + "Now you have " + list.size() + " tasks in the list"
        );
    }

    private static void markDone(String input) throws InvalidArgumentsException, InvalidTaskException {
        int taskNum;
        try {
            // filter out doneXXXX
            // StringIndexOutOfBoundsException thrown here if input = "done",
            // which is caught by IndexOutOfBoundsException
            if (input.charAt(4) != ' ') {
                throw new InvalidTaskException();
            }
            // NumberFormatException thrown here if substring is a invalid integer string
            taskNum = Integer.parseInt(input.substring(5)) - 1;
            // IndexOutOfBoundsException thrown here if taskNum > list size
            Task task = list.get(taskNum);
            task.markDone();
            display("Nice! I've marked this task as done: \n"
                    + "  "
                    + task.toString());
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentsException();
        }
    }

    private static void runDuke() {
        display("Hello I'm Duke\nWhat can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                displayList();
            } else if (input.startsWith("done")) {
                try {
                    markDone(input);
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else if (input.startsWith("todo")) {
                try {
                    addTask(input, TaskType.TODO);
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else if (input.startsWith("deadline")) {
                try {
                    addTask(input, TaskType.DEADLINE);
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else if (input.startsWith("event")) {
                try {
                    addTask(input, TaskType.EVENT);
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            } else {
                try {
                    addTask(input, TaskType.INVALID);
                } catch (DukeException e) {
                    display(e.getMessage());
                }
            }
        }
        scanner.close();
        display("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        runDuke();
    }

}
