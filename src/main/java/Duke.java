import java.util.Scanner;

public class Duke {

    // Messages
    private static final String WELCOME_MESSAGE = "Hello! I'm Duke\nWhat can I do for you?";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:\n%s";
    private static final String NO_TASKS_IN_LIST_MESSAGE = "You have no tasks currently. Go create some!";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:\n %s";
    private static final String INVALID_NUMBER = "Please input a valid task number";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";

    // Delimiters

    private static void inputLoop(TaskList taskList) {
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
                        Message.display_message(taskList.addTask(Deadline.newDeadline(remainingText)));
                        break;
                    case EVENT:
                        Message.display_message(taskList.addTask(Event.newEvent(remainingText)));
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
    }

    public static void main(String[] args) {
        Message.display_message(WELCOME_MESSAGE);
        TaskList taskList = new TaskList();
        inputLoop(taskList);
        Message.display_message(EXIT_MESSAGE);
    }
}
