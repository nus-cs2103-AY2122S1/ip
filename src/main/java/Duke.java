import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Duke {
    private final List<Task> list;
    private final Storage storage;

    public Duke(String filePath){
        this.list = new ArrayList<>(100);
        this.storage = new Storage(filePath);
    }

    private void begin() {
        try {
            storage.readTasks(list);
        } catch (Exception e) {
            System.out.println("Could not read the data file: " + e.getMessage());
        }

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            try {
                if (input.equals("bye")) {
                    break;
                } else if (input.equals("list")) {
                    listTasks();
                } else if (input.startsWith("done")) {
                    try {
                        int listIndex = Integer.parseInt(input.substring(5));
                        if (listIndex <= list.size() && listIndex >= 1) {
                            completeTask(listIndex);
                            storage.writeTasks(list);
                        } else {
                            throw new DukeException("Couldn't find that task in the list! Try again.");
                        }
                    } catch (NumberFormatException e) { //If 'done' is followed by a non-integer
                        throw new DukeException("Please make sure only a number follows the command 'done'. Try again.");
                    } catch (StringIndexOutOfBoundsException e) { //If 'done' is not followed by anything
                        throw new DukeException("Please add a number after the command 'done'. Try again.");
                    }
                } else if (input.startsWith("deadline")) {
                    if (!input.contains("/by")) {
                        throw new DukeException("Please state the deadline for this task with /by! Try again.");
                    } else {
                        try {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
                            int deadlineIndex = input.indexOf("/by") + 4;
                            String deadlineString = input.substring(deadlineIndex);
                            LocalDate deadline = LocalDate.parse(deadlineString, dtf);
                            String firstTrimmed = input.substring(9);
                            String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/by"));
                            Deadline createdDeadlineTask = new Deadline(lastTrimmed, false, deadline);
                            addToList(createdDeadlineTask);
                            System.out.println("Got it. I've added this task:\n" + "  " + createdDeadlineTask + "\n"
                                    + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                    " in the list.");
                            storage.writeTasks(list);
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("Please ensure that there is a task description and deadline. " +
                                    "Try again.");
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Please ensure that your deadline is formatted in the following " +
                                    "way: DD/MM/YY");
                        }
                    }
                } else if (input.startsWith("todo")) {
                    try {
                        String trimmed = input.substring(5);
                        Todo createdTodoTask = new Todo(trimmed, false);
                        addToList(createdTodoTask);
                        System.out.println("Got it. I've added this task:\n" + "  " + createdTodoTask + "\n"
                                + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                " in the list.");
                        storage.writeTasks(list);
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("Please add the task information. Try again.");
                    }
                } else if (input.startsWith("event")) {
                    if (!input.contains("/at")) {
                        throw new DukeException("Please state when the event will be held with /at! Try again.");
                    } else {
                        try {
                            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy HHmm");
                            int atIndex = input.indexOf("/at") + 4;
                            String atString = input.substring(atIndex);
                            String firstTrimmed = input.substring(6);
                            String lastTrimmed = firstTrimmed.substring(0, firstTrimmed.indexOf("/at"));
                            LocalDateTime at = LocalDateTime.parse(atString, dtf);
                            Event createdEventTask = new Event(lastTrimmed, false, at);
                            addToList(createdEventTask);
                            System.out.println("Got it. I've added this task:\n" + "  " + createdEventTask + "\n"
                                    + "Now you have " + list.size() + " task" + (list.size() == 1 ? "" : "s") +
                                    " in the list.");
                            storage.writeTasks(list);
                        } catch (StringIndexOutOfBoundsException e) {
                            throw new DukeException("Please ensure that there is an event time. Try again.");
                        } catch (DateTimeParseException e) {
                            throw new DukeException("Please ensure that your event is formatted in the following " +
                                    "way: DD/MM/YY HHmm (24 hr format)");
                        }
                    }
                } else if (input.startsWith("delete")) {
                    try {
                        int toDeleteIndex = Integer.parseInt(input.substring(7));
                        deleteTask(toDeleteIndex);
                        storage.writeTasks(list);
                    } catch (NumberFormatException e){
                        throw new DukeException("Please make sure only a number follows the command 'delete'. " +
                                "Try again.");
                    } catch (StringIndexOutOfBoundsException e) {
                        throw new DukeException("Please add a number after the command 'delete'. Try again.");
                    }
                } else {
                    throw new DukeException("I didn't quite get what you meant. To add a task, begin with " +
                            "'deadline', 'event' or 'todo'.");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void addToList(Task task) {
        this.list.add(task);
    }

    private void listTasks() throws DukeException {
        if (list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something.");
        } else {
            if (list.size() == 1) {
                System.out.println("Here is the sole task in your list:");
            } else {
                System.out.println("Here are the tasks in your list:");
            }
            for (int i = 1; i <= list.size(); i++) {
                System.out.println(i + ". " + list.get(i - 1));
            }
        }
    }

    private void deleteTask(int index) throws DukeException {
        if (list.size() < 1) {
            throw new DukeException("You haven't added anything to the list yet! Try adding something before " +
                    "deleting.");
        } else if (index <= list.size() && index >= 1) {
            Task toDelete = list.get(index - 1);
            list.remove(index - 1);
            System.out.println("Noted. I've removed this task:\n" + "  " + toDelete + "\n" + "Now you have " +
                    list.size() + " task" + (list.size() == 1 ? "" : "s") + " in the list.");
        } else {
            throw new DukeException("Couldn't find that task in the list! Try again.");
        }
    }

    private void completeTask(int index) {
        Task task = list.get(index - 1);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:\n" + "  " + task);
    }

    public static void main(String[] args) {
        Duke duke = new Duke("./data/duke.txt");
        duke.begin();
    }
}
