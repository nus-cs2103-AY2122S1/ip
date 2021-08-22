package duke;

import duke.storage.StorageHandler;
import duke.task.*;
import duke.command.CommandParser;
import duke.command.CommandType;
import duke.command.DukeInvalidCommandException;
import duke.ui.MessageFormatter;
import duke.ui.Ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Represents the Duke chatbot.
 *
 * @author Jay Aljelo Saez Ting
 */
public class DukeChatbot {

    private Ui ui;
    private CommandParser commandParser;
    private StorageHandler storageHandler;
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
            ui.printGreeting();
            listenForInput();
        } catch (IOException e) {
            ui.printUnexpectedErrorMessage();
        }
    }

    private void initialise() throws IOException {
        ui = new Ui();
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
                    ui.printExitMessage();
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
                    ui.printUnexpectedErrorMessage();
                    commandType = CommandType.EXIT;
                    break;
                }
                if (hasErrorOnSave) {
                    ui.printUnexpectedErrorMessage();
                    break;
                }
            } catch (IOException e) {
                ui.printUnexpectedErrorMessage();
                break;
            } catch (DukeInvalidCommandException e) {
                ui.printInvalidCommandErrorMessage(e.getMessage());
                commandType = null;
            }
        } while (commandType == null || !commandType.equals(CommandType.EXIT));
    }

    private void addTask(Task task) {
        taskHandler.addTask(task);
        ui.startMessage()
                .addLine("Got it. I've added this task:")
                .addTask(task)
                .addTasksListLength(taskHandler.getNumberOfTasks())
                .printFormatted();
    }

    private void printTasks() {
        if (taskHandler.getNumberOfTasks() == 0) {
            ui.startMessage()
                    .addLine("You have no tasks in the list.")
                    .printFormatted();
        } else {
            ui.startMessage()
                    .addLine("Here are the tasks in your list:")
                    .addLine(taskHandler.getTasksString(MessageFormatter.getInstance()))
                    .printFormatted();
        }
    }

    private void markTaskDone(int taskIndex) throws DukeInvalidCommandException {
        Task task;
        try {
            task = taskHandler.markTaskDone(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException("The task number does not exist.");
        }
        ui.startMessage()
                .addLine("Nice! I've marked this task as done:")
                .addTask(task)
                .printFormatted();
    }

    private void deleteTask(int taskIndex) throws DukeInvalidCommandException {
        Task task;
        try {
            task = taskHandler.deleteTask(taskIndex);
        } catch (IndexOutOfBoundsException e) {
            throw new DukeInvalidCommandException("The task number does not exist.");
        }
        ui.startMessage()
                .addLine("Noted. I've removed this task:")
                .addTask(task)
                .addTasksListLength(taskHandler.getNumberOfTasks())
                .printFormatted();
    }
}
