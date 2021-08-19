import java.util.Scanner;

public class Duke {

    // Messages
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n%s";
    private static final String NO_TASKS_IN_LIST_MESSAGE = "You have no tasks currently. Go create some!";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n %s";
    private static final String INVALID_NUMBER = "Please input a valid task number";
    private static final String INVALID_DEADLINE_MESSAGE = "Invalid use of deadline command. Use 'deadline <text> /by <datetime>'";
    private static final String INVALID_EVENT_MESSAGE = "Invalid use of event command. Use 'event <text> /at <datetime>'";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    // Delimiters
    private static final String DEADLINE_DELIMITER = "/by";
    private static final String EVENT_DELIMITER = "/at";

    public static void main(String[] args) {
        Message.display_message(WELCOME_MESSAGE);

        TaskList taskList = new TaskList();

        Scanner sc = new Scanner(System.in);
        boolean canContinue = true;
        while (canContinue) {
            String input = sc.nextLine();
            String[] inputArr = input.split(" ");
            try {
                String firstWord = inputArr[0];
                String remainingText = "";
                if (input.length() > firstWord.length() + 1) {
                    remainingText = input.substring(firstWord.length() + 1).trim();
                }
                Command command = Command.initialiseCommand(firstWord);
                // Verify that the remaining text doesn't have errors related to number of arguments etc
                command.verifyArguments(remainingText);
                switch (command) {
                    case LIST:
                        if (taskList.size() == 0) {
                            Message.display_message(NO_TASKS_IN_LIST_MESSAGE);
                        } else {
                            Message.display_message(String.format(LIST_MESSAGE, taskList));
                        }
                        break;
                    case DONE:
                        try {
                            int taskIndex = Integer.parseInt(remainingText);
                            Message.display_message(String.format(DONE_MESSAGE, taskList.markTaskAsDone(taskIndex)));
                        } catch (NumberFormatException err) {
                            throw new DukeException(INVALID_NUMBER);
                        }
                        break;
                    case DELETE:
                        try {
                            int taskIndex = Integer.parseInt(remainingText);
                            Message.display_message(taskList.deleteTask(taskIndex));
                        } catch (NumberFormatException err) {
                            throw new DukeException(INVALID_NUMBER);
                        }
                        break;
                    case TODO:
                        String todoText = input.substring(Command.TODO.toString().length() + 1).trim();
                        Message.display_message(taskList.addTask(new ToDo(todoText)));
                        break;
                    case DEADLINE:
                        String[] deadlineInfo = remainingText.split(DEADLINE_DELIMITER);
                        if (deadlineInfo.length < 2) {
                            throw new DukeException(INVALID_DEADLINE_MESSAGE);
                        }
                        String deadline = deadlineInfo[1].trim();
                        String deadlineText = deadlineInfo[0].trim();
                        Message.display_message(taskList.addTask(new Deadline(deadlineText, deadline)));
                        break;
                    case EVENT:
                        String[] eventInfo = remainingText.split(EVENT_DELIMITER);
                        if (eventInfo.length < 2) {
                            throw new DukeException(INVALID_EVENT_MESSAGE);
                        }
                        String event = eventInfo[1].trim();
                        String eventText = eventInfo[0].trim();
                        Message.display_message(taskList.addTask(new Event(eventText, event)));
                        break;
                    case BYE:
                        canContinue = false;
                        break;
                }
            } catch (DukeException err) {
                Message.display_message(err.getMessage());
            }
        }
        sc.close();

        Message.display_message(EXIT_MESSAGE);
    }
}
