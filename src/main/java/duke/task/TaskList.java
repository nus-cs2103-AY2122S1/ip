package duke.task;

import java.util.ArrayList;
import java.util.List;

import duke.util.DukeException;
import duke.util.Parser;

/**
 * A TaskList which stores Tasks.
 */
public class TaskList {
    // Messages
    private static final String OUT_OF_BOUNDS_TASK = "Could not find task. Check the task number again?";
    private static final String NUMBER_OF_TASKS_MESSAGE = "Now you have %d %s in the list.";
    private static final String ADD_TASK_MESSAGE = "Got it. I've added this task:\n  %s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String REMOVE_TASK_MESSAGE = "Noted. I've removed this task:\n %s\n" + NUMBER_OF_TASKS_MESSAGE;
    private static final String ERROR_SAVING_MESSAGE = "Error reading taskLst. Symbol not found.";
    private static final String NO_TASK_FOUND_MESSAGE = "Unfortunately no tasks with that name are found";
    private static final String MATCHING_TASKS_MESSAGE = "Here are the matching tasks in your list:\n";

    private List<Task> taskArr;

    /**
     * Represents the constructor for TaskList which creates a taskList from taskList.txt
     */
    public TaskList(String taskList) throws DukeException {
        this.taskArr = new ArrayList<>();
        String[] taskArrStr = taskList.split("\n");
        for (String task : taskArrStr) {
            this.addSavedTask(task);
        }
    }

    /**
     * Represents the constructor for TaskList to create an empty taskList
     */
    public TaskList() {
        this.taskArr = new ArrayList<>();
    }

    // Nouns for plural and singular
    private String taskWord() {
        return this.getSize() <= 1 ? "task" : "tasks";
    }


    /**
     * Gets the size of the array storing the Tasks.
     *
     * @return The size of the array storing the Tasks.
     */
    public int getSize() {
        return taskArr.size();
    }

    /**
     * Adds a task to the TaskList
     *
     * @param task The task to be added
     * @return Message when task is added
     */
    public String addTask(Task task) {
        this.taskArr.add(task);
        return String.format(ADD_TASK_MESSAGE, task, this.getSize(), taskWord());
    }

    /**
     * Finds tasks which matches the input string and prints the output.
     *
     * @param input The input string.
     * @return Returns a string message with the matching Tasks.
     */
    public String findTask(String input) {
        StringBuilder printedList = new StringBuilder();
        int index = 1;
        for (Task task : this.taskArr) {
            if (task.getText().contains(input)) {
                // Index from 1 onwards
                String indexStr = Integer.toString(index);
                printedList.append(String.format("%s. %s\n", indexStr, task));
                index++;
            }
        }
        if (index == 1) {
            printedList.insert(0, NO_TASK_FOUND_MESSAGE);
        } else {
            printedList.insert(0, MATCHING_TASKS_MESSAGE);
        }
        // Remove the last newline
        return printedList.toString().trim();
    }

    /**
     * Adds a task from the format in taskList.txt.
     *
     * @param input The format in taskList.txt.
     * @throws ArrayIndexOutOfBoundsException Error when for invalid taskList.
     * @throws DukeException An exception thrown according to the message given.
     */
    public void addSavedTask(String input) throws ArrayIndexOutOfBoundsException, DukeException {
        if (input == null || input.equals("")) {
            return;
        }

        String[] inputArr = input.split("\\|");
        String symbol = inputArr[0];
        String remainingText = Parser.getRemainingText(symbol, input);
        switch (symbol.charAt(0)) {
        case ToDo.SYMBOL:
            ToDo myTodo = ToDo.createNewToDoFromSave(remainingText);
            this.addTask(myTodo);
            break;
        case TimedToDo.SYMBOL:
            TimedToDo myTimedToDo = TimedToDo.createNewTimedTodoFromSave(remainingText);
            this.addTask(myTimedToDo);
            break;
        case Deadline.SYMBOL:
            Deadline myDeadline = Deadline.createNewDeadlineFromSave(remainingText);
            this.addTask(myDeadline);
            break;
        case Event.SYMBOL:
            Event myEvent = Event.createNewEventFromSave(remainingText);
            this.addTask(myEvent);
            break;
        default:
            throw new DukeException(ERROR_SAVING_MESSAGE);
        }
    }

    /**
     * Marks a task as done according to the index.
     *
     * @param taskIndex Index of task to be done.
     * @return Returns the String message when the task is done.
     * @throws DukeException An exception thrown according to the message given.
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
     * Deletes the task from the taskList.
     *
     * @param taskIndex The index to delete.
     * @return Returns the String message when the task is done.
     * @throws DukeException An exception thrown according to the message given.
     */
    public String deleteTask(int taskIndex) throws DukeException {
        // Task index starts from 1
        int index = taskIndex - 1;
        try {
            Task task = taskArr.remove(index);
            return String.format(REMOVE_TASK_MESSAGE, task, this.getSize(), taskWord());
        } catch (IndexOutOfBoundsException err) {
            throw new DukeException(OUT_OF_BOUNDS_TASK);
        }
    }

    /**
     * Gets all the tasks in the format for taskList.txt.
     *
     * @return the tasks in the format for taskList.txt.
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
     * Gets all the tasks in the format for the console.
     *
     * @return the tasks in the format for the console.
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
