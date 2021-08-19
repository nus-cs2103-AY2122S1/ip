import java.util.Scanner;

public class Duke {
    private static final String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String INTRO_MESSAGE = "Hello~ I'm Duke :D\n" + "\nWhat can I do for you?";
    private static final String EXIT_MESSAGE = "Goodbyeeee! Hope to see you again soon! :>";
    private static final String SEPARATOR = "-------------------------------------------------------";
    private static final String BLANK_INPUT_MESSAGE = "Please enter something! ANYTHING!";
    private static final String BLANK_DESCRIPTION_MESSAGE = "OOPS!!! The description of %s cannot be empty! x_x";
    private static final String TODO_ERROR_MESSAGE = "Invalid use of 'todo' command!! @_@\nTo add a new todo, use 'todo <task>'.";
    private static final String DEADLINE_ERROR_MESSAGE = "Invalid use of 'deadline' command!! @_@\nTo add a new deadline, use 'deadline <task> /by <due-date>'.";
    private static final String EVENT_ERROR_MESSAGE = "Invalid use of 'event' command!! @_@\nTo add a new event, use 'event <title> /at <time-stamp>'.";
    private static final String DONE_ERROR_MESSAGE = "Invalid use of 'done' command!! @_@\nTo mark a task as done, use 'done <task-number>'.";

    private static void printReply(String str) {
        System.out.println("\n" + "DUKE:\n" + str + "\n" + SEPARATOR + "\n");
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        TaskHandler taskHandler = new TaskHandler();
        printReply(INTRO_MESSAGE);
        Command command;

        while (sc.hasNextLine()) {
            String input = sc.nextLine();
            String inputUpperCase = input.trim().toUpperCase();
            try {
                if (inputUpperCase.isEmpty()) {
                    throw new DukeException(BLANK_INPUT_MESSAGE);
                }
                // Get the first word of the command
                String commandWord = input.split("\\s+")[0];
                String descExtractedRaw = input.replace(commandWord, "").trim();
                command = Command.evaluateInput(commandWord);
                switch (command) {
                    case LIST:
                        printReply(taskHandler.toString());
                        break;
                    case DONE:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        int taskIndex = Integer.parseInt(descExtractedRaw);
                        printReply(taskHandler.markTaskAsDone(taskIndex));
                        break;
                    case DEADLINE:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        String[] deadlineDetails = descExtractedRaw.split("\\s+/by\\s+", 2);
                        Deadline dl = new Deadline(deadlineDetails[0], deadlineDetails[1]);
                        printReply(taskHandler.addTask(dl));
                        break;
                    case TODO:
                        validateFilled(descExtractedRaw, command);
                        Todo td = new Todo(descExtractedRaw);
                        printReply(taskHandler.addTask(td));
                        break;
                    case EVENT:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        String[] eventDetails = descExtractedRaw.split("\\s+/at\\s+", 2);
                        Event event = new Event(eventDetails[0], eventDetails[1]);
                        printReply(taskHandler.addTask(event));
                        break;
                    case DELETE:
                        validateFilled(descExtractedRaw, command);
                        validateDetails(descExtractedRaw, command);
                        int deleteIndex = Integer.parseInt(descExtractedRaw);
                        printReply(taskHandler.deleteTask(deleteIndex));
                        break;
                    case BYE:
                        printReply(EXIT_MESSAGE);
                        sc.close();
                        return;
                    default:
                        throw new DukeException(("I don't quite understand you. :-("));
                }
            } catch (DukeException e) {
                printReply(e.getMessage());
            }
        }
    }

    private static void validateFilled(String input, Command c) throws DukeException {
        if (input.isEmpty()) {
            throw new DukeException(String.format(BLANK_DESCRIPTION_MESSAGE, c.toString().toLowerCase()));
        }
    }

    public static void validateDetails(String input, Command c) throws DukeException {
        switch (c) {
            case DONE:
                try {
                    Integer.parseInt(input);
                } catch (NumberFormatException e) {
                    throw new DukeException(DONE_ERROR_MESSAGE);
                }
                break;
            case DEADLINE:
                String[] deadlineTime = input.split("\\s+/by\\s+", 2);
                if (deadlineTime.length < 2) {
                    throw new DukeException(DEADLINE_ERROR_MESSAGE);
                }
                break;
            case TODO:
                if (input.isEmpty()) {
                    throw new DukeException(TODO_ERROR_MESSAGE);
                }
                break;
            case EVENT:
                String[] eventTime = input.split("\\s+/at\\s+", 2);
                if (eventTime.length < 2) {
                    throw new DukeException(EVENT_ERROR_MESSAGE);
                }
                break;
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}