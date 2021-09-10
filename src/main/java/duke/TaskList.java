package duke;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.NoSuchElementException;
import java.util.function.Function;

import duke.task.Task;
import duke.task.TaskName;

/**
 *  A class that encapsulates the list of task inputted by Duke
 */
public class TaskList {
    private ArrayList<Task> tasks;
    private final LinkedList<ArrayList<Task>> previousTasks;
    private final LinkedList<ArrayList<Task>> nextTasks;

    /**
     * Constructor for TaskList.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
        this.previousTasks = new LinkedList<>();
        this.nextTasks = new LinkedList<>();
    }

    /**
     * Adds the new Task into list and return the corresponding String reply.
     *
     * @param type The type of the task that will be added
     * @param input The corresponding description provided for the task
     * @param isDone The boolean if the task is done
     * @return The String of the reply after adding a task
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String addTask(TaskName type, String input, Boolean isDone) throws DukeException {
        assert !input.isBlank() : "Input cannot be null!";

        Task task;
        String[] inputArray;
        this.saveCurrentTasks();


        switch (type) {
        case TODO:
            task = Task.ofTask(type, input, null, isDone);
            break;

        case EVENT:
            // Fallthrough
        case DEADLINE:
            inputArray = input.split(type.getSplit());
            if (inputArray.length < 2) {
                throw new DukeException("The format for " + type + " is wrong.");
            }
            String description = inputArray[0];
            String timeDescription = inputArray[1];

            if (description.isBlank()) {
                throw new DukeException("The description of " + type + " cannot be empty.");
            } else if (timeDescription.isBlank()) {
                throw new DukeException("The date/time is missing from " + type + ".");
            }

            assert inputArray[0] != null && inputArray[1] != null : "The description is null!";
            task = Task.ofTask(type, description, timeDescription, false);
            break;

        default:
            throw new DukeException("Unexpected value: " + type);
        }

        this.tasks = this.copyTaskList();
        this.tasks.add(task);
        return Ui.showAddTaskReply(task.toString(), this.tasks.size());
    }

    /**
     * Adds the new Task into list and return the corresponding String reply.
     *
     * @param type The type of the task that will be added
     * @param input The corresponding description provided for the task
     * @return The String of the reply after adding a task
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String addTask(TaskName type, String input) throws DukeException {
        return addTask(type, input, false);
    }


    /**
     * Returns the list of all the Tasks.
     *
     * @return The String of the list of all the tasks formatted
     */
    public String displayTask() {
        StringBuilder output = new StringBuilder("\tHere are the tasks in your list:\n");
        int i = 1;

        for (Task task: this.tasks) {
            output.append("\t").append(i).append(".").append(task).append("\n");
            i++;
        }

        return output.toString();
    }

    /**
     * Marks the Task at the index of the list to be done.
     *
     * @param input The index of the Task in the list that is to be mark as done
     * @return The corresponding String reply after marking a task done
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String markTask(String input) throws DukeException {
        assert !input.isBlank() : "Input cannot be null!";

        int index;
        this.saveCurrentTasks();

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a number.");
        }
        if (index > this.tasks.size() || index < 1) {
            throw new DukeException("The index provided is not within the valid range");
        }

        this.tasks = this.copyTaskList();
        String taskName = this.tasks.get(index - 1).markDone();
        return Ui.showMarkTaskReply(taskName);
    }

    /**
     * Deletes the task at the specific index.
     *
     * @param input The index of the task in the list that is to be deleted
     * @return The corresponding String reply after deleting a task
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String deleteTask(String input) throws DukeException {
        assert !input.isBlank() : "Input cannot be null!";

        int index;
        this.saveCurrentTasks();

        try {
            index = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new DukeException("The index provided is not a number.");
        }
        if (index > this.tasks.size() || index < 1) {
            throw new DukeException("The index provided is not within the valid range");
        }

        this.tasks = this.copyTaskList();
        Task deleted = this.tasks.remove(index - 1);
        return Ui.showDeleteTaskReply(deleted.toString(), this.tasks.size());
    }

    /**
     * Returns the list of all tasks whose description which matches the provided keyword.
     *
     * @param input The String of the keyword that will be used to search the TaskList
     * @return The reply for Duke containing all the task matching the keyword
     */
    public String findTask(String input) {
        assert !input.isBlank() : "Input cannot be null!";
        StringBuilder reply = new StringBuilder();

        int i = 1;

        for (Task task: this.tasks) {
            if (task.matchKeyword(input)) {
                reply.append("\t").append(i).append(".").append(task).append("\n");
                i++;
            }
        }

        return Ui.showFindTaskReply(input, reply.toString());
    }

    /**
     * Iterates the list and perform a function on each task.
     *
     * @param f the function to be applied on each task
     */
    public void iterateList(Function<Task, Void> f) {
        for (Task t: this.tasks) {
            f.apply(t);
        }
    }

    /**
     * Undoes the TaskList to the state before the previous command.
     *
     * @return The reply for Duke showing the previous state
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String undoList() throws DukeException {
        try {
            this.nextTasks.addFirst(this.copyTaskList());
            this.tasks = this.previousTasks.remove();

            String displayedTasks = this.displayTask();
            return Ui.showUndoRedoListReply(displayedTasks, true);
        } catch (NoSuchElementException e) {
            throw new DukeException("Already at the oldest possible state");
        }
    }


    /**
     * Redoes the TaskList to the state before the previous undoList.
     *
     * @return The reply for Duke showing the previous state
     * @throws DukeException Exceptions specific to Duke's input
     */
    public String redoList() throws DukeException {
        try {
            this.previousTasks.addFirst(this.tasks);
            this.tasks = this.nextTasks.remove();

            String displayedTasks = this.displayTask();
            return Ui.showUndoRedoListReply(displayedTasks, false);
        } catch (NoSuchElementException e) {
            throw new DukeException("Already at the latest possible state");
        }
    }

    private ArrayList<Task> copyTaskList() {
        ArrayList<Task> copy = new ArrayList<>();
        this.tasks.forEach(task -> copy.add(task.copy()));
        return copy;
    }

    private void saveCurrentTasks() {
        this.previousTasks.addFirst(this.tasks);
    }
}
