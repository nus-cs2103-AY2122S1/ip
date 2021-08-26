package duke.command.task;

import duke.util.DukeException;
import duke.util.Parser;

import java.util.ArrayList;
import java.util.List;

/**
 * A TaskList which stores Tasks.
 */
public class TaskList {

    private final List<Task> taskArr;

    // Messages
    private static final String OUT_OF_BOUNDS_TASK = "Could not find task. Check the task number again?";
    private static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d %s in the list.";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n  %s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:\n %s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String ERROR_SAVING_MESSAGE = "Error reading taskLst. Symbol not found.";

    // Nouns for plural and singular
    private String taskWord() {
        return this.size() <= 1 ? "task" : "tasks";
    }

    /**
     * Constructor for TaskList
     */
    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    /**
     * The size of the array storing the Tasks.
     * @return The size of the array storing the Tasks.
     */
    public int size() {
        return taskArr.size();
    }

    /**
     * Adds a task to the TaskList
     * @param task The task to be added
     * @return Message when task is added
     */
    public String addTask(Task task) {
        this.taskArr.add(task);
        return String.format(ADD_TASK_MESSAGE, task, this.size(), taskWord());
    }

    /**
     * Adds a task from the format in taskList.txt
     *
     * @param input The format in taskList.txt
     * @throws ArrayIndexOutOfBoundsException When
     * @throws DukeException An exception thrown according to the message given
     */
    public void addSavedTask(String input) throws ArrayIndexOutOfBoundsException, DukeException {
        String[] inputArr = input.split("\\|");
        String symbol = inputArr[0];
        String remainingText = Parser.getRemainingText(symbol, input);
        switch (symbol.charAt(0)) {
        case ToDo.SYMBOL:
            ToDo myTodo = ToDo.newToDoFromSave(remainingText);
            this.addTask(myTodo);
            break;
        case Deadline.SYMBOL:
            Deadline myDeadline = Deadline.newDeadlineFromSave(remainingText);
            this.addTask(myDeadline);
            break;
        case Event.SYMBOL:
            Event myEvent = Event.newEventFromSave(remainingText);
            this.addTask(myEvent);
            break;
        default:
            throw new DukeException(ERROR_SAVING_MESSAGE);
        }
    }

    /**
     * Marks a task as done according to the index
     *
     * @param taskIndex Index of task to be done
     * @return Returns the String message when the task is done
     * @throws DukeException An exception thrown according to the message given
     */
    public String markTaskAsDone(int taskIndex) throws DukeException {
        // Task index starts from 1
        int index = taskIndex - 1;
        try {
            Task task = taskArr.get(index);
            task.markDone();
            return task.toString();
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(OUT_OF_BOUNDS_TASK);
        }
    }

    /**
     * Deletes the task from the tasklist
     *
     * @param taskIndex The index to delete
     * @return Returns the String message when the task is done
     * @throws DukeException An exception thrown according to the message given
     */
    public String deleteTask(int taskIndex) throws DukeException {
        // Task index starts from 1
        int index = taskIndex - 1;
        try {
            Task task = taskArr.remove(index);
            return String.format(REMOVE_TASK_MESSAGE, task, this.size(), taskWord());
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(OUT_OF_BOUNDS_TASK);
        }
    }

    /**
     * Gets all the tasks in the format for taskList.txt
     *
     * @return the tasks in the format for taskList.txt
     */
    public String getSaveFormat() {
        StringBuilder printedList = new StringBuilder();
        for (Task task : taskArr) {
            printedList.append(String.format("%s\n", task.getSaveFormat()));
        }
        // Remove the last newline
        return printedList.toString().trim();
    }

    /**
     * Gets all the tasks in the format for the console
     *
     * @return the tasks in the format for the console
     */
    @Override
    public String toString() {
        StringBuilder printedList = new StringBuilder();
        for (int i = 0; i < taskArr.size(); i++) {
            // Index from 1 onwards
            String index = Integer.toString(i + 1);
            printedList.append(String.format("%s. %s\n", index, this.taskArr.get(i)));
        }
        // Remove the last newline
        return printedList.toString().trim();
    }
}
