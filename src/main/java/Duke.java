import java.util.Scanner;

public class Duke {

    // Constants
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n%s";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n %s";
    private static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d %s in the list.";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n%s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String MISSING_DONE_NUMBER_MESSAGE = "Please input a number after the done command";
    private static final String INCOHERENT_INPUT_MESSAGE = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

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
            switch (firstCommand) {
                case LIST_COMMAND:
                    display_message(String.format(LIST_MESSAGE, taskList.toString()));
                    break;
                case DONE_COMMAND:
                    if (inputArr.length < 2) {
                        display_message(MISSING_DONE_NUMBER_MESSAGE);
                    } else {
                        String secondCommand = inputArr[1];
                        int taskIndex = Integer.parseInt(secondCommand);
                        display_message(String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex)));
                    }
                    break;
                case TODO_COMMAND:
                    String todoText = input.substring(TODO_COMMAND.length() + 1).trim();
                    display_message(String.format(ADD_TASK_MESSAGE, taskList.addTask(new ToDo(todoText)), taskList.size(), taskList.size() <= 1 ? "task" : "tasks"));
                    break;
                case DEADLINE_COMMAND:
                    String[] deadlineInfo = input.split(DEADLINE_DELIMITER);
                    String deadline = deadlineInfo[1].trim();
                    String deadlineText = deadlineInfo[0].substring(DEADLINE_COMMAND.length() + 1).trim();
                    display_message(String.format(ADD_TASK_MESSAGE, taskList.addTask(new Deadline(deadlineText, deadline)), taskList.size(), taskList.size() <= 1 ? "task" : "tasks"));
                    break;
                case EVENT_COMMAND:
                    String[] eventInfo = input.split(EVENT_DELIMITER);
                    String event = eventInfo[1].trim();
                    String eventText = eventInfo[0].substring(EVENT_COMMAND.length() + 1).trim();
                    display_message(String.format(ADD_TASK_MESSAGE, taskList.addTask(new Event(eventText, event)), taskList.size(), taskList.size() <= 1 ? "task" : "tasks"));
                    break;
                default:
                    display_message(INCOHERENT_INPUT_MESSAGE);
            }
        }
        sc.close();

        display_message(EXIT_MESSAGE);
    }
}
