import java.util.Scanner;

public class Duke {

    // Constants
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n%s";
    private static final String NO_TASKS_IN_LIST_MESSAGE = "You have no tasks currently. Go create some!";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n %s";
    private static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d %s in the list.";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n%s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String INVALID_DONE_NUMBER = "Please input a valid task number";
    private static final String MISSING_DONE_NUMBER_MESSAGE = "Please input a number after the done command";
    private static final String INCOHERENT_INPUT_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String MISSING_DELETE_NUMBER_MESSAGE = "Please input a number after the delete command";
    private static final String INVALID_DEADLINE_MESSAGE = "Invalid use of deadline command. Use 'deadline <text> /by <datetime>'";
    private static final String INVALID_EVENT_MESSAGE = "Invalid use of event command. Use 'event <text> /at <datetime>'";
    private static final String MISSING_TODO_MESSAGE = "Please input text after the todo command";

    // Commands
    private static final String EXIT_COMMAND = "bye";
    private static final String LIST_COMMAND = "list";
    private static final String DONE_COMMAND = "done";
    private static final String DEADLINE_COMMAND = "deadline";
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_COMMAND = "event";
    private static final String EVENT_DELIMITER = "/at";
    private static final String TODO_COMMAND = "todo";

    // Message methods
    public static void horizontal_line() {
        System.out.print("____________________________________________________________\n");
    }

    public static void display_message(String message) {
        horizontal_line();
        System.out.println(message);
        horizontal_line();
    }

    // Continue loop conditions
    public static boolean canContinue(String input) {
        return !input.equalsIgnoreCase(EXIT_COMMAND);
    }

    public static void main(String[] args) {
        display_message(WELCOME_MESSAGE);

        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);
        for (String input = sc.nextLine(); canContinue(input); input = sc.nextLine()) {
            String[] inputArr = input.split(" ");
            String firstCommand = inputArr[0];
            try {
                switch (firstCommand) {
                    case LIST_COMMAND:
                        if (taskList.size() == 0) {
                            display_message(NO_TASKS_IN_LIST_MESSAGE);
                        } else {
                            display_message(String.format(LIST_MESSAGE, taskList.toString()));
                        }
                        break;
                    case DONE_COMMAND:
                        if (inputArr.length < 2) {
                            throw new DukeException(MISSING_DONE_NUMBER_MESSAGE);
                        }
                        String secondCommandDone = inputArr[1];
                        try {
                            int taskIndex = Integer.parseInt(secondCommandDone);
                            display_message(String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex)));
                        } catch (NumberFormatException err) {
                            throw new DukeException(INVALID_DONE_NUMBER);
                        }
                        break;
                    case TODO_COMMAND:
                        if (inputArr.length < 2) {
                            throw new DukeException(MISSING_TODO_MESSAGE);
                        }
                        String todoText = input.substring(TODO_COMMAND.length() + 1).trim();
                        display_message(taskList.addTask(new ToDo(todoText)));
                        break;
                    case DEADLINE_COMMAND:
                        String[] deadlineInfo = input.split(DEADLINE_DELIMITER);
                        if (inputArr.length < 4 || deadlineInfo.length < 2) {
                            throw new DukeException(INVALID_DEADLINE_MESSAGE);
                        }
                        String deadline = deadlineInfo[1].trim();
                        String deadlineText = deadlineInfo[0].substring(DEADLINE_COMMAND.length() + 1).trim();
                        display_message(taskList.addTask(new Deadline(deadlineText, deadline)));
                        break;
                    case EVENT_COMMAND:
                        String[] eventInfo = input.split(EVENT_DELIMITER);
                        if (inputArr.length < 4 || eventInfo.length < 2) {
                            throw new DukeException(INVALID_EVENT_MESSAGE);
                        }
                        String event = eventInfo[1].trim();
                        String eventText = eventInfo[0].substring(EVENT_COMMAND.length() + 1).trim();
                        display_message(taskList.addTask(new Event(eventText, event)));
                        break;
                    default:
                        display_message(INCOHERENT_INPUT_MESSAGE);
                }
            } catch (DukeException err) {
                display_message(err.getMessage());
            }
        }
        sc.close();

        display_message(EXIT_MESSAGE);
    }
}
