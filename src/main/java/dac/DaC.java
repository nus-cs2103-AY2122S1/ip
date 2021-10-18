package dac;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.NoSuchElementException;

import dac.exception.InvalidInputException;
import dac.exception.InvalidInstructionException;
import dac.exception.StorageMissingException;
import dac.gui.Main;
import dac.parser.Parser;
import dac.storage.Storage;
import dac.task.Deadline;
import dac.task.Event;
import dac.task.Task;
import dac.task.ToDo;
import dac.tasklist.TaskList;
import dac.ui.Ui;
import javafx.application.Application;

/**
 * Represents a chat-bot named Dog-and-Cat (DaC).
 */
public class DaC {

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("HHmm");
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;
    private final Parser parser;

    /**
     * Constructor.
     *
     * @param filePath File path of storage file.
     */
    public DaC(String filePath) {
        storage = new Storage(filePath);
        ui = new Ui();
        try {
            ArrayList<String> savedTasks = storage.getStorageContents();
            taskList = new TaskList(savedTasks);
        } catch (StorageMissingException e) {
            ui.printException(e, "");
            taskList = new TaskList();
        } catch (InvalidInputException e) {
            ui.printException(e, "Please check data in " + filePath);
            taskList = new TaskList();
        }
        parser = new Parser();
    }

    /**
     * Saves the tasks in the task list into the storage file.
     *
     * @throws IOException If unable to write to storage file.
     */
    private void saveTasks() throws InvalidInputException, IOException {

        String contents = "";

        for (int i = 0; i < taskList.getSize(); i++) {
            Task task = taskList.getTask(i);
            Class<? extends Task> taskClass = task.getClass();
            assert taskClass == ToDo.class
                    || taskClass == Deadline.class
                    || taskClass == Event.class : taskClass.toString() + "  is not a recognized task.";
            String type = "" + task.getLabel();
            String details = task.getDetails();
            String done = task.isTaskCompleted()
                    ? "done"
                    : "not-done";
            if (taskClass == ToDo.class) {
                contents += type + ' ' + done
                        + '\n' + details;
            } else if (taskClass == Deadline.class) {
                contents += type + ' ' + done
                        + '\n' + details
                        + '\n' + ((Deadline) task).getDeadline().format(DATE_TIME_FORMATTER);
            } else if (taskClass == Event.class) {
                contents += type + ' ' + done
                        + '\n' + details
                        + '\n' + ((Event) task).getTiming().format(DATE_TIME_FORMATTER) + '-'
                        + ((Event) task).getEndTime().format(TIME_FORMATTER);
            }
            contents += System.lineSeparator();
        }
        storage.writeToStorage(contents, false);
    }

    /**
     * Gets Duke's response to the given input string.
     *
     * @param inputStr The user's input.
     * @return 3 values: Duke's response,
     * the appropriate task list to display and
     * whether Duke's response is an error message.
     */
    public DaCResponse getResponse(String inputStr) {

        try {
            HashMap<String, Object> input = parser.parse(inputStr);

            switch ((String) input.get("cmd")) {
            case "bye":
                saveTasks();
                return new DaCResponse(ui.farewell(),
                        null, false);
            case "list":
                return new DaCResponse(ui.listMsg(),
                        getTasks(), false);
            case "done":
                taskList.completeTask((Integer) input.get("index") - 1);
                return new DaCResponse(ui.doneMsg(taskList.getTask((Integer) input.get("index") - 1)),
                        getTasks(), false);
            case "delete":
                String reply = ui.deleteMsg(taskList.getTask((Integer) input.get("index") - 1));
                taskList.deleteTask((Integer) input.get("index") - 1);
                return new DaCResponse(reply,
                        getTasks(), false);
            case "todo":
                ToDo todo = new ToDo((String) input.get("details"));
                taskList.addTask(todo);
                return new DaCResponse(ui.addTaskMsg(todo),
                        getTasks(), false);
            case "deadline":
                Deadline deadline;
                deadline = new Deadline((String) input.get("details"), (String) input.get("deadline"));
                taskList.addTask(deadline);
                return new DaCResponse(ui.addTaskMsg(deadline),
                        getTasks(), false);
            case "event":
                Event event;
                event = new Event((String) input.get("details"), (String) input.get("timing"));
                taskList.addTask(event);
                return new DaCResponse(ui.addTaskMsg(event),
                        getTasks(), false);
            case "date":
                LocalDate date = (LocalDate) input.get("date");
                return new DaCResponse(ui.matchingDate(date),
                        getTasks(date), false);
            case "find":
                String keyword = (String) input.get("keyword");
                return new DaCResponse(this.ui.matchingKeyword(keyword),
                        getTasks(keyword), false);
            case "sort":
                boolean reverse = (Boolean) input.get("reverse");
                taskList.sort(reverse);
                return new DaCResponse(this.ui.sortMessage(reverse),
                        getTasks(), false);
            default:
                throw new InvalidInstructionException((String) input.get("cmd"));
            }
        } catch (InvalidInputException | IOException | InvalidInstructionException
                | IllegalStateException | NoSuchElementException | DateTimeParseException e) {
            return new DaCResponse(ui.printException(e, ""),
                    getTasks(), true);
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
