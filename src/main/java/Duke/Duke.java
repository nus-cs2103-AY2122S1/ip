package duke;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import duke.exception.InvalidInputException;
import duke.exception.InvalidInstructionException;
import duke.exception.StorageMissingException;
import duke.gui.Main;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.tasklist.TaskList;
import duke.ui.Ui;
import javafx.application.Application;

/**
 * Represents a chat-bot named Duke.
 */
public class Duke {

    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor.
     *
     * @param filePath File path of storage file.
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            ArrayList<String> savedTasks = storage.getStorageContents();
            taskList = new TaskList(savedTasks);
        } catch (StorageMissingException e) {
            ui.storageMissing(e, "");
            taskList = new TaskList();
        } catch (InvalidInputException e) {
            ui.invalidInput(e, "Please check data in " + filePath);
        }
        parser = new Parser();
    }

    /**
     * Saves the tasks in the task list into the storage file.
     *
     * @throws IOException If unable to write to storage file.
     */
    private void saveTasks() throws IOException {

        String contents = "";

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            Class<? extends Task> taskClass = task.getClass();
            String type = "" + task.getLabel();
            String details = task.getDetails();
            String done = task.isCompleted()
                    ? "done"
                    : "not-done";
            if (taskClass == ToDo.class) {
                contents += type + ' ' + done + ' ' + details;
            } else if (taskClass == Deadline.class) {
                contents += type + ' ' + done + ' ' + details + ' '
                        + ((Deadline) task).getDeadlineAsStr();
            } else if (taskClass == Event.class) {
                contents += type + ' ' + done + ' ' + details + ' '
                        + ((Event) task).getTimingAsStr();
            }
            contents += System.lineSeparator();

        }
        storage.writeToStorage(contents, false);
    }

    /**
     * Gets Duke's response to the given input string.
     *
     * @param inputStr The user's input.
     * @return A pair of strings: Duke's response and the appropriate task list to display.
     */
    public Pair<String, String> getResponse(String inputStr) {

        try {
            HashMap<String, Object> input = parser.parse(inputStr);

            switch ((String) input.get("cmd")) {
            case "bye":
                saveTasks();
                return new Pair<>(ui.farewell(),
                        null);
            case "list":
                return new Pair<>(ui.listMsg(),
                        getTasks());
            case "done":
                try {
                    taskList.completeTask((Integer) input.get("index") - 1);
                    return new Pair<>(ui.doneMsg(taskList.getTask((Integer) input.get("index") - 1)),
                            getTasks());
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidInputException("Task number does not exist. Complete failed.");
                }
            case "delete":
                try {
                    String reply = ui.deleteMsg(taskList.getTask((Integer) input.get("index") - 1));
                    taskList.deleteTask((Integer) input.get("index") - 1);
                    return new Pair<>(reply,
                            getTasks());
                } catch (IndexOutOfBoundsException e) {
                    throw new InvalidInputException("Task number does not exist. Delete failed.");
                }
            case "todo":
                ToDo todo = new ToDo((String) input.get("details"));
                taskList.addTask(todo);
                return new Pair<>(ui.addTaskMsg(todo),
                        getTasks());
            case "deadline":
                Deadline deadline = new Deadline((String) input.get("details"), (String) input.get("deadline"));
                taskList.addTask(deadline);
                return new Pair<>(ui.addTaskMsg(deadline),
                        getTasks());
            case "event":
                Event event = new Event((String) input.get("details"), (String) input.get("timing"));
                taskList.addTask(event);
                return new Pair<>(ui.addTaskMsg(event),
                        getTasks());
            case "date":
                LocalDate date = (LocalDate) input.get("date");
                return new Pair<>(ui.matchingDate(date),
                        getTasks(date));
            case "find":
                String keyword = (String) input.get("keyword");
                return new Pair<>(this.ui.matchingKeyword(keyword),
                        getTasks(keyword));
            default:
                throw new InvalidInstructionException((String) input.get("cmd"));
            }
        } catch (IllegalStateException | NoSuchElementException e) {
            try {
                saveTasks();
            } catch (IOException ie) {
                return new Pair<>(ui.printException(ie, "Tasks could not be saved."), getTasks());
            }
            return new Pair<>(ui.printException(e, ""), getTasks());
        } catch (InvalidInputException e) {
            return new Pair<>(ui.invalidInput(e, ""), getTasks());
        } catch (IOException e) {
            return new Pair<>(ui.printException(e, ""), getTasks());
        } catch (InvalidInstructionException e) {
            return new Pair<>(ui.invalidInstruction(e, ""), getTasks());
        }
    }

    /**
     * Returns a String representation of tasks in the task list.
     *
     * @return A String representing tasks in the task list.
     */
    public String getTasks() {
        return taskList.getList();
    }

    /**
     * Returns a String representation of tasks that occur on the given date.
     *
     * @param date The date to filter the tasks by.
     * @return A String representing tasks that occur on the given date.
     */
    public String getTasks(LocalDate date) {
        return taskList.filterByDate(date);
    }

    /**
     * Returns a String representation of tasks that match the given keyword.
     *
     * @param keyword The keyword to filter the tasks by.
     * @return A String representing tasks that match the given keyword.
     */
    public String getTasks(String keyword) {
        return taskList.filterByKeyword(keyword);
    }

    public static void main(String[] args) {
        Application.launch(Main.class, args);
    }
}
