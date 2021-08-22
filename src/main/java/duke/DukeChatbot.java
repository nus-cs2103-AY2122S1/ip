package duke;

import duke.storage.StorageHandler;
import duke.task.*;
import duke.command.CommandParser;
import duke.command.CommandType;
import duke.command.DukeInvalidCommandException;
import duke.ui.MessageFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents the Duke chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DukeChatbot {

    private static final String LOGO = " ____        _\n"
            + "|  _ \\ _   _| | _____\n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING_MESSAGE = "Hello! I'm Duke!\n"
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String UNEXPECTED_ERROR_MESSAGE = "An unexpected error has occurred.";
    private static final String INVALID_COMMAND_ERROR_TEMPLATE = "This command is invalid.\n%s\nPlease try again.";

    private StorageHandler storageHandler;
    private MessageFormatter messageFormatter;
    private CommandParser commandParser;
    private TaskHandler taskHandler;
    private boolean hasErrorOnSave;

    /**
     * Creates a Duke chatbot.
     */
    public DukeChatbot() {
    }

    /**
     * Runs the Duke chatbot.
     */
    public void run() {
        try {
            initialise();
            printGreeting();
            listenForInput();
        } catch (IOException e) {
            printFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
        }
    }

    private void initialise() throws IOException {
        messageFormatter = new MessageFormatter();
        commandParser = new CommandParser();
        storageHandler = StorageHandler.getInstance();
        taskHandler = new TaskHandler(storageHandler.loadTasks());
        taskHandler.addTasksListUpdateObserver(tasks -> {
            try {
                storageHandler.saveTasks(tasks);
            } catch (IOException e) {
                hasErrorOnSave = true;
            }
        });
        hasErrorOnSave = false;
    }

    private void printGreeting() {
        printFormattedMessage(LOGO + GREETING_MESSAGE);
    }

    private void listenForInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        CommandType commandType;
        do {
            try {
                command = br.readLine();
                commandType = commandParser.getCommandTypeFromCommand(command);
                int taskIndex;
                switch (commandType) {
                case EXIT:
                    printExitMessage();
                    break;
                case ADD_TODO_TASK:
                    ToDoTask toDo = commandParser.getToDoTask(command);
                    addTask(toDo);
                    break;
                case LIST_TASKS:
                    printTasks();
                    break;
                case MARK_TASK_DONE:
                    taskIndex = commandParser.getTaskIndexOfTaskMarkedDone(command);
                    markTaskDone(taskIndex);
                    break;
                case ADD_DEADLINE_TASK:
                    DeadlineTask deadlineTask = commandParser.getDeadlineTask(command);
                    addTask(deadlineTask);
                    break;
                case ADD_EVENT_TASK:
                    EventTask eventTask = commandParser.getEventTask(command);
                    addTask(eventTask);
                    break;
                case DELETE_TASK:
                    taskIndex = commandParser.getTaskIndexOfTaskDeleted(command);
                    deleteTask(taskIndex);
                    break;
                default:
                    // The default case should be unreachable. If this is reached, something is wrong.
                    printFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
                    commandType = CommandType.EXIT;
                    break;
                }
                if (hasErrorOnSave) {
                    printFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
                    break;
                }
            } catch (IOException e) {
                printFormattedMessage(UNEXPECTED_ERROR_MESSAGE);
                break;
            } catch (DukeInvalidCommandException e) {
                printFormattedMessage(String.format(INVALID_COMMAND_ERROR_TEMPLATE, e.getMessage()));
                commandType = null;
            }
        } while (commandType == null || !commandType.equals(CommandType.EXIT));
    }

    private void addTask(Task task) {
        taskHandler.addTask(task);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append(messageFormatter.formatTask(task)).append("\n");
        sb.append(getListLengthMessage());
        printFormattedMessage(sb.toString());
    }

    private void printTasks() {
        if (taskHandler.getNumberOfTasks() == 0) {
            printFormattedMessage("You have no tasks in the list.");
        } else {
            StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
            sb.append(taskHandler.getTasksString(messageFormatter));
            printFormattedMessage(sb.toString());
        }
    }

    private void markTaskDone(int taskIndex) throws DukeInvalidCommandException {
        Task task;
        try {
            task = taskHandler.markTaskDone(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException("The task number does not exist.");
        }
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append(messageFormatter.formatTask(task));
        printFormattedMessage(sb.toString());
    }

    private void deleteTask(int taskIndex) throws DukeInvalidCommandException {
        Task task;
        try {
            task = taskHandler.deleteTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException("The task number does not exist.");
        }
        StringBuilder sb = new StringBuilder("Noted. I've removed this task:\n");
        sb.append(messageFormatter.formatTask(task)).append("\n");
        sb.append(getListLengthMessage());
        printFormattedMessage(sb.toString());
    }

    private String getListLengthMessage() {
        int n = taskHandler.getNumberOfTasks();
        // Check whether singular or plural should be printed.
        if (n != 1) {
            return String.format("Now you have %d tasks in the list.", n);
        } else {
            return "Now you have 1 task in the list.";
        }
    }

    private void printExitMessage() {
        printFormattedMessage(EXIT_MESSAGE);
    }

    private void printFormattedMessage(String message) {
        System.out.println(messageFormatter.getFormattedMessage(message));
        System.out.println();
    }
}
