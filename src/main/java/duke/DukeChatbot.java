package duke;

import duke.task.DeadlineTask;
import duke.task.Task;
import duke.task.ToDoTask;
import duke.ui.CommandParser;
import duke.ui.CommandType;
import duke.ui.MessageFormatter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Represents the Duke chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DukeChatbot {

    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private static final String GREETING_MESSAGE = "Hello! I'm Duke!\n"
            + "What can I do for you?";
    private static final String EXIT_MESSAGE = "Bye. Hope to see you again soon!";
    private static final String READ_COMMAND_ERROR_MESSAGE = "An unexpected error has occurred.";
    private static final String INVALID_COMMAND_ERROR_TEMPLATE = "This command is invalid.\n%s\nPlease try again.";

    private MessageFormatter messageFormatter;
    private CommandParser commandParser;
    private List<Task> tasks;

    /**
     * Creates a Duke chatbot with an initially empty list of tasks.
     */
    public DukeChatbot() {
        this.messageFormatter = new MessageFormatter();
        this.commandParser = new CommandParser();
        this.tasks = new ArrayList<>();
    }

    /**
     * Prints the greeting message.
     */
    public void printGreeting() {
        printFormattedMessage(LOGO + GREETING_MESSAGE);
    }

    /**
     * Listens for inputs for commands and responds accordingly.
     */
    public void listenForInput() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String command;
        CommandType commandType;
        do {
            try {
                command = br.readLine();
                commandType = commandParser.getCommandTypeFromCommand(command);
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
                    int taskIndex = commandParser.getTaskIndexOfTaskMarkedDone(command);
                    markTaskDone(taskIndex);
                    break;
                case ADD_DEADLINE_TASK:
                    DeadlineTask deadlineTask = commandParser.getDeadlineTask(command);
                    addTask(deadlineTask);
                    break;
                default:
                    // The default case should be unreachable. If this is reached, something is wrong.
                    printFormattedMessage(READ_COMMAND_ERROR_MESSAGE);
                    commandType = CommandType.EXIT;
                    break;
                }
            } catch (IOException e) {
                printFormattedMessage(READ_COMMAND_ERROR_MESSAGE);
                break;
            } catch (IllegalArgumentException e) {
                printFormattedMessage(String.format(INVALID_COMMAND_ERROR_TEMPLATE, e.getMessage()));
                commandType = null;
            }
        } while (commandType == null || !commandType.equals(CommandType.EXIT));
    }

    private void addTask(Task task) {
        tasks.add(task);
        StringBuilder sb = new StringBuilder("Got it. I've added this task:\n");
        sb.append(messageFormatter.indent(task.toString(), 2));
        int n = tasks.size();
        if (n != 1) {
            sb.append(String.format("Now you have %d tasks in the list.\n", n));
        } else {
            sb.append("Now you have 1 task in the list.\n");
        }
        printFormattedMessage(sb.toString());
    }

    private void printTasks() {
        StringBuilder sb = new StringBuilder("Here are the tasks in your list:\n");
        int n = tasks.size();
        for (int i = 0; i < n; i++) {
            sb.append(String.format("%d.%s\n", i + 1, tasks.get(i)));
        }
        printFormattedMessage(sb.toString());
    }

    private void markTaskDone(int taskIndex) throws IllegalArgumentException {
        Task task;
        try {
            task = tasks.get(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("The task number does not exist.");
        }
        task.markDone();
        StringBuilder sb = new StringBuilder("Nice! I've marked this task as done:\n");
        sb.append(messageFormatter.indent(task.toString(), 2));
        printFormattedMessage(sb.toString());
    }

    private void printExitMessage() {
        printFormattedMessage(EXIT_MESSAGE);
    }

    private void printFormattedMessage(String message) {
        System.out.println(messageFormatter.getFormattedMessage(message));
    }
}
