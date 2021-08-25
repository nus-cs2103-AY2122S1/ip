import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;

    // Regex pattern for finding done commands
    private static final Pattern DONE_PATTERN = Pattern.compile("^done (\\d*)$");

    // Regex pattern for finding delete commands
    private static final Pattern DELETE_PATTERN = Pattern.compile("^delete (\\d*)$");

    // Regex pattern for finding todo commands
    private static final Pattern TODO_PATTERN = Pattern.compile("^todo (.*)$");

    // Regex pattern for finding deadline commands
    private static final Pattern DEADLINE_PATTERN = Pattern.compile("^deadline (.*) /by (\\d{4}-\\d{2}-\\d{2})$");

    // Regex pattern for finding event commands
    private static final Pattern EVENT_PATTERN = Pattern.compile("^event (.*) /at (\\d{4}-\\d{2}-\\d{2})$");

    public Duke(String storageFilePath) {
         storage = new Storage(storageFilePath);
         tasks = new TaskList(storage);
         ui = new Ui();
    }

    public void run() {

        Scanner scanner = new Scanner((System.in));

        ui.greet();

        while (true) {
            String userInput = scanner.nextLine().strip();
            try {
                if (userInput.equals("bye")) {
                    // Check if user is attempting to exit.
                    ui.goodbye();
                    break;
                } else if (userInput.equals("list")) {
                    // Check if user is requesting to print list.
                    ui.printTasks(tasks);
                } else if (userInput.startsWith("done")) {
                    markAsDone(userInput);
                } else if (userInput.startsWith("delete")) {
                    delete(userInput);
                } else if (userInput.startsWith("todo")) {
                    // User is attempting to add a to-do
                    addToDo(userInput);
                } else if (userInput.startsWith("deadline")) {
                    // User is attempting to add a deadline
                    addDeadline(userInput);
                } else if (userInput.startsWith("event")) {
                    // User is attempting to add an event
                    addEvent(userInput);
                } else {
                    // Invalid command
                    throw new DukeException("Sorry, I didn't understand what you meant by that");
                }
            } catch (DukeException e) {
                // Get Duke to say out the error
                ui.notifyException(e);
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke("data/tasks.txt");
        duke.run();
    }

    /**
     * Marks a task at taskPosition as done.
     * @param userInput User input to be split by regex pattern matching
     */
    private void markAsDone(String userInput) {

        // Check if user is attempting to mark a task as done.
        Matcher matcher =  DONE_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            throw new DukeException("Mark a task as done like this: done <task number>");
        }

        String taskPositionString = matcher.group(1);
        int taskPosition = Integer.parseInt(taskPositionString);

        Task task = tasks.markAsDone(taskPosition - 1);

        ui.notifyMarkDone(task, taskPosition - 1);
    }

    /**
     * Deletes a task from the store.
     * @param userInput User input to be split by regex pattern matching
     */
    private void delete(String userInput) {

        // Check if user is attempting to mark a task as done.
        Matcher matcher =  DELETE_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            throw new DukeException("Delete a task like this: delete <task number>");
        }

        String taskPositionString = matcher.group(1);
        int taskPosition = Integer.parseInt(taskPositionString);

        // Remove the task from the store.
        Task task = tasks.delete(taskPosition - 1);

        ui.notifyDelete(task, tasks.size());
    }

    /**
     * Adds a to-do which contains a description and no date/time.
     * @param userInput User input to be split by regex pattern matching
     */
    private void addToDo(String userInput) {
        Matcher matcher = TODO_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            throw new DukeException("Give me a description of the todo to add it as a task");
        }
        String description = matcher.group(1);

        ToDo todo = new ToDo(description, false);

        // Add to store
        tasks.add(todo);

        // Inform user
        ui.notifyAdd(todo, tasks.size());
    }

    /**
     * Adds a to-do which contains a description and an end date/time.
     * @param userInput User input to be split by regex pattern matching
     */
    private void addDeadline(String userInput) {
        Matcher matcher = DEADLINE_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            throw new DukeException("Give me a deadline like this: deadline <task> /by YYYY-MM-DD");
        }
        String description = matcher.group(1);
        String endDateTime = matcher.group(2);

        Deadline deadline = new Deadline(description, false, parseDate(endDateTime));

        // Add to store
        tasks.add(deadline);

        // Inform user
        ui.notifyAdd(deadline, tasks.size());
    }

    /**
     * Adds an event which contains a description and a start and end date/time.
     * @param userInput User input to be split by regex pattern matching
     */
    private void addEvent(String userInput) {
        Matcher matcher = EVENT_PATTERN.matcher(userInput);
        if (!matcher.find()) {
            throw new DukeException("Tell me an event like this: event <task> /at YYYY-MM-DD");
        }
        String description = matcher.group(1);
        String eventDateTime = matcher.group(2);

        Event event = new Event(description, false, parseDate(eventDateTime));

        // Add to store
        tasks.add(event);

        // Inform user
        ui.notifyAdd(event, tasks.size());
    }

    /**
     * Parses a date String into LocalDate.
     *
     * Uses the ISO_LOCAL_DATE format (e.g 1970-09-21) to parse a date.
     * @param date String containing the date
     * @return LocalDate corresponding to the date in the String
     */
    private static LocalDate parseDate(String date) {
        try {
            return LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        } catch (DateTimeParseException e) {
            throw new DukeException(date + " is not a valid date!");
        }
    }
}
